package xupt.se.ttms.view.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import xupt.se.ttms.view.system.SysUserModUI;
import xupt.se.ttms.view.tmpl.ImagePanel;
import xupt.se.ttms.view.tmpl.PopUITmpl;

public class UserUpdateUI extends JFrame implements ActionListener{
	private JLabel lblName, lblpassword;
	private JButton btnCancel, btnSave; 
	protected JTextField txtName;
	private static final long serialVersionUID = 1L;
	private int frmWidth=800;
	private int frmHeight=600;
	public final ImagePanel headPan = new ImagePanel("resource/image/header.png");
	public JPanel contPan = new JPanel();
	
     JPasswordField jPasswordField;

     public UserUpdateUI(){
    	 this.setSize(frmWidth, frmHeight);
    	 this.setLocationRelativeTo(null);
    	 this.setResizable(false);
    	 this.setLayout(null);
		
		headPan.setBounds(0, 0, frmWidth, 100);
		headPan.setLayout(null);
		this.add(headPan);
		
		contPan.setBounds(0, 150, frmWidth, this.frmHeight-100);
		contPan.setLayout(null);
		this.add(contPan);	
		
		//initHeader();
		//initContent();		
		
	/*
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new PopUITmpl().setVisible(true);
					
				} catch (Exception e) {
					javax.swing.JOptionPane.showMessageDialog(null, e, "Exception", 0);
					e.printStackTrace();
				}
			}
		});
	}
	*/
	
	//To be override by the detailed business block interface 
	//protected void initContent(){
		
		lblName = new JLabel("用户名：");
		lblName.setBounds(260,18,60,30);
		contPan.add(lblName);
		txtName = new JTextField();
		txtName.setBounds(335, 18, 200,30);
		contPan.add(txtName);
		
		lblpassword = new JLabel("密码：");
		lblpassword.setBounds(260, 80, 60, 30);
		contPan.add(lblpassword);
		jPasswordField = new JPasswordField();
		jPasswordField.setBounds(335, 80, 200, 30);
		contPan.add(jPasswordField);
		
		lblpassword = new JLabel("确认密码：");
		lblpassword.setBounds(260, 142, 60, 30);
		contPan.add(lblpassword);
		jPasswordField = new JPasswordField();
		jPasswordField.setBounds(335, 142, 200, 30);
		contPan.add(jPasswordField);
		
		btnSave = new JButton("保存");
		btnSave.addActionListener(this);
		btnSave.setBounds(320, 265, 60, 30);
		contPan.add(btnSave);

		btnCancel = new JButton("取消");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(440, 265, 60, 30);
		contPan.add(btnCancel);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("修改密码");
     }

     
     
 
	public void setModal(boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

