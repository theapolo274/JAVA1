package gui_QLNS;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import gui_QLNS.Gui_QLDA;

import java.sql.*;

import java.util.Vector;

public class Gui_QLDA extends JFrame implements ActionListener, MouseListener {

	JFrame DAFrame;
	JTextField tfID, tfTenDuAn, tfSoLuong, tfThuong;
	Vector vData = new Vector();
	Vector vTitle = new Vector();
	DefaultTableModel model;
	JTable tb = new JTable();
	JScrollPane tableResult;
	String[] colums = { "ID", "Tên Dự Án", " Số lượng tham gia", "Tiền thưởng dự án " };
	String[][] Data = {};

	public Gui_QLDA() {

		DAFrame = new JFrame("Quản lý dự án");
		DAFrame.setBounds(500, 100, 510, 420);
		DAFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DAFrame.setVisible(true);

		JButton btnAdd = new JButton("Thêm dự án");
		btnAdd.setBounds(330, 60, 150, 30);
		btnAdd.addActionListener(this);
		DAFrame.getContentPane().setLayout(null);
		DAFrame.getContentPane().add(btnAdd);

		JButton btnEdit = new JButton("Sửa thông tin dự án");
		btnEdit.setBounds(330, 100, 150, 30);
		btnEdit.addActionListener(this);
		DAFrame.getContentPane().add(btnEdit);

		JButton btnDelete = new JButton("Xóa dự án");
		btnDelete.setBounds(330, 140, 150, 30);
		btnDelete.addActionListener(this);
		DAFrame.getContentPane().add(btnDelete);

		JLabel lbID = new JLabel("ID");
		lbID.setBounds(20, 60, 150, 20);
		lbID.setForeground(Color.BLUE);
		DAFrame.getContentPane().add(lbID);

		JLabel lbTenDuAn = new JLabel("Tên Dự Án");
		lbTenDuAn.setBounds(20, 90, 150, 20);
		lbTenDuAn.setForeground(Color.BLUE);
		DAFrame.getContentPane().add(lbTenDuAn);

		JLabel lbSoLuong = new JLabel("Số lượng người tham gia");
		lbSoLuong.setBounds(20, 120, 150, 20);
		lbSoLuong.setForeground(Color.BLUE);
		DAFrame.getContentPane().add(lbSoLuong);

		JLabel lbThuong = new JLabel("Tiền thưởng dự án");
		lbThuong.setBounds(20, 150, 150, 20);
		lbThuong.setForeground(Color.BLUE);
		DAFrame.getContentPane().add(lbThuong);
		
		JLabel lbQLDA = new JLabel("QUẢN LÝ DỰ ÁN");
		lbQLDA.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 25));
		lbQLDA.setForeground(Color.BLUE);
		lbQLDA.setBounds(130, 10, 280, 40);
		DAFrame.getContentPane().add(lbQLDA);

		tfID = new JTextField();
		tfID.setBounds(190, 60, 120, 20);
		DAFrame.getContentPane().add(tfID);
		tfID.setColumns(10);

		tfTenDuAn = new JTextField();
		tfTenDuAn.setBounds(190, 90, 120, 20);
		DAFrame.getContentPane().add(tfTenDuAn);
		tfTenDuAn.setColumns(10);

		tfSoLuong = new JTextField();
		tfSoLuong.setBounds(190, 120, 120, 20);
		DAFrame.getContentPane().add(tfSoLuong);
		tfSoLuong.setColumns(10);

		tfThuong = new JTextField();
		tfThuong.setBounds(190, 150, 120, 20);
		DAFrame.getContentPane().add(tfThuong);
		tfThuong.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 175, 490, 200);
		DAFrame.getContentPane().add(scrollPane);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 40, 15);
		DAFrame.getContentPane().add(menuBar);

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

		DAFrame.setLocationRelativeTo(null);
		DAFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DAFrame.setVisible(true);

		load();
		model = new DefaultTableModel(vData, vTitle);
		tb = new JTable(model);
		tb.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		TableColumnModel colModel = tb.getColumnModel();
		colModel.getColumn(0).setPreferredWidth(30);
		colModel.getColumn(1).setPreferredWidth(150);
		colModel.getColumn(2).setPreferredWidth(100);
		colModel.getColumn(3).setPreferredWidth(100);
		tb.addMouseListener(this);
		tableResult = new JScrollPane(tb);
		tableResult.setBackground(Color.white);
		scrollPane.setViewportView(tb);
		DAFrame.getContentPane().add(tableResult, BorderLayout.SOUTH);
	}

	public void load() {
		try {
			String url = "jdbc:sqlserver://localhost:1433;databaseName=gui_qlns;user=sa;password=123456";
			Connection conn = DriverManager.getConnection(url);
			Statement st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery("Select * from gui_qlda");
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
		tfTenDuAn.setText("");
		tfSoLuong.setText("");
		tfThuong.setText("");
	}

	public void insert() {
		try {
			String url = "jdbc:sqlserver://localhost:1433;databaseName=gui_qlns;user=sa;password=123456";
			Connection conn = DriverManager.getConnection(url);
			PreparedStatement ps = conn
					.prepareStatement("insert into gui_qlda(ID, TenDA, So_Nguoi, Tien_Thuong) values(?, ?, ?, ?)");
			ps.setString(1, tfID.getText());
			ps.setString(2, tfTenDuAn.getText());
			ps.setString(3, tfSoLuong.getText());
			ps.setString(4, tfThuong.getText());
			int check = ps.executeUpdate();
			if (check > 0) {
				JOptionPane.showMessageDialog(this, "Đã thêm dự án!");
				model.setNumRows(0);
				load();
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public void edit() {
		try {
			String url = "jdbc:sqlserver://localhost:1433;databaseName=gui_qlns;user=sa;password=123456";
			Connection conn = DriverManager.getConnection(url);
			PreparedStatement ps = conn
					.prepareStatement("update gui_qlda set TenDA = ?, So_Nguoi = ?, Tien_Thuong = ? where ID = ?");
			ps.setString(1, tfTenDuAn.getText());
			ps.setString(2, tfSoLuong.getText());
			ps.setString(3, tfThuong.getText());
			ps.setString(4, tfID.getText());
			int check = ps.executeUpdate();
			if (check > 0) {
				JOptionPane.showMessageDialog(this, "Dự án đã được sửa!");
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
			PreparedStatement ps = conn.prepareStatement("delete from gui_qlda where ID = ?");
			ps.setString(1, tfID.getText());
			int check = ps.executeUpdate();
			if (check > 0) {
				JOptionPane.showMessageDialog(this, "Xóa thành công");
				model.setNumRows(0);
				load();
			} else {
				JOptionPane.showMessageDialog(this, "Dự án không tồn tại!");
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e0) {
		if (e0.getActionCommand().equals("Exit")) {
			int hoi = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn thoát?", null,
					JOptionPane.YES_NO_OPTION);
			if (hoi == JOptionPane.YES_OPTION) {
				DAFrame.setVisible(false);
			}
		}

		if (e0.getActionCommand().equals("New")) {
			reload();
		}

		if (e0.getActionCommand().equals("Thêm dự án")) {
			insert();
		}

		if (e0.getActionCommand().equals("Sửa thông tin dự án")) {
			edit();
		}

		if (e0.getActionCommand().equals("Xóa dự án")) {
			delete();
		}
	}

	public static void main(String[] args) {
		new Gui_QLDA();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == tb) {
			int iDongDaChon = tb.getSelectedRow();
			if (iDongDaChon != -1) {
				Vector vDongDaChon = (Vector) vData.get(iDongDaChon);
				tfID.setText(vDongDaChon.get(0).toString());
				tfTenDuAn.setText(vDongDaChon.get(1).toString());
				tfSoLuong.setText(vDongDaChon.get(2).toString());
				tfThuong.setText(vDongDaChon.get(3).toString());
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
