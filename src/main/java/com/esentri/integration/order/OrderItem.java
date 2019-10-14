package com.esentri.integration.order;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Corresponds to a single line of an order. It contains references to a product and the order.
 *
 * @author mlohn
 */
@EqualsAndHashCode
@Setter
@Getter
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "ORDER_ITEMS")
public class OrderItem implements Serializable {

  private static final long serialVersionUID = -7422874063309568168L;

  @EmbeddedId protected OrderItemId id;

  @Column(name = "UNIT_PRICE")
  private Double unitPrice;

  private Double quantity;

  @JoinColumn(
      name = "ORDER_ID",
      referencedColumnName = "ORDER_ID",
      insertable = false,
      updatable = false)
  @OneToOne
  private Order order;

  @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")
  @OneToOne
  private Product product;
}
