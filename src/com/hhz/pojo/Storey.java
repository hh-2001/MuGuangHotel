package com.hhz.pojo;

public class Storey {
	private String id;
	private Integer floor;
	private String descript;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getFloor() {
		return floor;
	}
	public void setFloor(Integer floor) {
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
		return "Storey [id=" + id + ", floor=" + floor + ", descript=" + descript + "]";
	}


}
