package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.dao;

@WebServlet("/")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MainController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doPro(request, response);
	}

	protected void doPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String context = request.getContextPath();
		String command = request.getServletPath();
		String site = null;
		
		System.out.println(context + "/" + command);
		
		dao dao = new dao();
		
		switch (command) {
		case "/home":
			site = "index.jsp";
			break;
		case "/inquire":
			site = dao.inquire(request, response);
			break;
		case "/insert":
			site = "insert.jsp";
			break;
		case "/statistics":
			site = "statistics.jsp";
	}
		getServletContext().getRequestDispatcher("/"+site).forward(request, response);
	
}
}
