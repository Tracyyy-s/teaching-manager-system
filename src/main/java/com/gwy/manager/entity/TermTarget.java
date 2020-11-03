package com.gwy.manager.entity;

public class TermTarget {
    private Integer termId;

    private Integer targetId;

    private Integer weight;

    private Integer termTargetObject;

    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
    }

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
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