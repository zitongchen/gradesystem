package team.wuming.common.service.impl;

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
}
