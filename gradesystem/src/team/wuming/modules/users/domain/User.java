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
	private String username;
	private String dzyj;
	private String lxdh;
	private String name;
	private int sex;// 性别
	private Date birthday;// 出生年月
	private String class_id;// 所在班级编号
	private String nj;// 年级
	private String zkzh;// 准考证号
	private String ksh;// 考生号
	private String mz;// 民族
	private String zzmm;// 政治面貌
	private Date byrq;// 已毕业日期
	private Date gzrq;// 工作日期
	private String byxx;// 毕业学校
	private String dw;// 工作单位
	private String zhiwu;// 职务
	private String photo;// 照片
	private String xxdd;// 学习地点
	private String wyyz;// 外语语种
	private String sfzh;// 身份证号
	private String xfzh;// 行政区号代码
	private String lqzy;// 专业代码
	private String tdzyh;// 投档志愿号
	private int rxcj;// 入学成绩
	private String studyform;// 学习形式
	private Date rxrq;// 入学日期
	private int ljsf;// 累积学分
	private Date bysj;// 毕业日期
	private String swzh;// 学位书号


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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDzyj() {
		return dzyj;
	}

	public void setDzyj(String dzyj) {
		this.dzyj = dzyj;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getClass_id() {
		return class_id;
	}

	public void setClass_id(String class_id) {
		this.class_id = class_id;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	public String getZkzh() {
		return zkzh;
	}

	public void setZkzh(String zkzh) {
		this.zkzh = zkzh;
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

	public Date getByrq() {
		return byrq;
	}

	public void setByrq(Date byrq) {
		this.byrq = byrq;
	}

	public Date getGzrq() {
		return gzrq;
	}

	public void setGzrq(Date gzrq) {
		this.gzrq = gzrq;
	}

	public String getByxx() {
		return byxx;
	}

	public void setByxx(String byxx) {
		this.byxx = byxx;
	}

	public String getDw() {
		return dw;
	}

	public void setDw(String dw) {
		this.dw = dw;
	}

	public String getZhiwu() {
		return zhiwu;
	}

	public void setZhiwu(String zhiwu) {
		this.zhiwu = zhiwu;
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

	public String getWyyz() {
		return wyyz;
	}

	public void setWyyz(String wyyz) {
		this.wyyz = wyyz;
	}

	public String getSfzh() {
		return sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	public String getXfzh() {
		return xfzh;
	}

	public void setXfzh(String xfzh) {
		this.xfzh = xfzh;
	}

	public String getLqzy() {
		return lqzy;
	}

	public void setLqzy(String lqzy) {
		this.lqzy = lqzy;
	}

	public String getTdzyh() {
		return tdzyh;
	}

	public void setTdzyh(String tdzyh) {
		this.tdzyh = tdzyh;
	}

	public int getRxcj() {
		return rxcj;
	}

	public void setRxcj(int rxcj) {
		this.rxcj = rxcj;
	}

	public String getStudyform() {
		return studyform;
	}

	public void setStudyform(String studyform) {
		this.studyform = studyform;
	}

	public Date getRxrq() {
		return rxrq;
	}

	public void setRxrq(Date rxrq) {
		this.rxrq = rxrq;
	}

	public int getLjsf() {
		return ljsf;
	}

	public void setLjsf(int ljsf) {
		this.ljsf = ljsf;
	}

	public Date getBysj() {
		return bysj;
	}

	public void setBysj(Date bysj) {
		this.bysj = bysj;
	}

	public String getSwzh() {
		return swzh;
	}

	public void setSwzh(String swzh) {
		this.swzh = swzh;
	}

	@Override
	public String toString() {
		return "User [user_acount=" + user_acount + ", password=" + password
				+ ", realname=" + realname + ", nickname=" + nickname
				+ ", score=" + score + ", tlelphone=" + tlelphone + ", qq="
				+ qq + ", email=" + email + ", weixin=" + weixin + ", age="
				+ age + ", city=" + city + ", occupation=" + occupation
				+ ", education=" + education + ", level=" + level + ", scores="
				+ scores + ", state=" + state + ", regist_time=" + regist_time
				+ ", usercode=" + usercode + ", marry=" + marry + ", hobby="
				+ hobby + ", province=" + province + ", address=" + address
				+ ", postCode=" + postCode + ", paymethod=" + paymethod
				+ ", user_type=" + user_type + ", username=" + username
				+ ", dzyj=" + dzyj + ", lxdh=" + lxdh + ", name=" + name
				+ ", sex=" + sex + ", birthday=" + birthday + ", class_id="
				+ class_id + ", nj=" + nj + ", zkzh=" + zkzh + ", ksh=" + ksh
				+ ", mz=" + mz + ", zzmm=" + zzmm + ", byrq=" + byrq
				+ ", gzrq=" + gzrq + ", byxx=" + byxx + ", dw=" + dw
				+ ", zhiwu=" + zhiwu + ", photo=" + photo + ", xxdd=" + xxdd
				+ ", wyyz=" + wyyz + ", sfzh=" + sfzh + ", xfzh=" + xfzh
				+ ", lqzy=" + lqzy + ", tdzyh=" + tdzyh + ", rxcj=" + rxcj
				+ ", studyform=" + studyform + ", rxrq=" + rxrq + ", ljsf="
				+ ljsf + ", bysj=" + bysj + ", swzh=" + swzh + "]";
	}

}
