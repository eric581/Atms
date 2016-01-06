package com.atms.service.solr.entity;

import java.util.List;

/**
 * @author eric 597918533 15-11-19 01:32 JDK 1.7
 * @Title: [title]
 * @Description: [description]
 * @Project: ec
 * @Name: SolrResult
 */
public class SolrResult<T> {
    /**
     * query status .
     */
    private int status;
    /**
     * query cost time .
     */
    private Long elapsedTime;
    /**
     * query result info .
     */
    private List<T> resultList;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(Long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public List<T> getResultList() {
        return resultList;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }

    @Override
    public String toString() {
        return "SolrResult{" +
                "status=" + status +
                ", elapsedTime=" + elapsedTime +
                ", resultList=" + resultList +
                '}';
    }
}
