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
        ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> topic = topics.get(sourceName);
        if ("POST".equals(req.httpRequestType())) {
            if (topic == null) {
                return new Resp("", "204");
            }
            topic.values().forEach(name -> name.offer(param));
            return new Resp(param, "200");
        } else {

            if ("GET".equals(req.httpRequestType())) {
                if (topic == null) {
                    topic = new ConcurrentHashMap<>();
                    topic.putIfAbsent(param, new ConcurrentLinkedQueue<>());
                    topics.putIfAbsent(sourceName, topic);
                } else {
                    if (topic.get(param) != null && !topic.get(param).isEmpty()) {
                        return new Resp(topic.get(param).poll(), "200");
                    } else {
                        topic.putIfAbsent(param, new ConcurrentLinkedQueue<>());
                    }
                    return new Resp("", "204");
                }
            }
            return new Resp("", "204");
        }
    }
}