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
			String terminal);

	public void createGradeSheet(String classId, String expacount);



	public List<StudentGrade> queryUserGrade(String userId);

}
