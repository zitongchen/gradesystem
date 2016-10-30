package team.wuming.test.upload;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.itcast.commons.CommonUtils;

public class fileUpServlet extends HttpServlet {

	// 上传不能使用BaseServlet
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置请求，响应头的为UTF-8格式
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		// 上传三部曲，创建工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 创建解析器
		ServletFileUpload sfu = new ServletFileUpload(factory);
		sfu.setFileSizeMax(1000 * 1024);
		String fileName;
		try {
			// 读取请求体的内容
			List<FileItem> fileItemList = sfu.parseRequest(request);

			fileName = fileItemList.get(0).getName();
			int index = fileName.lastIndexOf("\\");
			if (index != -1) {
				fileName = fileName.substring(index + 1);
			}
			String savepath = this.getServletContext().getRealPath(
					"/WEB-INF/resource");
			File file = new File(savepath, CommonUtils.uuid() + "_" + fileName);
			fileItemList.get(0).write(file);
		} catch (Exception e) {
			// TODO: handle exception
		}
		PrintWriter out = response.getWriter();

	}

}
