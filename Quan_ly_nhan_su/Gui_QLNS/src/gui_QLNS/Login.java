package gui_QLNS;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame implements ActionListener {

	JFrame frLogin;
	JTextField tfUser;
	JPasswordField tfPass;

	public Login() {

		frLogin = new JFrame("Login");
		frLogin.setBounds(500, 150, 460, 250);
		frLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frLogin.getContentPane().setLayout(null);

		JButton btnLogin = new JButton("Login");
		btnLogin.setBackground(Color.BLUE);
		btnLogin.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 17));
		btnLogin.setForeground(Color.WHITE);
		btnLogin.addActionListener(this);
		btnLogin.setBounds(135, 150, 150, 50);
		frLogin.getContentPane().add(btnLogin);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBackground(Color.RED);
		btnCancel.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 17));
		btnCancel.setForeground(Color.WHITE);
		btnCancel.addActionListener(this);
		btnCancel.setBounds(290, 150, 150, 50);
		frLogin.getContentPane().add(btnCancel);

		JLabel lbUser = new JLabel("UserName");
		tfUser = new JTextField(15);
		lbUser.setForeground(Color.BLUE);
		lbUser.setBounds(175, 70, 70, 30);
		frLogin.getContentPane().add(lbUser);

		JLabel lbPass = new JLabel("Password");
		tfPass = new JPasswordField("");
		lbPass.setForeground(Color.BLUE);
		lbPass.setBounds(175, 110, 70, 30);
		frLogin.getContentPane().add(lbPass);

		JLabel lbDangNhap = new JLabel("LOG IN TO SYSTEM");
		lbDangNhap.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 25));
		lbDangNhap.setForeground(Color.BLUE);
		lbDangNhap.setBounds(90, 10, 280, 40);
		frLogin.getContentPane().add(lbDangNhap);

		tfUser = new JTextField();
		tfUser.setBounds(295, 75, 110, 25);
		frLogin.getContentPane().add(tfUser);
		tfUser.setColumns(10);

		tfPass = new JPasswordField();
		tfPass.setBounds(295, 110, 110, 25);
		frLogin.getContentPane().add(tfPass);
		tfPass.setColumns(10);

		JLabel lbImage = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/login.png")).getImage();
		lbImage.setIcon(new ImageIcon(img));
		lbImage.setBounds(5, 40, 150, 175);
		frLogin.getContentPane().add(lbImage);

		JLabel lbAdmin = new JLabel("");
		Image img1 = new ImageIcon(this.getClass().getResource("/Admin.png")).getImage();
		lbAdmin.setIcon(new ImageIcon(img1));
		lbAdmin.setBounds(265, 60, 50, 50);
		frLogin.getContentPane().add(lbAdmin);

		JLabel lbKey = new JLabel("");
		Image img2 = new ImageIcon(this.getClass().getResource("/Key.png")).getImage();
		lbKey.setIcon(new ImageIcon(img2));
		lbKey.setBounds(265, 95, 50, 50);
		frLogin.getContentPane().add(lbKey);
	}

	public boolean Duplicate(String User) {
		try {
			String url="jdbc:sqlserver://localhost:1433;databaseName=gui_qlns;user=sa;password=123456";
            Connection conn = DriverManager.getConnection(url);
			PreparedStatement ps = conn.prepareStatement("select STT from Loginn where users = ?");
			ps.setString(1, tfUser.getText());
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
			PreparedStatement ps = conn.prepareStatement("insert into Loginn(users, pass) values(?, ?)");
			if(tfUser.getText().equals("")|| tfPass.getText().equals("") ) {
				JOptionPane.showMessageDialog(this, "Đừng để trống!");
			} else {
				if(Duplicate(tfUser.getText())) {
					JOptionPane.showMessageDialog(this, "Tài khoản đã tồn tại!");
				} else {
					ps.setString(1, tfUser.getText());
					ps.setString(2, tfPass.getText());
					int check = ps.executeUpdate();
					if(check > 0) {
						JOptionPane.showMessageDialog(this, "Đã thêm tài khoản thành công!");
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Login")) {
			try {
				String url="jdbc:sqlserver://localhost:1433;databaseName=gui_qlns;user=sa;password=123456";
	            Connection conn = DriverManager.getConnection(url);
				String username = tfUser.getText();
				String password = tfPass.getText();
				String sql = "SELECT * FROM Loginn WHERE users =  ? AND pass = ?";
	            PreparedStatement pst = conn.prepareStatement(sql);
	            pst.setString(1, username);
	            pst.setString(2, password);
	            ResultSet resultSet = pst.executeQuery();
	            
	            if (resultSet.next()) {
					new Menu();
					frLogin.setVisible(false);
				}else {
					int hoi = JOptionPane.showConfirmDialog(null, "Tài khoản không chính xác, vui lòng kiểm tra lại.\n" + 
	                        "Bạn có muốn tạo một tài khoản mới?",
			                null, JOptionPane.YES_NO_OPTION);
			        if (hoi == JOptionPane.YES_OPTION) {
			        	insert();
			        }
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		if (e.getActionCommand().equals("Cancel")) {
			int hoi = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn thoát?",
					null, JOptionPane.YES_NO_OPTION);
			if (hoi == JOptionPane.YES_OPTION) {
				frLogin.setVisible(false);
			}
		}
		if(e.getActionCommand().equals("Cancel")) {
			tfUser.setText("");
			tfPass.setText("");
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}