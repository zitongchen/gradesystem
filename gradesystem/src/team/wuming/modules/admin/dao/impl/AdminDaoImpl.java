package team.wuming.modules.admin.dao.impl;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import team.wuming.common.domain.Maijor;
import team.wuming.common.domain.Objcenter;
import team.wuming.common.domain.StudentGrade;
import team.wuming.common.domain.Xuexid;
import team.wuming.modules.admin.dao.AdminDao;
import team.wuming.modules.admin.domain.Admin;
import team.wuming.modules.experts.domain.Expert;
import team.wuming.modules.users.domain.User;
import cn.itcast.jdbc.TxQueryRunner;

public class AdminDaoImpl implements AdminDao {
	private QueryRunner qr = new TxQueryRunner();

	public Admin findAdminMessageById(String adminId) {
		String sql = "select admin_acount,password,name　from admins where admin_account=?";
		try {
			return qr.query(sql, new BeanHandler<Admin>(Admin.class), adminId);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	// 查询管理员个人信息
	@Override
	public Admin findAdminMessage(String userId) {
		String sql = "select * from admins where admin_acount=?";
		try {
			return qr.query(sql, new BeanHandler<Admin>(Admin.class), userId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	public void updateAdminPassword(String userId, String password) {
		String sql = "update admins set password=? where admin_account=?";
		try {
			qr.update(sql, password, userId);
		} catch (SQLException e) {
			throw new RuntimeException();
		}

	}

	public Admin findAdminByAdminId(Admin form) {
		String sql = "select * from admins where admin_acount=?";
		try {
			return qr.query(sql, new BeanHandler<Admin>(Admin.class),
					form.getAdmin_acount());
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	/**
	 * 把Excel转化成List集合然后传入数据库
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

	// 添加学习地点
	@Override
	public void addXuexid(Xuexid xuexid) {
		String sql = "insert into xuexid value(?,?,?,?,?,?,?,?,?,?)";
		try {
			qr.update(sql, xuexid.getDeparid(), xuexid.getXxdd(),
					xuexid.getDizhi(), xuexid.getLianxiren(),
					xuexid.getTelephone(), xuexid.getEmial(), xuexid.getYtdw(),
					xuexid.getLeixing(), xuexid.getYongdate(), xuexid.getHuze());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

	}

	// 查询专业
	@Override
	public List<Maijor> findMaijor() {
		String sql = "select zydm,zymc from maijor";
		try {
			return qr.query(sql, new BeanListHandler<Maijor>(Maijor.class));
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}

	}

	// 根据专业代码查询课程目录
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

	// 根据专业代码查询班级名称-用于添加班级课程
	@Override
	public List<Object> findClassByZydm(String zydm) {
		String sql = "select distinct bh from users where zydm=?";// 去掉重复的行
		try {
			return qr.query(sql, new ColumnListHandler(), zydm);
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}

	}

	@Override
	public Objcenter findObjcenterById(String visit_count) {
		String sql = "select * from objcenter where visit_count=?";
		try {
			return qr.query(sql, new BeanHandler<Objcenter>(Objcenter.class),
					visit_count);
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}

	}

	@Override
	public Expert findExpertById(String expacount) {
		String sql = "select * from experts where expacount=?";
		try {
			return qr.query(sql, new BeanHandler<Expert>(Expert.class),
					expacount);
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}

	}

	@Override
	public List<User> findUserByClassName(String className) {
		String sql = "select * from users where bh=?";
		try {
			return qr.query(sql, new BeanListHandler<User>(User.class),
					className);
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}

	}

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

	// 保存图片的途径
	@Override
	public void savePhotoPath(String userId, String filePath) {
		String sql = "update admins set photo=? where admin_acount=?";
		try {
			qr.update(sql, filePath, userId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	// 根据教师Id模糊查询教师所教的班级（代老师管理成绩)
	@Override
	public List<Object> searchClass(String id) {
		String sql = "select  DISTINCT bh from studentgrade where expacount like ?";
		try {
			return qr.query(sql, new ColumnListHandler("bh"), id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	// 查找补考的班级(代老师管理成绩)
	@Override
	public List<Object> searchFailClass(String id) {
		String sql = "select DISTINCT bh from studentgrade where expacount like ? and totalscores<60 and state>0";
		try {
			return qr.query(sql, new ColumnListHandler("bh"), id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Expert> displayExpert() {
		String sql = "select expacount,name from experts where state=0";
		try {
			return qr.query(sql, new BeanListHandler<Expert>(Expert.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void changeExpertState(String expacount) {
		String sql = "update experts set state=1 where expacount=?";
		try {
			qr.update(sql, expacount);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	// 模糊查询班级名称
	@Override
	public List<Object> searchClassByName(String bh) {
		String sql = "select DISTINCT bh from users where bh like ?";
		try {
			return qr.query(sql, new ColumnListHandler(), bh + "%");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// 根据班级名称查询班级学生，用于下载班级成绩登记表
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

}
