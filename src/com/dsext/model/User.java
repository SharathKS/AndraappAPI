package com.dsext.model;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Map;

import javax.json.JsonObject;

public class User {
	public String username;
	public String dak_id;
	public String forwarded_user_id;
	public String note;
	public String meeting_id;
	public String status;
	public String remark;
	public String department_code;	
	public Map<String, String> json;
	public ArrayList<Map<String, String>> jarr;

	public ArrayList<Map<String, String>> getJarr() {
		return jarr;
	}

	public void setJarr(ArrayList<Map<String, String>> jarr) {
		this.jarr = jarr;
	}

	public Map<String, String> getJson() {
		return json;
	}

	public void setJson(Map<String, String> json) {
		this.json = json;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getForwarded_user_id() {
		return forwarded_user_id;
	}

	public void setForwarded_user_id(String forwarded_user_id) {
		this.forwarded_user_id = forwarded_user_id;
	}

	public String getDepartment_code() {
		return department_code;
	}

	public void setDepartment_code(String department_code) {
		this.department_code = department_code;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMeeting_id() {
		return meeting_id;
	}

	public void setMeeting_id(String meeting_id) {
		this.meeting_id = meeting_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String userName) {
		this.username = userName;
	}

	public String getPassWord() {
		return password;
	}

	public void setPassWord(String passWord) {
		this.password = passWord;
	}

	public String getDak_id() {
		return dak_id;
	}

	public void setDak_id(String dak_id) {
		this.dak_id = dak_id;
	}

	public String getForwardedUserId() {
		return forwarded_user_id;
	}

	public void setForwardedUserId(String forwardedUserId) {
		this.forwarded_user_id = forwardedUserId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String password;

}
