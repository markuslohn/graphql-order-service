package com.esentri.integration.order;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * A Category is used to further explain a Product.
 *
 * @author mlohn
 */
@ToString
@EqualsAndHashCode
@Getter
@NoArgsConstructor
@Table(name = "CATEGORIES_TAB")
public class Category implements Serializable {

  private static final long serialVersionUID = -4451505474374622547L;

  @Id
  @Basic(optional = false)
  @NotNull
  @Column(name = "CATEGORY_ID", nullable = false, updatable = false)
  private Long id;

  @Size(max = 50)
  @Column(name = "CATEGORY_NAME")
  private String name;

  @Size(max = 1000)
  @Column(name = "CATEGORY_DESCRIPTION")
  private String description;

  @OneToOne
  @JoinColumn(name = "PARENT_CATEGORY_ID", insertable = false, updatable = false)
  private Category parent;
}
