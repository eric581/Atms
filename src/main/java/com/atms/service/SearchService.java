package com.demo.service;

import com.demo.service.solr.PdfSearch;
import com.demo.service.solr.SolrUtil;
import com.demo.service.solr.entity.HLSolrParam;
import com.demo.service.solr.entity.HLSolrResult;
import com.demo.service.solr.entity.SolrParam;
import com.demo.service.solr.entity.SolrResult;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

/**
 * Title:
 * Description:
 *
 * @author:eric
 * @date: 2015-12-22 12:04
 */
public class SearchService {
    /**
     * 日志服务
     */
    private Logger logger = Logger.getLogger(getClass());

    public List<PdfSearch> search() {
        List<PdfSearch> pdfSearchList = SolrUtil.query(PdfSearch.class);
//        logger.info("pdfSearchList:" + pdfSearchList);
        return pdfSearchList;
    }

    public List<PdfSearch> hlSearch() {
        HLSolrParam hlSolrParam = new HLSolrParam();
        hlSolrParam.setPre("<span style=\"color:red\">");
        hlSolrParam.setPost("</span>");

        PdfSearch pdfSearchParam = new PdfSearch();
        hlSolrParam.setT(pdfSearchParam);
        Map<String, String> param = Maps.newHashMap();
        param.put("text", "Google");
        hlSolrParam.setHlParam(param);


        HLSolrResult<PdfSearch> pdfSearchList = SolrUtil.hightlightQuery(hlSolrParam);
        List<PdfSearch> pdfSearches = Lists.newArrayList();
        if (pdfSearchList == null) {
            return null;
        }

        for (PdfSearch pdfSearch : pdfSearchList.getResultList()) {
            for (Map.Entry<String, Map<String, List<String>>> entry : pdfSearchList.getHighlightResults().entrySet()) {
                if (pdfSearch.getId().equals(entry.getKey())) {
                    pdfSearch.setHlContent(entry.getValue().get("text") == null ? null : entry.getValue().get("text").get(0));
                    pdfSearches.add(pdfSearch);
                }
            }
        }

        return pdfSearches;
    }

}
