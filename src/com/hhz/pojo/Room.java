package com.hhz.pojo;
//房间
public class Room {
	private String id;
	private String roomNo;
	private String typeId;
	private String storeyId;
	private String status;//根据0,1表示空闲还是有人
	private String updateDate;
	private String createDate;
	private String typeName;//房间类型
	private String floor;//房间楼层
	private String descript;//房间描述

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getStoreyId() {
		return storeyId;
	}
	public void setStoreyId(String storeyId) {
		this.storeyId = storeyId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		if(status.equals("0")) {
			status = "空闲";
		}else {
			status = "已入住";
		}
		this.status = status;
	}
	public void setStatus(String status,int flag) {
		this.status = status;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	@Override
	public String toString() {
		return "Room [id=" + id + ", roomNo=" + roomNo + ", typeId=" + typeId + ", storeyId=" + storeyId + ", status="
				+ status + ", updateDate=" + updateDate + ", createDate=" + createDate + ", typeName=" + typeName
				+ ", floor=" + floor + ", descript=" + descript + "]";
	}


}
