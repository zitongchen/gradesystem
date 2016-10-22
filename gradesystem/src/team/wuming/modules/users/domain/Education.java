package team.wuming.modules.users.domain;

import java.io.Serializable;

public class Education implements Serializable {
	private int id; // 教育程度编号
	private String education;//受教育

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	@Override
	public String toString() {
		return "Education [id=" + id + ", education=" + education + "]";
	}

}
