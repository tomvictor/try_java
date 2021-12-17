package com.pluralsight.bdd;

import com.azure.cosmos.*;
import com.azure.cosmos.models.*;
import com.azure.cosmos.util.CosmosPagedIterable;
import com.pluralsight.bdd.calculator.Calculator;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.deps.com.google.gson.JsonObject;

import java.util.Collections;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorStepDefinitions {
    Calculator calculator;
    Integer result;
    @Given("I have a calculator")
    public void i_have_a_calculator() {
        calculator = new Calculator();
    }

    @When("I add {int} and {int}")
    public void i_add_and(Integer int1, Integer int2) {

        CosmosClient client = new CosmosClientBuilder()
                .endpoint("https://cosmos-db-deneral-sql2.documents.azure.com:443/")
                .key("xIBcxAXgr1Rqp4ia0O9zMQvQILansW0uj9uyjsFwpouDYnycw0Qh6Wrmer7y0ptpHpugUTTA7hnKbZFv0XyKXA==")
                //  Setting the preferred location to Cosmos DB Account region
                //  West US is just an example. User should set preferred location to the Cosmos DB region closest to the application
//                .preferredRegions(Collections.singletonList("West US"))
                .consistencyLevel(ConsistencyLevel.EVENTUAL)
                .buildClient();

        CosmosDatabase database = client.getDatabase("ToDoList");
        CosmosContainer container = database.getContainer("Items");


        int preferredPageSize = 10;
        CosmosQueryRequestOptions queryOptions = new CosmosQueryRequestOptions();
        //  Set populate query metrics to get metrics around query executions
        queryOptions.setQueryMetricsEnabled(true);

        CosmosPagedIterable<Family> familiesPagedIterable = container.queryItems(
                "SELECT * FROM c where c.id = 'SalesOrder1dd'", queryOptions, Family.class);

        System.out.println(familiesPagedIterable.stream().count());


        familiesPagedIterable.iterableByPage(preferredPageSize).forEach(cosmosItemPropertiesFeedResponse -> {
            System.out.println("Got a page of query result with " +
                    cosmosItemPropertiesFeedResponse.getResults().size() + " items(s)"
                    + " and request charge of " + cosmosItemPropertiesFeedResponse.getRequestCharge());

            System.out.println("Item Ids " + cosmosItemPropertiesFeedResponse
                    .getResults()
                    .stream()
                    .map(Family::getId)
                    .collect(Collectors.toList()));
        });


        result = calculator.add(int1,int2);
    }

    @Then("I should get {int}")
    public void i_should_get(Integer int1) {
        assertThat(result).isEqualTo(int1);
    }
}
