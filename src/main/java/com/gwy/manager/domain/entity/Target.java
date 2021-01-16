package com.gwy.manager.domain.entity;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
public class Target {
    private Integer targetId;

    private String targetContent;

    private Integer targetObject;

    private Integer weight;

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
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

    @Override
    public String toString() {
        return "Target{" +
                "targetId=" + targetId +
                ", targetContent='" + targetContent + '\'' +
                ", targetObject=" + targetObject +
                ", weight=" + weight +
                '}';
    }
}