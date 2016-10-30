package team.wuming.common.service;

import team.wuming.common.domain.PageBean;
import team.wuming.common.domain.StudentGrade;

public interface StudentGradeService {
	public PageBean<StudentGrade> queryUserGrade(int pc, int ps, String userId);
}
