package com.hhz.controller;

import com.hhz.component.BaseController;
import com.hhz.component.IocContainer;
import com.hhz.pojo.User;
import com.hhz.service.UserService;
import com.hhz.utils.CodeUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Map;

@WebServlet("/Common/*")
public class CommonController extends BaseController {
    //{1}向 IOC 容器获取 userService 对象。
    private UserService userSerivce = (UserService) IocContainer.getBean("userService");

    //a. showLogin
    public String showLogin(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        //内部转发到 /WEB-INF/jsp/login.jsp
        return "forward:/login.jsp";
    }

    //b. index
    public String index(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        //内部转发到 /WEB-INF/jsp/login.jsp
        return "forward:/WEB-INF/jsp/index.jsp";
    }



    //b. login 方法
    public String login( HttpServletRequest req, HttpServletResponse resp )
            throws IOException, ServletException {
        //{1}获取表单的参数。[getBean]
        User user = getBean(req, User.class);//设置用户名和密码
        //{2}登录失败返回到登录页。
        String retPath = "/Common/showLogin";
        try {
            System.out.println("执行登录");
            //{3}执行登录..
            User backUser = userSerivce.doLogin(user);
            if(backUser!=null){
                System.out.println(backUser.toString());
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                //{ps}在酒店管理系统中, 这里还有代码要写的。
                // --------留个位置---------
                //{5}导航到主页
                retPath = "/Common/index";
                print("用户登录成功..");
            }
        } catch (Exception e) {
            e.printStackTrace();
            String msg = e.getMessage();
            msg = URLEncoder.encode(msg,"UTF-8");
            retPath += "?error="+ msg;
        }
        return "redirect:"+ retPath;
    }

    public void GetCode(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //把验证码放进session
        Map<String, Object> map = CodeUtils.generateCode();
        Object code = map.get("code");
        System.out.println(code.toString());
        HttpSession session = req.getSession();
        session.setAttribute("code", code);
        //传输验证码图片到前jsp
        ServletOutputStream os = resp.getOutputStream();
        resp.setHeader("Pragma", "no-cache");//取消缓存
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", -1);
        BufferedImage img = (BufferedImage) map.get("codePic");
        ImageIO.write(img, "jpeg", os);
    }
}
