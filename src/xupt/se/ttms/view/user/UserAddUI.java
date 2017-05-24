package xupt.se.ttms.view.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextField;

//import view.studioUI.ImageJPanel;
import xupt.se.ttms.model.User;
import xupt.se.ttms.service.UserSrv;
import xupt.se.ttms.view.studio.ImageJPanel;
import xupt.se.ttms.view.tmpl.*;
public class UserAddUI extends PopUITmpl implements ActionListener {

	private JButton btnCancel, btnSave; 	//取消，保存按鈕

	protected boolean rst=false; 				//操作结果
	private JLabel lblName, lblpassword, lblemail,lblPhoneNumber,lblage,lblsex;
	protected JTextField txtName, txtpassword, txtemail,txtPhoneNumber,txtage,txtsex;

	public UserAddUI() {
	}

	@Override
	protected void initContent(){
		this.setTitle("添加新用户");

		lblName = new JLabel("用户名：");
		lblName.setBounds(60, 30, 80, 30);
		contPan.add(lblName);
		txtName = new JTextField();
		txtName.setBounds(150, 30, 120, 30);
		contPan.add(txtName);

		lblpassword = new JLabel("密码：");
		lblpassword.setBounds(60, 80, 50, 30);
		contPan.add(lblpassword);
		txtpassword = new JTextField();
		txtpassword.setBounds(150, 80, 120, 30);
		contPan.add(txtpassword);

		lblemail = new JLabel("邮箱：");
		lblemail.setBounds(60, 130, 90, 30);
		contPan.add(lblemail);
		txtemail = new JTextField();
		txtemail.setBounds(150, 130, 120, 30);
		contPan.add(txtemail);
		
		lblPhoneNumber = new JLabel("电话：");
		lblPhoneNumber.setBounds(60, 180, 90, 30);
		contPan.add(lblPhoneNumber);
		txtPhoneNumber = new JTextField();
		txtPhoneNumber.setBounds(150, 180, 120, 30);
		contPan.add(txtPhoneNumber);
		
		lblage = new JLabel("年龄：");
		lblage.setBounds(60, 230, 90, 30);
		contPan.add(lblage);
		txtage = new JTextField();
		txtage.setBounds(150, 230, 120, 30);
		contPan.add(txtage);
		
		lblsex = new JLabel("性别：");
		lblsex.setBounds(60, 280, 90, 30);
		contPan.add(lblsex);
		txtsex = new JTextField();
		txtsex.setBounds(150, 280, 120, 30);
		contPan.add(txtsex);

		btnSave = new JButton("保存");

		btnSave.addActionListener(this);
		btnSave.setBounds(60, 330, 60, 30);
		contPan.add(btnSave);

		btnCancel = new JButton("取消");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(180, 330, 60, 30);
		contPan.add(btnCancel);

		ImageJPanel imageJP = new ImageJPanel(new ImageIcon(
				"files/imgs/pencil.jpg").getImage());
		imageJP.setBounds(360, 160, 100, 100);
		imageJP.setLayout(null);
		this.add(imageJP);
	}
	
	
	public boolean getReturnStatus(){
		   return rst;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			rst=false;
			this.dispose();
			getParent().setVisible(true);

		} else if (e.getSource() == btnSave) {
			btnSaveClicked();		//以前未调用，新添加的调用语句
		}

	}
	
	protected void btnSaveClicked(){
		if (txtName.getText() != null && txtpassword.getText() != null
				&& txtemail.getText() != null && txtPhoneNumber.getText() != null && txtage.getText() != null && txtsex.getText() != null ) {
			UserSrv userSrv = new UserSrv();
			User user=new User();
			user.setName(txtName.getText());
			user.setPassword(txtpassword.getText());
			user.setEmail(txtemail.getText());
			user.setPhoneNumber(txtPhoneNumber.getText());
			user.setAge(Integer.parseInt(txtage.getText()));
			user.setSex(txtsex.getText());
		

			userSrv.add(user);
			this.setVisible(false);
			rst=true;
			getParent().setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "数据不完整");
		}		
	}
}
