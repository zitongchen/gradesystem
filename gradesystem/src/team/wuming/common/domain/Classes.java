package team.wuming.common.domain;

import java.io.Serializable;

public class Classes implements Serializable {
	private String class_id;
	private String className;

	public String getClass_id() {
		return class_id;
	}

	public void setClass_id(String class_id) {
		this.class_id = class_id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Override
	public String toString() {
		return "Classes [class_id=" + class_id + ", className=" + className
				+ "]";
	}

}
