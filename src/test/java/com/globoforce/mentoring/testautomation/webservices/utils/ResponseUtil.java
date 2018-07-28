package com.globoforce.mentoring.testautomation.webservices.utils;

import org.json.simple.JSONObject;
import org.apache.http.HttpResponse;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStreamReader;

public class ResponseUtil {

    public String valueByKeyCountryResp(HttpResponse response, String propertyName) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject responseJson;
        responseJson = (JSONObject)jsonParser.parse((new InputStreamReader(response.getEntity().getContent())));
        responseJson = (JSONObject) responseJson.get("RestResponse");
        responseJson = (JSONObject) responseJson.get("result");
        String value = String.valueOf(responseJson.get(propertyName));
        return value;
    }

    public String getValidationMessage(HttpResponse response) throws IOException, ParseException{
        JSONParser jsonParser = new JSONParser();
        JSONObject responseJson;
        responseJson = (JSONObject)jsonParser.parse((new InputStreamReader(response.getEntity().getContent())));
        responseJson = (JSONObject) responseJson.get("RestResponse");
        String message = String.valueOf(responseJson.get("messages"));
        return message;
    }
}
