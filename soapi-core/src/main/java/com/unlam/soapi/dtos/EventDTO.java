package com.unlam.soapi.dtos;

public class EventDTO {
    private String username;
    private String eventType;
    private String environment;

    public EventDTO(String username, String eventType, String environment) {
        this.username = username;
        this.eventType = eventType;
        this.environment = environment;
    }

    public EventDTO() {
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
}
