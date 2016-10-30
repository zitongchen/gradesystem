package team.wuming.modules.users.dao;

import java.sql.SQLException;
import java.util.List;

import team.wuming.common.domain.PageBean;
import team.wuming.common.domain.StudentGrade;
import team.wuming.modules.users.domain.User;

public interface UserDao {
	public User findByUserid(String userid);

	public User findUserMessageById(String userId);

	/*
	 * user_acount; password;realname;// 真实姓名 nickname; // 昵称score; //
	 * 用户积分tlelphone; // 手机号qq;// QQ号 email;// emailweixin;// 微信号age;//
	 * 年龄city;// 城市occupation;// 职业education;// 学历level;// 会员级别scores;// 运费
	 * state;// 现状态(可能填0/1)regist_time;// 注册时间usercode;// 用户类型编码marry;//
	 * 婚否hobby;// 爱好province;// 省 address;// 通信地址postCode;// 邮编paymethod;//
	 * '在线支付' COMMENT '支付方法'user_type;// 用户类型1-操作和编辑（要批准）username;
	 * dzyj;lxdh;name; sex;// 性别birthday;// 出生年月class_id;// 所在班级编号nj;//
	 * 年级zkzh;// 准考证号ksh;// 考生号 mz;// 民族zzmm;// 政治面貌byrq;// 已毕业日期 gzrq;//
	 * 工作日期byxx;// 毕业学校dw;// 工作单位zhiwu;// 职务 photo;// 照片xxdd;// 学习地点wyyz;// 外语语种
	 * sfzh;// 身份证号xfzh;// 行政区号代码lqzy;// 专业代码tdzyh;// 投档志愿号rxcj;//
	 * 入学成绩studyform;// 学习形式 rxrq;// 入学日期 ljsf;// 累积学分bysj;// 毕业日期swzh;// 学位书号
	 */

	// 修改用户信息
	public void updateUserMessageById(User user);

	public void updateUserPasswordById(User user);

	public List<StudentGrade> queryGradeByUserId(String userId);


			
}

