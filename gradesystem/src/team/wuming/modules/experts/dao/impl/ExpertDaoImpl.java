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

	/**
	 * 根据教师编号获取教师对象
	 */
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

	/**
	 * 更新教师密码
	 */
	public void updateExpertPassword(Expert form) {
		String sql = "update experts set password=? where expacount=?";
		try {
			qr.update(sql, form.getPassword(), form.getExpacount());
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	/**
	 * 通过教师的编号查询教师所教的所有班级
	 */
	@Override
	public List<Object> findClassIdByExpert(String expacount) {
		String sql = "select  DISTINCT bh from users where user_acount in "
				+ "(select user_acount from studentgrade where expacount=?)";
		try {
			return qr.query(sql, new ColumnListHandler("bh"),
					expacount);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

}
