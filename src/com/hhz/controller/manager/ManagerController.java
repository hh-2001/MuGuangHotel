package com.hhz.controller.manager;

import com.hhz.component.BaseController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Manager/*")
public class ManagerController extends BaseController {

    //-----------------------------视图构造---------------------------------
    //-----------------------------视图跳转-----------------------------
    public String index(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        //内部转发到 /WEB-INF/jsp/login.jsp
        return "forward:/WEB-INF/jsp/manager/managerIndex.jsp";
    }
    //-----------------------------操作----------------------------------

}
