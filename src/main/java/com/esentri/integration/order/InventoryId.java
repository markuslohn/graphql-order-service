package com.esentri.integration.order;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents the primary key for an Inventory.
 *
 * @author mlohn
 */
@ToString
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class InventoryId implements Serializable {

  @Basic(optional = false)
  @NotNull
  @Column(name = "PRODUCT_ID")
  private int productId;

  @Basic(optional = false)
  @NotNull
  @Column(name = "WAREHOUSE_ID")
  private short warehouseId;
}
