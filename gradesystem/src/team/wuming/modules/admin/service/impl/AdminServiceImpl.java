package team.wuming.modules.admin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import team.wuming.common.dao.StudentGradeDao;
import team.wuming.common.dao.impl.StudentGradeDaoImpl;
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




	public void updateAdminPassword(String userId, String password) {
		adminDao.updateAdminPassword(userId, password);

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

	@Override
	public List<String> searchClass(String id) {
		List<String> classList = new ArrayList<String>();
		List<Object> classObject = adminDao.searchClass(id);
		for (Object object : classObject) {
			classList.add(String.valueOf(object));
		}
		return classList;
	}

	@Override
	public List<String> searchFailClass(String id) {
		List<String> classFailList = new ArrayList<String>();
		List<Object> classFaileObject = adminDao.searchFailClass(id);
		for (Object object : classFaileObject) {
			classFailList.add(String.valueOf(object));
		}
		return classFailList;
	}

	@Override
	public Admin findAdminMessage(String userId) {

		return adminDao.findAdminMessage(userId);
	}

	@Override
	public List<Expert> displayExert() {

		return adminDao.displayExpert();
	}

	@Override
	public void changeExpertState(String expacount) {
		adminDao.changeExpertState(expacount);
		
	}

	@Override
	public List<String> searchClassByName(String bh) {
		List<String> classLists = new ArrayList<String>();
		List<Object> classNameList = adminDao.searchClassByName(bh);
		for (Object object : classNameList) {
			classLists.add(String.valueOf(object));
		}
		return classLists;
	}

	// 根据班级名称查询班级学生
	@Override
	public List<User> findClassStudentByBh(String classId) {
		return adminDao.findClassStudentByBh(classId);
	}



	// 查询毕业班学生的全部成绩
	@Override
	public Map<String, ArrayList<StudentGrade>> SearchGraduateGrade(String bh) {
		List<String> userIdList = new ArrayList<String>();
		List<Object> userId = adminDao.serachUserIdByBh(bh);
		for (Object object : userId) {
			userIdList.add(String.valueOf(object));
		}
		Map<String, ArrayList<StudentGrade>> mapList = adminDao
				.searchGraduateGrade(userIdList);
		return mapList;
	}

	// 根据专业代码查询毕业班级
	@Override
	public List<String> searchGraduateClass(String zydm, String nj) {
		List<Object> graduateClass = adminDao.searchGraduateClass(zydm, nj);
		List<String> graduateClassList = new ArrayList<String>();
		for (Object object : graduateClass) {
			graduateClassList.add(String.valueOf(object));
		}
		return graduateClassList;
	}

	// 查询学习地点，用于查询补考名单功能
	@Override
	public List<Xuexid> searchXuxid() {
		return adminDao.searchXuxid();
	}

	// 用于根据地点查询补考学生名单
	@Override
	public List<StudentGrade> searchFailStudentByXuxid(String xuexiId) {
		List<String> classnameList = new ArrayList<String>();
		List<Object> classList = adminDao.searchClassByXuexid(xuexiId);
		for (Object object : classList) {
			classnameList.add(String.valueOf(object));
		}
		return adminDao.searchFailStudent(classnameList);
	}

	// 用于打印毕业班成绩功能
	@Override
	public User searchUserByBh(String bh) {
		return adminDao.serchUserByBh(bh);
	}

	// 上传学生信息的时候，根据信息表内容填充专业表
	@Override
	public void addMaijor() {
		adminDao.addMaijor();
	}

	// 上传学生信息的时候，根据信息表内容填充学习点表
	@Override
	public void addXuexid() {
		adminDao.addXuexid();

	}

	// 显示未审核的班级跟课程
	@Override
	public List<StudentGrade> displayNoAuditGrade() {
		return adminDao.displayNoAuditGrade();
	}

	@Override
	public void auditGrades(List<String[]> messageList, String shenhe) {
		adminDao.auditGrades(messageList, shenhe);

	}

	@Override
	public List<StudentGrade> searchClassGrade(String bh, String visit_count) {

		return adminDao.searchClassGrade(bh, visit_count);
	}

	@Override
	public List<Objcenter> findObjcenter() {

		return adminDao.findObjcenter();
	}
}
