package com.globoforce.mentoring.testautomation.businessobject;

import java.util.ResourceBundle;

public class Client {

    private long clientId;
    private String clientName = null;
    private String clientStoreCode = null;

    public Client(String clientId) {
        ResourceBundle resource =
                ResourceBundle.getBundle("database");
        this.clientId = Long.valueOf(resource.getString(clientId +".id"));
        this.clientName = resource.getString(clientId + ".shortname");
        this.clientStoreCode = resource.getString(clientId + "storecode");
    }

    public long getClientId() {
        return clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientStoreCode() {
        return clientStoreCode;
    }
}
