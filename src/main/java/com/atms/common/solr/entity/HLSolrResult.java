package com.atms.common.solr.entity;

import java.util.List;
import java.util.Map;

/**
 * @author eric 597918533 15-11-19 19:12 JDK 1.7
 * @Title: [title]
 * @Description: [description]
 * @Project: ec
 * @Name: HLSolrResult
 */
public class HLSolrResult<T> extends SolrResult<T> {

    private Map<String, Map<String,List<String>>> highlightResults;

    public Map<String, Map<String, List<String>>> getHighlightResults() {
        return highlightResults;
    }

    public void setHighlightResults(Map<String, Map<String, List<String>>> highlightResults) {
        this.highlightResults = highlightResults;
    }
}
