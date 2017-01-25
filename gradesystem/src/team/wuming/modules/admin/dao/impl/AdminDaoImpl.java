package team.wuming.modules.admin.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.spi.Bean;
import javax.management.RuntimeErrorException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import team.wuming.common.domain.Maijor;
import team.wuming.common.domain.Objcenter;
import team.wuming.common.domain.StudentGrade;
import team.wuming.common.domain.Xuexid;
import team.wuming.modules.admin.dao.AdminDao;
import team.wuming.modules.admin.domain.Admin;
import team.wuming.modules.experts.domain.Expert;
import team.wuming.modules.users.domain.User;
import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;

public class AdminDaoImpl implements AdminDao {
	private QueryRunner qr = new TxQueryRunner();


	/**
	 * 查询个人信息（个人中心)
	 */
	@Override
	public Admin findAdminMessage(String userId) {
		String sql = "select * from admins where admin_acount=?";
		try {
			return qr.query(sql, new BeanHandler<Admin>(Admin.class), userId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 更新管理员密码
	 */
	public void updateAdminPassword(String userId, String password) {
		String sql = "update admins set password=? where admin_acount=?";
		try {
			qr.update(sql, password, userId);

		} catch (SQLException e) {
			throw new RuntimeException();
		}

	}


	/**
	 * 用户登录
	 */
	public Admin findAdminByAdminId(Admin form) {
		String sql = "select admin_acount,password,name from admins where admin_acount=?";
		try {
			return qr.query(sql, new BeanHandler<Admin>(Admin.class),
					form.getAdmin_acount());
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	/**
	 * 把学生信息的Excel转化成List集合然后传入数据库中的users表中去
	 */
	@Override
	public void inputStudentMessage(List<User> userList) {
		String sql="insert into users(ksh,user_acount,password,nickname,xb,csrq,sfzh,"
				+ "zzmm,mz,yxdm,yxmc,zydm,zymc,xxdd,bh,cc,xz,xxxs,rxrq,dqszj,zczt,yjbyrq)"
				+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			for (User user : userList) {
				qr.update(sql, user.getKsh(),user.getUser_acount(),user.getPassword(),user.getNickname(),user.getXb(),user.getCsrq(),user.getSfzh(),
 user.getZzmm(),
						user.getMz(), user.getYxdm(), user.getYxmc(),
						user.getZydm(), user.getZymc(), user.getXxdd(),
						user.getBh(), user.getCc(),
						user.getXz(),user.getXxxs(),user.getRxrq(),user.getDqszj(),user.getZczt(),user.getYjbyrq());
			}
			} catch (SQLException e) {

			throw new RuntimeException(e.getMessage());
			}
	}

	/**
	 * 添加课程到课程表中
	 */
	@Override
	public void addObjcenter(Objcenter obj) {
		String sql = "insert into objcenter(title,description,states,remark,zydm,termth,sthours,classhour,sbhour,lyid,score) value(?,?,?,?,?,?,?,?,?,?,?)";
		try {
			qr.update(sql, obj.getTitle(),
					obj.getDescription(), obj.getStates(), obj.getRemark(),
					obj.getZydm(), obj.getTermth(), obj.getSthours(),
					obj.getClasshour(), obj.getSbhour(), obj.getLyid(),
					obj.getScore());
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}

	}

	/**
	 * 添加专业到专业表中（暂时不是使用，专业表用学生信息表自动构建）
	 */
	@Override
	public void addMaijor(Maijor maijor) {
		String sql = "insert into maijor value(?,?,?,?,?,?,?,?)";
		try {
			qr.update(sql, maijor.getZydm(), maijor.getZymc(),
					maijor.getXxxs(), maijor.getPycc(), maijor.getXznx(),
					maijor.getXz(), maijor.getBz(), maijor.getLyid());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * 教学点，暂时不使用（由学生信息表自动构建）
	 */
	@Override
	public void addXuexid(Xuexid xuexid) {
		String sql = "insert into xuexid value(?,?,?,?,?,?,?,?,?,?)";
		try {
			qr.update(sql, xuexid.getDepartid(), xuexid.getXxdd(),
					xuexid.getDizhi(), xuexid.getLianxiren(),
					xuexid.getTelephone(), xuexid.getEmial(), xuexid.getYtdw(),
					xuexid.getLeixing(), xuexid.getYongdate(), xuexid.getHuze());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

	}

	/**
	 * 查询专业（系统管理-添加课程）
	 */
	@Override
	public List<Maijor> findMaijor() {
		String sql = "select zydm,zymc from maijor";
		try {
			return qr.query(sql, new BeanListHandler<Maijor>(Maijor.class));
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}

	}

	/**
	 * 根据专业代码查询课程目录（系统管理-添加班级课程)
	 */
	@Override
	public List<Objcenter> findObjcenterByZydm(String zydm) {
		String sql = "select * from objcenter where zydm=?";
		try {
			return qr.query(sql,
					new BeanListHandler<Objcenter>(Objcenter.class), zydm);
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * 根据专业代码查询班级名称（系统管理-添加班级课程)
	 */
	@Override
	public List<Object> findClassByZydm(String zydm) {
		String sql = "select distinct bh from users where zydm=?";// 去掉重复的行
		try {
			return qr.query(sql, new ColumnListHandler(), zydm);
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}

	}

	/**
	 * 根据课程Id查询课程信息,用于添加班级课程时向studentgrade表插入课程信息
	 */
	@Override
	public Objcenter findObjcenterById(String visit_count) {
		String sql = "select visit_count,title,termth,sthours from objcenter where visit_count=?";
		try {
			return qr.query(sql, new BeanHandler<Objcenter>(Objcenter.class),
					visit_count);
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}

	}



	/**
	 * 根据班级名称，查询班级学生信息（用于添加班级课程，向studentgrade表格填入学生信息）
	 */
	@Override
	public List<User> findUserByClassName(String className) {
		String sql = "select user_acount,nickname,bh from users where bh=?";
		try {
			return qr.query(sql, new BeanListHandler<User>(User.class),
					className);
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}

	}

	/**
	 * 添加班级课程，向studentgrade表中插入基本信息（系统管理-添加班级课程）
	 */
	@Override
	public void addStuentGrade(List<StudentGrade> studentGradeList) {
		String sql = "insert into studentgrade(user_acount,nickname,visit_count,title,sthours,bh,termth,expacount) value(?,?,?,?,?,?,?,?)";

		try {
			for (StudentGrade studentGrade : studentGradeList) {
				qr.update(sql, studentGrade.getUser_acount(), studentGrade
						.getNickname(), studentGrade.getVisit_count(),
						studentGrade.getTitle(), studentGrade.getSthours(),
						studentGrade.getBh(), studentGrade.getTermth(),
						studentGrade.getExpert().getExpacount());

			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}

	}

	/**
	 * 保存图片路径（个人中心-个人基本信息）
	 */
	@Override
	public void savePhotoPath(String userId, String filePath) {
		String sql = "update admins set photo=? where admin_acount=?";
		try {
			qr.update(sql, filePath, userId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 根据教师编号，查询教师所教班级
	 */
	@Override
	public List<Object> searchClass(String id) {
		String sql = "select  DISTINCT bh from studentgrade where expacount=?";
		try {
			return qr.query(sql, new ColumnListHandler("bh"), id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 查找补考的班级(代老师管理成绩)
	 */
	@Override
	public List<Object> searchFailClass(String id) {
		String sql = "select DISTINCT bh from studentgrade where expacount=? and totalscores<60 and state>0";
		try {
			return qr.query(sql, new ColumnListHandler("bh"), id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 查询未审核的教师，（系统管理-教师审核）
	 */
	@Override
	public List<Expert> displayExpert() {
		String sql = "select expacount,name from experts where state=0";
		try {
			return qr.query(sql, new BeanListHandler<Expert>(Expert.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 教师审核，改变教师注册状态
	 */
	@Override
	public void changeExpertState(String expacount) {
		String sql = "update experts set state=1 where expacount=?";
		try {
			qr.update(sql, expacount);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 模糊查询班级名称-（学生信息管理-下载班级成绩登记表-展示班级全称）
	 */
	@Override
	public List<Object> searchClassByName(String bh) {
		String sql = "select DISTINCT bh from users where bh like ?";
		try {
			return qr.query(sql, new ColumnListHandler(), bh + "%");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据班级名称，下载班级成绩登记表
	 */
	@Override
	public List<User> findClassStudentByBh(String classId) {
		String sql = "select user_acount,nickname from users where bh=?";
		try {
			return qr
					.query(sql, new BeanListHandler<User>(User.class), classId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}



	/**
	 * 根据毕业班级名称查询学生学号集合，用于毕业班级成绩登记表下载，
	 */
	@Override
	public List<Object> serachUserIdByBh(String bh) {
		String sql = "select user_acount from users where bh=?";
		try {
			return qr.query(sql, new ColumnListHandler(), bh);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据学号查询所有的学生成绩(用于打印毕业班学生成绩)
	 */
	@Override
	public Map<String, ArrayList<StudentGrade>> searchGraduateGrade(
			List<String> userIdList) {
		String sql = "select user_acount,nickname,sthours,gradelei,totalscores,termth,title from studentgrade where user_acount=?";
		Map<String, ArrayList<StudentGrade>> mapList = new HashMap<String, ArrayList<StudentGrade>>();
		List<StudentGrade> gradeList = new ArrayList<StudentGrade>();
		try {
			for (String userId : userIdList) {
				gradeList = qr.query(sql, new BeanListHandler<StudentGrade>(
						StudentGrade.class), userId);
				mapList.put(userId, (ArrayList<StudentGrade>) gradeList);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return mapList;
	}

	/**
	 * 根据专业代码，当前年级（查询当前毕业班级的名称-毕业生成绩登记表下载功能）
	 */
	@Override
	public List<Object> searchGraduateClass(String zydm, String nj) {
		String sql = "select DISTINCT bh from users where zydm=? and dqszj=?";
		try {
			return qr.query(sql, new ColumnListHandler(), zydm, nj);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 查询学习地，（教学点补考学生名单）
	 */
	@Override
	public List<Xuexid> searchXuxid() {
		String sql = "select departid,xxdd from xuexid";
		try {
			return qr.query(sql, new BeanListHandler<Xuexid>(Xuexid.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据查询地点，获取该教学点的班级，然后确定该教学点的补考学生名单
	 */
	@Override
	public List<Object> searchClassByXuexid(String xuexiId) {
		String sql = "select DISTINCT bh from users where xxdd=?";
		try {
			return qr.query(sql, new ColumnListHandler(), xuexiId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据教学点查询补考学生名单
	 */
	@Override
	public List<StudentGrade> searchFailStudent(List<String> classnameList) {
		String sql = "select * from studentgrade where bh=? and totalscores < 60";
		List<StudentGrade> gradeList = new ArrayList<StudentGrade>();
		for (String string : classnameList) {
			try {
				gradeList.addAll(qr.query(sql,
						new BeanListHandler<StudentGrade>(StudentGrade.class),
						string));
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
		return gradeList;
	}

	/**
	 * 根据班别查询该班级的专业名称，学习形式（毕业生成绩登记表下载）
	 */
	public User serchUserByBh(String bh) {
		String sql = "select DISTINCT zymc,xxxs,cc,xz from users where bh=?";
		try {
			return qr.query(sql, new BeanHandler<User>(User.class), bh);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}



	/**
	 * 上传教学点信息，根据学生信息表
	 */
	@Override
	public void addXuexid() {
		String sql = "insert into xuexid(xxdd) select DISTINCT xxdd from users where xxdd not in(select xxdd from xuexid)";
		try {
			qr.update(sql);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 上传专业信息，根据学生信息表
	 */
	public void addMaijor() {
		String sql = "insert into maijor(zydm,zymc,xxxs,pycc,xz) select DISTINCT zydm,zymc,xxxs,cc,xz from users where zydm not in(select zydm from maijor )";
		try {
			qr.update(sql);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获取成绩未审核的班级跟课程信息（班级课程成绩审核）
	 */
	@Override
	public List<StudentGrade> displayNoAuditGrade() {
		String sql = "select DISTINCT visit_count,title,bh from  studentgrade  where state=1";
		try {
			return qr.query(sql, new BeanListHandler<StudentGrade>(
					StudentGrade.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 审核班级成绩，改变成绩状态
	 */
	@Override
	public void auditGrades(List<String[]> messageList,String shenhe) {
		String sql = "update studentgrade set state=2,shenhe=? where bh=? and visit_count=?";
		for (String[] strings : messageList) {
			try {
				qr.update(sql, shenhe, strings[0], strings[1]);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * 根据班级，课程编号查询学生成绩（班级成绩审核）
	 */
	@Override
	public List<StudentGrade> searchClassGrade(String bh, String visit_count) {
		String sql = "select user_acount,nickname,title,visit_count,psscore,syscore,ksscore,totalscores,termth,oper,bh,expacount from studentgrade where bh=? and visit_count=?";
		List<StudentGrade> studentGradeList = new ArrayList<StudentGrade>();
		try {
			List<Map<String, Object>> mapList = qr.query(sql,
					new MapListHandler(), bh, visit_count);
			for (Map<String, Object> map : mapList) {
				Expert expert = CommonUtils.toBean(map, Expert.class);
				StudentGrade studentGrade = CommonUtils.toBean(map,
						StudentGrade.class);
				studentGrade.setExpert(expert);
				studentGradeList.add(studentGrade);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return studentGradeList;
	}

	/**
	 * 用于课程页面，展示课程信息（系统管理-课程）
	 */
	@Override
	public List<Objcenter> findObjcenter() {
		String sql = "select * from objcenter";
		try {
			return qr.query(sql,
					new BeanListHandler<Objcenter>(Objcenter.class));
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
