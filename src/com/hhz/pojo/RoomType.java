package com.hhz.pojo;

import lombok.Data;

@Data
public class RoomType {
    private String id;
    private String typeName;
    private Float dayPrice;
    private Float hourPrice;
    private String updateDate;
    private String createDate;
}
