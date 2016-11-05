package team.wuming.common.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.enterprise.inject.spi.Bean;
import javax.management.Query;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;
import team.wuming.common.dao.StudentGradeDao;
import team.wuming.common.domain.Docourse;
import team.wuming.common.domain.PageBean;
import team.wuming.common.domain.StudentGrade;
import team.wuming.modules.experts.domain.Expert;

public class StudentGradeDaoImpl implements StudentGradeDao {

	private QueryRunner qr = new TxQueryRunner();

	@Override
	public List<StudentGrade> queryGradeByUserId(int pc, int ps,
			String userId) throws RuntimeException {

		List<StudentGrade> studentGrades = new ArrayList<StudentGrade>();
		String sql = "select * from studentgrade where user_acount=? limit ?,?";
		try {

			List<Map<String, Object>> maps = qr.query(sql,
					new MapListHandler(), userId, (pc - 1) * ps, ps);
			for (Map<String, Object> map : maps) {
				Docourse docourse = CommonUtils.toBean(map, Docourse.class);
				Expert expert = CommonUtils.toBean(map, Expert.class);
				StudentGrade studentGrade = CommonUtils.toBean(map,
						StudentGrade.class);
				studentGrade.setDocourse(docourse);
				studentGrade.setExpert(expert);
				studentGrades.add(studentGrade);
			}
			return studentGrades;

		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	@Override
	public int queryTrByUserId(String userId) {
		// TODO Auto-generated method stub
		String sql = "select count(*) from studentgrade where user_acount=?";
		try {
			return ((Number) qr.query(sql, new ScalarHandler(), userId))
					.intValue();

		} catch (Exception e) {
			throw new RuntimeException();
		}

	}

	@Override
	public Docourse queryDocourse(String visit_count) {
		String sql = "select * from docourse where visit_count=?";

		try {
			return qr.query(sql, new BeanHandler<Docourse>(Docourse.class),
					visit_count);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	@Override
	public Expert queryExpert(String expacount) {
		String sql = "select * from experts where expacount=?";

		try {
			return qr.query(sql, new BeanHandler<Expert>(Expert.class),
					expacount);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	@Override
	public List<StudentGrade> queryFailGradeByUserId(String userId) {
		String sql = "select * from studentgrade where user_acount=? and grade<60";
		List<StudentGrade> studentGrades = new ArrayList<StudentGrade>();
		try {
			List<Map<String,Object>> maps=qr.query(sql, new MapListHandler(),userId);
			for (Map<String, Object> map : maps) {
				Docourse docourse = CommonUtils.toBean(map, Docourse.class);
				Expert expert = CommonUtils.toBean(map, Expert.class);
				StudentGrade studentGrade = CommonUtils.toBean(map,
						StudentGrade.class);
				studentGrade.setDocourse(docourse);
				studentGrade.setExpert(expert);
				studentGrades.add(studentGrade);
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		return studentGrades;
	}

	@Override
	public List<StudentGrade> findClassStudentByClass(String classId) {
		String sql = " select * from studentgrade where user_acount in"
				+ " (select user_acount from users where class_id =?)";
		List<StudentGrade> studentGrades = new ArrayList<StudentGrade>();
		try {
			List<Map<String, Object>> maps = qr.query(sql,
					new MapListHandler(), classId);
			for (Map<String, Object> map : maps) {
				Docourse docourse = CommonUtils.toBean(map, Docourse.class);
				Expert expert = CommonUtils.toBean(map, Expert.class);
				StudentGrade studentGrade = CommonUtils.toBean(map,
						StudentGrade.class);
				studentGrade.setDocourse(docourse);
				studentGrade.setExpert(expert);
				studentGrades.add(studentGrade);
			}

		} catch (Exception e) {
			throw new RuntimeException();
		}
		return studentGrades;
	}

}
