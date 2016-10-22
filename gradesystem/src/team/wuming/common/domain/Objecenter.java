package team.wuming.common.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Tony 学科类
 */
public class Objecenter implements Serializable {
	private int visit_count;// 学科课程、知识专题号(主键)(int类型，string类型更大可能)
	private String title;// 学科、知识专题名称
	private String description;// 学科、知识专题
	private Date vreatetime;// 创建日期
	private int states; // '1' COMMENT '使用状态'
	private String remark;// 备注
	private String pictures;// 图片目录位置
	private int courseHour;// 课时数
	private int theoryHour;// 理论课
	private int labHour;// 实验课时
	private int doself;// '0' COMMENT '作业课时'
	private String zydm;// 专业代码
	private String courselei;// 课程类型(必修选修限选)
	private int scores;// 课程学分
	private int term;// NOT NULL DEFAULT '1' COMMENT '开设学期';
	private String precourse;// 先行课编号

	public int getVisit_count() {
		return visit_count;
	}

	public void setVisit_count(int visit_count) {
		this.visit_count = visit_count;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getVreatetime() {
		return vreatetime;
	}

	public void setVreatetime(Date vreatetime) {
		this.vreatetime = vreatetime;
	}

	public int getStates() {
		return states;
	}

	public void setStates(int states) {
		this.states = states;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPictures() {
		return pictures;
	}

	public void setPictures(String pictures) {
		this.pictures = pictures;
	}

	public int getCourseHour() {
		return courseHour;
	}

	public void setCourseHour(int courseHour) {
		this.courseHour = courseHour;
	}

	public int getTheoryHour() {
		return theoryHour;
	}

	public void setTheoryHour(int theoryHour) {
		this.theoryHour = theoryHour;
	}

	public int getLabHour() {
		return labHour;
	}

	public void setLabHour(int labHour) {
		this.labHour = labHour;
	}

	public int getDoself() {
		return doself;
	}

	public void setDoself(int doself) {
		this.doself = doself;
	}

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String getCourselei() {
		return courselei;
	}

	public void setCourselei(String courselei) {
		this.courselei = courselei;
	}

	public int getScores() {
		return scores;
	}

	public void setScores(int scores) {
		this.scores = scores;
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public String getPrecourse() {
		return precourse;
	}

	public void setPrecourse(String precourse) {
		this.precourse = precourse;
	}

	@Override
	public String toString() {
		return "Objecenter [visit_count=" + visit_count + ", title=" + title
				+ ", description=" + description + ", vreatetime=" + vreatetime
				+ ", states=" + states + ", remark=" + remark + ", pictures="
				+ pictures + ", courseHour=" + courseHour + ", theoryHour="
				+ theoryHour + ", labHour=" + labHour + ", doself=" + doself
				+ ", zydm=" + zydm + ", courselei=" + courselei + ", scores="
				+ scores + ", term=" + term + ", precourse=" + precourse + "]";
	}

}
