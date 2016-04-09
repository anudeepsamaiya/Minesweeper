import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyDB {
	
	Connection con = null;
	
	MyDB() throws ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
	}

	public String addUserToDB(String name, String pwd) {
		Statement st = null;
		int rs;
		String stmt = "INSERT INTO User ( name, pwd ) " + "VALUES('" + name + "','" + pwd + "')";

		try {
			con = DriverManager.getConnection(
                    "jdbc:default:connection");

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
		String stmt = "Select * from User where name = "+ name + ";" ;

		try {
			con = DriverManager.getConnection(
                    "jdbc:default:connection");

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