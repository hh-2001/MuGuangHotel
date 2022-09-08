package com.hhz.component;

import com.hhz.exception.CreationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class IocContainer {
    private static final Map<String,BeanInfo> definition = new LinkedHashMap<>();//存放注册表解析出的类
    private static final Map<String,Object> container = new HashMap<>();//存放bean
    //内部类
    static class BeanInfo {
        String id;
        String clsPath;
        public BeanInfo(String line){
            String[] data = line.split("=");
            this.id = data[0];
            this.clsPath = data[1];
        }
    }

    //拆解文本的信息，存放到definition中
    private static void loadConfig(BufferedReader reader) throws IOException {

        String line = null;
        while( (line=reader.readLine())!=null ){
            line = line.trim();
            if(line.length()==0) break;
            //{2}解析行数据, 生成 BeanInfo 对象.
            BeanInfo BI = new BeanInfo(line);
            //{3}存入 BeanInfo 到注册表。
            definition.put(BI.id, BI);
        }
    }

    //创建bean，并且存入对象容器
    private static Object instanceBean(String beanId) throws ClassNotFoundException, CreationException {

        Object obj = null;
        //{1}从注册表中获取 BeanInfo
        BeanInfo BI = definition.get( beanId );
        //{2}根据 info.clsPath 来获取字节码。
        Class<?> clazz = Class.forName(BI.clsPath);
        try {
            //{3}创建这个  bean 对象。
            obj = clazz.newInstance();
            print("创建 ["+ clazz.getSimpleName() +"] 完成." );
        } catch (InstantiationException |
                IllegalAccessException e) {
            e.printStackTrace();
            throw new CreationException("创建对象失败。");
        }
        //{4}存入 ioc 容器。
        container.put(beanId, obj);
        return obj;   //{5}返回对象。
    }

    private static void print(Object obj) {
        System.out.println("{IOC容器}"+ obj);
    }

    //判断container中的对象是否存在，不存在就到definition中寻找，若存在类模板就实例化成bean并存入container
    private static void createBeans() throws ClassNotFoundException, CreationException {
        //{1}从注册表中获取所有 keys(即: ID)
        Set<String> ids = definition.keySet();
        for (String id : ids) {
            //{2}迭代 ids 从容器中获取 Bean
            Object obj = container.get( id );
            //{3}没有则调用 instanceBean 创建 bean
            if(obj==null){
                print("容器中没有 ["+ id +"] 现在去创建 [BEAN] "+
                        ". [createBeans中]");
                instanceBean( id );
            }else{
                print("容器中有 ["+ id +"][createBeans中]");
            }
        }
    }

    //获取bean，外界通过beanId获取(如userDao)
    public static Object getBean(String id) throws CreationException {

        print("getBean:"+ id);
        Object obj = null;
        //{1}从容器中取 bean 对象
        //   如: [UserServiceImpl, UserDaoImpl]
        obj = container.get(id);
        //{2}如果没有，再从注册表中获取 BeanInfo 的信息。
        BeanInfo BI = null;
        if( obj==null ){
            print("容器中没有 ["+ id +"] 去注册表中查找注册信息 [getBean中].");
            BI = definition.get(id);
            //{3}如果没有注册 BeanInfo, 抛出异常。
            if( BI==null ){
                throw new CreationException(
                        "没有此Bean注册信息.");
            }
            //{4}存在注册信息则, 调用 instanceBean 创建 Bean 对象。
            try {
                print("注册表中已注册, 去创建 ["+ BI.clsPath +"] [getBean中].");
                obj = instanceBean( id );
            } catch (ClassNotFoundException e) {
                throw new CreationException(
                        "找不到此类的字节码.");
            }
        }
        //{5}返回创建的 Bean 对象。
        return obj;
    }

    static {
        //{1}获取类装载器。
        ClassLoader loader = IocContainer.class.getClassLoader();
        //{2}获取 bean.txt 的输入流。
        InputStream is = loader.getResourceAsStream("beans.txt");
        //{3}创建一个 BufferedReader
        BufferedReader BR = null;
        try {
            BR = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            System.out.println(
                    "+-----------------IOC初始化---------"+
                            "--------+" );
            //{4}调用 loadConfig 加载 bean 配置
            loadConfig( BR );
            //{5}调用 createBeans 创建配置清单的 bean
            createBeans();
            //{6}关闭流 br.close();
            BR.close();
            System.out.println(
                    "+-----------------IOC初始化---------"+
                            "--------+" );
        } catch (IOException | ClassNotFoundException | CreationException e) {
            e.printStackTrace();
        }
    }

}
