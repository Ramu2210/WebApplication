package sms;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// from this page will be called 

public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	StudentDbUtils sdu;

	// gets connection object
	@Resource(name = "dbConn")
	DataSource ds;

	public void init() {
		sdu = new StudentDbUtils(ds);
	}

	// method which will be called from remotely

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

		String cmd = req.getParameter("command");
		if (cmd == null) {
			cmd = "LIST";
		}
		try {
			// it switches when cmd triggers eg if cmd== list ->gives list of all student

			switch (cmd) {

			case "LIST":
				listStudents(req, resp);
				break;

			case "SAVE":
				saveStudent(req, resp);
				break;

			case "DELETE":
				deleteStudent(req, resp);
				break;

			case "LOAD":
				loadData(req, resp);
				break;

			case "UPDATE":
				updateStudent(req, resp);
				break;
				
			case "AUTH":	
				userAuth(req,resp);
				break;
				

			}

		} catch (Exception e) {

		}

	}

	private void userAuth(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		String user=req.getParameter("user");
		String pass=req.getParameter("pass");
		System.out.println(user+"    "+pass);
		
		boolean isAuth = sdu.check(user,pass);
		
		if(isAuth) {
			resp.sendRedirect(req.getContextPath()+"/HomeController");
		}
		else {
			resp.sendRedirect(req.getContextPath()+"/Error.jsp");
		}
	}

	private void loadData(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//System.out.println("Load student Data");
		
		String sid = req.getParameter("sid");

		Student theStudent = sdu.loadStudent(sid);
		req.setAttribute("THE_STUDENT", theStudent);
		
		req.getRequestDispatcher("/update-student.jsp").forward(req, resp);

	}

	private void updateStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		System.out.println("Update hoga ");
		
		int sid=Integer.parseInt(req.getParameter("sid"));
		String fn=req.getParameter("fname");
		String ln = req.getParameter("lname");
		String email=req.getParameter("email");
		
		Student theStudent=new Student(sid,fn,ln,email);
		
		sdu.updateStudent(theStudent);
		resp.sendRedirect(req.getContextPath()+"/HomeController");;
		
		
	}

	private void deleteStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("Wait Kar del hoga ");

		String sid = req.getParameter("sid");

		sdu.delete(sid);

		resp.sendRedirect(req.getContextPath() + "/HomeController");

	}

	private void listStudents(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Student> students = sdu.getStudents();

		req.setAttribute("LIST_STUDENTS", students);

		RequestDispatcher rd = req.getRequestDispatcher("/student-list.jsp");
		rd.forward(req, resp);

	}

	private void saveStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fname = req.getParameter("fn");
		String lname = req.getParameter("ln");
		String email = req.getParameter("email");

		Student theStudent = new Student(fname, lname, email);
		System.out.println(theStudent);

		sdu.save(theStudent);

		System.out.println("Hey Don't push me");
		//listStudents(req, resp);
		
		resp.sendRedirect(req.getContextPath()+"/HomeController");
	}

}
