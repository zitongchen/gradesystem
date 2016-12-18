package team.wuming.common.dao;

import java.util.List;

import team.wuming.common.domain.Classes;
import team.wuming.common.domain.Docourse;
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
	 * @param userId
	 * @return 通过学号，查询对应学号的的成绩信息总条数
	 */
	public int queryTrByUserId(String userId);

	/**
	 * @param visit_count
	 * @return 通过学生的成绩信息查询相关的课程
	 */
	public Docourse queryDocourse(String visit_count);

	/**
	 * @param expacount
	 * @return 通过学生的成绩信息查询相关老师
	 */
	public Expert queryExpert(String expacount);

	public List<StudentGrade> queryFailGradeByUserId(String userId);

	public List<StudentGrade> findClassStudentByClass(String classId,
			String expacount);

	public List<Object> queryUserName(String classId);

	public void saveStudentGrades(List<StudentGrade> newStudentGrade);



	public Classes findClassNameByClassId(String classId);

	public List<Object> querySexName(String classId);

}
