package ru.job4j.pooh;


public class Req {

    private final String httpRequestType;
    private final String poohMode;
    private final String sourceName;
    private final String param;

    public Req(String httpRequestType, String poohMode, String sourceName, String param) {
        this.httpRequestType = httpRequestType;
        this.poohMode = poohMode;
        this.sourceName = sourceName;
        this.param = param;
    }

    public static Req of(String content) {
        String[] splitContent = content.split(System.lineSeparator());
        String[] splitFirstLine = splitContent[0].split(" ");
        String[] splitForParams = splitFirstLine[1].split("/");
        String httpRequestType = content.startsWith("POST") ? "POST" : "GET";
        String poohMode = splitForParams[1];
        String sourceName = splitForParams[2];
        String param;
        if (httpRequestType.equals("POST")) {
            param = splitContent[splitContent.length - 1];
        } else {
            param = splitForParams.length > 3 ? splitForParams[3] : "";
        }
        return new Req(httpRequestType, poohMode, sourceName, param);
    }

    public String httpRequestType() {
        return httpRequestType;
    }

    public String getPoohMode() {
        return poohMode;
    }

    public String getSourceName() {
        return sourceName;
    }

    public String getParam() {
        return param;
    }
}