package team.wuming.common.service;

import java.util.List;

import team.wuming.common.domain.Classes;
import team.wuming.common.domain.PageBean;
import team.wuming.common.domain.StudentGrade;

public interface StudentGradeService {

	// 根据学号差学生的补考情况
	public List<StudentGrade> queryUserFail(String userId);

	public List<StudentGrade> findClassStudentByClass(String classId,
			String expacount);

	public List<Object> queryUserName(String classId);

	public void saveClassStudentGrade(String[] userId,
 String[] psGrades,
			String[] ksGrades, String paecetime,
			String terminal);

	public void createGradeSheet(String classId, String expacount);

	public Classes findClassNameByClassId(String classId);

	public List<StudentGrade> queryUserGrade(String userId);

	public List<Object> queryUserSex(String classId);
}
