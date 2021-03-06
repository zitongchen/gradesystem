package team.wuming.common.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Tony 学科类
 */
public class Objcenter implements Serializable {
	private String visit_count;// 学科课程、知识专题号(主键)(int类型，string类型更大可能)
	private String title;// 学科、知识专题名称
	private String description;// 学科、知识专题
	private Date createtime;// 创建日期
	private String states; // '1' COMMENT '使用状态'
	private String remark;// 备注
	private String picture;// 图片目录位置
	private String zydm;// 专业代码
	private String termth;// 开课学期
	private Integer sthours;// 学习时数
	private Integer classhour;// 面授时数
	private Integer sbhour;// 实验时数
	private String lyid;// 分类号
	private Integer score;// 学分

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

	public String getLyid() {
		return lyid;
	}

	public void setLyid(String lyid) {
		this.lyid = lyid;
	}

	public int getSthours() {
		return sthours;
	}

	public void setSthours(int sthours) {
		this.sthours = sthours;
	}

	public int getClasshour() {
		return classhour;
	}

	public void setClasshour(int classhour) {
		this.classhour = classhour;
	}

	public int getSbhour() {
		return sbhour;
	}

	public void setSbhour(int sbhour) {
		this.sbhour = sbhour;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getVisit_count() {
		return visit_count;
	}

	public void setVisit_count(String visit_count) {
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



	public String getStates() {
		return states;
	}

	public void setStates(String states) {
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
