package team.wuming.modules.admin.domain;

import java.io.Serializable;

public class Admin implements Serializable {
	private String admin_acount;// 管理员账号（主键）
	private String password;// 管理员密码
	private String name;// 管理员姓名
	private String sex;// 管理员性别
	private String description;// 管理员描述
	private String photo;// 管理员照片
	private String title;// 管理员职位
	private String education;// 教育程度
	private String qq;// qq
	private String telephone;// 电话号码
	private String email;// 邮箱
	private String admincode;// 管理员类型（超级管理员/教学点管理员）
	private String city;// 地址
	private String regtime;// 注册时间
	private String weixin;// 微信号
	private String state;// 状态
	private String departid;

	public String getDepartid() {
		return departid;
	}

	public void setDepartid(String departid) {
		this.departid = departid;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}


	public String getRegtime() {
		return regtime;
	}

	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}

	public String getAdmin_acount() {
		return admin_acount;
	}

	public void setAdmin_acount(String admin_acount) {
		this.admin_acount = admin_acount;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdmincode() {
		return admincode;
	}

	public void setAdmincode(String admincode) {
		this.admincode = admincode;
	}

	@Override
	public String toString() {
		return "Admin [admin_acount=" + admin_acount + ", password=" + password
				+ ", name=" + name + ", sex=" + sex + ", description="
				+ description + ", photo=" + photo + ", title=" + title
				+ ", education=" + education + ", qq=" + qq + ", telephone="
				+ telephone + ", email=" + email + ", admincode=" + admincode
				+ ", city=" + city + ", regtime=" + regtime + ", weixin="
				+ weixin + ", state=" + state + ", departid=" + departid + "]";
	}

}
