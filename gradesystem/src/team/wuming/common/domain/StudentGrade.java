package team.wuming.common.domain;

import java.io.Serializable;
import java.util.Date;

import team.wuming.modules.experts.domain.Expert;
import team.wuming.modules.users.domain.User;

public class StudentGrade implements Serializable {
	private String user_acount;// 学号
	private String nickname;// 姓名
	private String title;// 课程名称
	private String visit_count;// 课程代码
	private String sthours;// 学习时数
	private String gradelei;// 成绩类型
	private String psscore;// 平时成绩
	private String syscore;// 实验成绩
	private String ksscore;// 期末成绩
	private String totalscores;// 总评成绩
	private String bh;// 班级
	private String termth;// 开课学期
	private Expert expert;// 任课教师
	private String oper;// 操作员代码
	private String shenhe;// 审核人员代码
	private Date optime;// 操作时间
	private String remark;// 备注
	private String state;// 状态字段
	public String getUser_acount() {
		return user_acount;
	}
	public void setUser_acount(String user_acount) {
		this.user_acount = user_acount;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getVisit_count() {
		return visit_count;
	}

	public void setVisit_count(String visit_count) {
		this.visit_count = visit_count;
	}

	public String getSthours() {
		return sthours;
	}

	public void setSthours(String sthours) {
		this.sthours = sthours;
	}
	public String getGradelei() {
		return gradelei;
	}
	public void setGradelei(String gradelei) {
		this.gradelei = gradelei;
	}

	public String getPsscore() {
		return psscore;
	}

	public void setPsscore(String psscore) {
		this.psscore = psscore;
	}

	public String getSyscore() {
		return syscore;
	}

	public void setSyscore(String syscore) {
		this.syscore = syscore;
	}

	public String getKsscore() {
		return ksscore;
	}

	public void setKsscore(String ksscore) {
		this.ksscore = ksscore;
	}

	public String getTotalscores() {
		return totalscores;
	}

	public void setTotalscores(String totalscores) {
		this.totalscores = totalscores;
	}

	public String getBh() {
		return bh;
	}

	public void setBh(String bh) {
		this.bh = bh;
	}

	public String getTermth() {
		return termth;
	}

	public void setTermth(String termth) {
		this.termth = termth;
	}

	public Expert getExpert() {
		return expert;
	}

	public void setExpert(Expert expert) {
		this.expert = expert;
	}
	public String getOper() {
		return oper;
	}
	public void setOper(String oper) {
		this.oper = oper;
	}
	public String getShenhe() {
		return shenhe;
	}
	public void setShenhe(String shenhe) {
		this.shenhe = shenhe;
	}
	public Date getOptime() {
		return optime;
	}
	public void setOptime(Date optime) {
		this.optime = optime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "StudentGrade [user_acount=" + user_acount + ", nickname="
				+ nickname + ", title=" + title + ", visit_count="
				+ visit_count + ", sthours=" + sthours + ", gradelei="
				+ gradelei + ", psscore=" + psscore + ", syscore=" + syscore
				+ ", ksscore=" + ksscore + ", totalscores=" + totalscores
				+ ", bh=" + bh + ", termth=" + termth + ", expert=" + expert
				+ ", oper=" + oper + ", shenhe=" + shenhe + ", optime="
				+ optime + ", remark=" + remark + ", state=" + state + "]";
	}

}
