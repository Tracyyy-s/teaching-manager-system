package com.gwy.manager.domain.entity;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
public class TermTarget {
    private String termId;

    private Integer targetId;

    private Integer weight;

    private Integer termTargetObject;

    public String getTermId() {
        return termId;
    }

    public void setTermId(String termId) {
        this.termId = termId == null ? null : termId.trim();
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

    @Override
    public String toString() {
        return "TermTarget{" +
                "termId='" + termId + '\'' +
                ", targetId=" + targetId +
                ", weight=" + weight +
                ", termTargetObject=" + termTargetObject +
                '}';
    }
}