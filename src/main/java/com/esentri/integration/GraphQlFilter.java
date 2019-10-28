package com.esentri.integration;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** @author mlohn */
@WebFilter(
    filterName = "GraphQlFilter",
    urlPatterns = {"/graphql"})
public class GraphQlFilter implements Filter {

  private static final Logger logger = LoggerFactory.getLogger(GraphQlFilter.class);

  private FilterConfig filterConfig = null;

  public GraphQlFilter() {}

  /** Return the filter configuration object for this filter. */
  public FilterConfig getFilterConfig() {
    return (this.filterConfig);
  }

  /**
   * Set the filter configuration object for this filter.
   *
   * @param filterConfig The filter configuration object
   */
  public void setFilterConfig(FilterConfig filterConfig) {
    this.filterConfig = filterConfig;
  }

  /** Destroy method for this filter */
  public void destroy() {}

  /** Init method for this filter */
  public void init(FilterConfig filterConfig) {
    this.filterConfig = filterConfig;
  }

  public void log(String msg) {
    filterConfig.getServletContext().log(msg);
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    final HttpServletRequest httpRequest = (HttpServletRequest) request;
    final GraphQlHttpServletRequestWrapper wrappedRequest =
        new GraphQlHttpServletRequestWrapper(httpRequest);
    chain.doFilter(wrappedRequest, response);
  }
}
