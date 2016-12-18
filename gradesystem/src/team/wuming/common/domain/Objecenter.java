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
	private Date createtime;// 创建日期
	private int states; // '1' COMMENT '使用状态'
	private String remark;// 备注
	private String picture;// 图片目录位置
	private String zydm;// 专业代码
	private String termth;// 开课学期
	private String sthours;// 学习时数
	private String classhour;// 面授时数
	private String sbhour;// 实验时数
	private String lyid;// 分类号
	private String score;// 学分

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getTermth() {
		return termth;
	}

	public void setTermth(String termth) {
		this.termth = termth;
	}

	public String getSthours() {
		return sthours;
	}

	public void setSthours(String sthours) {
		this.sthours = sthours;
	}

	public String getClasshour() {
		return classhour;
	}

	public void setClasshour(String classhour) {
		this.classhour = classhour;
	}

	public String getSbhour() {
		return sbhour;
	}

	public void setSbhour(String sbhour) {
		this.sbhour = sbhour;
	}

	public String getLyid() {
		return lyid;
	}

	public void setLyid(String lyid) {
		this.lyid = lyid;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

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



	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	@Override
	public String toString() {
		return "Objecenter [visit_count=" + visit_count + ", title=" + title
				+ ", description=" + description + ", createtime=" + createtime
				+ ", states=" + states + ", remark=" + remark + ", picture="
				+ picture + ", zydm=" + zydm + ", termth=" + termth
				+ ", sthours=" + sthours + ", classhour=" + classhour
				+ ", sbhour=" + sbhour + ", lyid=" + lyid + ", score=" + score
				+ "]";
	}


}
