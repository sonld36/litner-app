package com.sonld.ps.finalflashcard.business.model;

public class TopicCreateRequest {
    private String name;
    private String description;
    private String owner;
    private String[] tags;

    public TopicCreateRequest() {
    }

    public TopicCreateRequest(String name, String description, String[] tags) {
        this.name = name;
        this.description = description;
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }
}