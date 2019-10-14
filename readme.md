# graphql-order-service

Implements an API based on GraphQL to managed orders. It accesses an Oracle Database (XE) with the samples schema loaded (OE). The application is prepared to run on Oracle WebLogic Server.

# Build

```
mvn package
```

# Deploy

```
mvn pre-integration-test -Pweblogic
```

# Usage

1. Use GraphiQL

2. Use the following URL: http://localhost:7001/graphql-db-test/graphql
