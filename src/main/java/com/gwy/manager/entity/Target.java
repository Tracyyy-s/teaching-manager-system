package com.gwy.manager.entity;

public class Target {
    private String targetId;

    private String targetContent;

    private Integer targetObject;

    private Integer weight;

    private Integer state;

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId == null ? null : targetId.trim();
    }

    public String getTargetContent() {
        return targetContent;
    }

    public void setTargetContent(String targetContent) {
        this.targetContent = targetContent == null ? null : targetContent.trim();
    }

    public Integer getTargetObject() {
        return targetObject;
    }

    public void setTargetObject(Integer targetObject) {
        this.targetObject = targetObject;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}