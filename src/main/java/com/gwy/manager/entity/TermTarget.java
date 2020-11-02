package com.gwy.manager.entity;

public class TermTarget {
    private String termId;

    private String targetId;

    private Integer weight;

    private Integer termTargetObject;

    public String getTermId() {
        return termId;
    }

    public void setTermId(String termId) {
        this.termId = termId == null ? null : termId.trim();
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId == null ? null : targetId.trim();
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getTermTargetObject() {
        return termTargetObject;
    }

    public void setTermTargetObject(Integer termTargetObject) {
        this.termTargetObject = termTargetObject;
    }
}