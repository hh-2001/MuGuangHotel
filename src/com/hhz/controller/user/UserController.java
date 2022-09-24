package com.hhz.controller.user;

import com.hhz.component.BaseController;
import com.hhz.component.IocContainer;
import com.hhz.exception.UpdateExceptioin;
import com.hhz.pojo.BillDay;
import com.hhz.pojo.LodgingInfo;
import com.hhz.pojo.Room;
import com.hhz.pojo.User;
import com.hhz.pojo.page.PageBean;
import com.hhz.pojo.page.PageParam;
import com.hhz.service.LodgerService;
import com.hhz.service.RoomService;
import com.hhz.service.UserService;
import com.hhz.vo.RoomVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/User/*")
public class UserController extends BaseController {

    private UserService userService = (UserService) IocContainer.getBean("userService");
    private RoomService roomService = (RoomService) IocContainer.getBean("roomService");
    private LodgerService lodgerService = (LodgerService) IocContainer.getBean("lodgerService");

    //-----------------------------视图构造---------------------------------
    //房间列表
    public String viewList(HttpServletRequest req, HttpServletResponse resp){
        //{1}获取分页参数对象
        PageParam param = PageParam.getPageParam(req);
        //{2}调用 service 获取PageBean
        PageBean<RoomVo> pBean = new PageBean<>();
        try {
            List<RoomVo> roomList = roomService.getRoomList(param, pBean);
            if(roomList!=null){
                pBean.setCode(0);
                pBean.setData(roomList);
            }
            System.out.println(pBean.getData().toString());
            return "data:"+ pBean.toJSON();
        } catch (Exception e) {
            e.printStackTrace();
            //{ps}这是传给 LayUI 给它解析的。符合 LayUI 格式。
            return "data:"+ pageError(e);
        }
    }

    //查看营业额
    public String viewTake(HttpServletRequest req, HttpServletResponse resp){
        //{1}获取分页参数对象
        PageParam param = PageParam.getPageParam(req);
        //{2}调用 service 获取PageBean
        PageBean<BillDay> pBean = new PageBean<>();
        try {
            List<BillDay> all = roomService.getAll();
            if(all!=null){
                pBean.setCode(0);
                pBean.setData(all);
            }
            return "data:"+ pBean.toJSON();
        } catch (Exception e) {
            e.printStackTrace();
            //{ps}这是传给 LayUI 给它解析的。符合 LayUI 格式。
            return "data:"+ pageError(e);
        }
    }

    //查看住房信息(客人入住的)
    public String viewRoomHasGuset(HttpServletRequest req, HttpServletResponse resp){
        //{1}获取分页参数对象
        PageParam param = PageParam.getPageParam(req);
        //{2}调用 service 获取PageBean
        PageBean<LodgingInfo> pBean = new PageBean<>();
        try {
            List<LodgingInfo> lodgingInfo = lodgerService.getAllLodgingInfo();
            if(lodgingInfo != null){
                pBean.setCode(0);
                pBean.setData(lodgingInfo);
            }
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
        //内部转发到 /WEB-INF/jsp/login.jsp
        return "forward:/WEB-INF/jsp/user/userIndex.jsp";
    }
    //业务处理情况(房间号为单位)
    public String viewBusiness(HttpServletRequest req, HttpServletResponse resp){
        return null;
    }

    //新增用户跳转
    public String viewEditUser(HttpServletRequest req, HttpServletResponse resp){
        return "forward:/WEB-INF/jsp/user/editUser.jsp";
    }

    //-----------------------------操作----------------------------------
//操作，删除，保存，-------------------------------->
    public String doSaveSelf(HttpServletRequest req, HttpServletResponse resp) {
        User user = getBean(req, User.class);
        try {
            //只允许密码，nickName,sex
            if(user.getPassword()!=null || user.getNickName()!=null || user.getSex()!=null){
                userService.updateUser(user);
                return "data:" +  sendSuccess();
            }else {
                throw new SQLException("参数传入有误");
            }
        } catch (SQLException | UpdateExceptioin e) {
            e.printStackTrace();
            return "data:" + sendError("failed", e.getMessage(), 500);
        }
    }

    public String updateRoomStatus(HttpServletRequest req, HttpServletResponse resp){
        Room room = getBean(req, Room.class);
        try {
            roomService.updateStatus(room, room.getRoomNo());
            return "data:" + sendSuccess();
        } catch (SQLException | UpdateExceptioin e) {
            e.printStackTrace();
            return "data:" + sendError("failed", e.getMessage(), 500);
        }
    }
}
