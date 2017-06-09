package xupt.se.ttms.view.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import xupt.se.ttms.model.Employee;
import xupt.se.ttms.service.EmployeeSrv;
import xupt.se.ttms.view.sellticket.SellSelect;
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
	private Employee emp_g= new Employee();
	private JPasswordField password= new JPasswordField();
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
		
		lblName = new JLabel("用户ID：");
		lblName.setBounds(260,18,60,30);
		contPan.add(lblName);
		txtName = new JTextField();
		txtName.setBounds(335, 18, 200,30);
		contPan.add(txtName);
		
		
		lblpassword = new JLabel("密码：");
		lblpassword.setBounds(260, 80, 60, 30);
		contPan.add(lblpassword);
		
		password.setBounds(335, 80, 200, 30);
		contPan.add(password);
		
//		jrb1 = new JRadioButton("管理员");
//        jrb1.setBounds(305, 160, 65, 30);
//        contPan.add(jrb1);
//        jrb2 = new JRadioButton("售票员");
//        jrb2.setBounds(380, 160, 65, 30);
//        contPan.add(jrb2);
//        jrb3 = new JRadioButton("经理");
//        jrb3.setBounds(455, 160, 60, 30);
//        contPan.add(jrb3);
//        ButtonGroup bGroup = new ButtonGroup();
//        bGroup.add(jrb1);
//        bGroup.add(jrb2);
//        bGroup.add(jrb3);
		
		btnSave = new JButton("确认");
		btnSave.setBackground(new Color(255,246,143));
		btnSave.addActionListener(new ActionListener(){

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int r=Integer.parseInt(txtName.getText());
				emp_g.setId(r);
				emp_g.setPassword(password.getText());
				setModal();
			}
		
		});
		btnSave.setBounds(320, 205, 60, 30);
		contPan.add(btnSave);

		btnCancel = new JButton("取消");
		btnCancel.setBackground(new Color(255,246,143));
		//btnCancel.addActionListener(this);
		btnCancel.setBounds(440, 205, 60, 30);
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
         SysUserModUI mbs=new SysUserModUI();
         
     }

	public void setModal() {
		 
		Employee emp=EmployeeSrv.Fetch_id(emp_g.getId()).get(0);
		
		
		if(emp.getId()==emp_g.getId()){
			if(emp.getPassword().equals(emp_g.getPassword())){
				if(emp.getAccess()==0){
					System.out.print("售票员");
					 SellSelect s = new SellSelect();
			        s.setSize(1024, 700);
			        s.setVisible(true);
			       setVisible(false);
					
				}
				if(emp.getAccess()==1){
					System.out.print("经理");
					Menu menu =new Menu();
					menu.setVisible(true);
				}
				if(emp.getAccess()==2){
					System.out.print("您是管理员，即将进入管理界面");
					Menu menu =new Menu();
					menu.setVisible(false);
				}
			}
		}
		
	}
	
 }