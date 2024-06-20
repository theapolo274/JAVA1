package gui_QLNS;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.sql.*;

import java.util.Vector;

public class Gui_QLPB extends JFrame implements ActionListener, MouseListener {

	JFrame PBFrame;
	JTextField tfID, tfPhongBan, tfSoLuong;
	JTable table;
	Vector vData = new Vector();
	Vector vTitle = new Vector();
	JScrollPane tableResult;
	DefaultTableModel model;
	JTable tb = new JTable();
	String[] colums = { "ID", "Tên phòng ban", " Số lượng nhân viên" };
	String[][] Data = {};

	public Gui_QLPB() {

		PBFrame = new JFrame("Quản lý phòng ban");
		PBFrame.setBounds(500, 100, 510, 420);
		PBFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PBFrame.getContentPane().setLayout(null);

		JButton btnAdd = new JButton("Thêm phòng ban");
		btnAdd.setBounds(290, 60, 180, 30);
		btnAdd.addActionListener(this);
		PBFrame.getContentPane().add(btnAdd);

		JButton btnEdit = new JButton("Sửa thông tin phòng ban");
		btnEdit.setBounds(290, 100, 180, 30);
		btnEdit.addActionListener(this);
		PBFrame.getContentPane().add(btnEdit);

		JButton btnDelete = new JButton("Xóa phòng ban");
		btnDelete.setBounds(290, 140, 180, 30);
		btnDelete.addActionListener(this);
		PBFrame.getContentPane().add(btnDelete);

		JLabel lbID = new JLabel("ID");
		lbID.setForeground(Color.BLUE);
		lbID.setBounds(30, 65, 120, 30);
		PBFrame.getContentPane().add(lbID);

		JLabel lblPhongBan = new JLabel("Tên Phòng Ban");
		lblPhongBan.setForeground(Color.BLUE);
		lblPhongBan.setBounds(30, 100, 120, 30);
		PBFrame.getContentPane().add(lblPhongBan);

		JLabel lbSoLuong = new JLabel("Số lượng nhân viên");
		lbSoLuong.setForeground(Color.BLUE);
		lbSoLuong.setBounds(30, 135, 120, 30);
		PBFrame.getContentPane().add(lbSoLuong);
		
		JLabel lbQLPB = new JLabel("QUẢN LÝ PHÒNG BAN");
		lbQLPB.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 25));
		lbQLPB.setForeground(Color.BLUE);
		lbQLPB.setBounds(110, 10, 280, 40);
		PBFrame.getContentPane().add(lbQLPB);

		tfID = new JTextField();
		tfID.setBounds(170, 70, 100, 20);
		PBFrame.getContentPane().add(tfID);
		tfID.setColumns(10);

		tfPhongBan = new JTextField();
		tfPhongBan.setBounds(170, 105, 100, 20);
		PBFrame.getContentPane().add(tfPhongBan);
		tfPhongBan.setColumns(10);

		tfSoLuong = new JTextField();
		tfSoLuong.setBounds(170, 140, 100, 20);
		PBFrame.getContentPane().add(tfSoLuong);
		tfSoLuong.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 175, 485, 200);
		PBFrame.getContentPane().add(scrollPane);

		table = new JTable();

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 40, 15);
		PBFrame.getContentPane().add(menuBar);

		JMenu mMenu = new JMenu("Menu");
		menuBar.add(mMenu);

		JMenuItem iNew = new JMenuItem("New");
		iNew.setForeground(Color.BLUE);
		iNew.addActionListener(this);
		mMenu.add(iNew);

		JMenuItem iExit = new JMenuItem("Exit");
		iExit.setForeground(Color.RED);
		iExit.addActionListener(this);
		mMenu.add(iExit);

		PBFrame.setLocationRelativeTo(null);
		PBFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PBFrame.setVisible(true);

		load();
		model = new DefaultTableModel(vData, vTitle);
		tb = new JTable(model);
		tb.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		TableColumnModel colModel = tb.getColumnModel();
		colModel.getColumn(0).setPreferredWidth(30);
		colModel.getColumn(1).setPreferredWidth(150);
		colModel.getColumn(2).setPreferredWidth(100);
		tb.addMouseListener(this);
		tableResult = new JScrollPane(tb);
		tableResult.setBackground(Color.white);

		scrollPane.setViewportView(tb);
		PBFrame.getContentPane().add(tableResult, BorderLayout.SOUTH);
	}

	public void load() {
		try {
			String url = "jdbc:sqlserver://localhost:1433;databaseName=gui_qlns;user=sa;password=123456";
			Connection conn = DriverManager.getConnection(url);
			Statement st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery("Select * from gui_qlpb");
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

	public void reload() {
		tfID.setText("");
		tfPhongBan.setText("");
		tfSoLuong.setText("");
	}

	public void insert() {
		try {
			String url = "jdbc:sqlserver://localhost:1433;databaseName=gui_qlns;user=sa;password=123456";
			Connection conn = DriverManager.getConnection(url);
			PreparedStatement ps = conn.prepareStatement("insert into gui_qlpb(ID, TenPB, So_Luong) values(?, ?, ?)");
			ps.setString(1, tfID.getText());
			ps.setString(2, tfPhongBan.getText());
			ps.setString(3, tfSoLuong.getText());
			int check = ps.executeUpdate();
			if (check > 0) {
				JOptionPane.showMessageDialog(this, "Thêm thành công!");
				model.setNumRows(0);
				load();
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public void edit() {
		try {
			String url = "jdbc:sqlserver://localhost;databaseName=gui_qlns;user=sa;password=123456";
			Connection conn = DriverManager.getConnection(url);
			PreparedStatement ps = conn.prepareStatement("update gui_qlpb set TenPB = ?, So_Luong = ? where ID = ?");
			ps.setString(1, tfPhongBan.getText());
			ps.setString(2, tfSoLuong.getText());
			ps.setString(3, tfID.getText());
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
			String url = "jdbc:sqlserver://localhost:1433;databaseName=gui_qlns;user=sa;password=123456";
			Connection conn = DriverManager.getConnection(url);
			PreparedStatement ps = conn.prepareStatement("delete from gui_qlpb where ID = ?");
			ps.setString(1, tfID.getText());
			int check = ps.executeUpdate();
			if (check > 0) {
				JOptionPane.showMessageDialog(this, "Xóa thành công!");
				model.setNumRows(0);
				load();
			} else {
				JOptionPane.showMessageDialog(this, "Không có phòng ban nào để xóa!");
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e1) {
		if (e1.getActionCommand().equals("Exit")) {
			int hoi = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn thoát?",
					null, JOptionPane.YES_NO_OPTION);
			if (hoi == JOptionPane.YES_OPTION) {
				PBFrame.setVisible(false);
			}
		}

		if (e1.getActionCommand().equals("New")) {
			reload();
		}

		if (e1.getActionCommand().equals("Thêm phòng ban")) {
			insert();
		}

		if (e1.getActionCommand().equals("Sửa thông tin phòng ban")) {
			edit();
		}

		if (e1.getActionCommand().equals("Xóa phòng ban")) {
			delete();
		}
	}

	public static void main(String[] args) {
		new Gui_QLPB();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == tb) {
			int iDongDaChon = tb.getSelectedRow();
			if (iDongDaChon != -1) {
				Vector vDongDaChon = (Vector) vData.get(iDongDaChon);
				tfID.setText(vDongDaChon.get(0).toString());
				tfPhongBan.setText(vDongDaChon.get(1).toString());
				tfSoLuong.setText(vDongDaChon.get(2).toString());
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}
}
