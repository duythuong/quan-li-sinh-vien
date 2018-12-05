package sinh_vien;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;

public class tim {

	public Connection conn= null;
	Vector<String> vc = new Vector<String>();
	Vector<Vector<String>> vd = new Vector<Vector<String>>();
	String s;
	Statement st;
	ResultSet rs;
	String t2 = null;
	public void timkiem() {
		ketnoi kn = new ketnoi();
		conn = kn.connec();
		vc.clear();
		vd.clear();
		String a[] = {"Mã Sinh Viên","Tên Sinh Viên","Ngày Sinh","Lớp","Địa Chỉ","Chuyên Ngành"};
		try {
			for(int i=0;i<a.length;i++) {
				vc.add(a[i]);
			}
			st = conn.createStatement();
			rs = st.executeQuery("select * from thongtinsv where masinhvien like '%"+t2+"%'");
			ResultSetMetaData rsm = rs.getMetaData();
			int n = rsm.getColumnCount();
			while(rs.next()) {
				Vector<String> row = new Vector<String>(n);
				for(int i=1;i<=n;i++)
					row.add(rs.getString(i));
				vd.add(row);
			}
			rs.close();
                 
		} catch (Exception e) {
         JOptionPane.showMessageDialog(null, e);
		}
		
	}
}
