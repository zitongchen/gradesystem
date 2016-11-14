package team.wuming.common.domain;

import java.io.Serializable;
import java.util.Date;

import team.wuming.modules.experts.domain.Expert;
import team.wuming.modules.users.domain.User;

public class StudentGrade implements Serializable {
	private String user_acount;// 学号



	// private String visit_count;// 课程编号
	private Docourse docourse;// 外键
	private String psgrade;// 平时成绩



	private String ksgrade;// 考试成绩
	private String grade;// 成绩
	private String nf;// 年级
	private String gradelei;// 考核类型（正考补考重修换证免考）
	private int scores;// 积分
	// private String expacount;// 任教教师号
	private Expert expert;// 获取任课教师的信息
	private String oper;// 操作员代码
	private String shenhe;// 审核人员代码
	private Date optime;// 操作日期





	public String getUser_acount() {
		return user_acount;
	}

	public void setUser_acount(String user_acount) {
		this.user_acount = user_acount;
	}
	public String getPsgrade() {
		return psgrade;
	}

	public void setPsgrade(String psgrade) {
		this.psgrade = psgrade;
	}

	public String getKsgrade() {
		return ksgrade;
	}

	public void setKsgrade(String ksgrade) {
		this.ksgrade = ksgrade;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getNf() {
		return nf;
	}

	public void setNf(String nf) {
		this.nf = nf;
	}

	public String getGradelei() {
		return gradelei;
	}

	public void setGradelei(String gradelei) {
		this.gradelei = gradelei;
	}

	public int getScores() {
		return scores;
	}

	public void setScores(int scores) {
		this.scores = scores;
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

	@Override
	public String toString() {
		return "StudentGrade [user_acount=" + ", visit_count="
				+ ", psgrade=" + psgrade + ", ksgrade=" + ksgrade
				+ ", grade=" + grade + ", nf=" + nf + ", gradelei=" + gradelei
 + ", scores="
				+ scores + ", expacount=" + ", oper="
				+ oper + ", shenhe=" + shenhe + ", optime=" + optime + "]";
	}

	public Docourse getDocourse() {
		return docourse;
	}

	public void setDocourse(Docourse docourse) {
		this.docourse = docourse;
	}

	public Expert getExpert() {
		return expert;
	}

	public void setExpert(Expert expert) {
		this.expert = expert;
	}

}
