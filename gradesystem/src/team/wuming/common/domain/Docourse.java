package team.wuming.common.domain;

import java.io.Serializable;

import team.wuming.modules.experts.domain.Expert;

/**
 * @author Tony 课程类
 */
public class Docourse implements Serializable {
	private String visit_count;// 课程编号（主键）
	private String title;// 课程名称
	private int courseHour;// 课时数
	private String expacount;// 授课教师账号(涉及到教师表)
	private String state;// 执行情况' /* 启动，停用*/

	// private Expert expert;

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

	public int getCourseHour() {
		return courseHour;
	}

	public void setCourseHour(int courseHour) {
		this.courseHour = courseHour;
	}



	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getExpacount() {
		return expacount;
	}

	public void setExpacount(String expacount) {
		this.expacount = expacount;
	}

	@Override
	public String toString() {
		return "Docurse [visit_count=" + visit_count + ", title=" + title
				+ ", courseHour=" + courseHour + ", expacount="
				+ ", state=" + state + "]";
	}

}
