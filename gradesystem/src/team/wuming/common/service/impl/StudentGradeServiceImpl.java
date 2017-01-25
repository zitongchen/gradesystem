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
		for (StudentGrade studentGrade : studentGrades) {
			String name = String.valueOf(studentGradeDao
					.queryExpert(studentGrade.getExpert().getExpacount()));
			studentGrade.getExpert().setName(name);
		}
		return studentGrades;
	}

	// 根据学生名字查询成绩
	@Override
	public List<StudentGrade> serchStudentGradeByName(String value) {
		List<StudentGrade> studentGrades = studentGradeDao
				.searchStudentGradeByName(value);
		for (StudentGrade studentGrade : studentGrades) {
			String name = String.valueOf(studentGradeDao
					.queryExpert(studentGrade.getExpert().getExpacount()));
			studentGrade.getExpert().setName(name);
		}
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
		return studentGrades;
	}


	/**
	 * 教师保存学生的成绩
	 */
	@Override
	public void saveClassStudentGrade(String[] userId, String[] psGrades,
			String[] syGrades, String[] ksGrades, String paecetime,
			String sytime, String terminal, String kc, String expacount) {
		List<StudentGrade> newStudentGrade = new ArrayList<StudentGrade>();// 创建一个保存学生成绩的List集合
		Float ps = Float.parseFloat(paecetime) / 100;
		Float sy = Float.parseFloat(sytime) / 100;
		Float qm = Float.parseFloat(terminal) / 100;
		Float psScore;
		Float syScore;
		Float ksScore;
		Float grades = (float) 0;
		for (int index = 0; index < userId.length; index++) {
			StudentGrade studentGrade = new StudentGrade();
			if (psGrades[index] != "") {
				psScore = Float.parseFloat(psGrades[index]);
				grades = psScore * ps;
			} else {
				psGrades[index] = null;
			}
			if (syGrades[index] != "") {
				syScore = Float.parseFloat(syGrades[index]);
				grades += syScore * sy;
			} else {
				syGrades[index] = null;
			}
			if (ksGrades[index] != "") {
				ksScore = Float.parseFloat(ksGrades[index]);
				grades += ksScore * qm;
			} else {
				ksGrades[index] = null;
			}
			studentGrade.setUser_acount(userId[index]);
			studentGrade.setPsscore(psGrades[index]);
			studentGrade.setSyscore(syGrades[index]);
			studentGrade.setKsscore(ksGrades[index]);
			studentGrade.setTotalscores(String.valueOf(grades));
			studentGrade.setVisit_count(kc);
			studentGrade.setOper(expacount);
			newStudentGrade.add(studentGrade);
		}

		studentGradeDao.saveStudentGrades(newStudentGrade);

	}

	@Override
	public List<StudentGrade> findFailStudent(String classId, String expacount) {
		return studentGradeDao.findFailStudent(classId, expacount);
	}

	// 保存补考成绩
	@Override
	public void saveFailStudentGrade(String[] userId, String[] bkGrades,
			String kc, String expacount, String gradelei) {
		List<StudentGrade> studentList=new ArrayList<StudentGrade>();
		for(int i=0;i<userId.length;i++){
			StudentGrade stuGrade=new StudentGrade();
			stuGrade.setBkscore(bkGrades[i]);
			stuGrade.setVisit_count(kc);
			stuGrade.setUser_acount(userId[i]);
			stuGrade.setOper(expacount);
			stuGrade.setGradelei(gradelei);
			studentList.add(stuGrade);
		}
		studentGradeDao.saveFailStudentGrade(studentList);
		
	}

	// 根据教师编号查询教师姓名
	@Override
	public String findExpertName(String expacount) {
		Object name = studentGradeDao.findExpertName(expacount);
		return String.valueOf(name);
	}

	@Override
	public void saveBkScore(String[] user_acount, String[] bkscore, String kc,
			String bh) {
		studentGradeDao.saveBkScore(user_acount, bkscore, kc, bh);
	}


}
