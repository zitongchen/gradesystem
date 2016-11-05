package team.wuming.modules.experts.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import team.wuming.modules.experts.dao.ExpertDao;
import team.wuming.modules.experts.domain.Expert;
import cn.itcast.jdbc.TxQueryRunner;

public class ExpertDaoImpl implements ExpertDao {
	private QueryRunner qr = new TxQueryRunner();

	public Expert findExertByExperId(Expert form) {
		String sql = "select * from experts where expacount=?";
		try {
			return qr.query(sql, new BeanHandler<Expert>(Expert.class),
					form.getExpacount());
		} catch (SQLException e) {
			throw new RuntimeException();
		}

	}

	public void updateExpertMessageById(Expert form) {
		String sql = "update experts ";
		try {
			qr.update(sql);
		} catch (SQLException e) {
			throw new RuntimeException();
		}

	}

	public Expert findExpertMessageById(String expertId) {
		String sql = "select *　from experts where expacount=?";
		try {
			return qr.query(sql, new BeanHandler<Expert>(Expert.class),
					expertId);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public void updateExpertPassword(Expert form) {
		String sql = "update experts set password=? where expacount=?";
		try {
			qr.update(sql, form.getPassword(), form.getExpacount());
		} catch (SQLException e) {
			throw new RuntimeException();
		}

	}

	@Override
	public List<Object> findClassNameByExpert(String expacount) {
		String sql = "select  DISTINCT class_id from users where user_acount in "
				+ "(select user_acount from studentgrade where expacount=?)";
		try {
			return qr.query(sql, new ColumnListHandler("class_id"),
					expacount);
		} catch (Exception e) {
			throw new RuntimeException();
		}

	}

}
