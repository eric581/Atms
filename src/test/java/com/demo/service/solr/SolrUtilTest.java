package com.atms.common.solr;

import com.atms.common.solr.entity.HLSolrParam;
import com.atms.common.solr.entity.HLSolrResult;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import junit.framework.TestCase;

import java.util.List;
import java.util.Map;

/**
 * Title:
 * Description:
 *
 * @author:eric
 * @date: 2015-12-23 14:51
 */
public class SolrUtilTest extends TestCase {

    public void testHightlightQuery() throws Exception {
        HLSolrParam hlSolrParam = new HLSolrParam();
        hlSolrParam.setPre("<div>");
        hlSolrParam.setPost("</div>");

        PdfSearch pdfSearchParam = new PdfSearch();
        hlSolrParam.setT(pdfSearchParam);
        Map<String, String> param = Maps.newHashMap();
        param.put("text", "Google");
        hlSolrParam.setHlParam(param);


        HLSolrResult<PdfSearch> pdfSearchList = SolrUtil.hightlightQuery(hlSolrParam);
        List<PdfSearch> pdfSearches = Lists.newArrayList();

        for (PdfSearch pdfSearch : pdfSearchList.getResultList()) {
            for (Map.Entry<String, Map<String, List<String>>> entry : pdfSearchList.getHighlightResults().entrySet()) {
                if (pdfSearch.getId().equals(entry.getKey())){
                    pdfSearch.setHlContent(entry.getValue().get("text").get(0));
                    pdfSearches.add(pdfSearch);
                }
            }
        }

        System.out.println(pdfSearchList);

    }
}