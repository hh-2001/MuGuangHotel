package com.hhz.pojo;
//客户的个人信息
public class Lodger {
	private String id;
	private String idCard;//身份证信息，使用加密保存
	private String certificate;
	private String certificateNo;
	private String lodgerName;//客人名字
	private String sex;
	private String isRegister;
	private String phone;
	private String note;
	private String createDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getCertificate() {
		return certificate;
	}
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
	public String getCertificateNo() {
		return certificateNo;
	}
	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}
	public String getLodgerName() {
		return lodgerName;
	}
	public void setLodgerName(String lodgerName) {
		this.lodgerName = lodgerName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getIsRegister() {
		return isRegister;
	}
	public void setIsRegister(String isRegister) {
		this.isRegister = isRegister;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "Lodger [id=" + id + ", idCard=" + idCard + ", certificate=" + certificate + ", certificateNo="
				+ certificateNo + ", lodgerName=" + lodgerName + ", sex=" + sex + ", isRegister=" + isRegister
				+ ", phone=" + phone + ", note=" + note + ", createDate=" + createDate + "]";
	}

}
