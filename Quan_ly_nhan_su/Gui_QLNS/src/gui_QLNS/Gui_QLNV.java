package gui_QLNS;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.sql.*;

import java.util.Vector;

public class Gui_QLNV extends JFrame implements ActionListener, MouseListener, TableModelListener {

	JFrame NVFrame;
	JTextField tfID, tfTen, tfCMND, tfNgaySinh, tfGioiTinh, tfDiaChi;
	DefaultTableModel model;
	Vector vData = new Vector();
	Vector vTitle = new Vector();
	JScrollPane tableResult;
	JTable tb = new JTable();
	JComboBox cbbPhongBan, cbbDuAn;
	String[] colums = { "ID", "Họ Và Tên", "CMND", " Ngày Sinh", "Giới Tính ", "Địa Chỉ ", "Phòng Ban", "Dự Án" };
	String[][] Data = {};

	public Gui_QLNV() {

		NVFrame = new JFrame("Quản lý nhân viên");
		NVFrame.setBounds(500, 100, 600, 550);
		NVFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		NVFrame.getContentPane().setLayout(null);

		JButton btnAdd = new JButton("Thêm nhân viên");
		btnAdd.addActionListener(this);
		btnAdd.setBounds(40, 170, 170, 35);
		NVFrame.getContentPane().add(btnAdd);

		JButton btnEdit = new JButton("Sửa thông tin nhân viên");
		btnEdit.addActionListener(this);
		btnEdit.setBounds(215, 170, 170, 35);
		NVFrame.getContentPane().add(btnEdit);

		JButton btnDelete = new JButton("Xóa nhân viên");
		btnDelete.addActionListener(this);
		btnDelete.setBounds(390, 170, 170, 35);
		NVFrame.getContentPane().add(btnDelete);

		JButton btnSearch = new JButton("Tìm kiếm nhân viên");
		btnSearch.addActionListener(this);
		btnSearch.setBounds(125, 210, 170, 35);
		NVFrame.getContentPane().add(btnSearch);

		JButton btnSort = new JButton("Sắp xếp nhân viên");
		btnSort.addActionListener(this);
		btnSort.setBounds(300, 210, 170, 35);
		NVFrame.getContentPane().add(btnSort);

		JLabel lbID = new JLabel("ID");
		lbID.setForeground(Color.BLUE);
		lbID.setBounds(70, 60, 70, 20);
		NVFrame.getContentPane().add(lbID);

		JLabel lbTen = new JLabel("Tên");
		lbTen.setForeground(Color.BLUE);
		lbTen.setBounds(70, 85, 70, 20);
		NVFrame.getContentPane().add(lbTen);

		JLabel lbCMND = new JLabel("CMND");
		lbCMND.setForeground(Color.BLUE);
		lbCMND.setBounds(70, 110, 70, 20);
		NVFrame.getContentPane().add(lbCMND);

		JLabel lbNgaySinh = new JLabel("Ngày sinh");
		lbNgaySinh.setForeground(Color.BLUE);
		lbNgaySinh.setBounds(70, 135, 70, 20);
		NVFrame.getContentPane().add(lbNgaySinh);

		JLabel lbGioiTinh = new JLabel("Giới tính");
		lbGioiTinh.setForeground(Color.BLUE);
		lbGioiTinh.setBounds(310, 60, 70, 20);
		NVFrame.getContentPane().add(lbGioiTinh);

		JLabel lbDiaChi = new JLabel("Địa chỉ");
		lbDiaChi.setForeground(Color.BLUE);
		lbDiaChi.setBounds(310, 85, 70, 20);
		NVFrame.getContentPane().add(lbDiaChi);

		JLabel lbPhongBan = new JLabel("Phòng ban");
		lbPhongBan.setForeground(Color.BLUE);
		lbPhongBan.setBounds(310, 110, 70, 20);
		NVFrame.getContentPane().add(lbPhongBan);

		JLabel lbDuAn = new JLabel("Dự án");
		lbDuAn.setForeground(Color.BLUE);
		lbDuAn.setBounds(310, 135, 70, 20);
		NVFrame.getContentPane().add(lbDuAn);
		
		JLabel lbQLNV = new JLabel("QUẢN LÝ NHÂN VIÊN");
		lbQLNV.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 25));
		lbQLNV.setForeground(Color.BLUE);
		lbQLNV.setBounds(150, 10, 280, 40);
		NVFrame.getContentPane().add(lbQLNV);

		tfID = new JTextField();
		tfID.setBounds(160, 60, 120, 20);
		NVFrame.getContentPane().add(tfID);
		tfID.setColumns(10);

		tfTen = new JTextField();
		tfTen.setBounds(160, 85, 120, 20);
		NVFrame.getContentPane().add(tfTen);
		tfTen.setColumns(10);

		tfCMND = new JTextField();
		tfCMND.setBounds(160, 110, 120, 20);
		NVFrame.getContentPane().add(tfCMND);
		tfCMND.setColumns(10);

		tfNgaySinh = new JTextField();
		tfNgaySinh.setBounds(160, 135, 120, 20);
		NVFrame.getContentPane().add(tfNgaySinh);
		tfNgaySinh.setColumns(10);

		tfGioiTinh = new JTextField();
		tfGioiTinh.setBounds(400, 60, 120, 20);
		NVFrame.getContentPane().add(tfGioiTinh);
		tfGioiTinh.setColumns(10);

		tfDiaChi = new JTextField();
		tfDiaChi.setBounds(400, 85, 120, 20);
		NVFrame.getContentPane().add(tfDiaChi);
		tfDiaChi.setColumns(10);
		
		cbbPhongBan = new JComboBox();
		cbbPhongBan.setBounds(400, 110, 120, 20);
		loadComboBoxPB();
		NVFrame.getContentPane().add(cbbPhongBan);
		
		cbbDuAn = new JComboBox();
		cbbDuAn.setBounds(400, 135, 120, 20);
		loadComboBoxDA();
		NVFrame.getContentPane().add(cbbDuAn);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 40, 15);
		NVFrame.getContentPane().add(menuBar);

		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);

		JMenuItem iNew = new JMenuItem("New");
		iNew.setForeground(Color.BLUE);
		iNew.addActionListener(this);
		mnMenu.add(iNew);
		
		JMenuItem iExit = new JMenuItem("Exit");
		iExit.setForeground(Color.RED);
		iExit.addActionListener(this);
		mnMenu.add(iExit);
		
		NVFrame.setLocationRelativeTo(null);
		NVFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		NVFrame.setVisible(true);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 255, 580, 250);
		NVFrame.getContentPane().add(scrollPane);

		load();
		model = new DefaultTableModel(vData,vTitle);
		tb = new JTable(model);
		tb.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		TableColumnModel colModel=tb.getColumnModel();
		colModel.getColumn(0).setPreferredWidth(50); 
		colModel.getColumn(1).setPreferredWidth(130);    
		colModel.getColumn(3).setPreferredWidth(80);
		colModel.getColumn(4).setPreferredWidth(60);
		tb.addMouseListener(this);
		tableResult = new JScrollPane(tb);
		tableResult.setBackground(Color.white);
		scrollPane.setViewportView(tb);
		NVFrame.getContentPane().add(tableResult, BorderLayout.SOUTH);
	}

	private void load() {
		try {
			String url="jdbc:sqlserver://localhost:1433;databaseName=gui_qlns;user=sa;password=123456";
            Connection conn = DriverManager.getConnection(url);
			Statement st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery("Select * from gui_qlnv");
			ResultSetMetaData metadata = (ResultSetMetaData) rs.getMetaData();
			vTitle.clear();
			vData.clear();
			int num_column = colums.length;
			for (int i = 0; i < num_column; i++) {
				vTitle.add(colums[i]);
			}
			while (rs.next()) {
				Vector row = new Vector();
				for (int i = 1; i <= num_column; i++) {
					row.addElement(rs.getString(i));
				}
				vData.add(row);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void loadComboBoxPB() {
		try {
			String url="jdbc:sqlserver://localhost:1433;databaseName=gui_qlns;user=sa;password=123456";
            Connection conn = DriverManager.getConnection(url);
			PreparedStatement ps = conn.prepareStatement("Select TenPB from gui_qlpb group by TenPB");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				cbbPhongBan.addItem(rs.getString("TenPB"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadComboBoxDA() {
		try {
			String url="jdbc:sqlserver://localhost:1433;databaseName=gui_qlns;user=sa;password=123456";
            Connection conn = DriverManager.getConnection(url);
			PreparedStatement ps = conn.prepareStatement("Select TenDA from gui_qlda group by TenDA");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				cbbDuAn.addItem(rs.getString("TenDA"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void reload() {
		tfID.setText("");
		tfTen.setText("");
		tfCMND.setText("");
		tfNgaySinh.setText("");
		tfGioiTinh.setText("");
		tfDiaChi.setText("");
	}

	public boolean Duplicate(String CMND) {
		try {
			String url="jdbc:sqlserver://localhost:1433;databaseName=gui_qlns;user=sa;password=123456";
            Connection conn = DriverManager.getConnection(url);
			PreparedStatement ps = conn.prepareStatement("select ID from gui_qlnv where CMND = ?");
			ps.setString(1, tfCMND.getText());
			return ps.executeQuery().next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void insert() {
		try {
			String url="jdbc:sqlserver://localhost:1433;databaseName=gui_qlns;user=sa;password=123456";
            Connection conn = DriverManager.getConnection(url);
			PreparedStatement ps = conn.prepareStatement(
					"insert into gui_qlnv(ID, TenNV, CMND, Ngay_Sinh, Gioi_Tinh, Dia_Chi, Phong_Ban, Du_An) values(?, ?, ?, ?, ?, ?, ?, ?)");
			if (tfTen.getText().equals("") || tfCMND.getText().equals("") || tfNgaySinh.getText().equals("")
					|| tfGioiTinh.getText().equals("") || tfDiaChi.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Không được để trống mục nào nhé !!!!");
			} else {
				if (Duplicate(tfCMND.getText())) {
					JOptionPane.showMessageDialog(this, "Đã tồn tại!");
				} else {
					ps.setString(1, tfID.getText());
					ps.setString(2, tfTen.getText());
					ps.setString(3, tfCMND.getText());
					ps.setString(4, tfNgaySinh.getText());
					ps.setString(5, tfGioiTinh.getText());
					ps.setString(6, tfDiaChi.getText());
					ps.setString(7, (String) cbbPhongBan.getSelectedItem());
					ps.setString(8, (String) cbbDuAn.getSelectedItem());
					int check = ps.executeUpdate();
					if (check > 0) {
						JOptionPane.showMessageDialog(this, "Thêm thành công!");
						model.setNumRows(0);
						load();
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public void edit() {
		try {
			String url="jdbc:sqlserver://localhost:1433;databaseName=gui_qlns;user=sa;password=123456";
            Connection conn = DriverManager.getConnection(url);
			PreparedStatement ps = conn.prepareStatement(
					"update gui_qlnv set TenNV = ?, CMND = ?, Ngay_Sinh = ?, Gioi_Tinh = ?, Dia_Chi = ?, Phong_Ban = ?, Du_An = ? where ID = ?");
			ps.setString(1, tfTen.getText());
			ps.setString(2, tfCMND.getText());
			ps.setString(3, tfNgaySinh.getText());
			ps.setString(4, tfGioiTinh.getText());
			ps.setString(5, tfDiaChi.getText());
			ps.setString(6, (String) cbbPhongBan.getSelectedItem());
			ps.setString(7, (String) cbbDuAn.getSelectedItem());
			ps.setString(8, tfID.getText());
			int check = ps.executeUpdate();
			if (check > 0) {
				JOptionPane.showMessageDialog(this, "Sửa thành công!");
				model.setNumRows(0);
				load();
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public void delete() {
		try {
			String url="jdbc:sqlserver://localhost:1433;databaseName=gui_qlns;user=sa;password=123456";
            Connection conn = DriverManager.getConnection(url);
			PreparedStatement ps = conn.prepareStatement("delete from gui_qlnv where ID = ?");
			ps.setString(1, tfID.getText());
			int check = ps.executeUpdate();
			if (check > 0) {
				JOptionPane.showMessageDialog(this, "Xóa thành công!");
				model.setNumRows(0);
				load();
			} else {
				JOptionPane.showMessageDialog(this, "Không có nhân viên nào để xóa!");
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	
	public void findByID() {
		try {
			String url="jdbc:sqlserver://localhost:1433;databaseName=gui_qlns;user=sa;password=123456";
            Connection conn = DriverManager.getConnection(url);
			PreparedStatement ps = conn.prepareStatement("select * from gui_qlnv where ID = ?");
			ps.setString(1, tfID.getText());
			ResultSet check = ps.executeQuery();
			if (check.next()) {
				JOptionPane.showMessageDialog(this, "Tìm kiếm thành công!");
				tfID.setText(check.getString("ID"));
				tfTen.setText(check.getString("TenNV"));
				tfCMND.setText(check.getString("CMND"));
				tfNgaySinh.setText(check.getString("Ngay_Sinh"));
				tfGioiTinh.setText(check.getString("Gioi_Tinh"));
				tfDiaChi.setText(check.getString("Dia_Chi"));
				cbbPhongBan.setSelectedItem(check.getString("Phong_Ban"));
				cbbDuAn.setSelectedItem(check.getString("Du_An"));
			} else {
				JOptionPane.showMessageDialog(this, "Nhân viên này không có sẵn!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void findByCMND() {
		try {
			String url="jdbc:sqlserver://localhost:1433;databaseName=gui_qlns;user=sa;password=123456";
            Connection conn = DriverManager.getConnection(url);
			PreparedStatement ps = conn.prepareStatement("select * from gui_qlnv where CMND = ?");
			ps.setString(1, tfCMND.getText());
			ResultSet check = ps.executeQuery();
			if (check.next()) {
				JOptionPane.showMessageDialog(this, "Tìm kiếm thành công!");
				tfID.setText(check.getString("ID"));
				tfTen.setText(check.getString("TenNV"));
				tfCMND.setText(check.getString("CMND"));
				tfNgaySinh.setText(check.getString("Ngay_Sinh"));
				tfGioiTinh.setText(check.getString("Gioi_Tinh"));
				tfDiaChi.setText(check.getString("Dia_Chi"));
				cbbPhongBan.setSelectedItem(check.getString("Phong_Ban"));
				cbbDuAn.setSelectedItem(check.getString("Du_An"));
			} else {
				JOptionPane.showMessageDialog(this, "Nhân viên này không có sẵn!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public void findByName() {
		try {
			String url="jdbc:sqlserver://localhost:1433;databaseName=gui_qlns;user=sa;password=123456";
            Connection conn = DriverManager.getConnection(url);
			PreparedStatement ps = conn.prepareStatement("select * from gui_qlnv where TenNV = ?");
				ps.setString(1, tfTen.getText());
				ResultSet check = ps.executeQuery();
				ResultSetMetaData metadata = (ResultSetMetaData) ps.getMetaData();
				JOptionPane.showMessageDialog(this, "Tìm kiếm thông tin nhân viên thành công");
				model.setNumRows(0);
				vTitle.clear();
				vData.clear();
				int num_column = colums.length;
				for ( int i = 0; i< num_column; i++) {
					vTitle.add(colums[i]);
				}
				while(check.next()) {
					Vector row = new Vector();
					for(int i = 1; i <= num_column; i++) {
						row.addElement(check.getString(i));
					}
					vData.add(row);
				}  
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
	}
	
	
	public void sortByName() {
		try {
			String url="jdbc:sqlserver://localhost:1433;databaseName=gui_qlns;user=sa;password=123456";
            Connection conn = DriverManager.getConnection(url);
            Statement st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery("Select * from gui_qlnv order by TenNV ASC");
			ResultSetMetaData metadata = (ResultSetMetaData) rs.getMetaData();
			JOptionPane.showMessageDialog(this, "Sắp xếp thành công!");
			model.setNumRows(0);
			vTitle.clear();
			vData.clear();
			int num_column = colums.length;
			for ( int i = 0; i< num_column; i++) {
				vTitle.add(colums[i]);
			}
			while(rs.next()) {
				Vector row = new Vector();
				for(int i = 1; i <= num_column; i++) {
					row.addElement(rs.getString(i));
				}
				vData.add(row);
			}
			} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
	
	public void Reset() {
		try {
			String url="jdbc:sqlserver://localhost:1433;databaseName=gui_qlns;user=sa;password=123456";
            Connection conn = DriverManager.getConnection(url);
            Statement st = (Statement) conn.createStatement();
				PreparedStatement ps = conn.prepareStatement("select * from nhanvien");
				ResultSet check = ps.executeQuery();
				ResultSetMetaData metadata = (ResultSetMetaData) ps.getMetaData();
				model.setNumRows(0);
				vTitle.clear();
				vData.clear();
				int num_column = colums.length;
				for ( int i = 0; i< num_column; i++) {
					vTitle.add(colums[i]);
				}
				while(check.next()) {
					Vector row = new Vector();
					for(int i = 1; i <= num_column; i++) {
						row.addElement(check.getString(i));
					}
					vData.add(row);
				}  
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
	}


	@Override
	public void tableChanged(TableModelEvent arg0) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tb) {
            int iDongDaChon = tb.getSelectedRow();
            if (iDongDaChon != -1) {
                Vector vDongDaChon = (Vector) vData.get(iDongDaChon);
                tfID.setText(vDongDaChon.get(0).toString());
                tfTen.setText(vDongDaChon.get(1).toString());
                tfCMND.setText(vDongDaChon.get(2).toString());
                tfNgaySinh.setText(vDongDaChon.get(3).toString());
                tfGioiTinh.setText(vDongDaChon.get(4).toString());
                tfDiaChi.setText(vDongDaChon.get(5).toString());
				cbbPhongBan.setSelectedItem(vDongDaChon.get(6).toString());
				cbbDuAn.setSelectedItem(vDongDaChon.get(7).toString());
            }
        }
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Exit")) {
			int hoi = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn thoát?",
					null, JOptionPane.YES_NO_OPTION);
			if (hoi == JOptionPane.YES_OPTION) {
				NVFrame.setVisible(false);
			}
		}
		if (e.getActionCommand().equals("Reset")) {
			Reset();
		}
		if (e.getActionCommand().equals("New")) {
			reload();
		}
		if (e.getActionCommand().equals("Thêm nhân viên")) {
			insert();
		}
		if (e.getActionCommand().equals("Tìm kiếm nhân viên")) {
//			findByCMND();
//			findByName();
			findByID();
			
		}
		if (e.getActionCommand().equals("Sửa thông tin nhân viên")) {
			edit();
		}
		if (e.getActionCommand().equals("Xóa nhân viên")) {
			delete();
		}
		if (e.getActionCommand().equals("Sắp xếp nhân viên")) {
			sortByName();
		}
	}
	public static void main(String[] args) {
		new Gui_QLNV();
	}
}