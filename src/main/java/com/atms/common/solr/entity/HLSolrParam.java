package com.atms.common.solr.entity;

import java.util.Map;

/**
 * @author eric 597918533 15-11-19 18:29 JDK 1.7
 * @Title: [title]
 * @Description: [description]
 * @Project: ec
 * @Name: HLSolrParam
 */
public class HLSolrParam extends SolrParam {

    private String pre;

    private String post;

    private Map<String, String> hlParam;

    public String getPre() {
        return pre;
    }

    public void setPre(String pre) {
        this.pre = pre;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Map<String, String> getHlParam() {
        return hlParam;
    }

    public void setHlParam(Map<String, String> hlParam) {
        this.hlParam = hlParam;
    }
}
