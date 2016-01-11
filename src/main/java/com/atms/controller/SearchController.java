package com.atms.controller;

import com.atms.service.SearchService;
import com.atms.service.shiro.ShiroConst;
import com.atms.service.solr.PdfSearch;
import com.jfinal.aop.Enhancer;
import com.jfinal.core.Controller;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;

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


    @RequiresPermissions(ShiroConst.PERMISSION_SEARCH)
    public void index() {
        String query = getPara("query");
        List<PdfSearch> pdfSearchList = searchService.hlSearch(query);
        setAttr("searchLists", pdfSearchList);
        setAttr("query", query);
        render("/views/search/search.jsp");
//        render("/views/error/404.jsp");
    }
}
