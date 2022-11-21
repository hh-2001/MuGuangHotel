package com.hhz.test;

import com.hhz.component.IocContainer;

import com.hhz.pojo.PageBean;
import com.hhz.service.UserService;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class ServiceTest {

    UserService userService = (UserService) IocContainer.getBean("userService");

    @Test
    public void viewList() throws SQLException {
        PageBean pBean = null;
        try {
            pBean = new PageBean();
            pBean.setData(userService.viewList());
            System.out.println("data:" + pBean.toJSON());
        } catch (Exception e) {
            e.printStackTrace();
            //{ps}这是传给 LayUI 给它解析的。符合 LayUI 格式。
        }
    }

    @Test
    public void roomVo(){
        //{1}获取分页参数对象

    }
}
