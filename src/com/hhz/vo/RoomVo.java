package com.hhz.vo;

import lombok.Data;

//房间信息
@Data
public class RoomVo {
    private String roomNo;
    private String floor;//层数返回
    private String status;
    private String typeName;//房间类型
    //当前房间的各种价格
    private Float dayPrice;
    private Float hourPrice;
}
