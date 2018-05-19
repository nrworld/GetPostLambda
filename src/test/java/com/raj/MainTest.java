package com.raj;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MainTest {

    private Main main = new Main();

    @Test
    public void testSingle() {
        RequestResponsePojo requestResponsePojo =
                new RequestResponsePojo(0, "http://api.externalip.net/ip", "test");
        List<RequestResponsePojo> requestList = new ArrayList<>();
        List<RequestResponsePojo> responses = main.handleRequest(requestList, null);
        responses.forEach(System.out::println);
    }
}
