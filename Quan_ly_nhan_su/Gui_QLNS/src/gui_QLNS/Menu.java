package gui_QLNS;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import gui_QLNS.Gui_QLDA;
import gui_QLNS.Gui_QLNV;
import gui_QLNS.Gui_QLPB;

public class Menu extends JFrame implements ActionListener {

	JFrame frMenu;

	public Menu() {

		frMenu = new JFrame("Menu");
		frMenu.setBounds(500, 150, 410, 250);
		frMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frMenu.getContentPane().setLayout(null);

		JButton btnQLNV = new JButton("Quản lý nhân viên");
		btnQLNV.setForeground(Color.BLUE);
		btnQLNV.addActionListener(this);
		btnQLNV.setBounds(20, 60, 140, 25);
		frMenu.getContentPane().add(btnQLNV);

		JButton btnQLDA = new JButton("Quản lý dự án");
		btnQLDA.setForeground(Color.BLUE);
		btnQLDA.addActionListener(this);
		btnQLDA.setBounds(20, 95, 140, 25);
		frMenu.getContentPane().add(btnQLDA);

		JButton btnQLPB = new JButton("Quản lý phòng ban");
		btnQLPB.setForeground(Color.BLUE);
		btnQLPB.addActionListener(this);
		btnQLPB.setBounds(20, 130, 140, 25);
		frMenu.getContentPane().add(btnQLPB);

		JButton btnExit = new JButton("Exit");
		btnExit.setForeground(Color.RED);
		btnExit.addActionListener(this);
		btnExit.setBounds(20, 165, 140, 25);
		frMenu.getContentPane().add(btnExit);

		JLabel lbQLNS = new JLabel("HUMAN RESOURCE MANAGEMENT");
		lbQLNS.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 25));
		lbQLNS.setForeground(Color.BLUE);
		lbQLNS.setBounds(5, 20, 390, 25);
		frMenu.getContentPane().add(lbQLNS);

		JLabel lbImage = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/myhome.png")).getImage();
		lbImage.setIcon(new ImageIcon(img));
		lbImage.setBounds(200, 40, 150, 175);
		frMenu.getContentPane().add(lbImage);

		frMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frMenu.setLocationRelativeTo(null);
		frMenu.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Quản lý nhân viên"))
			new Gui_QLNV();

		if (e.getActionCommand().equals("Quản lý dự án"))
			new Gui_QLDA();

		if (e.getActionCommand().equals("Quản lý phòng ban"))
			new Gui_QLPB();

		if (e.getActionCommand().equals("Exit")) {
			int hoi = JOptionPane.showConfirmDialog(null, "Bạn có chăc muốn thoát?", null,
					JOptionPane.YES_NO_OPTION);
			if (hoi == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}

	}
}