package com.esentri.integration.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
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
 * A product can be orderd by a customer and is group by into a category.
 *
 * @author mlohn
 */
@ToString
@EqualsAndHashCode
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "PRODUCT_INFORMATION")
public class Product implements Serializable {

  private static final long serialVersionUID = -1638861881955496023L;

  @Id
  @Basic(optional = false)
  @NotNull
  @Column(name = "PRODUCT_ID")
  private Long id;

  @Size(max = 50)
  @Column(name = "PRODUCT_NAME")
  private String name;

  @Size(max = 2000)
  @Column(name = "PRODUCT_DESCRIPTION")
  private String description;

  @Column(name = "CATEGORY_ID")
  private Long categoryId;

  @Column(name = "WEIGHT_CLASS")
  private Integer weightClass;

  @Column(name = "SUPPLIER_ID")
  private Integer supplierId;

  @Column(name = "PRODUCT_STATUS")
  @Convert(converter = ProductStatusConverter.class)
  private ProductStatus status;

  @Column(name = "LIST_PRICE")
  private BigDecimal listPrice;

  @Column(name = "MIN_PRICE")
  private BigDecimal minPrice;

  @Size(max = 50)
  @Column(name = "CATALOG_URL")
  private String catalogUrl;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
  private List<Inventory> inventories;
}
