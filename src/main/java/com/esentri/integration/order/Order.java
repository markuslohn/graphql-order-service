package com.esentri.integration.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * An Order contains a reference to a Customer and contains at least one order position.
 *
 * @author mlohn
 */
@ToString
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@Entity
@SequenceGenerator(name = "Orders_Id_Seq_Gen", sequenceName = "ORDERS_SEQ")
public class Order implements Serializable {

  private static final long serialVersionUID = 1996352047754351594L;

  @Id
  @Basic(optional = false)
  @NotNull
  @Column(name = "ORDER_ID", nullable = false, unique = true, updatable = false)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Orders_Id_Seq_Gen")
  private Long id;

  @Column(name = "ORDER_DATE", nullable = false)
  @Convert(converter = LocalDateTimeFlexibleAttributeConverter.class)
  private LocalDateTime orderDate;

  @Size(max = 8)
  @Column(name = "ORDER_MODE")
  private OrderMode mode;

  @Enumerated
  @Column(name = "ORDER_STATUS")
  private OrderStatus status;

  @Column(name = "ORDER_TOTAL")
  private BigDecimal total;

  @Column(name = "SALES_REP_ID")
  private Integer salesRepId;

  @Column(name = "PROMOTION_ID")
  private Integer promotionId;

  @OneToMany(
      mappedBy = "order",
      cascade = {CascadeType.PERSIST, CascadeType.MERGE},
      fetch = FetchType.EAGER)
  @Getter(value = AccessLevel.NONE)
  @EqualsAndHashCode.Exclude
  private List<OrderItem> orderItems;

  @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "CUSTOMER_ID")
  @OneToOne
  private Customer customer;

  public List<OrderItem> getOrderItems() {
    if (orderItems == null) {
      orderItems = new ArrayList<OrderItem>();
    }
    return orderItems;
  }

  @NotNull
  public OrderItem addOrderItem(OrderItem item) {
    getOrderItems().add(item);
    item.setOrder(this);
    return item;
  }

  public void removeOrderItem(@NotNull OrderItem item) {
    getOrderItems().remove(item);
  }
}
