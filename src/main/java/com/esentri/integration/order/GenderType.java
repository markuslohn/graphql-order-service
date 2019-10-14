package com.esentri.integration.order;

/**
 * Represents the gender. For a female "F" and for a men "M" will be stored in the database column.
 *
 * @author mlohn
 */
public enum GenderType {
  F("Female"),
  M("Men");

  private String value;

  private GenderType(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return (value != null ? value : super.toString());
  }
}
