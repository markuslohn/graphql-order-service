# graphql-order-service

Implements an API based on GraphQL to managed orders. It accesses an Oracle Database (XE) with the samples schema loaded (OE). The application is prepared to run on Oracle WebLogic Server.


## Prepare Infrastructure

The infrastructure used was WebLogic Server 12.2.1.3.0 and Oracle XE 12c. You can obtain an Oracle XE 12c virtual machine from [here](https://github.com/oracle/vagrant-boxes/tree/master/OracleDatabase/18.4.0-XE). In order to run this example you have to install Oracle Sample schemas in the database. The content and installation steps for this database schemas can be obtained [here](https://github.com/oracle/db-sample-schemas).

Oracle WebLogic Server can be obtained and installed from this [location](https://www.oracle.com/middleware/technologies/fusionmiddleware-downloads.html).

## Build Sources

```
mvn package
```

## Deploy to WebLogic Server

```
mvn pre-integration-test -Pweblogic
```

## Usage

1. Download and Install [GraphiQL](https://electronjs.org/apps/graphiql).

2. Use the following URL: http://localhost:7001/graphql-db-test/graphql
