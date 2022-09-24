package com.hhz.controller.admin;

import com.hhz.component.BaseController;
import com.hhz.component.IocContainer;
import com.hhz.pojo.User;
import com.hhz.pojo.page.PageBean;
import com.hhz.pojo.page.PageParam;
import com.hhz.service.UserService;
import com.hhz.vo.UserVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Admin/*")
public class AdminController extends BaseController {

    private UserService userService = (UserService) IocContainer.getBean("userService");

    //-----------------------------视图构造---------------------------------
    //用户列表
    public String viewList(HttpServletRequest req, HttpServletResponse resp){
        //{1}获取分页参数对象
        PageParam param = PageParam.getPageParam(req);
        //{2}调用 service 获取PageBean
        PageBean<UserVO> pBean = null;
        try {
            pBean = new PageBean<>();
            pBean.setData(userService.viewLimitList(param));
            pBean.setCount(userService.getCount());
            return "data:"+ pBean.toJSON();
        } catch (Exception e) {
            e.printStackTrace();
            //{ps}这是传给 LayUI 给它解析的。符合 LayUI 格式。
            return "data:"+ pageError(e);
        }
    }

    //-----------------------------视图跳转-----------------------------
    public String index(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        //内部转发到 /WEB-INF/jsp/index.jsp
        return "forward:/WEB-INF/jsp/admin/adminIndex.jsp";
    }
    //新增用户跳转
    public String viewAddUser(HttpServletRequest req, HttpServletResponse resp){
        return "forward:/WEB-INF/jsp/admin/addUser.jsp";
    }

    //跳转编辑用户
    public String viewEditUser(HttpServletRequest req, HttpServletResponse resp){
        return "forward:/WEB-INF/jsp/admin/updateUser.jsp";
    }

    //-----------------------------操作----------------------------------
    //删除用户，根据id，account，no
    public String delUser(HttpServletRequest req, HttpServletResponse resp) {
        User user = getBean(req, User.class);
        //{2}调用 service 的方法[新增 / 更新]。
        try {
            userService.delUser(user);
            //{4}返回数据给前端。
            return "data:" + sendSuccess();
        } catch (Exception e) {
            //{3}异常数据处理 [发送给前端页面]
            e.printStackTrace();
            return "data:" + sendError("failed",
                    e.getMessage(), 500);
        }
    }

    public String addUser(HttpServletRequest req, HttpServletResponse resp){
        User user = getBean(req, User.class);
        //{2}调用 service 的方法[新增 / 更新]。
        try {
            userService.addUser(user);
            //{4}返回数据给前端。
            return "data:" + sendSuccess();
        } catch (Exception e) {
            //{3}异常数据处理 [发送给前端页面]
            e.printStackTrace();
            return "data:" + sendError("failed",
                    e.getMessage(), 500);
        }
    }

    public String updateUser(HttpServletRequest req, HttpServletResponse resp){
        User user = getBean(req, User.class);
        //{2}调用 service 的方法[新增 / 更新]。
        try {
            userService.updateUser(user);
            //{4}返回数据给前端。
            return "data:" + sendSuccess();
        } catch (Exception e) {
            //{3}异常数据处理 [发送给前端页面]
            e.printStackTrace();
            return "data:" + sendError("failed",
                    e.getMessage(), 500);
        }
    }
//    //保存
//    public String saveUser(HttpServletRequest req, HttpServletResponse resp){
//        return null;
//    }

    //搜索特定的用户
    public String getUser(HttpServletRequest req, HttpServletResponse resp){
        User user = getBean(req, User.class);
        //{2}调用 service 的方法[新增 / 更新]。
        try {
            User ans = userService.getUser(user);
            //{4}返回数据给前端。
            return "data:" + sendData(ans);
        } catch (Exception e) {
            //{3}异常数据处理 [发送给前端页面]
            e.printStackTrace();
            return "data:" + sendError("failed",
                    e.getMessage(), 500);
        }
    }

}
