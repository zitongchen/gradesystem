package team.wuming.modules.experts.service.impl;

import java.util.List;

import team.wuming.common.dao.StudentGradeDao;
import team.wuming.common.dao.impl.StudentGradeDaoImpl;
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
			throw new ExpertException("账号不存在！");
		}
		if (!expert.getPassword().equals(form.getPassword())) {
			throw new ExpertException("账号或密码有误！");
		}
		if ("0".equals(expert.getState())) {
			throw new ExpertException("你的账号还没有审核通过，请耐心等待管理员审核！");
		}
		return expert;
	}



	public Expert findExpertMessage(String expertId) {
		return expertDao.findExpertMessageById(expertId);
	}



	/**
	 * 查询教师所教班级序列
	 */
	@Override
	public List<Object> findClassNameByExpert(String expacount) {

		List<Object> classIdList = expertDao.findClassIdByExpert(expacount);
		return classIdList;

	}

	@Override
	public void registExpert(Expert expert) {
		expertDao.registExpert(expert);

	}

	@Override
	public int quertExpertNumber() {
		return expertDao.quertExpertNumber();
	}


	@Override
	public List<Expert> findExpertId() {
		return expertDao.findExpertId();

	}

	@Override
	public void changeGradeState(String bh, String kcId) {
		expertDao.changeGradeState(bh, kcId);

	}

	// 根据账号，更新密码
	@Override
	public void updateExpertPassword(String expacount, String password) {
		expertDao.updateExpertPassword(expacount, password);

	}

	// 查询补考的班级
	@Override
	public List<Object> findClassNameFail(String expacount) {
		return expertDao.findClassNameFail(expacount);
	}
}
