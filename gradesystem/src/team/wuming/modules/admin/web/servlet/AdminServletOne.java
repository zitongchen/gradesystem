package team.wuming.modules.admin.web.servlet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	// 管理员上传学生信息
	public String inputStudentMessage(HttpServletRequest request,
			HttpServletResponse response) throws IOException,
			FileUploadException {
		// 上传三部曲
		DiskFileItemFactory factory = new DiskFileItemFactory();// 创建工厂
		ServletFileUpload sfu = new ServletFileUpload(factory);// 创建解析器
		sfu.setFileSizeMax(1024 * 1024 * 10);// 设置上传的大小为10m
		List<FileItem> fileItems = sfu.parseRequest(request);// 利用解析器来解析request
		Iterator iter = fileItems.iterator();// 创建迭代器
		String fileName = "";
		List<User> userList = new ArrayList<User>();// 创建保存学生信息的List集合
		InputStudentMessageUitl inputStudentMessageUtil = new InputStudentMessageUitl();// 引入Excel信息转化类
		while (iter.hasNext()) {
			FileItem fileItem = (FileItem) iter.next();
			if (!fileItem.isFormField()) {
				fileName = fileItem.getName();// 获取文件名称
				int fileNameLength = fileName.length();
				if (fileNameLength <= 0) {
					request.setAttribute("errorMessage", "你还没有选择上传的文件");
					return "f:/jsps/admin/input_student_message.jsp";
				}
				int start = fileName.lastIndexOf(".");
				String fileType = fileName.substring(start + 1, fileNameLength);
				if (!fileType.equals("xls")) {
					request.setAttribute("errorMessage",
							"你上传的文件不是Excel文件，请上传正确的文件！");
					return "f:/jsps/admin/add_student_message.jsp";
				}
				try {
					userList = inputStudentMessageUtil
							.studentMessageToList(fileItem.getInputStream());
				} catch (Exception e) {
					request.setAttribute("errorMessage", "上传失败！");
					return "f:jsps/admin/add_student_message.jsp";
				}
			}
			try {
				adminService.inputStudentMessage(userList);
			} catch (Exception e) {
				String message = e.getMessage();
				message = message.substring(message.indexOf("\'") + 1,
						message.indexOf("\'") + 13);
				request.setAttribute("errorMessage", "上传失败！数据库里面已经存在学号为："
						+ message + "，请确认上传的信息表格是否正确！");
				return "f:jsps/admin/add_student_message.jsp";
			}
			request.setAttribute("successMessage", "学生信息录入成功！");
		}
		return "f:/jsps/admin/add_student_message.jsp";
	}

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
}
