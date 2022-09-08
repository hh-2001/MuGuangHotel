package com.hhz.component;

import com.hhz.utils.DruidUtils;
import lombok.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//上传文件的配置类
@Data
public class Config {
    private static String uploadDir;
    private static String tempDir;
    private static Integer maxSize;
    private static String sysName;

    static {
        try{
            Connection conn = DruidUtils.getConnection();
            String sql = "select * from config";
            PreparedStatement pstat = conn.prepareStatement(sql);
            ResultSet rs = pstat.executeQuery();
            while(rs.next()){
                uploadDir = rs.getString("uploadDir");
                tempDir = rs.getString("tempDir");
                maxSize = rs.getInt("maxSize");
                sysName = rs.getString("sysName");
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Config加载不到");
        }
    }

    public static void main(String[] args) {
        System.out.println(Config.tempDir);
    }
}
