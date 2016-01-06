package com.demo.service.solr;

import com.demo.service.solr.entity.HLSolrParam;
import com.demo.service.solr.entity.HLSolrResult;
import com.demo.service.solr.entity.SolrParam;
import com.demo.service.solr.entity.SolrResult;
import com.google.common.collect.Lists;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @author eric 597918533 15-11-17 22:07 JDK 1.7
 * @Title: [solr util]
 * @Description: [description]
 * @Project: ec
 * @Name: SolrUtil
 */
public class SolrUtil {

    public static Logger logger = LoggerFactory.getLogger(SolrUtil.class);

    public static String SOLR_SERVER_URL = "http://localhost:8983/solr";

    public static String COLLECTION = "tika";

    public static <T> List<T> query(Class<T> clazz) {
        List<T> result = Lists.newArrayList();
        try {
            Long begin = System.currentTimeMillis();
            SolrClient solrClient = new HttpSolrClient(SOLR_SERVER_URL);
            QueryResponse queryResponse = solrClient.query(COLLECTION, new SolrQuery("*:*"));
            result = queryResponse.getBeans(clazz);
            logger.info("检索耗时：{}", System.currentTimeMillis() - begin);
        } catch (Exception e) {
            logger.error("全文检索异常：{}", e.getMessage());
        }
        return result;
    }

    public static <T> SolrResult<T> query(SolrParam<T> solrParam) {
        try {
            List<T> result;
            Long begin = System.currentTimeMillis();
            SolrClient solrClient = new HttpSolrClient(SOLR_SERVER_URL);

            SolrQuery solrQuery = new SolrQuery();
            String param = getParams(solrParam.getT());//平装查询参数
            solrQuery.setQuery(param);

            if (solrParam.getSort() != null && solrParam.getSortParam() != null) {
                solrQuery.addSort(solrParam.getSortParam(), solrParam.getSort());//sort
            }

            QueryResponse queryResponse = solrClient.query(COLLECTION, solrQuery);
            result = (List<T>) queryResponse.getBeans(solrParam.getT().getClass());

            int status = queryResponse.getStatus();//status
            Long elapsedTime = queryResponse.getElapsedTime();//除查询时间，还包括传输，序列化，反序列化等的时间
            int QTime = queryResponse.getQTime();//获取在solr内查询的时间
            logger.info("status:{},elapsedTime:{},QTime:{}" + status, elapsedTime, QTime);

            SolrResult<T> solrResult = new SolrResult<T>();
            solrResult.setStatus(status);
            solrResult.setResultList(result);
            solrResult.setElapsedTime(elapsedTime);
            logger.info("查询耗时：{}", System.currentTimeMillis() - begin);
            return solrResult;
        } catch (Exception e) {
            logger.error("全文检索异常：{}", e.getMessage());
        }
        return null;
    }

    public static <T> HLSolrResult<T> hightlightQuery(HLSolrParam solrParam) {
        try {
            List<T> result;
            Long begin = System.currentTimeMillis();
            SolrClient solrClient = new HttpSolrClient(SOLR_SERVER_URL);

            SolrQuery solrQuery = new SolrQuery();
//            String param = getParams(solrParam.getT());//平装查询参数
            solrQuery.setQuery("text:\"管理\"");
//            solrQuery.addSort(solrParam.getSortParam(), solrParam.getSort());//sort config

            Map<String, String> hlParam = solrParam.getHlParam();//add highlight config
            solrQuery.setHighlightSimplePre(solrParam.getPre());
            solrQuery.setHighlightSimplePost(solrParam.getPost());
            solrQuery.setParam("hl", "true");
            for (Map.Entry<String, String> entry : hlParam.entrySet()) {
//                solrQuery.setParam("fl", entry.getValue());
                solrQuery.setParam("hl.fl", entry.getKey());
            }

            QueryResponse queryResponse = solrClient.query(COLLECTION, solrQuery);
            result = (List<T>) queryResponse.getBeans(solrParam.getT().getClass());

            Map<String, Map<String, List<String>>> highlightResult = queryResponse.getHighlighting();

            int status = queryResponse.getStatus();//status
            Long elapsedTime = queryResponse.getElapsedTime();//除查询时间，还包括传输，序列化，反序列化等的时间
            int QTime = queryResponse.getQTime();//获取在solr内查询的时间
            logger.info("status:{},elapsedTime:{},QTime:{}" + status, elapsedTime, QTime);

            HLSolrResult<T> solrResult = new HLSolrResult<T>();
            solrResult.setStatus(status);
            solrResult.setResultList(result);
            solrResult.setElapsedTime(elapsedTime);
            solrResult.setHighlightResults(highlightResult);
            logger.info("查询耗时：{}", System.currentTimeMillis() - begin);
            return solrResult;
        } catch (Exception e) {
            logger.error("全文检索异常：{}", e.getMessage());
        }
        return null;
    }


    @Deprecated
    public static <T> SolrResult<T> query(Class<T> clazz, T t) {
        try {
            List<T> result;
            Long begin = System.currentTimeMillis();
            SolrClient solrClient = new HttpSolrClient(SOLR_SERVER_URL);

            SolrQuery solrQuery = new SolrQuery();
            String param = getParams(t);//平装查询参数
            solrQuery.setQuery(param);

            QueryResponse queryResponse = solrClient.query(COLLECTION, solrQuery);
            result = queryResponse.getBeans(clazz);

            int status = queryResponse.getStatus();//status
            Long elapsedTime = queryResponse.getElapsedTime();//除查询时间，还包括传输，序列化，反序列化等的时间
            int QTime = queryResponse.getQTime();//获取在solr内查询的时间
            logger.info("status:{},elapsedTime:{},QTime:{}" + status, elapsedTime, QTime);

            SolrResult<T> solrResult = new SolrResult<T>();
            solrResult.setStatus(status);
            solrResult.setResultList(result);
            solrResult.setElapsedTime(elapsedTime);
            logger.info("查询耗时：{}", System.currentTimeMillis() - begin);
            return solrResult;
        } catch (Exception e) {
            logger.error("全文检索异常：{}", e.getMessage());
        }
        return null;
    }

    public static <T> List<T> query(Class<T> clazz, String sortParam, SolrQuery.ORDER order) {
        List<T> result = Lists.newArrayList();
        try {
            Long begin = System.currentTimeMillis();
            SolrClient solrClient = new HttpSolrClient(SOLR_SERVER_URL);

            SolrQuery solrQuery = new SolrQuery();
            solrQuery.setQuery("*:*");
            solrQuery.addSort(sortParam, order);

            QueryResponse queryResponse = solrClient.query(COLLECTION, solrQuery);
            result = queryResponse.getBeans(clazz);
            logger.info("查询耗时：{}", System.currentTimeMillis() - begin);
        } catch (Exception e) {
            logger.error("全文检索异常：{}", e.getMessage());
        }
        return result;
    }

    public static <T> String getParams(T t) {
        StringBuilder result = new StringBuilder();
        Class clazz = t.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                Object resultObject = invokeMethod(clazz, t, field.getName());
                if (resultObject != null) {
                    result.append(" ")
                            .append(field.getName())
                            .append(":")
                            .append(resultObject)
                            .append(" ")
                            .append("AND");
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        String resultParam = result.toString();
        return resultParam.substring(0, resultParam.length() - 3);
    }

    public static <T> Object invokeMethod(Class clazz, T t, String fieldName) throws SecurityException, NoSuchMethodException,
            IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        Method method = clazz.getMethod(toGetter(fieldName));
        return method.invoke(t);
    }

    /**
     * @param fieldName
     * @return
     */
    public static String toGetter(String fieldName) {
        if (fieldName == null || fieldName.length() == 0) {
            return null;
        }
        if (fieldName.length() > 2) {
            String second = fieldName.substring(1, 2);
            if (second.equals(second.toUpperCase())) {
                return "get" + fieldName;
            }
        }
        fieldName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        return fieldName;
    }


}
