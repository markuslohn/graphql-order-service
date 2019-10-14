package com.esentri.integration.order;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Manages the availability and stock on hand for a specific product.
 *
 * @author mlohn
 */
@ToString
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "INVENTORIES")
public class Inventory implements Serializable {

  private static final long serialVersionUID = 1L;

  @EmbeddedId protected InventoryId id;

  @Basic(optional = false)
  @NotNull
  @Column(name = "QUANTITY_ON_HAND")
  private int quantityOnHand;

  @JoinColumn(
      name = "PRODUCT_ID",
      referencedColumnName = "PRODUCT_ID",
      insertable = false,
      updatable = false)
  @ManyToOne(optional = false)
  private Product product;

  @JoinColumn(
      name = "WAREHOUSE_ID",
      referencedColumnName = "WAREHOUSE_ID",
      insertable = false,
      updatable = false)
  @ManyToOne(optional = false)
  private Warehouse warehouse;
}
