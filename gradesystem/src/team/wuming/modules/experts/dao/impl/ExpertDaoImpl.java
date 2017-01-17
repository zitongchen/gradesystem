package team.wuming.modules.experts.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
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
		String sql = "update experts descpription=?,education=?,qq=?,telephone=?,"
				+ "email=?,weixin=?,city=? where expacount=?";
		try {
			qr.update(sql, form.getDescription(), form.getEducation(),
					form.getQq(), form.getTelephone(), form.getEmail(),
					form.getWeixin(), form.getCity(), form.getExpacount());
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

	@Override
	public void registExpert(Expert expert) {
		String sql = "insert into experts(expacount,password,name,sex,description,"
				+ "picture,title,education,qq,telephone,email,weixin,city) value(?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			qr.update(sql, expert.getExpacount(), expert.getPassword(),
					expert.getName(), expert.getSex(),
					expert.getDescription(), expert.getPicture(),
					expert.getTitle(), expert.getEducation(), expert.getQq(),
					expert.getTelephone(), expert.getEmail(),
					expert.getWeixin(), expert.getCity());
		} catch (Exception e) {
			throw new RuntimeException();
		}

	}

	@Override
	public int quertExpertNumber() {
		String sql = "select count(*) from experts";
		try {
			Number obj = (Number) qr.query(sql, new ScalarHandler());
			return obj.intValue();
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	// 保存图片路径
	@Override
	public void updateExpertPhoto(String expacount, String photoPath) {
		String sql = "update experts set picture=? where expacount=?";
		try {
			qr.update(sql, photoPath, expacount);
		} catch (Exception e) {
			throw new RuntimeException();
		}

	}

	@Override
	public List<Expert> findExpertId() {
		String sql = "select expacount,name from experts";
		try {
			return qr.query(sql, new BeanListHandler<Expert>(Expert.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	// 改变学生某刻成绩的状态
	@Override
	public void changeGradeState(String bh, String kcId) {
		String sql = "update studentgrade set state=? where bh=? and visit_count=?";
		try {
			qr.update(sql, "1", bh, kcId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// 保存图片的途径
	@Override
	public void savePhotoPath(String userId, String filePath) {
		String sql = "update experts set photo=? where expacount=?";
		try {
			qr.update(sql, filePath, userId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}
