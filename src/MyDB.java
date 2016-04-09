import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyDB {
	
	Connection con = null;
	String url = "jdbc:mysql://localhost:3306/UserDB";
	
	MyDB() throws ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		try {
			con = DriverManager.getConnection(url, "root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String addUserToDB(String name, String pwd) {
		Statement st = null;
		int rs;
		String stmt = "INSERT INTO user " + "VALUES('" + name + "', '" + pwd + "')";

		try {
			st = con.createStatement();
			rs = st.executeUpdate(stmt);
			
			con.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
			return "-1";
		}

		return "1";
	}
	public Boolean verifyUserFromDB(String name, String pwd) {
		Statement st = null;
		ResultSet rs;
		String rsPwd = "";
		String stmt = "Select * from user where name = '"+ name + "';" ;

		try {
			st = con.createStatement();
			rs = st.executeQuery(stmt);
			while(rs.next())
				rsPwd = rs.getString("pwd");
			con.close();
			
			if(rsPwd.equals(pwd))
				return true;			

		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
			}

		return false;
	}
}