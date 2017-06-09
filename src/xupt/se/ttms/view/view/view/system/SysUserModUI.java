package xupt.se.ttms.view.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import xupt.se.ttms.view.studio.StudioMgrUI;
import xupt.se.ttms.view.tmpl.ImagePanel;
import xupt.se.ttms.view.tmpl.PopUITmpl;

public class SysUserModUI extends JFrame{
	
	private JLabel lblName, lblpassword;
	private JButton btnCancel, btnSave; 
	protected JTextField txtName;
	private static final long serialVersionUID = 1L;
	private int frmWidth=800;
	private int frmHeight=600;
	public final ImagePanel headPan = new ImagePanel("resource/image/header.png");
	public JPanel contPan = new JPanel();
	
     JPasswordField jPasswordField;
     JRadioButton jrb1,jrb2,jrb3;

     public SysUserModUI(){
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
		
		jrb1 = new JRadioButton("管理员");
        jrb1.setBounds(305, 160, 65, 30);
        contPan.add(jrb1);
        jrb2 = new JRadioButton("售票员");
        jrb2.setBounds(380, 160, 65, 30);
        contPan.add(jrb2);
        jrb3 = new JRadioButton("经理");
        jrb3.setBounds(455, 160, 60, 30);
        contPan.add(jrb3);
        ButtonGroup bGroup = new ButtonGroup();
        bGroup.add(jrb1);
        bGroup.add(jrb2);
        bGroup.add(jrb3);
		
		btnSave = new JButton("确认");
		//btnSave.addActionListener(this);
		btnSave.setBounds(320, 265, 60, 30);
		contPan.add(btnSave);

		btnCancel = new JButton("取消");
		//btnCancel.addActionListener(this);
		btnCancel.setBounds(440, 265, 60, 30);
		contPan.add(btnCancel);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("登录");
     }

/*
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		
	}
*/

     public static void main(String[] args){
         new SysUserModUI();
     }

	public void setModal(boolean b) {
		// TODO Auto-generated method stub
		
	}
	
 }