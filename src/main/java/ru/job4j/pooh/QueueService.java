package ru.job4j.pooh;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;


public class QueueService implements Service {
    private final ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> queue = new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        String param = req.getParam();
        String sourceName = req.getSourceName();
        if ("POST".equals(req.httpRequestType())) {
            queue.putIfAbsent(sourceName, new ConcurrentLinkedQueue<>());
            queue.get(sourceName).add(param);
            return new Resp(param, "200");
        }

        if ("GET".equals(req.httpRequestType())) {
            if (queue.get(sourceName) != null && !queue.get(sourceName).isEmpty()) {
                return new Resp(queue.get(sourceName).poll(), "200");
            }
        }
        return new Resp("", "204");
    }
}