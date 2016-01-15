package com.atms.common.solr;

import com.google.common.collect.Lists;
import org.apache.solr.client.solrj.SolrQuery;

import java.util.Iterator;
import java.util.List;

/**
 * @author eric 597918533 15-10-30 07:49 JDK 1.7
 * @Title: [title]
 * @Description: [description]
 * @Project: ec
 * @Name: QueryHelper
 */
public class QueryHelper {
    private StringBuilder queryString = new StringBuilder();
    private List<String> filterList = Lists.newArrayList();
    private boolean searchQueryIsOR = true;

    private String testMust(Boolean mustHave) {
        String ret;
        if (mustHave != null) {
            if (mustHave) {
                ret = "+";
            } else {
                ret = "-";
            }
        } else {
            ret = "";
        }

        return ret;
    }

    public QueryHelper() {
    }

    public QueryHelper(boolean defaultOperatorOR) {
        this.searchQueryIsOR = defaultOperatorOR;
    }

    private StringBuilder getQuery(Boolean mustHave, String field, Object value, boolean valueIfAdd, Integer boost) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.testMust(mustHave));
        sb.append(field).append(":");
        if (value instanceof List) {
            sb.append("(");
            int index = 0;

            for (Iterator i$ = ((List) value).iterator(); i$.hasNext(); ++index) {
                Object o = i$.next();
                if (index > 0 && valueIfAdd) {
                    sb.append("&& ").append(o).append(" ");
                } else {
                    sb.append(o).append(" ");
                }
            }

            sb.append(")");
        } else {
            sb.append(value);
        }

        if (boost != null) {
            sb.append("^").append(boost);
        }

        sb.append(" ");
        return sb;
    }

    private StringBuilder getRangeQuery(Boolean must, String field, Object minValue, Object maxValue, Integer boost) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.testMust(must));
        sb.append(field).append(":[").append(minValue).append(" TO ").append(maxValue).append("]");
        if (boost != null) {
            sb.append("^").append(boost);
        }

        sb.append(" ");
        return sb;
    }

    private StringBuilder getQuery(Boolean mustHave, String searchParam) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.testMust(mustHave));
        sb.append(searchParam);
        return sb;
    }

    public QueryHelper addQuery(Boolean mustHave, String field, Object value, boolean valueIfAdd, Integer boost) {
        if (!this.searchQueryIsOR && this.queryString.length() > 0) {
            this.queryString.append(" && ");
        }

        this.queryString.append(this.getQuery(mustHave, field, value, valueIfAdd, boost));
        return this;
    }

    public QueryHelper addRangeQuery(Boolean must, String field, Object minValue, Object maxValue, Integer boost) {
        if (!this.searchQueryIsOR && this.queryString.length() > 0) {
            this.queryString.append(" && ");
        }

        this.queryString.append(this.getRangeQuery(must, field, minValue, maxValue, boost));
        return this;
    }

    public QueryHelper addFilter(Boolean mustHave, String searchParam) {
        this.filterList.add(this.getQuery(mustHave, searchParam).toString());
        return this;
    }

    public QueryHelper addFilter(Boolean mustHave, String field, Object value, boolean valueIfAdd) {
        this.filterList.add(this.getQuery(mustHave, field, value, valueIfAdd, null).toString());
        return this;
    }

    public QueryHelper addRangeFilter(Boolean must, String field, Object minValue, Object maxValue) {
        this.filterList.add(this.getRangeQuery(must, field, minValue, maxValue, null).toString());
        return this;
    }

    public SolrQuery toQuery() {
        if (this.queryString.length() == 0) {
            this.queryString.append("*:*");
        }

        SolrQuery query = new SolrQuery();
        query.setQuery(this.queryString.toString());

        for (Object fq : this.filterList) {
            query.addFilterQuery(String.valueOf(fq));
        }

        return query;
    }
}
