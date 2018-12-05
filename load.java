package sinh_vien;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;
class sinhvien{
	public String masv;
	public String tensv;
	public String ngays;
	public String lop;
	public String diachi;
	public String cn;
	public sinhvien(String masv, String tensv, String ngays, String lop, String diachi, String cn) {
		this.masv=masv;
		this.tensv=tensv;
		this.ngays=ngays;
		this.lop=lop;
		this.diachi=diachi;
		this.cn=cn;
	}
	
	public String getMasv() {
		return masv;
	}
	public void setMasv(String masv) {
		this.masv = masv;
	}
	public String getTensv() {
		return tensv;
	}
	public void setTensv(String tensv) {
		this.tensv = tensv;
	}
	public String getNgays() {
		return ngays;
	}
	public void setNgays(String ngays) {
		this.ngays = ngays;
	}
	public String getLop() {
		return lop;
	}
	public void setLop(String lop) {
		this.lop = lop;
	}
	public String getDiachi() {
		return diachi;
	}
	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}
	public String getCn() {
		return cn;
	}
	public void setCn(String cn) {
		this.cn = cn;
	}
	
	
}
public class load {

	Vector<String> va = new Vector<String>();
	Vector<Vector<String>> vb = new Vector<Vector<String>>();
	Statement st;
	ResultSet rs;
	ResultSetMetaData rsm;
	int n;
	Connection conn=null;
	public void load1() {
		ketnoi kn = new ketnoi();
		conn = kn.connec();
		va.clear();
		vb.clear();
		String s[] = {"Mã Sinh Viên","Tên Sinh Viên","Ngày Sinh","Lớp","Địa Chỉ","Chuyên Ngành"};
		try {
			for(int i=0;i<s.length;i++) {
				va.add(s[i]);
			}
			st = conn.createStatement();
			rs = st.executeQuery("select * from thongtinsv");
			rsm = rs.getMetaData();
			n = rsm.getColumnCount();
			while(rs.next()) {
				Vector<String> row = new Vector<String>(n);
//				sinhvien sv = new sinhvien(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
				for(int i=1;i<=n;i++) {
					row.add(rs.getString(i));			    
				}
				vb.add(row);
			}
			rs.close();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
}
