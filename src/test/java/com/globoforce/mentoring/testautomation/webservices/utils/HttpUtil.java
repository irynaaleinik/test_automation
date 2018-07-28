package com.globoforce.mentoring.testautomation.webservices.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.Closeable;
import java.io.IOException;

public class HttpUtil {
    private HttpClient client;

    public  HttpResponse getResponseByUrl(String url) throws IOException {
        if (client == null){
            client = HttpClients.createDefault();
        }
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = client.execute(httpGet);
        return response;
    }

}
