package com.esentri.integration.order;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * A Warehouse can ship products.
 *
 * @author mlohn
 */
@ToString
@EqualsAndHashCode
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "WAREHOUSES")
public class Warehouse implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Basic(optional = false)
  @NotNull
  @Column(name = "WAREHOUSE_ID")
  private long id;

  @Size(max = 35)
  @Column(name = "WAREHOUSE_NAME")
  private String name;

  @Column(name = "LOCATION_ID")
  private Long locationId;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "warehouse")
  private List<Inventory> inventories;
}
