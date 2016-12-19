package team.wuming.common.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.Document;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import team.wuming.common.dao.StudentGradeDao;
import team.wuming.common.dao.impl.StudentGradeDaoImpl;
import team.wuming.common.domain.PageBean;
import team.wuming.common.domain.StudentGrade;
import team.wuming.common.service.StudentGradeService;
import team.wuming.modules.experts.domain.Expert;

public class StudentGradeServiceImpl implements StudentGradeService {
	StudentGradeDao studentGradeDao = new StudentGradeDaoImpl();

	/*
	 * 根据学生账号查询自己的成绩
	 */
	@Override
	public List<StudentGrade> queryUserGrade(String userId) {
		List<StudentGrade> studentGrades = studentGradeDao
				.queryGradeByUserId(userId);
		Expert expert = new Expert();
		return studentGrades;
	}

	/**
	 * 学生根据本人学号查询补考信息
	 */
	@Override
	public List<StudentGrade> queryUserFail(String userId) {
		List<StudentGrade> studentGrades = studentGradeDao
				.queryFailGradeByUserId(userId);

		Expert expert = new Expert();


		return studentGrades;
	}

	/**
	 * 根据班级编号，教师编号查询学生成绩信息
	 */
	@Override
	public List<StudentGrade> findClassStudentByClass(String classId,
			String expacount) {
		List<StudentGrade> studentGrades = studentGradeDao
				.findClassStudentByClass(classId, expacount);

		Expert expert = new Expert(); // 创建教师对象

		return studentGrades;
	}

	/**
	 * 根据班级编号查询学生姓名
	 */
	@Override
	public List<Object> queryUserName(String classId) {
		return studentGradeDao.queryUserName(classId);
	}

	/**
	 * 教师保存学生的成绩
	 */
	@Override
	public void saveClassStudentGrade(String[] userId, String[] psGrades,
			String[] ksGrades, String paecetime, String terminal) {
		List<StudentGrade> newStudentGrade = new ArrayList<StudentGrade>();// 创建一个保存学生成绩的List集合
		for (int index = 0; index < userId.length; index++) {
			StudentGrade studentGrade = new StudentGrade();
			int grades = (int) (Float.parseFloat(psGrades[index]) // 根据公式计算学生的总评成绩，把各项成绩的占比转化为小数进行计算
					* Float.parseFloat(paecetime) / 100 + Float
					.parseFloat(ksGrades[index])
					* Float.parseFloat(terminal)
					/ 100);
			studentGrade.setUser_acount(userId[index]);
			studentGrade.setPsscore(psGrades[index]);
			studentGrade.setKsscore(ksGrades[index]);
			studentGrade.setTotalscores(String.valueOf(grades));
			newStudentGrade.add(studentGrade);
		}
		studentGradeDao.saveStudentGrades(newStudentGrade);
	}

	/**
	 * 创建成绩表
	 */
	@Override
	public void createGradeSheet(String classId, String expacount) {
		XSSFWorkbook workBook = new XSSFWorkbook();
		XSSFSheet sheet = workBook.createSheet();

	}


	@Override
	public List<Object> queryUserSex(String classId) {
		return studentGradeDao.querySexName(classId);
	}

}
