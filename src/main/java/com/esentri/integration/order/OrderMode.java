package com.esentri.integration.order;

/**
 * Describes possible modes for an order.
 *
 * @author mlohn
 */
public enum OrderMode {
  DIRECT("direct"),
  ONLINE("online");

  private String value;

  private OrderMode(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }

  public String toValue() {
    return value;
  }

  public static OrderMode fromValue(String value) {
    switch (value) {
      case "direct":
        return OrderMode.DIRECT;
      case "online":
        return OrderMode.ONLINE;
      default:
        throw new IllegalArgumentException("value [" + value + "] not supported.");
    }
  }
}
