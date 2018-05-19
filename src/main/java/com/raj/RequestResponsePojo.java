package com.raj;

import java.io.Serializable;

public class RequestResponsePojo implements Serializable {
    private int waitSeconds;
    private String url;
    private String identifier;
    private String response;
    private Exception exception;

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public RequestResponsePojo(int waitSeconds, String url, String identifier) {
        this.waitSeconds = waitSeconds;
        this.url = url;
        this.identifier = identifier;
    }

    public int getWaitSeconds() {
        return waitSeconds;
    }

    public String getUrl() {
        return url;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return response;
    }
}
