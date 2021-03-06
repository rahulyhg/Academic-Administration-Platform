package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.NewsDao;
import dto.News;

public class SelectAllNewsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public SelectAllNewsServlet() {
		super();
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		NewsDao dao=new NewsDao();
		int rowCount;
		int currentCount;
		int sumPageCount;
		String strPage=request.getParameter("page");
		if(strPage==null){
		  	currentCount=1;
		}else{
		  	currentCount=Integer.parseInt(strPage);
		}
		rowCount=dao.sumData();
		sumPageCount=dao.sumPageCount();
		ArrayList<News> slist = dao.fenye(currentCount);//分页
		if(slist.size()==0){
			slist=null;
		}
		request.setAttribute("stry1", slist);
		request.setAttribute("rowCount", rowCount);
		request.setAttribute("sumPageCount", sumPageCount);
		request.setAttribute("currentCount", currentCount);
		request.getRequestDispatcher("selectAllNews.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
