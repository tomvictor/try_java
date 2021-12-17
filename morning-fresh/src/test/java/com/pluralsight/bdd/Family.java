package com.pluralsight.bdd;

public class Family {
    public Family() {
    }
    private String id;
    private String partitionKey;
    private String purchase_order_number;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPartitionKey() {
        return partitionKey;
    }

    public void setPartitionKey(String partitionKey) {
        this.partitionKey = partitionKey;
    }

    public String get_purchase_order_number() {
        return purchase_order_number;
    }

    public void set_purchase_order_number(String purchase_order_number) {
        this.purchase_order_number = purchase_order_number;
    }
}
