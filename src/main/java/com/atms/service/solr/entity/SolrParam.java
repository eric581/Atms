package com.atms.service.solr.entity;

import org.apache.solr.client.solrj.SolrQuery;

/**
 * @author eric 597918533 15-11-19 01:42 JDK 1.7
 * @Title: [title]
 * @Description: [description]
 * @Project: e
 * @Name: SolrParam
 */
public class SolrParam<T> {

    /**
     * query param
     */
    private T t;
    /**
     * sort param
     */
    private SolrQuery.ORDER sort;
    private String sortParam;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public SolrQuery.ORDER getSort() {
        return sort;
    }

    public void setSort(SolrQuery.ORDER sort) {
        this.sort = sort;
    }

    public String getSortParam() {
        return sortParam;
    }

    public void setSortParam(String sortParam) {
        this.sortParam = sortParam;
    }
}
