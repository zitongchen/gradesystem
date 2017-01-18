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
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;
import team.wuming.common.dao.StudentGradeDao;
import team.wuming.common.domain.PageBean;
import team.wuming.common.domain.StudentGrade;
import team.wuming.modules.experts.domain.Expert;

public class StudentGradeDaoImpl implements StudentGradeDao {

	private QueryRunner qr = new TxQueryRunner();

	/**
	 * 根据学生的编号查询学生本人成绩
	 */
	@Override
	public List<StudentGrade> queryGradeByUserId(String userId)
			throws RuntimeException {
		String sql = "select * from studentgrade where user_acount=?";
		List<StudentGrade> studentGrades = new ArrayList<StudentGrade>();
		try {
			List<Map<String, Object>> maps = qr.query(sql,
					new MapListHandler(), userId);
			for (Map<String, Object> map : maps) {

				Expert expert = CommonUtils.toBean(map, Expert.class);
				StudentGrade studentGrade = CommonUtils.toBean(map,
						StudentGrade.class);

				studentGrade.setExpert(expert);
				studentGrades.add(studentGrade);
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		return studentGrades;
	}


	/**
	 * 根据教师编号查询教师-用于学生查询成绩功能
	 */
	@Override
	public Object queryExpert(String expacount) {
		String sql = "select name from experts where expacount=?";
		try {
			return qr.query(sql, new ScalarHandler(), expacount);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}


	/**
	 * 根据班级编号，教师编号查询教师所教的成绩信息
	 */
	@Override
	public List<StudentGrade> findClassStudentByClass(String classId,
			String expacount) {
		String sql = " select * from studentgrade where bh =? and expacount=? order by user_acount asc";
		List<StudentGrade> studentGrades = new ArrayList<StudentGrade>();
		try {
			List<Map<String, Object>> maps = qr.query(sql,
					new MapListHandler(), classId, expacount);
			for (Map<String, Object> map : maps) {

				Expert expert = CommonUtils.toBean(map, Expert.class);
				StudentGrade studentGrade = CommonUtils.toBean(map,
						StudentGrade.class);

				studentGrade.setExpert(expert);
				studentGrades.add(studentGrade);
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return studentGrades;
	}
	/**
	 * 保存学生成绩
	 */
	@Override
	public void saveStudentGrades(List<StudentGrade> newStudentGrade) {

		String sql = "update studentgrade set psscore=? ,syscore=?,ksscore=?,totalscores=?,oper=? where user_acount=? and visit_count=?";
		try {
			for (StudentGrade studentGrade : newStudentGrade) {// 循环保存学生信息
				qr.update(sql, studentGrade.getPsscore(),
						studentGrade.getSyscore(),
						studentGrade.getKsscore(),
						studentGrade.getTotalscores(),
 studentGrade.getOper(),
						studentGrade.getUser_acount(),
						studentGrade.getVisit_count());
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	// 查询要补考的学生信息
	@Override
	public List<StudentGrade> findFailStudent(String classId, String expacount) {
		String sql = "select * from studentgrade where bh=? and expacount=? and totalscores < 60";
		List<StudentGrade> studentList = new ArrayList<StudentGrade>();
		try {
			List<Map<String, Object>> listMap = qr.query(sql,
					new MapListHandler(), classId, expacount);
			for (Map<String, Object> map : listMap) {
				Expert expert = CommonUtils.toBean(map, Expert.class);
				StudentGrade studentGrade = CommonUtils.toBean(map,
						StudentGrade.class);
				studentGrade.setExpert(expert);
				studentList.add(studentGrade);
			}
		} catch (SQLException e) {

			throw new RuntimeException(e);
		}
		return studentList;

	}

	// 保存补考同学的成绩
	@Override
	public void saveFailStudentGrade(List<StudentGrade> studentList) {
		String sql = "update studentgrade set totalscore=?,bkscore=?,oper=? where user_acount=? and visit_count=?";
		for (StudentGrade studentGrade : studentList) {

			try {
				qr.update(sql, studentGrade.getBkscore(),
						studentGrade.getBkscore(),
 studentGrade.getOper(),
						studentGrade.getUser_acount(),
						studentGrade.getVisit_count());
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

	}

	// 根据教师编号查询教师姓名：（管理员代教师管理成绩功能）
	@Override
	public Object findExpertName(String expacount) {
		String sql = "select name from experts where expacount=?";
		try {
			return qr.query(sql, new ScalarHandler(), expacount);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
