package team.wuming.modules.users.domain;

import java.io.Serializable;

/**
 * @author Tony 省表
 */
public class Province implements Serializable {
	private int pronos;// 省编号
	private String province;// 省名称

	public int getPronos() {
		return pronos;
	}

	public void setPronos(int pronos) {
		this.pronos = pronos;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Override
	public String toString() {
		return "Province [pronos=" + pronos + ", province=" + province + "]";
	}

}
