package com.esentri.integration;

import com.esentri.integration.order.OrderManagementBean;
import graphql.schema.GraphQLSchema;
import graphql.servlet.GraphQLHttpServlet;
import graphql.servlet.config.GraphQLConfiguration;
import io.leangen.graphql.GraphQLSchemaGenerator;
import io.leangen.graphql.metadata.strategy.type.DefaultTypeTransformer;
import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * It provides a GraphQL interface for orders.
 *
 * @author mlohn
 */
@WebServlet(
    name = "GraphQLEndpoint",
    urlPatterns = {"graphql/*"},
    loadOnStartup = 1)
public final class GraphQlEndpoint extends GraphQLHttpServlet {

  private static final Logger logger = LoggerFactory.getLogger(GraphQlEndpoint.class);

  @EJB private OrderManagementBean orderService;

  @Override
  protected GraphQLConfiguration getConfiguration() {
    logger.trace("getConfiguration called.");
    return GraphQLConfiguration.with(createSchema()).build();
  }

  private GraphQLSchema createSchema() {
    logger.trace("createSchema called.");

    GraphQLSchema schema =
        new GraphQLSchemaGenerator(
                "Query",
                "OrderQueryAPI",
                "Mutation",
                "OrderModificationAPI",
                "Subscription",
                "OrderSubscriptionAPI")
            .withTypeTransformer(new DefaultTypeTransformer(true, true))
            .withOperationsFromSingleton(orderService, OrderManagementBean.class)
            .generate();
    logger.trace("createSchema finished.");
    return schema;
  }
}
