package sinh_vien;

import java.sql.Connection;
import java.sql.DriverManager;

public class ketnoi {

	Connection conn=null;
	public Connection connec() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sinhvien","root","1234");
			if(conn!=null) {
				System.out.println("thanh cong");
			}	
      		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return conn;
	}
}
