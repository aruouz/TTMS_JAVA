package xupt.se.ttms.view.tmpl;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PopUITmpl extends JDialog implements ActionListener {
	
	private JLabel lblName, lblpassword;
	private JButton btnCancel, btnSave; 
	
	protected JTextField txtName, txtpassword;
	private static final long serialVersionUID = 1L;
	private int frmWidth=800;
	private int frmHeight=600;
	public final ImagePanel headPan = new ImagePanel("resource/image/header.png");
	public JPanel contPan = new JPanel();
	public JLabel windowName = new JLabel();

	public PopUITmpl(){
		this.setSize(frmWidth, frmHeight);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				onWindowClosing();
			}
		});		
		
		headPan.setBounds(0, 0, frmWidth, 60);
		headPan.setLayout(null);
		this.add(headPan);
		
		contPan.setBounds(0, 60, frmWidth, this.frmHeight-80);
		contPan.setLayout(null);
		this.add(contPan);	
		
		initHeader();
		initContent();		
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new PopUITmpl().setVisible(true);;
					
				} catch (Exception e) {
					javax.swing.JOptionPane.showMessageDialog(null, e, "Exception", 0);
					e.printStackTrace();
				}
			}
		});
		
	}
	
	private void initHeader() {
		try {


			windowName.setBounds(frmWidth-160, 5, 160, 50);
			windowName.setFont(new java.awt.Font("dialog", 1, 20));
			windowName.setForeground(Color.blue);	
			headPan.add(windowName);
			setWindowName("修改密码");
			
			
		} catch (Exception e) {
			javax.swing.JOptionPane.showMessageDialog(null, e, "Exception", 0);
			e.printStackTrace();
		}
	}
	

	//Set the name of the poppup window 
	
	public void setWindowName(String name){
		windowName.setText(name);
	}
	
	//To be override by the detailed business block interface 
	protected void onWindowClosing(){
		this.dispose();
	}
	
	
	//To be override by the detailed business block interface 
	protected void initContent(){
//		lblName = new JLabel("用户名：");
//		lblName.setBounds(260, 30, 100, 30);
//		contPan.add(lblName);
//		txtName = new JTextField();
//		txtName.setBounds(350, 30, 140, 30);
//		contPan.add(txtName);
//		
//		lblpassword = new JLabel("新密码：");
//		lblpassword.setBounds(260, 80, 100, 30);
//		contPan.add(lblpassword);
//		txtpassword = new JTextField();
//		txtpassword.setBounds(350, 80, 140, 30);
//		contPan.add(txtpassword);
//		
//		lblpassword = new JLabel("确认密码：");
//		lblpassword.setBounds(260, 130, 100, 30);
//		contPan.add(lblpassword);
//		txtpassword = new JTextField();
//		txtpassword.setBounds(350, 130, 140, 30);
//		contPan.add(txtpassword);
//		
//		btnSave = new JButton("保存");
//
//		btnSave.addActionListener(this);
//		btnSave.setBounds(260, 180, 60, 30);
//		contPan.add(btnSave);
//
//		btnCancel = new JButton("取消");
//		btnCancel.addActionListener(this);
//		btnCancel.setBounds(380, 180, 60, 30);
//		contPan.add(btnCancel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		
	}
	

}
