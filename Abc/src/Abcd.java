
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
public class Abcd {

	public static void main(String[] args) {
		ResultSet rs=null;
		Statement stmt=null;
		// TODO Auto-generated method stub
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = null;
		conn = DriverManager.getConnection("jdbc:mysql://localhost/jdbc","root", "");
		System.out.print("Database is connected !");
		stmt= conn.createStatement();
		rs = stmt.executeQuery("Select * from emp");
		while(rs.next()){
			String id=rs.getString("id");
			String name=rs.getString("name");
			System.out.print("ID = "+ id +" Name = "+name);
		}
		conn.close();
		}
		catch(Exception e)
		{
		System.out.print("Do not connect to DB - Error:"+e);
		}

	}

}
