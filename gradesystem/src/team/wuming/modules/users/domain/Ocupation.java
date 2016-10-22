package team.wuming.modules.users.domain;

import java.io.Serializable;

/**
 * @author Tony 职业
 */
public class Ocupation implements Serializable {
	private int id;// 职业编号
	private String ocupation;// 职业

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOcupation() {
		return ocupation;
	}

	public void setOcupation(String ocupation) {
		this.ocupation = ocupation;
	}

	@Override
	public String toString() {
		return "Ocupation [id=" + id + ", ocupation=" + ocupation + "]";
	}

}
