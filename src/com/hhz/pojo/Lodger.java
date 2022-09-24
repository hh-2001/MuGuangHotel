package com.hhz.pojo;

import lombok.Data;

@Data
public class Lodger {
    private String id;
    private String lodgingInfoId;//这是lodginginfo的外键 ,即lodginginfo的id
    private String certificate;//每个用户的凭证，只有当前系统使用的算法计算出来
    private String certificateNo;
    private String lodgerName;
    private String sex;
    private String isRegister;//有记录但是没注册过
    private String phone;
    private String note;//描述
    private String createDate;
}
