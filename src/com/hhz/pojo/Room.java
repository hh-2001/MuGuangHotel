package com.hhz.pojo;

import lombok.Data;

@Data
public class Room {
    private String id;
    private String roomNo;
    private String typeId;
    private String storeyId;
    private String status;
    private String updateDate;
    private String createDate;
}
