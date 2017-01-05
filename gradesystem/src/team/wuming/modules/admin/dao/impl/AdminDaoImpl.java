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



	public void updateAdminMessageById(Admin form) {
		String sql = "update admin ";
		try {
			qr.update(sql);
		} catch (SQLException e) {
			throw new RuntimeException();
		}

	}

	public Admin findAdminMessageById(String adminId) {
		String sql = "select *　from admins where admin_account=?";
		try {
			return qr.query(sql, new BeanHandler<Admin>(Admin.class), adminId);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public void updateAdminPassword(Admin form) {
		String sql = "update admins set password=? where admin_account=?";
		try {
			qr.update(sql, form.getPassword(), form.getAdmin_acount());
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
			throw new RuntimeException();
			}
	}

	@Override
	public void addObjcenter(Objcenter obj) {
		String sql = "insert into objcenter(visit_count,title,description,states,remark,zydm,termth,sthours,classhour,sbhour,lyid,score) value(?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			qr.update(sql, obj.getVisit_count(), obj.getTitle(),
					obj.getDescription(), obj.getStates(), obj.getRemark(),
					obj.getZydm(), obj.getTermth(), obj.getSthours(),
					obj.getClasshour(), obj.getSbhour(), obj.getLyid(),
					obj.getScore());
		} catch (SQLException e) {
			throw new RuntimeException();
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
			throw new RuntimeException();
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
			throw new RuntimeException();
		}

	}

	// 查询专业
	@Override
	public List<Maijor> findMaijor() {
		String sql = "select * from maijor";
		try {
			return qr.query(sql, new BeanListHandler<Maijor>(Maijor.class));
		} catch (SQLException e) {
			throw new RuntimeException();
		}

	}

	// 根据专业代码查询课程目录
	@Override
	public List<Objcenter> findObjcenterByZydm(String zydm) {
		String sql = "select * from Objcenter where zydm=?";
		try {
			return qr.query(sql,
					new BeanListHandler<Objcenter>(Objcenter.class), zydm);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	@Override
	public List<Object> findClassByZydm(String zydm) {
		String sql = "select distinct bh from users wherr zydm=?";// 去掉重复的行
		try {
			return qr.query(sql, new ColumnListHandler(), zydm);
		} catch (SQLException e) {
			throw new RuntimeException();
		}

	}

	@Override
	public Objcenter findObjcenterById(String visit_count) {
		String sql = "select * from objcenter where visit_count=?";
		try {
			return qr.query(sql, new BeanHandler<Objcenter>(Objcenter.class),
					visit_count);
		} catch (SQLException e) {
			throw new RuntimeException();
		}

	}

	@Override
	public Expert findExpertById(String expacount) {
		String sql = "select * from expert where expacount=?";
		try {
			return qr.query(sql, new BeanHandler<Expert>(Expert.class),
					expacount);
		} catch (SQLException e) {
			throw new RuntimeException();
		}

	}

	@Override
	public List<User> findUserByClassName(String className) {
		String sql = "select * from users where bh=?";
		try {
			return qr.query(sql, new BeanListHandler<User>(User.class),
					className);
		} catch (SQLException e) {
			throw new RuntimeException();
		}

	}

	@Override
	public void addStuentGrade(List<StudentGrade> studentGradeList) {
		String sql = "insert into studentgrade(user_acount,nickname,visit_count,title,sthours,bh,termth,expacount) "
				+ " value(?,?,?,?,?,?,?,?)";
		try {
			for (StudentGrade studentGrade : studentGradeList) {
				qr.update(sql, studentGrade.getUser_acount(), studentGrade
						.getNickname(), studentGrade.getVisit_count(),
						studentGrade.getTitle(), studentGrade.getSthours(),
						studentGrade.getBh(), studentGrade.getTermth(),
						studentGrade.getExpert().getExpacount());
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		}

	}

}
