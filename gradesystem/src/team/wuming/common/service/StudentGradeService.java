package team.wuming.common.service;

import java.util.List;

import team.wuming.common.domain.PageBean;
import team.wuming.common.domain.StudentGrade;

public interface StudentGradeService {


	public List<StudentGrade> findClassStudentByClass(String classId,
			String expacount);



	public void saveClassStudentGrade(String[] userId,
 String[] psGrades,
			String[] syGrades, String[] ksGrades, String paecetime,
			String sytime,
 String terminal, String kc, String expacount);



	public List<StudentGrade> queryUserGrade(String userId);

	public List<StudentGrade> findFailStudent(String classId, String expacount);

	public void saveFailStudentGrade(String[] userId, String[] bkGrades,
			String kc, String expacount, String gradelei);

	public String findExpertName(String expacount);

	public List<StudentGrade> serchStudentGradeByName(String value);



	public void saveBkScore(String[] user_acount, String[] bkscore, String kc,
			String bh);

}
