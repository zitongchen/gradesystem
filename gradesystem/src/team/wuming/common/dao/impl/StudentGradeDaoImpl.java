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
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;
import team.wuming.common.dao.StudentGradeDao;
import team.wuming.common.domain.Classes;
import team.wuming.common.domain.Docourse;
import team.wuming.common.domain.PageBean;
import team.wuming.common.domain.StudentGrade;
import team.wuming.modules.experts.domain.Expert;

public class StudentGradeDaoImpl implements StudentGradeDao {

	private QueryRunner qr = new TxQueryRunner();

	/**
	 * 根据学生的编号查询学生本人成绩
	 */
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
						StudentGrade.class); // 通过CommonUtils把Map集合的内容转化为响应的对象
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

	/**
	 * 根据课程编号查询课程表
	 */
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

	/**
	 * 根据教师编号查询教师
	 */
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

	/**
	 * 根据学号查询学生补考成绩信息
	 */
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

	/**
	 * 根据班级编号，教师编号查询教师所教的成绩信息
	 */
	@Override
	public List<StudentGrade> findClassStudentByClass(String classId,
			String expacount) {
		String sql = " select * from studentgrade where user_acount in"
				+ " (select user_acount from users where class_id =?) and expacount=? order by user_acount asc";
		List<StudentGrade> studentGrades = new ArrayList<StudentGrade>();
		try {
			List<Map<String, Object>> maps = qr.query(sql,
					new MapListHandler(), classId, expacount);
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

	/**
	 * 根据班级编号查询学生姓名
	 */
	@Override
	public List<Object> queryUserName(String classId) {
		String sql = "select realname from users where class_id=?";
		try {
			return qr.query(sql, new ColumnListHandler(), classId);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	/**
	 * 保存学生成绩
	 */
	@Override
	public void saveStudentGrades(List<StudentGrade> newStudentGrade) {

		String sql = "update studentgrade set psgrade=? ,ksgrade=?,grade=? where user_acount=?";
		try {
			for (StudentGrade studentGrade : newStudentGrade) {// 循环保存学生信息
				qr.update(sql, studentGrade.getPsgrade(),
						studentGrade.getKsgrade(), studentGrade.getGrade(),
						studentGrade.getUser_acount());
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	@Override
	public List<Classes> findClassNameByClassId(List<Object> classIdList) {
		String sql = "select * from classes where class_id=?";
		List<Classes> classList = new ArrayList<Classes>();
		try {
			for (Object classId : classIdList) {
				Classes classes = qr.query(sql, new BeanHandler<Classes>(
						Classes.class), String.valueOf(classId));
				classList.add(classes);
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return classList;
	}

	@Override
	public Classes findClassNameByClassId(String classId) {
		String sql = "select * from classes where class_id=?";
		try {
			return qr.query(sql, new BeanHandler<Classes>(Classes.class),
					classId);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

}
