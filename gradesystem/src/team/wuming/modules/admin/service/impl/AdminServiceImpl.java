package team.wuming.modules.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import team.wuming.common.domain.Maijor;
import team.wuming.common.domain.Objcenter;
import team.wuming.common.domain.StudentGrade;
import team.wuming.common.domain.Xuexid;
import team.wuming.modules.admin.dao.AdminDao;
import team.wuming.modules.admin.dao.impl.AdminDaoImpl;
import team.wuming.modules.admin.domain.Admin;
import team.wuming.modules.admin.service.AdminService;
import team.wuming.modules.experts.domain.Expert;
import team.wuming.modules.users.domain.User;
import team.wuming.test.json.Student;

public class AdminServiceImpl implements AdminService {
	private AdminDao adminDao = new AdminDaoImpl();

	public Admin login(Admin form) throws Exception {
		Admin admin = adminDao.findAdminByAdminId(form);
		if (admin == null) {
			throw new Exception("用户名不存在！");
		}
		if (!admin.getPassword().equals(form.getPassword())) {
			throw new Exception("用户名或密码有误！");
		}
		return admin;
	}

	public void updateAdminMessage(Admin form) {
		adminDao.updateAdminMessageById(form);

	}


	public void updateAdminPassword(Admin form) {
		adminDao.updateAdminPassword(form);

	}

	@Override
	public void inputStudentMessage(List<User> userList) {
		adminDao.inputStudentMessage(userList);
	}

	@Override
	public void addObjcenter(Objcenter objecenter) {
		adminDao.addObjcenter(objecenter);

	}

	@Override
	public void addMaijor(Maijor maijor) {
		adminDao.addMaijor(maijor);

	}

	@Override
	public void addXuexid(Xuexid xuexid) {
		adminDao.addXuexid(xuexid);
	}

	@Override
	public List<Maijor> findMaijor() {

		return adminDao.findMaijor();

	}

	@Override
	public List<Objcenter> findObjcenterByZydm(String zydm) {

		return adminDao.findObjcenterByZydm(zydm);
	}

	@Override
	public List<Object> findClassByZydm(String zydm) {
		return adminDao.findClassByZydm(zydm);
	}

	@Override
	public void addStudentGrade(String className, String visit_count,
			String expacount) {
		// 查询课程的详细信息
		Objcenter objcenter = adminDao.findObjcenterById(visit_count);
		// 查询教师的信息
		// Expert expert = adminDao.findExpertById(expacount);
		Expert expert = new Expert();
		expert.setExpacount(expacount);
		List<User> userList = adminDao.findUserByClassName(className);
		List<StudentGrade> studentGradeList = new ArrayList<StudentGrade>();
		for (User user : userList) {
			StudentGrade student = new StudentGrade();
			student.setUser_acount(user.getUser_acount());
			student.setNickname(user.getNickname());
			student.setVisit_count(objcenter.getVisit_count());
			student.setTitle(objcenter.getTitle());
			student.setSthours(String.valueOf(objcenter.getSthours()));
			student.setBh(user.getBh());
			student.setTermth(objcenter.getTermth());
			student.setExpert(expert);
			studentGradeList.add(student);
		}
		adminDao.addStuentGrade(studentGradeList);
	}
}
