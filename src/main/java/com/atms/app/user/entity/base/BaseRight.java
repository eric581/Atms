package com.atms.app.user.entity.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseRight<M extends BaseRight<M>> extends Model<M> implements IBean {

	public void setId(java.lang.String id) {
		set("id", id);
	}

	public java.lang.String getId() {
		return get("id");
	}

	public void setRightCode(java.lang.String rightCode) {
		set("right_code", rightCode);
	}

	public java.lang.String getRightCode() {
		return get("right_code");
	}

	public void setRightName(java.lang.String rightName) {
		set("right_name", rightName);
	}

	public java.lang.String getRightName() {
		return get("right_name");
	}

	public void setRemark(java.lang.String remark) {
		set("remark", remark);
	}

	public java.lang.String getRemark() {
		return get("remark");
	}

	public void setCreateTime(java.util.Date createTime) {
		set("create_time", createTime);
	}

	public java.util.Date getCreateTime() {
		return get("create_time");
	}

	public void setModifyTime(java.util.Date modifyTime) {
		set("modify_time", modifyTime);
	}

	public java.util.Date getModifyTime() {
		return get("modify_time");
	}

}
