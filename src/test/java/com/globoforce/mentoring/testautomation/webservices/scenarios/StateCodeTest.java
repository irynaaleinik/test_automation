package com.globoforce.mentoring.testautomation.webservices.scenarios;

import com.globoforce.mentoring.testautomation.uitesting.utils.TestListener;
import com.globoforce.mentoring.testautomation.webservices.utils.HttpUtil;
import com.globoforce.mentoring.testautomation.webservices.utils.ResponseUtil;
import org.apache.http.HttpResponse;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

@Listeners(TestListener.class)
public class StateCodeTest {
    HttpUtil util;
    HttpResponse response;
    ResponseUtil responseUtil;

    public final static String URL = "http://services.groupkt.com/state/get/";
    public final static String COUNTRY = "USA";
    public final static String VALID_COUNTRY_CODE = "AK";
    public final static String INVALID_COUNTRY_CODE = "UU";
    public final static String CAPITAL_NAME = "Juneau";
    public final static String VALIDATION_MESSAGE = "[\"No matching state found for requested code [USA->UU].\"]";

    @Test (description = "Verify that a capital of requested state is correct")
    public void verifyCapitalByUsaCountryStateCodes() throws IOException, ParseException {
        util = new HttpUtil();
        response = util.getResponseByUrl(URL + "/" + COUNTRY + "/" + VALID_COUNTRY_CODE);
        System.out.println("Response status code is : " + response.getStatusLine().getStatusCode());
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
        responseUtil = new ResponseUtil();
        String stateCapital = responseUtil.valueByKeyCountryResp(response,"capital");
        System.out.println("A capital of AK state of USA is " + stateCapital);
        Assert.assertEquals(stateCapital, CAPITAL_NAME);
    }


    @Test (description = "Verify that user friendly message is return when invalid state is sent to WS")
        public void verifyMessageForUsaCountryInvalideStateCode() throws IOException, ParseException{
        util = new HttpUtil();
        response = util.getResponseByUrl(URL + "/" + COUNTRY + "/" + INVALID_COUNTRY_CODE);
        System.out.println("Response status code is : " + response.getStatusLine().getStatusCode());
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
        responseUtil = new ResponseUtil();
        String validationMessage = responseUtil.getValidationMessage(response);
        System.out.println("If state code is incorrect validation message is " + validationMessage);
        Assert.assertEquals(validationMessage, VALIDATION_MESSAGE);
    }
}
