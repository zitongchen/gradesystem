package team.wuming.modules.experts.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Tony 教师类
 */
/**
 * @author Tony
 * 
 */
public class Expert implements Serializable {
	private String expacount;// 专家帐号(主键)
	private String password;// 密码
	private String name;// 姓名
	private String nickname;// 昵称
	private String sex;// 性别
	private String description;// 简介
	private String picture;// 相片
	private String title;// 职称
	private String education;// 学历
	private String qq;// qq
	private String telephone;// 电话
	private String email;// email
	private String weixin;// 微信号
	private String city;// 城市
	private String state;// 状态
	private Date regist_time;// 注册时间
	private String expcode;// 专家类型编码
	private String departid;// 所属单位编号


	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getExpacount() {
		return expacount;
	}

	public void setExpacount(String expacount) {
		this.expacount = expacount;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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


	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getRegist_time() {
		return regist_time;
	}

	public void setRegist_time(Date regist_time) {
		this.regist_time = regist_time;
	}

	public String getDepartid() {
		return departid;
	}

	public void setDepartid(String departid) {
		this.departid = departid;
	}

	public String getExpcode() {
		return expcode;
	}

	public void setExpcode(String expcode) {
		this.expcode = expcode;
	}

	@Override
	public String toString() {
		return "Expert [expacount=" + expacount + ", password=" + password
				+ ", name=" + name + ", nickname=" + nickname + ", sex=" + sex
				+ ", description=" + description + ", picture=" + picture
				+ ", title=" + title + ", education=" + education + ", qq="
				+ qq + ", telephone=" + telephone + ", email=" + email
				+ ", weixin=" + weixin + ", city=" + city + ", state=" + state
				+ ", regist_time=" + regist_time + ", expcode=" + expcode
				+ ", departid=" + departid + "]";
	}



}
