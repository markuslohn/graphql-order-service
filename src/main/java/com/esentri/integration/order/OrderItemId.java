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
 * Describes the primary key to identify an OrderItem.
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
public class OrderItemId implements Serializable {

  @Basic(optional = false)
  @NotNull
  @Column(name = "ORDER_ID")
  private Long orderId;

  @Basic(optional = false)
  @NotNull
  @Column(name = "LINE_ITEM_ID")
  private Long lineId;
}
