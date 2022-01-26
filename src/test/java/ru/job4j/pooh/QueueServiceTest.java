package ru.job4j.pooh;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class QueueServiceTest {

    @Test
    public void whenPostThenGetQueue() {
        QueueService queueService = new QueueService();
        String paramForPostMethod = "temperature=18";
        String paramForPostMethod2 = "temperature=19";
        String paramForPostMethod3 = "temperature=20";
        queueService.process(
                new Req("POST", "queue", "weather2", paramForPostMethod3)
        );
        queueService.process(
                new Req("POST", "queue", "weather", paramForPostMethod)
        );
        queueService.process(
                new Req("POST", "queue", "weather", paramForPostMethod2)
        );
        Resp result = queueService.process(
                new Req("GET", "queue", "weather", null)
        );
        Resp result2 = queueService.process(
                new Req("GET", "queue", "weather", null)
        );
        Resp result3 = queueService.process(
                new Req("GET", "queue", "weather2", null)
        );
        assertEquals(result.text(), "temperature=18");
        assertEquals(result2.text(), "temperature=19");
        assertEquals(result3.text(), "temperature=20");
    }
}