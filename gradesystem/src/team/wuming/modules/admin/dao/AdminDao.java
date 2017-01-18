package team.wuming.modules.admin.dao;

import java.util.List;

import team.wuming.common.domain.Maijor;
import team.wuming.common.domain.Objcenter;
import team.wuming.common.domain.StudentGrade;
import team.wuming.common.domain.Xuexid;
import team.wuming.modules.admin.domain.Admin;
import team.wuming.modules.experts.domain.Expert;
import team.wuming.modules.users.domain.User;

public interface AdminDao {



	public void updateAdminPassword(String userId, String password);

	public Admin findAdminByAdminId(Admin form);

	public void inputStudentMessage(List<User> userList);

	public void addObjcenter(Objcenter objecenter);

	public void addMaijor(Maijor maijor);

	public void addXuexid(Xuexid xuexid);

	public List<Maijor> findMaijor();

	public List<Objcenter> findObjcenterByZydm(String zydm);

	public List<Object> findClassByZydm(String zydm);

	public Objcenter findObjcenterById(String visit_count);

	public Expert findExpertById(String expacount);

	public List<User> findUserByClassName(String className);

	public void addStuentGrade(List<StudentGrade> studentGradeList);

	public void savePhotoPath(String userId, String filePath);

	public List<Object> searchClass(String id);

	public List<Object> searchFailClass(String id);

	public Admin findAdminMessage(String userId);

	public List<Expert> displayExpert();

	public void changeExpertState(String expacount);

	public List<Object> searchClassByName(String bh);

	public List<User> findClassStudentByBh(String classId);

}