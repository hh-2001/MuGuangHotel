package com.hhz.pojo;
//房间类型设置
public class RoomType {
	private String id;
	private String typeName;
	private Double dayPrice;
	private Double hourPrice;
	private Integer number;
	private String updateDate;
	private String createDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Double getDayPrice() {
		return dayPrice;
	}
	public void setDayPrice(Double dayPrice) {
		this.dayPrice = dayPrice;
	}
	public Double getHourPrice() {
		return hourPrice;
	}
	public void setHourPrice(Double hourPrice) {
		this.hourPrice = hourPrice;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
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
	@Override
	public String toString() {
		return "RoomType [id=" + id + ", typeName=" + typeName + ", dayPrice=" + dayPrice + ", hourPrice=" + hourPrice
				+ ", number=" + number + ", updateDate=" + updateDate + ", createDate=" + createDate + "]";
	}

}
