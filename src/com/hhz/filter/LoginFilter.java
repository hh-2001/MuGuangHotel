package com.hhz.filter;

import com.hhz.utils.BaseFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Enumeration;

public class LoginFilter extends BaseFilter {
    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        // TODO Auto-generated method stub
        HttpSession session = req.getSession();
        String ctxPath = req.getContextPath();
        Object seCode = session.getAttribute("code");
        String msg = null;
        String code = req.getParameter("code");
        if(code.equals(seCode.toString())) {//证明有信息可以继续请求
            chain.doFilter(req, resp);
            return;
        }else {
            msg = URLEncoder.encode("验证码不正确", "utf-8");
        }
        resp.sendRedirect(ctxPath + "/login.jsp?error=" + msg);
    }
}
