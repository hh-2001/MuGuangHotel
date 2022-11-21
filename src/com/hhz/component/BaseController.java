package com.hhz.component;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hhz.exception.CreateationException;
import com.hhz.exception.ResultInvaildException;
import org.omg.IOP.ServiceContext;

import com.alibaba.fastjson.JSONObject;


public abstract class BaseController
        extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req,
                           HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        //{1}获取 URI 地址。---> /MyWeb/User/login
        String uri = req.getRequestURI();
        //{2}获取 login 字符串。[login]
        String action = getAction(uri);
        String ret = null;
        String error = null;
        try {
            //{1}执行分发处理, 获取控制器返回结果。
            ret = dispatcher(action,req,resp);
            //{2}判断有没有返回值。
            if( ret!=null ){
                parseResult(ret, req, resp);
            }else{
                //---[先待定]---
            }
        } catch (NoSuchMethodException e) {
            //{1}回显 404 到页面..
            e.printStackTrace();
            error = "["+ uri + "] 此资源不存在。";
            resp.sendError(404, error);
        } catch(InvocationTargetException e){
            //{2}回显到页面..
            Throwable cause = e.getCause(); //获取异常的根源。
            cause.printStackTrace();
            resp.sendError(500, cause.toString());
        } catch (ResultInvaildException e) {
            e.printStackTrace();
            resp.sendError(500, e.toString());
        }
    }

    //{ps}获取当前对象的类名:
    //如是: UserController ---->UserController
    //如是: AdminController---->AdminController
    String clsName = this.getClass().getSimpleName();
    protected void print(Object obj){
        System.out.printf("{%s}%s\n", clsName, obj);
    }

    //	protected void sendFile(String fileName, HttpServletRequest req, HttpServletResponse resp) throws IOException {
//		ServletContext servletContext = this.getServletContext();
//		InputStream is = servletContext.getResourceAsStream(fileName);
//
//		String mimeType = servletContext.getMimeType(fileName);
//		resp.setHeader("content-type", mimeType);
//		resp.setHeader("content-disposition", "attachment;filename"+fileName);
//		char[] buffer = new char[8192];
//		int length = 0;
//		while((length = is.read(buffer)) > 0) {
//			System.out.println(length);
//			resp.getWriter().write(buffer, 0, length);
//		}
//		is.close();
//		out.close();
//	}
//
    private String dispatcher(
            String action, HttpServletRequest req,
            HttpServletResponse resp)
            throws NoSuchMethodException,
            InvocationTargetException {
        //{1}获取当前对象的字节码对象。
        //可能: UserController.class, AdminController.class
        Class clazz = this.getClass();
        //{2}根据 action 获取对应的方法。
        //   参数类型清单是固定格式: (req, resp)
        //   你在子类中定义必须按照这个格式的声明方法。
        Method mtd = null;
        mtd = clazz.getDeclaredMethod( action,
                HttpServletRequest.class,
                HttpServletResponse.class );
        mtd.setAccessible( true );
        //{3}调用方法。
        //这个 this 是谁 ? UserController, AdminController...
        String ret = null;
        try {
            ret = (String)mtd.invoke( this, req, resp );
        } catch ( IllegalAccessException |
                IllegalArgumentException e ) {
            //{ps}自己编码能控制异常，不抛出去。
            e.printStackTrace();
        }
        return ret;
    }

    private String getAction(String uri) {
        int pos = uri.lastIndexOf("/");
        if(pos!=-1){
            return uri.substring(pos+1, uri.length());
        }
        return "";
    }

    //result可能为以下的值:
    //[1] "redirect:/Admin/index"
    //[2] "forward:/Admin/edit"
    //[3] "data:你好，同学."
    private void parseResult( String result,
                              HttpServletRequest req, HttpServletResponse resp)
            throws ResultInvaildException,
            NoSuchMethodException,
            InvocationTargetException {
        String regex = "([^:]+):(.+)";
        Pattern cmp = Pattern.compile(regex);
        Matcher mat = cmp.matcher(result);
        boolean isMatch = mat.matches();
        if( !isMatch ){
            String msg = result +"是一个无效的返回值。";
            throw new ResultInvaildException( msg );
        }
        respDispatcher( mat.group(1),
                mat.group(2), req, resp );
    }


    //{3}中游方法
    private void respDispatcher(
            String mtdName, String action,
            HttpServletRequest req, HttpServletResponse resp )
            throws NoSuchMethodException,
            InvocationTargetException
    {
        //return "rediret:/xxxx";
        //{1}获取到 BaseController 的字节码【父类】
        Class clazz = this.getClass().getSuperclass();
        Method method = clazz.getDeclaredMethod(
                mtdName, String.class,
                HttpServletRequest.class,
                HttpServletResponse.class );
        //obj: this (当前对象UserController)
        try {
            method.invoke( this, action, req, resp );
        } catch (IllegalAccessException |
                IllegalArgumentException e) {
            e.printStackTrace();   //选择性忽略掉。。
        }
    }

    //{4}最下游方法.
    //A. forward 方法  [内部转发方法]
    protected void forward( String path,
                            HttpServletRequest req, HttpServletResponse resp )
            throws IOException, ServletException {
        req.getRequestDispatcher(path)
                .forward(req, resp);
    }

    //B. redirect 方法
    protected void redirect( String path,
                             HttpServletRequest req, HttpServletResponse resp )
            throws IOException, ServletException {
        String ctxPath = req.getContextPath();
        resp.sendRedirect( ctxPath + path );
    }

    //C. data 方法
    protected void data( String content,
                         HttpServletRequest req, HttpServletResponse resp )
            throws IOException, ServletException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().write( content );
    }

    protected <T> T getBean(
            HttpServletRequest req, Class<T> clazz ) {
        //{1}获取所有参数【key-val】。
        Map<String, String[]> map = req.getParameterMap();
        Set<String> fldNames = map.keySet();
        T t = null;
        try {
            t = clazz.newInstance();
        } catch (InstantiationException |
                IllegalAccessException e1) {
            e1.printStackTrace();
            throw new CreateationException("创建 "+
                    clazz.getSimpleName() +" 对象失败。");
        }
        for (String fName : fldNames) {
            Field F = null;
            String mtdName = "";
            try {
                F = clazz.getDeclaredField( fName );
                //把 username ---> setUsername
                mtdName = getMtdName( fName );
                Method M = clazz.getDeclaredMethod(
                        mtdName, F.getType());
                //{2}获取参数数组第 0 号元素。
                String _val = map.get(fName)[0];
                //{3}调用 user.setUsername();
                M.invoke( t, getVal(F.getType(), _val) );
            } catch (NoSuchFieldException |
                    NoSuchMethodException e ) {
                //{ps}没有方法没有必要处理。
                //e.printStackTrace();
                print("没有这个字段:"+ fName);
            } catch( IllegalArgumentException |
                    IllegalAccessException |
                    InvocationTargetException e){
                e.printStackTrace();
                throw new RuntimeException("字段"+ fName +"赋值失败。");
            }
        }
        return t;
    }

    private Object getVal(Class type, String _val) {
        if( type==String.class ){
            return _val;
        }else if(type==int.class || type==Integer.class){
            return Integer.valueOf(_val);
        }else if(type==double.class || type==Double.class){
            return Double.valueOf(_val);
        }
        return null;
    }

    private String getMtdName(String fName){
        String first = fName.substring(0,1);
        first = first.toUpperCase();
        return "set"+ first +
                fName.substring(1, fName.length());
    }

    //这三个方法用来传递 json 数据给前端
    //sendError 方法
    protected String sendError( String result , String cause , int status ) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put( "result", result );
        jsonObj.put( "cause", cause );
        jsonObj.put("status", status);
        return jsonObj.toString();
    }

    protected String sendData(Object obj) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("result", "success");
        jsonObj.put("data", obj);
        jsonObj.put("status", 200);
        return jsonObj.toString();
    }

    protected String sendSuccess(){
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("result", "success");
        jsonObj.put("status", 200);
        return jsonObj.toString();
    }

    protected String pageError(Exception e) {
        //LayUI 需要这些。。
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("code", -1);
        jsonObj.put("msg", e.getMessage());
        jsonObj.put("count", 0);
        return jsonObj.toString();
    }

    protected String  sendURLErr(Exception e) {
        String msg = e.getMessage();
        try {
            msg = URLEncoder.encode(msg,"UTF-8");
        } catch (IOException el) {
            el.printStackTrace();
            msg="";
        }
        return "?result=failed&msg="+msg;
    }

}







