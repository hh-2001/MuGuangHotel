package com.hhz.pojo;
//登记入住信息
public class LodgingInfo {
	private String id;
	private String entryDate;
	private String leaveDate;
	private String status;//判断是什么状态，设置不同的Date
	private String days;//天数住户
	private String hours;//小时住户
	private String roomId;
	private String phone;
	private String deposit;//预定金(若有就是预定，房间挂起)
	private String reservationId;//预定Id(手机号尾号4位加日期)
	private String operatorId;//工作人员id
	private String registerId;//此人在系统的注册信息(联表lodger)
	private String registerName;
	private String createDate;

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getLeaveDate() {
		return leaveDate;
	}
	public void setLeaveDate(String leaveDate) {
		this.leaveDate = leaveDate;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getHours() {
		return hours;
	}
	public void setHours(String hours) {
		this.hours = hours;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public String getDeposit() {
		return deposit;
	}
	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}
	public String getReservationId() {
		return reservationId;
	}
	public void setReservationId(String reservationId) {
		this.reservationId = reservationId;
	}
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	public String getRegisterId() {
		return registerId;
	}
	public void setRegisterId(String registerId) {
		this.registerId = registerId;
	}
	public String getRegisterName() {
		return registerName;
	}
	public void setRegisterName(String registerName) {
		this.registerName = registerName;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "LodgingInfo [id=" + id + ", entryDate=" + entryDate + ", leaveDate=" + leaveDate + ", status=" + status
				+ ", days=" + days + ", hours=" + hours + ", roomId=" + roomId + ", phone=" + phone + ", deposit="
				+ deposit + ", reservationId=" + reservationId + ", operatorId=" + operatorId + ", registerId="
				+ registerId + ", registerName=" + registerName + ", createDate=" + createDate + "]";
	}

}
