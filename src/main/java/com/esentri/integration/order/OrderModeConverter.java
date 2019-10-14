package com.esentri.integration.order;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Convertes the values for OrderMode between the database and java.
 *
 * @author mlohn
 */
@Converter(autoApply = true)
public class OrderModeConverter implements AttributeConverter<OrderMode, String> {

  @Override
  public String convertToDatabaseColumn(OrderMode attribute) {
    return attribute.toValue();
  }

  @Override
  public OrderMode convertToEntityAttribute(String dbData) {
    return OrderMode.fromValue(dbData);
  }
}
