package ru.job4j.pooh;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ReqTest {

    @Test
    public void whenQueueModePostMethod() {
        String ls = System.lineSeparator();
        String content = "POST /queue/weather HTTP/1.1" + ls +
                "Host: localhost:9000" + ls +
                "User-Agent: curl/7.72.0" + ls +
                "Accept: */*" + ls +
                "Content-Length: 14" + ls +
                "Content-Type: application/x-www-form-urlencoded" + ls +
                "" + ls +
                "temperature=18" + ls;
        Req req = Req.of(content);
        assertEquals(req.httpRequestType(), "POST");
        assertEquals(req.getPoohMode(), "queue");
        assertEquals(req.getSourceName(), "weather");
        assertEquals(req.getParam(), "temperature=18");
    }

    @Test
    public void whenQueueModeGetMethod() {
        String ls = System.lineSeparator();
        String content = "GET /queue/weather HTTP/1.1" + ls
                + "Host: localhost:9000" + ls
                + "User-Agent: curl/7.72.0" + ls
                + "Accept: */*" + ls + ls + ls;
        Req req = Req.of(content);
        assertEquals(req.httpRequestType(), "GET");
        assertEquals(req.getPoohMode(), "queue");
        assertEquals(req.getSourceName(), "weather");
        assertEquals(req.getParam(), "");
    }

    @Test
    public void whenTopicModePostMethod() {
        String ls = System.lineSeparator();
        String content = "POST /topic/weather HTTP/1.1" + ls +
                "Host: localhost:9000" + ls +
                "User-Agent: curl/7.72.0" + ls +
                "Accept: */*" + ls +
                "Content-Length: 14" + ls +
                "Content-Type: application/x-www-form-urlencoded" + ls +
                "" + ls +
                "temperature=18" + ls;
        Req req = Req.of(content);
        assertEquals(req.httpRequestType(), "POST");
        assertEquals(req.getPoohMode(), "topic");
        assertEquals(req.getSourceName(), "weather");
        assertEquals(req.getParam(), "temperature=18");
    }

    @Test
    public void whenTopicModeGetMethod() {
        String ls = System.lineSeparator();
        String content = "GET /topic/weather/client407 HTTP/1.1" + ls
                + "Host: localhost:9000" + ls
                + "User-Agent: curl/7.72.0" + ls
                + "Accept: */*" + ls + ls + ls;
        Req req = Req.of(content);
        assertEquals(req.httpRequestType(), "GET");
        assertEquals(req.getPoohMode(), "topic");
        assertEquals(req.getSourceName(), "weather");
        assertEquals(req.getParam(), "client407");
    }
}