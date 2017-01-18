package team.wuming.modules.admin.web.servlet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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

	// 学生信息Excel样表的下载
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

	// 查询专业-用于添加课程页面的专业选择
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

	// 根据专业编号查询课程
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

	// 查询教师信息-录入班级课程的接口
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

	// 根据专业代码查询班级-添加班级课程接口
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

	public String displayExpert(HttpServletRequest request,
			HttpServletResponse response) {
		List<Expert> expertList = new ArrayList<Expert>();
		expertList = adminService.displayExert();
		request.setAttribute("expertList", expertList);
		return "f:/jsps/admin/system/display_expert.jsp";
	}

	public String checkExpert(HttpServletRequest request,
			HttpServletResponse response) {
		String expacount = request.getParameter("expacount");
		Expert expert = expertService.findExpertMessage(expacount);
		request.setAttribute("expert", expert);
		return "f:/jsps/admin/system/check_expert.jsp";

	}
	public String passExpert(HttpServletRequest request,HttpServletResponse response){
		String expacount=request.getParameter("expacount");
		adminService.changeExpertState(expacount);
		return "f:/AdminServletOne?method=displayExpert";
	}

	public String searchClassByName(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		String bh = request.getParameter("bh");
		List<String> classList=adminService.searchClassByName(bh);
		request.setAttribute("classList",classList);
		request.setAttribute("bh", bh);
		return "f:/jsps/admin/managestudent/down_gradesheet.jsp";
	}
}
	
