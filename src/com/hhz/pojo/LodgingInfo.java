package com.hhz.pojo;

import lombok.Data;

@Data
public class LodgingInfo {
    private String id;//主键
    private String entryDate;//入住时间
    private String leaveDate;//退房时间
    private String days;//日期
    private String roomId;//房间号，同时要改变room的status
    private Integer deposit;//押金
    private String reservationId;//预定号
    private String operatorId;//操作员的工号
    private String registerId;
    private String registerName;
    private String createDate;
}
