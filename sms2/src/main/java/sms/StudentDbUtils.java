package sms;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDbUtils {

	DataSource ds;

	StudentDbUtils(DataSource ds) {
		this.ds = ds;
	}

	// creating a method of save()
	void save(Student theStudent) {

		String q = "Insert into students(fname,lname,email) values(?,?,?) ";

		try {
			PreparedStatement ps = ds.getConnection().prepareStatement(q);

			ps.setString(1, theStudent.getFirstname());
			ps.setString(2, theStudent.getLastname());
			ps.setString(3, theStudent.getEmail());

			ps.execute();

			System.out.println("Registration Successful ");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<Student> getStudents() {
		String q = "Select * from students";
		List<Student> students = new ArrayList<>();

		try {
			Statement stmt = ds.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(q);

			Student theStudent = null;

			while (rs.next()) {
				theStudent = new Student(rs.getInt("sid"), rs.getString("fname"), rs.getString("lname"),
						rs.getString("email"));
				students.add(theStudent);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return students;
		// get students() ends here ;

	}

	public void delete(String sid) {

		String q = "Delete from students where sid = " + sid;

		try {
			Statement stmt = ds.getConnection().createStatement();
			stmt.execute(q);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Student loadStudent(String sid) {
		String q = "Select * from students  where sid = " + sid;
		Student theStudent = null;

		try {
			Statement stmt = ds.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(q);

			if (rs.next()) {
				theStudent = new Student(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));

			}
		} catch (SQLException e) {
			e.printStackTrace();

		}

		return theStudent;
	}

	public void updateStudent(Student theStudent) {

		String q = "Update students set fname=?, lname=?, email = ? where sid=?";

		try {

			PreparedStatement ps = ds.getConnection().prepareStatement(q);
			ps.setString(1, theStudent.getFirstname());
			ps.setString(2, theStudent.getLastname());
			ps.setString(3, theStudent.getEmail());
			ps.setInt(4, theStudent.getSid());

			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	

	public boolean check(String user, String pass) {

		String q = "SELECT * FROM users WHERE email=? AND password=?";
		try {

			PreparedStatement ps = ds.getConnection().prepareStatement(q);
			ps.setString(1, user);
			ps.setString(2, pass);

			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
				return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return false;

	}

}


