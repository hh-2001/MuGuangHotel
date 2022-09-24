package com.hhz.pojo.page;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/*
  2. PageBean 对象
          设置相关属性:
	{a}int code;
	{b}String msg;
	{c}int count;
	{d}List<T> data;

	相关方法:
	a. toJSON();
 */
@Data
public class PageBean <T> {
	private int code = 0;
	private String msg = "";
	private int count = 0;
	private List<T> data = new ArrayList();

	public PageBean(){}
	public PageBean(int count){
		this.count = count;
	}
	//相关方法:
	public String toJSON(){
		JSONObject jsObj = new JSONObject();
		jsObj.put("code", code);
		jsObj.put("msg", msg);
		jsObj.put("count", count);
		jsObj.put("data", data);
		return jsObj.toJSONString();
	}
}
