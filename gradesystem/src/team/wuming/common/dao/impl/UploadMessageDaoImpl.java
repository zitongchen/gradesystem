package team.wuming.common.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import cn.itcast.jdbc.TxQueryRunner;
import team.wuming.common.dao.UploadMessageDao;

public class UploadMessageDaoImpl implements UploadMessageDao {
	private QueryRunner qr = new TxQueryRunner();
	@Override
	public void savePersonMessage(String type, String userId, String key,
			String val) {
		String sql = null;
		if (type.equals("student")) {
			sql = "update users set " + key + "=? where user_acount=?";
		} else if (type.equals("expert")) {
			sql = "update experts set " + key + "=? where expacount=?";
		} else if (type.equals("admin")) {
			sql = "update admins set " + key + "=? where admin_acount=?";
		}
		try {
			qr.update(sql, val, userId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

}
