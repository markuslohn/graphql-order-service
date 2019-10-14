package com.esentri.integration.order;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Convertes the values for ProductStatus between the database and java.
 *
 * @author mlohn
 */
@Converter
public class ProductStatusConverter implements AttributeConverter<ProductStatus, String> {

  @Override
  public String convertToDatabaseColumn(ProductStatus attribute) {
    return attribute.toValue();
  }

  @Override
  public ProductStatus convertToEntityAttribute(String dbData) {
    return ProductStatus.fromValue(dbData);
  }
}
