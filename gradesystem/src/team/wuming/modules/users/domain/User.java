package team.wuming.modules.users.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Tony 学生类
 */
public class User implements Serializable {

	private String user_acount; // 帐号/学号
	private String password;// 密码
	private String realname;// 真实姓名
	private String nickname; // 昵称
	private String score; // 用户积分
	private String tlelphone; // 手机号
	private String qq;// QQ号
	private String email;// email
	private String weixin;// 微信号
	private int age;// 年龄
	private String city;// 城市
	private String occupation;// 职业
	private String education;// 学历
	private String level;// 会员级别
	private int scores;// 运费
	private int state;// 现状态(可能填0/1)
	private Date regist_time;// 注册时间
	private String usercode;// 用户类型编码
	private int marry;// 婚否
	private String hobby;// 爱好
	private String province;// 省
	private String address;// 通信地址
	private String postCode;// 邮编
	private String paymethod;// '在线支付' COMMENT '支付方法'
	private int user_type;// 用户类型1-操作和编辑（要批准）
	private int sex;// 性别码
	private Date csrq;// 出生年月
	private String bh;// 所在班别编号
	private String dqszj;// 年级
	private String ksh;// 考生号
	private String mz;// 民族
	private String zzmm;// 政治面貌
	private String photo;// 照片
	private String xxdd;// 学习地点
	private String sfzh;// 身份证号
	private String zydm;// 专业代码
	private String zymc;// 专业名称
	private String yxdm;// 院校代码
	private String yxmc;// 院校名称
	private String xxxs;// 学习形式
	private Date rxrq;// 入学日期
	private Date yjbyrq;// 预计毕业日期
	private String swzh;// 学位书号
	private String xb;// 性别
	private String cc;// 层次
	private String xz;// 学制
	private String zczt;// 注册状态

	public String getUser_acount() {
		return user_acount;
	}

	public void setUser_acount(String user_acount) {
		this.user_acount = user_acount;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getTlelphone() {
		return tlelphone;
	}

	public void setTlelphone(String tlelphone) {
		this.tlelphone = tlelphone;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public int getScores() {
		return scores;
	}

	public void setScores(int scores) {
		this.scores = scores;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Date getRegist_time() {
		return regist_time;
	}

	public void setRegist_time(Date regist_time) {
		this.regist_time = regist_time;
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public int getMarry() {
		return marry;
	}

	public void setMarry(int marry) {
		this.marry = marry;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getPaymethod() {
		return paymethod;
	}

	public void setPaymethod(String paymethod) {
		this.paymethod = paymethod;
	}

	public int getUser_type() {
		return user_type;
	}

	public void setUser_type(int user_type) {
		this.user_type = user_type;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public Date getCsrq() {
		return csrq;
	}

	public void setCsrq(Date csrq) {
		this.csrq = csrq;
	}

	public String getBh() {
		return bh;
	}

	public void setBh(String bh) {
		this.bh = bh;
	}

	public String getDqszj() {
		return dqszj;
	}

	public void setDqszj(String dqszj) {
		this.dqszj = dqszj;
	}

	public String getKsh() {
		return ksh;
	}

	public void setKsh(String ksh) {
		this.ksh = ksh;
	}

	public String getMz() {
		return mz;
	}

	public void setMz(String mz) {
		this.mz = mz;
	}

	public String getZzmm() {
		return zzmm;
	}

	public void setZzmm(String zzmm) {
		this.zzmm = zzmm;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getXxdd() {
		return xxdd;
	}

	public void setXxdd(String xxdd) {
		this.xxdd = xxdd;
	}

	public String getSfzh() {
		return sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String getZymc() {
		return zymc;
	}

	public void setZymc(String zymc) {
		this.zymc = zymc;
	}

	public String getYxdm() {
		return yxdm;
	}

	public void setYxdm(String yxdm) {
		this.yxdm = yxdm;
	}

	public String getYxmc() {
		return yxmc;
	}

	public void setYxmc(String yxmc) {
		this.yxmc = yxmc;
	}

	public String getXxxs() {
		return xxxs;
	}

	public void setXxxs(String xxxs) {
		this.xxxs = xxxs;
	}

	public Date getRxrq() {
		return rxrq;
	}

	public void setRxrq(Date rxrq) {
		this.rxrq = rxrq;
	}

	public Date getYjbyrq() {
		return yjbyrq;
	}

	public void setYjbyrq(Date yjbyrq) {
		this.yjbyrq = yjbyrq;
	}

	public String getSwzh() {
		return swzh;
	}

	public void setSwzh(String swzh) {
		this.swzh = swzh;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getXz() {
		return xz;
	}

	public void setXz(String xz) {
		this.xz = xz;
	}

	public String getZczt() {
		return zczt;
	}

	public void setZczt(String zczt) {
		this.zczt = zczt;
	}




}