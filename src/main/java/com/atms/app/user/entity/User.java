package com.atms.app.user.entity;

import com.atms.app.user.entity.base.BaseUser;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import java.util.List;

@SuppressWarnings("serial")
public class User extends BaseUser<User> {
    public static final User dao = new User();

    public Record findByUserName(String userName) {
        return Db.findFirst("select * from user u where u.username = ?", userName);
    }

    public List<String> findRightsByUserId(String userId) {
        List<String> rights = Db.query("");
        return rights;
    }
}
