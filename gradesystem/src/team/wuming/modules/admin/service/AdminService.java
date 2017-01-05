package team.wuming.modules.admin.service;

import java.util.List;

import team.wuming.common.domain.Maijor;
import team.wuming.common.domain.Objcenter;
import team.wuming.common.domain.Xuexid;
import team.wuming.modules.admin.domain.Admin;
import team.wuming.modules.users.domain.User;

public interface AdminService {
	public Admin login(Admin form) throws Exception;

	public void updateAdminMessage(Admin form);

	public void updateAdminPassword(Admin form);

	public void inputStudentMessage(List<User> userList);

	public void addObjcenter(Objcenter objecenter);

	public void addMaijor(Maijor maijor);

	public void addXuexid(Xuexid xuexid);

	public List<Maijor> findMaijor();

	public List<Objcenter> findObjcenterByZydm(String zydm);

	public List<Object> findClassByZydm(String zydm);

	public void addStudentGrade(String className, String visit_count,
			String expacount);
}
