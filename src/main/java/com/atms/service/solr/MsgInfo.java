package com.atms.service.solr;

import org.apache.solr.client.solrj.beans.Field;

import java.util.Date;

/**
 * @author eric 597918533 15-11-19 00:38 JDK 1.7
 * @Title: [title]
 * @Description: [description]
 * @Project: ec
 * @Name: MsgInfo
 */
public class MsgInfo {

    @Field("id")
    private String id;
    @Field("msgInfoContent")
    private String msgInfoContent;
    @Field("msgInfoType")
    private String msgInfoType;
    @Field("msgInfoCreateTime")
    private Date msgInfoCreateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMsgInfoContent() {
        return msgInfoContent;
    }

    public void setMsgInfoContent(String msgInfoContent) {
        this.msgInfoContent = msgInfoContent;
    }

    public String getMsgInfoType() {
        return msgInfoType;
    }

    public void setMsgInfoType(String msgInfoType) {
        this.msgInfoType = msgInfoType;
    }

    public Date getMsgInfoCreateTime() {
        return msgInfoCreateTime;
    }

    public void setMsgInfoCreateTime(Date msgInfoCreateTime) {
        this.msgInfoCreateTime = msgInfoCreateTime;
    }

    @Override
    public String toString() {
        return "MsgInfo{" +
                "id='" + id + '\'' +
                ", msgInfoContent='" + msgInfoContent + '\'' +
                ", msgInfoType='" + msgInfoType + '\'' +
                ", msgInfoCreateTime=" + msgInfoCreateTime +
                '}';
    }
}
