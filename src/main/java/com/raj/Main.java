package com.raj;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.List;

public class Main implements RequestHandler<List<RequestResponsePojo>, List<RequestResponsePojo>> {
    private static LambdaLogger logger;

    @Override
    public List<RequestResponsePojo> handleRequest(List<RequestResponsePojo> requestList, Context context) {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        requestList.forEach(req -> {
            try {
                Thread.sleep(req.getWaitSeconds() * 1000);
                HttpGet httpget = new HttpGet(req.getUrl());
                // Create a custom response handler
                ResponseHandler<String> responseHandler = response -> {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                };
                req.setResponse(httpclient.execute(httpget, responseHandler));
            } catch (Exception ex) {
                req.setException(ex);
            }
        });
        return requestList;
    }
}
