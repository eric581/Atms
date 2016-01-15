package com.demo.service;

import com.atms.app.search.service.SearchService;
import com.jfinal.aop.Enhancer;
import junit.framework.TestCase;

/**
 * Title:
 * Description:
 *
 * @author:eric
 * @date: 2015-12-22 14:58
 */
public class SearchServiceTest extends TestCase {

    public static final SearchService searchService = Enhancer.enhance(SearchService.class);

    public void testSearch() throws Exception {
        searchService.search();
    }
}