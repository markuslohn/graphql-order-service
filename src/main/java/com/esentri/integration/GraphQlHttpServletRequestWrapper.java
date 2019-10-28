package com.esentri.integration;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This decorator is needed, because the GraphQLServlet reads the InputStream from the
 * HttpServletRequest object multiple times. In this case starting with the second read on the
 * InputStream that content is null. This behavior was detected on WebLogic Server. Maybe other
 * platforms are also affected.
 *
 * <p>It copies the body of the request in a new InputStream.
 *
 * @author mlohn
 */
public class GraphQlHttpServletRequestWrapper extends HttpServletRequestWrapper {

  /** This logger is only used to print the fault details into a separated log file. */
  private static final Logger logger =
      LoggerFactory.getLogger(GraphQlHttpServletRequestWrapper.class);

  private final String body;

  public GraphQlHttpServletRequestWrapper(HttpServletRequest request) {
    super(request);

    StringBuilder bodyBuilder = new StringBuilder();
    BufferedReader bufferedReader = null;

    try {
      InputStream inputStream = request.getInputStream();

      if (inputStream != null) {
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        char[] charBuffer = new char[128];
        int bytesRead = -1;

        while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
          bodyBuilder.append(charBuffer, 0, bytesRead);
        }
      } else {
        bodyBuilder.append("");
      }
    } catch (IOException ex) {
      logger.error("Error reading the request body...");
    } finally {
      if (bufferedReader != null) {
        try {
          bufferedReader.close();
        } catch (IOException ex) {
          logger.error("Error closing bufferedReader...");
        }
      }
    }

    body = bodyBuilder.toString();
  }

  @Override
  public BufferedReader getReader() throws IOException {
    return new BufferedReader(new InputStreamReader(getInputStream()));
  }

  @Override
  public ServletInputStream getInputStream() throws IOException {

    ServletInputStream inputStream =
        new ServletInputStream() {
          final ByteArrayInputStream byteArrayInputStream =
              new ByteArrayInputStream(body.getBytes());

          public int read() throws IOException {
            return byteArrayInputStream.read();
          }

          @Override
          public boolean isFinished() {
            return false;
          }

          @Override
          public boolean isReady() {
            return true;
          }

          @Override
          public void setReadListener(ReadListener listener) {}
        };

    return inputStream;
  }
}
