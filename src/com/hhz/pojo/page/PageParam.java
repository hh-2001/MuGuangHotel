package com.hhz.pojo.page;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

public class PageParam {
	//设置相关属性:
	private int page = 1;                     //页码
	private int limit = 10;                   //页大小
	private Map<String,String> keywords = new HashMap(); //搜索关键字

	public void addParam(String key, String val){
		keywords.put(key, val);
	}

	public PageParam(){}
	public PageParam(int page, int limit, Map map){
		this.page = page;
		this.limit = limit;
		this.keywords = map;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public Map<String, String> getKeywords() {
		return keywords;
	}
	public void setKeywords(Map<String, String> keywords) {
		this.keywords = keywords;
	}


	public static PageParam getPageParam(
			HttpServletRequest req ){
		//{1}获取表单传过来所有参数。
		Map<String, String[]> map = req.getParameterMap();
		Set<String> keys = map.keySet();
		int page;
		int limit;
		PageParam pageParm = new PageParam();
		for (String K : keys) {
			if("limit".equals(K)){
				limit = getInt(map,"limit",10);
				pageParm.setLimit(limit);
			}else if("page".equals(K)){
				page = getInt(map,"page",1);
				pageParm.setPage(page);
			}else if("ran".equals(K)){
				//ran 参数是用来防止浏览器不发出 get 请求,
				//在地址栏上设置的伪参数。(不用理它)
			}else{
				pageParm.addParam(
						K, map.get(K)[0] );
			}
		}
		return pageParm;
	}

	//3. 获取表单传过来的参数: page, limit (要传成 int 类型)
	static int getInt(
			Map<String,String[]> map,
			String key, int defVal ){
		//KEY = limit, page
		String[] val = map.get( key );
		if( val==null ){
			//返回默认值
			return defVal;
		}
		return Integer.valueOf( val[0] );
	}

}
