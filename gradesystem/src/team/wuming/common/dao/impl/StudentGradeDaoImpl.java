package team.wuming.common.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.management.Query;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;
import team.wuming.common.dao.StudentGradeDao;
import team.wuming.common.domain.Docourse;
import team.wuming.common.domain.StudentGrade;
import team.wuming.modules.experts.domain.Expert;
import team.wuming.test.page.PageBean;

public class StudentGradeDaoImpl implements StudentGradeDao {

	private QueryRunner qr = new TxQueryRunner();
	@Override
	public PageBean<StudentGrade> queryGradeByUserId(int pc, int ps,
			String userId) throws RuntimeException {
		String sql = "select count(*) from studentgrade";
		List<StudentGrade> studentGrades = new ArrayList<StudentGrade>();
		try {
			// 获取总记录数
			int tr = ((Number) qr.query(sql, new ScalarHandler())).intValue();
			sql = "select * from studentgrade where user_acount=? limit ?,?";
			List<Map<String, Object>> maps = qr.query(sql,
					new MapListHandler(), userId, (pc - 1) * ps, ps);
			for (Map<String, Object> map : maps) {
				Docourse docourse = CommonUtils.toBean(map, Docourse.class);
				Expert expert = CommonUtils.toBean(map, Expert.class);
				StudentGrade studentGrade = CommonUtils.toBean(map,
						StudentGrade.class);
				studentGrade.setDocourse(docourse);
				studentGrade.setExpert(expert);
				studentGrades.add(studentGrade);
			}
			PageBean<StudentGrade> pb = new PageBean<StudentGrade>();
			pb.setPc(pc);
			pb.setPs(ps);
			pb.setTr(tr);
			pb.setBeanList(studentGrades);
			return pb;

		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

}
