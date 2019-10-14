package com.esentri.integration.order;

/**
 * Describes the possible states of a product.
 *
 * @author mlohn
 */
public enum ProductStatus {
  ORDERABLE("orderable"),
  PLANNED("planned"),
  UNDER_DEVELOPMENT("under development"),
  OBSOLETE("obsolete");

  private String value;

  private ProductStatus(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }

  public String toValue() {
    return value;
  }

  public static ProductStatus fromValue(String value) {
    switch (value) {
      case "orderable":
        return ProductStatus.ORDERABLE;
      case "planned":
        return ProductStatus.PLANNED;
      case "under development":
        return ProductStatus.UNDER_DEVELOPMENT;
      case "obsolete":
        return OBSOLETE;
      default:
        throw new IllegalArgumentException("value [" + value + "] not supported.");
    }
  }
}
