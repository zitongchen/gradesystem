package team.wuming.common.dao;

import java.util.List;

import team.wuming.common.domain.PageBean;
import team.wuming.common.domain.StudentGrade;
import team.wuming.modules.experts.domain.Expert;

public interface StudentGradeDao {

	/**
	 * @param pc
	 * @param ps
	 * @param userId
	 * @return 通过用户的学号，结合分页查询学生若干条数的成绩信息
	 */
	public List<StudentGrade> queryGradeByUserId(String userId);




	/**
	 * @param expacount
	 * @return 通过学生的成绩信息查询相关老师
	 */
	public Object queryExpert(String expacount);



	public List<StudentGrade> findClassStudentByClass(String classId,
			String expacount);


	public void saveStudentGrades(List<StudentGrade> newStudentGrade);


	public List<StudentGrade> findFailStudent(String classId, String expacount);

	public void saveFailStudentGrade(List<StudentGrade> studentList);

	public Object findExpertName(String expacount);

	public List<StudentGrade> searchStudentGradeByName(String value);



	public void saveBkScore(String[] user_acount, String[] bkscore, String kc,
			String bh);

}
