package ru.job4j.pooh;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;


public class TopicService implements Service {
    private final ConcurrentHashMap<String, ConcurrentHashMap<String, ConcurrentLinkedQueue<String>>> topics =
            new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        String sourceName = req.getSourceName();
        String param = req.getParam();
        if ("POST".equals(req.httpRequestType()) && topics.get(sourceName) != null) {
            topics.get(sourceName).values().forEach(name -> name.offer(param));
            return new Resp(param, "200");
        } else if ("GET".equals(req.httpRequestType())) {
            topics.putIfAbsent(sourceName, new ConcurrentHashMap<>());
            topics.get(req.getSourceName()).putIfAbsent(param, new ConcurrentLinkedQueue<>());
            String text = topics.get(req.getSourceName()).get(req.getParam()).poll();
            return text == null ? new Resp("", "204") : new Resp(text, "200");
        }
        return new Resp("", "204");
    }
}