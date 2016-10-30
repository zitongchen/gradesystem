package team.wuming.common.dao;

import team.wuming.common.domain.StudentGrade;
import team.wuming.test.page.PageBean;

public interface StudentGradeDao {
	public PageBean<StudentGrade> queryGradeByUserId(int pc, int ps,
			String userId);
}
