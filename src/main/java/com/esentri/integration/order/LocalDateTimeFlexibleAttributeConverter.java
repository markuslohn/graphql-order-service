package com.esentri.integration.order;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import org.eclipse.persistence.internal.platform.database.oracle.TIMESTAMPLTZWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Converts date/time values between the database and Java as LocalDateTime objects.
 *
 * @author mlohn
 */
@Converter
public class LocalDateTimeFlexibleAttributeConverter
    implements AttributeConverter<LocalDateTime, Object> {

  private static final Logger logger =
      LoggerFactory.getLogger(LocalDateTimeFlexibleAttributeConverter.class);

  @Override
  public Object convertToDatabaseColumn(LocalDateTime attribute) {
    return attribute == null ? null : Timestamp.valueOf(attribute);
  }

  @Override
  public LocalDateTime convertToEntityAttribute(Object dbData) {
    logger.trace(
        "Tries to convert data/time value of type {} with value {}.",
        dbData.getClass().getName(),
        dbData);
    LocalDateTime dateTimeValueConverted = null;

    if (dbData instanceof TIMESTAMPLTZWrapper) {
      dateTimeValueConverted = ((TIMESTAMPLTZWrapper) dbData).getTimestamp().toLocalDateTime();
    } else if (dbData instanceof Timestamp) {
      dateTimeValueConverted = ((Timestamp) dbData).toLocalDateTime();
    } else if (dbData instanceof Date) {
      dateTimeValueConverted = new Timestamp(((Date) dbData).getTime()).toLocalDateTime();
    }

    return dateTimeValueConverted;
  }
}
