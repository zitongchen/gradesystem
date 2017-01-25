package team.wuming.modules.admin.web.servlet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.HTML;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.alibaba.fastjson.JSON;

import team.wuming.common.domain.Maijor;
import team.wuming.common.domain.Objcenter;
import team.wuming.common.domain.StudentGrade;
import team.wuming.common.domain.Xuexid;
import team.wuming.modules.admin.service.AdminService;
import team.wuming.modules.admin.service.impl.AdminServiceImpl;
import team.wuming.modules.admin.util.InputStudentMessageUitl;
import team.wuming.modules.experts.domain.Expert;
import team.wuming.modules.experts.service.ExpertService;
import team.wuming.modules.experts.service.impl.ExpertServiceImpl;
import team.wuming.modules.users.domain.User;
import cn.itcast.servlet.BaseServlet;

public class AdminServletOne extends BaseServlet {
	private AdminService adminService = new AdminServiceImpl();
	private ExpertService expertService = new ExpertServiceImpl();

	/**
	 * 学生信息的Excel样表下载
	 * 
	 * @param request
	 * @param response
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void downloadUserExcel(HttpServletRequest request,
			HttpServletResponse response) throws FileNotFoundException,
			IOException {
		String fileName = "学生数据导入格式表.xls";
		InputStream in = this.getServletContext().getResourceAsStream(
				"/WEB-INF/resource/" + fileName);
		fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
		response.addHeader("content-disposition", "attachment;filename="
				+ fileName);
		OutputStream out = response.getOutputStream();
		IOUtils.copy(in, out);
		in.close();
		out.close();
	}

	/**
	 * 查询专业（系统管理-添加班级课程）
	 * 
	 * @param request
	 * @param response
	 */
	public void findMaijor(HttpServletRequest request,
			HttpServletResponse response) {
		List<Maijor> maijorList = adminService.findMaijor();
		String output = null;
		if (!maijorList.isEmpty()) {
			output = JSON.toJSONString(maijorList);
		}
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.println(output);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	/**
	 * 根据专业代码查询课程（系统管理-添加班级成绩）
	 * 
	 * @param request
	 * @param response
	 */
	public void findObjByZydm(HttpServletRequest request,
			HttpServletResponse response) {
		String zydm = request.getParameter("zydm");
		List<Objcenter> objcenterList = adminService.findObjcenterByZydm(zydm);
		String output = null;
		if (!objcenterList.isEmpty()) {
			output = JSON.toJSONString(objcenterList);
		}
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.println(output);
			out.flush();
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	/**
	 * 查询课程信息(系统管理-课程信息)
	 * 
	 * @param request
	 * @param response
	 */
	public void findObjcenter(HttpServletRequest request,
			HttpServletResponse response) {
		List<Objcenter> objcenterList = adminService.findObjcenter();
		String output = null;

		if (!objcenterList.isEmpty()) {
			output = JSON.toJSONString(objcenterList);
		}
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.println(output);
			out.flush();
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	/**
	 * 查询系统教师的名称，编号（系统管理-添加班级课程）
	 * 
	 * @param request
	 * @param response
	 */
	public void findExpertId(HttpServletRequest request,
			HttpServletResponse response) {
		List<Expert> expertList = expertService.findExpertId();
		String output = null;

		if (!expertList.isEmpty()) {
			output = JSON.toJSONString(expertList);
		}
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.println(output);
			out.flush();
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			out.close();
		}

	}

	/**
	 * 根据专业代码查询班级（系统管理-添加班级课程）
	 * 
	 * @param request
	 * @param response
	 */
	public void findClassByZydm(HttpServletRequest request,
			HttpServletResponse response) {
		String zydm = request.getParameter("zydm");
		List<Object> classNameLists = adminService.findClassByZydm(zydm);
		List<String> classList = new ArrayList<String>();
		String output = null;
		if (!classNameLists.isEmpty()) {
			output = JSON.toJSONString(classNameLists);
		}
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.println(output);
			out.flush();
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	/**
	 * 展示注册但未审核教师的名单
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String displayExpert(HttpServletRequest request,
			HttpServletResponse response) {
		List<Expert> expertList = new ArrayList<Expert>();
		expertList = adminService.displayExert();
		request.setAttribute("expertList", expertList);
		return "f:/jsps/admin/system/display_expert.jsp";
	}

	/**
	 * 根据教师编号，展示未审核教师详细信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String checkExpert(HttpServletRequest request,
			HttpServletResponse response) {
		String expacount = request.getParameter("expacount");
		Expert expert = expertService.findExpertMessage(expacount);
		request.setAttribute("expert", expert);
		return "f:/jsps/admin/system/check_expert.jsp";

	}

	/**
	 * 教师通过审核
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String passExpert(HttpServletRequest request,HttpServletResponse response){
		String expacount=request.getParameter("expacount");
		adminService.changeExpertState(expacount);
		return "f:/AdminServletOne?method=displayExpert";
	}

	/**
	 * 下载某个班级的成绩登记表（学生信息管理）
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String searchClassByName(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		String bh = request.getParameter("bh");
		List<String> classList=adminService.searchClassByName(bh);
		if (classList.isEmpty()) {
			request.setAttribute("errorMessage", "查询不到该班级！");
		} else {
			request.setAttribute("classList", classList);
			request.setAttribute("bh", bh);
		}
		return "f:/jsps/admin/managestudent/down_gradesheet.jsp";
	}


	/**
	 * 根据专业代码查询毕业班级，同时自动计算毕业学年(学生信息管理)
	 * 
	 * @param request
	 * @param response
	 */
	public void searchGraduateClassByZydm(HttpServletRequest request,
			HttpServletResponse response) {
		String zydm = request.getParameter("zydm");
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		if (month >= 2) {
			year = year - 2;
		} else if (month < 2) {
			year = year - 3;
		}
		String nj = String.valueOf(year);
		List<String> graduateClassList = new ArrayList<String>();
		graduateClassList = adminService.searchGraduateClass(zydm, nj);
		PrintWriter out = null;
		try {
			out = response.getWriter();
			if (graduateClassList.isEmpty()) {
				out.println("null");
			} else {
				out.println(JSON.toJSONString(graduateClassList));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	/**
	 * 查询学习地点（查询某个教学点补考的名单）
	 * 
	 * @param request
	 * @param response
	 */
	// 查询学习地点
	public void searchXuexidi(HttpServletRequest request,
			HttpServletResponse response) {
		List<Xuexid> xuexidList = new ArrayList<Xuexid>();
		xuexidList = adminService.searchXuxid();
		String output = null;
		PrintWriter out = null;
		try {
			out = response.getWriter();
			if (!xuexidList.isEmpty()) {
				output = JSON.toJSONString(xuexidList);
				out.println(output);
			} else {
				out.println(JSON.toJSONString("不存在学习地点！"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	/**
	 * 根据教学点查询补考学生名单
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String searchFailStudentByXuxid(HttpServletRequest request,
			HttpServletResponse response) {
		String xuexiId = request.getParameter("jxd");
		List<StudentGrade> failStudentList = new ArrayList<StudentGrade>();
		failStudentList = adminService.searchFailStudentByXuxid(xuexiId);
		if (failStudentList.isEmpty()) {
			request.setAttribute("errorMessage", "该教学点不存补考学生名单！");
			return "f:/jsps/admin/managestudent/search_fail_grade.jsp";
		} else {
			request.setAttribute("studentList", failStudentList);
			request.setAttribute("jxd", xuexiId);
			return "f:/jsps/admin/managestudent/display_fail_grade.jsp";
		}
	}
}
	
