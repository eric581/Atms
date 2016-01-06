package com.demo.controller;

import com.demo.service.SearchService;
import com.demo.service.solr.PdfSearch;
import com.jfinal.aop.Enhancer;
import com.jfinal.core.Controller;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Title:
 * Description:
 *
 * @author:eric
 * @date: 2015-12-11 17:13
 */
public class SearchController extends Controller {

    private Logger logger = Logger.getLogger(BlogController.class);

    public static final SearchService searchService = Enhancer.enhance(SearchService.class);


    public void index() {
        String param = getPara("param");
        List<PdfSearch> pdfSearchList = searchService.hlSearch();
        setAttr("searchLists", pdfSearchList);
        render("/views/search/search.jsp");
    }
}
