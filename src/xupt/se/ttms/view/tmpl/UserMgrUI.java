package xupt.se.ttms.view.user;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import xupt.se.ttms.model.User;
import xupt.se.ttms.service.UserSrv;
import xupt.se.ttms.view.user.UserTableMouseListener;
import xupt.se.ttms.view.user.UserTable;
import xupt.se.ttms.view.tmpl.MainUITmpl;

class UserTableMouseListener extends MouseAdapter {

	private JTable jt;
	private static  User user;

	public User getStudio() {
		return user;
	}

	public UserTableMouseListener(JTable jt, Object[] number, User user) {
		this.user = user;
		this.jt = jt;
	}

	// 监听到行号，将所选行的内容依次赋到 user对象，以便传有值对象到修改面板进行修改
	public void mouseClicked(MouseEvent event) {
		int row = jt.getSelectedRow();
		user.setUserID(Integer.parseInt(jt.getValueAt(row, 0).toString()));
		user.setName(jt.getValueAt(row, 1).toString());
		user.setPassword(jt.getValueAt(row, 2).toString()); // 0
		user.setEmail(jt.getValueAt(row, 3).toString());
		user.setPhoneNumber(jt.getValueAt(row, 4).toString());
		user.setAge(Integer.parseInt(jt.getValueAt(row, 5).toString()));
		user.setSex(jt.getValueAt(row, 6).toString());
		System.out.println(jt.getValueAt(row, 1).toString());
	}
}

class UserTable {

	private User user;
	private JTable jt = null;

	public UserTable(User user) {
		this.user = user;
	}

	// 创建JTable
	public void createTable(JScrollPane jp, Object[] columnNames, List<User> userList) {
		try {

			Object data[][] = new Object[userList.size()][columnNames.length];

			Iterator<User> itr = userList.iterator();
			int i = 0;
			while (itr.hasNext()) {
				User user = itr.next();
				data[i][0] = Integer.toString(user.getUserID());
				data[i][1] = user.getName();
				data[i][2] = user.getPassword();
				data[i][3] = user.getEmail();
				data[i][4] = user.getPhoneNumber();
				data[i][5] = Integer.toString(user.getAge());
				data[i][6] = user.getSex();
				i++;
			}

			// 生成JTable
			jt = new JTable(data, columnNames);
			jt.setBounds(0, 0, 700, 450);

			// 添加鼠标监听，监听到所选行
			UserTableMouseListener tml = new UserTableMouseListener(jt, columnNames, user);
			jt.addMouseListener(tml);

			// 设置可调整列宽
			jt.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

			jp.add(jt);
			jp.setViewportView(jt);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}



public class UserMgrUI extends MainUITmpl{
	
	private static final long serialVersionUID = 1L;
	private User user=new User();
	private JLabel ca1 = null; // 界面提示
	// 用来放表格的滚动控件
	private JScrollPane jsc;
	// 查找的提示和输出
	private JTextField input;

	// 查找，编辑和删除按钮
	private JButton btnAdd, btnDel;

	public UserMgrUI() {
	}

	// To be override by the detailed business block interface
	@Override
	protected void initContent() {
		Rectangle rect = contPan.getBounds();

		ca1 = new JLabel("用户管理", JLabel.CENTER);
		ca1.setBounds(0, 5, rect.width, 30);
		ca1.setFont(new java.awt.Font("宋体", 1, 20));
		ca1.setForeground(Color.blue);
		contPan.add(ca1);

		jsc = new JScrollPane();
		jsc.setBounds(0, 40, rect.width, rect.height - 90);
		contPan.add(jsc);

//		input = new JTextField();
//		input.setBounds(220, rect.height - 45, 200, 30);
//		contPan.add(input);

		btnAdd = new JButton("添加");
		btnAdd.setBounds(rect.width - 220, rect.height - 45, 60, 30);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				btnAddClicked();
			}
		});
		contPan.add(btnAdd);


		btnDel = new JButton("删除");
		btnDel.setBounds(rect.width - 120, rect.height - 45, 60, 30);
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				btnDelClicked();
			}
		});
		contPan.add(btnDel);
		contPan.add(ca1);
		showTable();
	}

	private void btnAddClicked() {
		UserAddUI adduser = new UserAddUI();
		adduser.setWindowName("添加用户");
		adduser.toFront();
		adduser.setModal(true);
		adduser.setVisible(true);

		if (adduser.getReturnStatus()) {
			showTable();
		}
	}

	private void btnModClicked() {
			
//		StudioEditUI modStu = new StudioEditUI(stud);
//		modStu.setWindowName("修改演出厅");
//		modStu.toFront();
//		modStu.setModal(true);
//		modStu.setVisible(true);
//		if (modStu.getReturnStatus()) {
//			showTable();
//		}
	}

	private void btnDelClicked() {
		int confirm = JOptionPane.showConfirmDialog(null, "确认删除所选？", "删除", JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			UserSrv userSrv = new UserSrv();
			userSrv.delete(user.getUserID());
			showTable();
		}
	}

	public void showTable() {
		UserTable tms = new UserTable(user);
		Object[] in = { "id", "name", "password", "email", "phoneNumber"," age" ,"sex"};
		List<User> userList = new UserSrv().FetchAll();

		tms.createTable(jsc, in, userList);
		jsc.repaint();
	}

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		UserMgrUI frmStuMgr = new UserMgrUI();
		frmStuMgr.setVisible(true);
	}

}
