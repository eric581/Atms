package com.demo.service.solr;

import org.apache.solr.client.solrj.beans.Field;

import java.util.Date;

/**
 * @author eric 597918533 15-11-18 01:49 JDK 1.7
 * @Title: [title]
 * @Description: [description]
 * @Project: ec
 * @Name: Account
 */
public class Account {

    @Field("id")
    private String id;
    @Field("cur_code")
    private String cur_code;
    @Field("create_time")
    private Date create_time;
    @Field("pay_pwd")
    private String pay_pwd;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCur_code() {
        return cur_code;
    }

    public void setCur_code(String cur_code) {
        this.cur_code = cur_code;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getPay_pwd() {
        return pay_pwd;
    }

    public void setPay_pwd(String pay_pwd) {
        this.pay_pwd = pay_pwd;
    }


    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", cur_code='" + cur_code + '\'' +
                ", create_time='" + create_time + '\'' +
                ", pay_pwd='" + pay_pwd + '\'' +
                '}';
    }
}
