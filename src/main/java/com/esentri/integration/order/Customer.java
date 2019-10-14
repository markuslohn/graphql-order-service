package com.esentri.integration.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * A customer the can buy products through an order.
 *
 * @author mlohn
 */
@ToString
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "CUSTOMERS")
public class Customer implements Serializable {

  private static final long serialVersionUID = 5840385380873563927L;

  @Id
  @Basic(optional = false)
  @NotNull
  @Column(name = "CUSTOMER_ID")
  private Long id;

  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 20)
  @Column(name = "CUST_FIRST_NAME")
  private String firstName;

  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 20)
  @Column(name = "CUST_LAST_NAME")
  private String lastName;

  @Size(max = 3)
  @Column(name = "NLS_LANGUAGE")
  private String language;

  @Column(name = "CREDIT_LIMIT")
  private BigDecimal creditLimit;

  @Size(max = 40)
  @Column(name = "CUST_EMAIL")
  private String mail;

  @Column(name = "DATE_OF_BIRTH")
  @Convert(converter = LocalDateTimeFlexibleAttributeConverter.class)
  private LocalDateTime birthday;

  @Size(max = 20)
  @Column(name = "MARITAL_STATUS")
  private String maritalStatus;

  @Column(name = "GENDER")
  @Enumerated(EnumType.STRING)
  private GenderType gender;

  @Size(max = 20)
  @Column(name = "INCOME_LEVEL")
  private String incomeLevel;
}
