package com.esentri.integration.order;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * A facade to manage Orders.
 *
 * @author mlohn
 */
@Stateless
public class OrderManagementBean {

  @PersistenceContext(unitName = "order-service")
  private EntityManager em;

  /**
   * Start processing of a new Order in the order system.
   *
   * @param newOrder a new Order
   * @return persistent Order processed in the order system.
   */
  @GraphQLMutation(
      name = "sendOrder",
      description = "Creates and starts processing of a new order.")
  public Order sendOrder(@GraphQLArgument(name = "newOrder") Order newOrder)
      throws IllegalArgumentException {
    if (newOrder == null) {
      throw new IllegalArgumentException("Please provide a non-null order for sending.");
    }

    try {
      em.persist(newOrder);
      em.flush();
      em.refresh(newOrder);
      return newOrder;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }

  /**
   * Lists available Order information.
   *
   * @return a List with Order objects.
   */
  @GraphQLQuery(name = "orders", description = "Lists available Order information.")
  public List<Order> findOrders(@GraphQLArgument(name = "orderId") final Long orderId) {
    CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
    Root<Order> from = criteriaQuery.from(Order.class);
    CriteriaQuery<Order> select = criteriaQuery.select(from);
    if (orderId != null && orderId.longValue() > 0) {
      criteriaQuery.where(criteriaBuilder.equal(from.get("id"), orderId));
    }
    TypedQuery<Order> typedQuery = em.createQuery(select);
    select.orderBy(criteriaBuilder.asc(from.get("orderDate")));
    List<Order> orders = typedQuery.getResultList();

    return orders;
  }

  /**
   * List information about one specific Order.
   *
   * @param parameter the unique id of an Order.
   * @return the found Order or null if not found
   * @throws IllegalArgumentException if orderId is invalid
   */
  @GraphQLQuery(name = "order", description = "Lists available Order information.")
  public Order findOrderById(@GraphQLArgument(name = "orderId") final Long orderId)
      throws IllegalArgumentException {
    if (orderId == null || orderId.longValue() < 0) {
      throw new IllegalArgumentException(
          "No valid orderId provided for lookup. It has to be not null and > 0.");
    }

    CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
    Root<Order> from = criteriaQuery.from(Order.class);
    CriteriaQuery<Order> select = criteriaQuery.select(from);
    criteriaQuery.where(criteriaBuilder.equal(from.get("id"), orderId));
    TypedQuery<Order> typedQuery = em.createQuery(select);
    Order foundOrder = typedQuery.getSingleResult();

    return foundOrder;
  }

  /**
   * List available Products.
   *
   * @return a List with Products
   */
  @GraphQLQuery(name = "products", description = "Lists available Products.")
  public List<Product> findAvailableProducts() {
    CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
    Root<Product> from = criteriaQuery.from(Product.class);
    CriteriaQuery<Product> select = criteriaQuery.select(from);
    TypedQuery<Product> typedQuery = em.createQuery(select);
    List<Product> products = typedQuery.getResultList();

    return products;
  }
}
