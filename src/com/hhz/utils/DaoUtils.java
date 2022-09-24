package com.hhz.utils;

import com.hhz.exception.CreationException;
import com.hhz.exception.UpdateExceptioin;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

//实现dao层的一些小功能
public class DaoUtils {

    //通过Object来实现要修改的参数
    public static List<Object> getField(Object obj) throws UpdateExceptioin {
        //反射调用get操作，但返回值不为空就加到string中
        StringBuffer buffer = new StringBuffer();
        StringBuffer buffer1 = new StringBuffer();
        StringBuffer buffer2 = new StringBuffer();
        List<Object> list = new ArrayList<>();
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field s : fields) {
                String getMethod = "get" + s.getName().substring(0, 1).toUpperCase() + s.getName().substring(1);
                Method method = clazz.getDeclaredMethod(getMethod, null);
                method.setAccessible(true);
                Object ans = method.invoke(obj, null);
                if (ans != null) {
                    buffer.append(s.getName()+"=?,");
                    buffer1.append(ans.toString() + ",");
                    buffer2.append("?,");
                }
            }
        }catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            throw new UpdateExceptioin("无此参数");
        }
        int index = buffer.toString().lastIndexOf(",");
        int index1 = buffer2.toString().lastIndexOf(",");
        list.add(buffer.toString().substring(0,index));
        list.add(buffer1.toString());
        list.add(buffer2.toString().substring(0,index1));
        return list;
    }
}
