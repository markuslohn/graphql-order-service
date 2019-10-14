package com.esentri.integration.order;

/**
 * Provides a human readable representation of the order status. The order status is represented as
 * numeric value in the database.
 *
 * @author mlohn
 */
public enum OrderStatus {
  NOT_FULLY_ENTERED(0, "Not fully entered"),
  ENTERED(1, "Entered"),
  CANCELED(2, "Bad Credit"),
  CANCELED_BY_CUSTOMER(3, "Canceled By Customer"),
  SHIPPED_WHOLE_ORDER(4, "Shipped whole order"),
  SHIPPED_REPLACEMENT_ITEMS(5, "Shipped replacement items"),
  SHIPPED_BACKLOG(6, "Shipped backlog on items"),
  SHIPPED_SPECIAL_DELIVERY(7, "Shipped special delivery"),
  SHIPPED_BILLED(8, "Shipped billed"),
  SHIPPED_PAYMENT_PLAN(9, "Shipped payment plan"),
  SHIPPED_PAID(10, "Shipped paid");

  private int value;
  private String description;

  private OrderStatus(int value, String description) {
    this.value = value;
    this.description = description;
  }

  @Override
  public String toString() {
    return "[" + value + "]" + (description != null ? " " + description : super.toString());
  }
}
