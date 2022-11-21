package com.hhz.filter;

import com.hhz.utils.BaseFilter;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginFilter extends BaseFilter {
	

	@Override
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)throws IOException, ServletException {
		HttpSession session = req.getSession();
        Object seCode = session.getAttribute("code");
        String code = req.getParameter("code");
        if(code.equals(seCode.toString())) {//֤������Ϣ���Լ�������
            chain.doFilter(req, resp);
        }else {
        	String msg = URLEncoder.encode(
					"������˼����֤�����", "UTF-8");
			String path = "/WEB-INF/jsp/login.jsp?error="+msg;
			req.getRequestDispatcher( path ).forward(req, resp);
        }
	}

}
