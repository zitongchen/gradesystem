package team.wuming.modules.experts.service.impl;

import java.util.List;

import team.wuming.common.dao.StudentGradeDao;
import team.wuming.common.dao.impl.StudentGradeDaoImpl;
import team.wuming.common.domain.Classes;
import team.wuming.modules.experts.dao.ExpertDao;
import team.wuming.modules.experts.dao.impl.ExpertDaoImpl;
import team.wuming.modules.experts.domain.Expert;
import team.wuming.modules.experts.service.ExpertException;
import team.wuming.modules.experts.service.ExpertService;
import team.wuming.modules.users.domain.User;

public class ExpertServiceImpl implements ExpertService {
	private ExpertDao expertDao = new ExpertDaoImpl();
	private StudentGradeDao studentGradeDao = new StudentGradeDaoImpl();

	/**
	 * 教师登陆验证
	 */
	public Expert login(Expert form) throws ExpertException {
		Expert expert = expertDao.findExertByExperId(form);
		if (expert == null) {
			throw new ExpertException("用户名不存在！");
		}
		if (!expert.getPassword().equals(form.getPassword())) {
			throw new ExpertException("用户名或密码有误！");
		}
		return expert;
	}

	public void updateExpertMessage(Expert form) {
		expertDao.updateExpertMessageById(form);

	}

	public Expert findExpertMessage(String expertId) {
		return expertDao.findExpertMessageById(expertId);
	}

	public void updateExpertPassword(Expert form) {
		expertDao.updateExpertPassword(form);

	}

	/**
	 * 查询教师所教班级序列
	 */
	@Override
	public List<Classes> findClassNameByExpert(String expacount) {

		List<Object> classIdList = expertDao.findClassIdByExpert(expacount);
		return studentGradeDao.findClassNameByClassId(classIdList);

	}
}
