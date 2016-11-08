package team.wuming.common.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.Document;

import team.wuming.common.dao.StudentGradeDao;
import team.wuming.common.dao.impl.StudentGradeDaoImpl;
import team.wuming.common.domain.Docourse;
import team.wuming.common.domain.PageBean;
import team.wuming.common.domain.StudentGrade;
import team.wuming.common.service.StudentGradeService;
import team.wuming.modules.experts.domain.Expert;

public class StudentGradeServiceImpl implements StudentGradeService {
	StudentGradeDao studentGradeDao = new StudentGradeDaoImpl();

	/**
	 * 学生按照自己学号查询自己的成绩
	 */
	@Override
	public PageBean<StudentGrade> queryUserGrade(int pc, int ps, String userId) {
		PageBean<StudentGrade> pb = new PageBean<StudentGrade>();
		pb.setPc(pc);
		pb.setPs(ps);
		List<StudentGrade> studentGrades = studentGradeDao.queryGradeByUserId(
				pc, ps, userId);
		int tr = studentGradeDao.queryTrByUserId(userId);
		pb.setTr(tr);
		Docourse docourse = new Docourse();
		Expert expert = new Expert();
		for (StudentGrade studentGrade : studentGrades) {

			docourse = studentGradeDao.queryDocourse(studentGrade
					.getDocourse().getVisit_count());
			studentGrade.setDocourse(docourse);
			expert = studentGradeDao.queryExpert(studentGrade
					.getDocourse().getExpacount());
			studentGrade.setExpert(expert);
		}
		pb.setBeanList(studentGrades);
		return pb;
	}

	@Override
	public List<StudentGrade> queryUserFail(String userId) {
		List<StudentGrade> studentGrades = studentGradeDao
				.queryFailGradeByUserId(userId);
		Docourse docourse = new Docourse();
		Expert expert = new Expert();
		for (StudentGrade studentGrade : studentGrades) {

			docourse = studentGradeDao.queryDocourse(studentGrade.getDocourse()
					.getVisit_count());
			studentGrade.setDocourse(docourse);
			expert = studentGradeDao.queryExpert(studentGrade.getDocourse()
					.getExpacount());
			studentGrade.setExpert(expert);
		}

		return studentGrades;
	}

	@Override
	public List<StudentGrade> findClassStudentByClass(String classId,
			String expacount) {
		List<StudentGrade> studentGrades = studentGradeDao
				.findClassStudentByClass(classId, expacount);
		Docourse docuourse = new Docourse();
		Expert expert = new Expert();
		for (StudentGrade studentGrade : studentGrades) {
			docuourse = studentGradeDao.queryDocourse(studentGrade
					.getDocourse().getVisit_count());
			studentGrade.setDocourse(docuourse);
			expert = studentGradeDao.queryExpert(studentGrade.getDocourse()
					.getExpacount());
			studentGrade.setExpert(expert);
		}
		return studentGrades;
	}

	@Override
	public List<Object> queryUserName(String classId) {
		return studentGradeDao.queryUserName(classId);
	}

	@Override
	public void saveClassStudentGrade(String[] userId,
 String[] psGrades,
			String[] ksGrades, String paecetime,
			String terminal) {

		List<StudentGrade> newStudentGrade = new ArrayList<StudentGrade>();

		for (int index = 0; index < userId.length; index++) {
			StudentGrade studentGrade = new StudentGrade();
			/*
			 * int grades = Integer.parseInt(psGrades[index])
			 * Integer.parseInt(paecetime) + Integer.parseInt(ksGrades[index])
			 * Integer.parseInt(terminal);
			 */
			int grades = (int) (Float.parseFloat(psGrades[index])
					* Float.parseFloat(paecetime) + Float
					.parseFloat(ksGrades[index]) * Float.parseFloat(terminal));

			studentGrade.setUser_acount(userId[index]);
			studentGrade.setPsgrade(psGrades[index]);
			studentGrade.setKsgrade(ksGrades[index]);
			studentGrade.setGrade(String.valueOf(grades));
			newStudentGrade.add(studentGrade);
		}
		studentGradeDao.saveStudentGrades(newStudentGrade);
	}

}
