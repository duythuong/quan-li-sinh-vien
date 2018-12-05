package sinh_vien;

import java.awt.EventQueue;
import sinh_vien.*;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class thong_tin_sv {

	
	private JFrame frame;
	Connection conn = null;
	DefaultTableModel d1,d2;
	private JTable table;
	load l = new load();
	private JTable table_1;
	JTextField textField;
	tim t1 = new tim();
	sua s = new sua();
	int row;
	TableModel t;

	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					thong_tin_sv window = new thong_tin_sv();
					window.frame.setVisible(true);
					window.frame.setTitle("thông tin Sinh viên");
					window.frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public thong_tin_sv() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 935, 677);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 920, 641);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Thông Tin Sinh Viên", null, panel, null);
		panel.setLayout(null);
		
		
	    d1 = new DefaultTableModel(l.vb, l.va);
	    l.load1();
		table = new JTable(d1);
		table.setBounds(10, 74, 895, 508);
		panel.add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				row = table.getSelectedRow();
				t = table.getModel();
				
			}
		});
		scrollPane.setBounds(10, 74, 895, 508);
		panel.add(scrollPane);
		
		JLabel lblThngTinSinh = new JLabel("Thông tin sinh viên");
		lblThngTinSinh.setForeground(Color.RED);
		lblThngTinSinh.setFont(new Font("SansSerif", Font.PLAIN, 24));
		lblThngTinSinh.setBounds(92, 10, 220, 36);
		panel.add(lblThngTinSinh);
		
		JButton btnThm = new JButton("Thêm");
		btnThm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				them t = new them();
				t.getFrame().setVisible(true);
				
			}
		});
		btnThm.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnThm.setBounds(457, 12, 85, 34);
		panel.add(btnThm);
		
		JButton btnSa = new JButton("Sửa");
		btnSa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				s = new sua();
				s.getTextField().setText(t.getValueAt(row, 0).toString());
				s.getTextField_1().setText(t.getValueAt(row, 1).toString());
				s.getTextField_2().setText(t.getValueAt(row, 2).toString());
				s.getTextField_3().setText(t.getValueAt(row, 3).toString());
				s.getTextField_4().setText(t.getValueAt(row, 4).toString());
				s.getTextField_5().setText(t.getValueAt(row, 5).toString());
				s.getFrame().setVisible(true);
			}
		});
		btnSa.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnSa.setBounds(570, 12, 85, 34);
		panel.add(btnSa);
		
		JButton btnXa = new JButton("Xóa");
		btnXa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sql = "delete from thongtinsv where masinhvien=?";
				try {
					ketnoi kn = new ketnoi();
					conn = kn.connec();
					PreparedStatement p = conn.prepareStatement(sql);
					p.setString(1, table.getValueAt(row, 0).toString());
					int i = p.executeUpdate();
					if(i!=-1) {
						JOptionPane.showConfirmDialog(null, "xóa thành công", "thông báo", JOptionPane.CANCEL_OPTION);
					}
					l.load1();
					d1.fireTableDataChanged();
				}catch (Exception e) {
					System.out.println(e);
				}
			}
		});
		btnXa.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnXa.setBounds(683, 12, 85, 34);
		panel.add(btnXa);
		
		JButton btnCapNhap = new JButton("cap nhap");
		btnCapNhap.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCapNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				l.load1();
				d1.fireTableDataChanged();
			}
		});
		btnCapNhap.setBounds(790, 10, 115, 34);
		panel.add(btnCapNhap);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Tìm Kiếm", null, panel_2, null);
		panel_2.setLayout(null);
		d2 = new DefaultTableModel(t1.vd, t1.vc);
		t1.timkiem();
		
		table_1 = new JTable(d2);
		table_1.setBounds(10, 122, 895, 450);
		panel_2.add(table_1);
		
		JScrollPane scrollPane_1 = new JScrollPane(table_1);
		scrollPane_1.setBounds(10, 122, 895, 450);
		panel_2.add(scrollPane_1);
		
		textField = new JTextField();
		textField.setFont(new Font("SansSerif", Font.PLAIN, 20));
		textField.setBounds(194, 41, 480, 32);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JButton btnTmKim = new JButton("Tìm Kiếm");
		btnTmKim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	          t1.timkiem();
	          d2.fireTableDataChanged();
			}
		});
		btnTmKim.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnTmKim.setBounds(753, 42, 125, 31);
		panel_2.add(btnTmKim);
	}
}
