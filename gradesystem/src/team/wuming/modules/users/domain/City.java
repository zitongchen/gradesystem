package team.wuming.modules.users.domain;

import java.io.Serializable;

public class City implements Serializable {
	private int id;// 市编号
	// private int pronos;// 省的编号
	private String city;// 市
	private Province province;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", pronos=" + ", city=" + city + "]";
	}

}
