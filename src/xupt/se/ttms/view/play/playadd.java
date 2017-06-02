package xupt.se.ttms.view.play;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import xupt.se.ttms.model.Play;
import xupt.se.ttms.service.PlaySrv;
import xupt.se.ttms.view.studio.ImageJPanel;
import xupt.se.ttms.view.tmpl.MainUITmpl;
import xupt.se.ttms.view.tmpl.PopUITmpl;

public class PlayAddUI extends PopUITmpl implements ActionListener {
	private JButton btnCancel, btnSave; 	//取消，保存按鈕

	protected boolean rst=false; 				//操作结果
	private JLabel lblName, lbltype, lbllang,lblintroduction,lbllength,lblticketPrice,lblstatus;
	protected JTextField txtName, txttype, txtlang,txtlength,txtticketPrice,txtstatus;

	JTextArea txtintroduction;

	public PlayAddUI() {
		
	}

	@Override
	protected void initContent(){
		this.setTitle("添加剧目信息");

		lblName = new JLabel("剧目名称：");
		lblName.setBounds(260, 30, 80, 30);
		contPan.add(lblName);
		txtName = new JTextField();
		txtName.setBounds(350, 30, 200, 30);
		contPan.add(txtName);

		lbltype = new JLabel("类型：");  
        String str1[] = {};  
        JComboBox jcbtype = new JComboBox(str1);  
        contPan.add(lbltype);  
        contPan.add(jcbtype);    
        lbltype.setBounds(260, 80, 50, 30);
        jcbtype.setBounds(350, 80, 120, 30);
        this.add(contPan);
        this.setVisible(true);  
	    

        lbllang = new JLabel("语言：");  
        String str2[] = {};  
        JComboBox jcblan = new JComboBox(str2);  
        contPan.add(lbllang);  
        contPan.add(jcblan);    
        lbllang.setBounds(260, 130, 90, 30);
        jcblan.setBounds(350, 130, 120, 30);
        this.add(contPan);
        this.setVisible(true); 
	
		lblintroduction = new JLabel("剧目简介：");
		lblintroduction.setBounds(260, 180, 90, 30);
		contPan.add(lblintroduction);
		txtintroduction = new JTextArea();
		txtintroduction.setBounds(350, 180, 300, 100);
		contPan.add(txtintroduction);
		txtintroduction.grabFocus();
		
		lbllength = new JLabel("时长：");
		lbllength.setBounds(260, 300, 90, 30);
		contPan.add(lbllength);
		txtlength = new JTextField();
		txtlength.setBounds(350, 300, 120, 30);
		contPan.add(txtlength);
		
		lblticketPrice = new JLabel("票价：");
		lblticketPrice.setBounds(260, 350, 90, 30);
		contPan.add(lblticketPrice);
		txtticketPrice = new JTextField();
		txtticketPrice.setBounds(350, 350, 120, 30);
		contPan.add(txtticketPrice);
		
		lblstatus = new JLabel("状态：");  
        String str3[] = {};  
        JComboBox jcbsta = new JComboBox(str3);  
        contPan.add(lblstatus);  
        contPan.add(jcbsta);    
        lblstatus.setBounds(260, 400, 90, 30);
        jcbsta.setBounds(350, 400, 120, 30);
        this.add(contPan);
        this.setVisible(true);

		btnSave = new JButton("保存");
		btnSave.addActionListener(this);
		btnSave.setBounds(300, 450, 60, 30);
		contPan.add(btnSave);

		btnCancel = new JButton("取消");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(400, 450, 60, 30);
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
		
		if (txtName.getText() != null && txttype.getText() != null && txtlang.getText() != null && txtintroduction.getText() != null && txtlength .getText() != null && txtticketPrice.getText() != null && txtstatus.getText() != null ) {
			PlaySrv playSrv = new PlaySrv();
			Play play=new Play();
			play.setPlay_name(txtName.getText());
			play.setPlay_type_id(Integer.parseInt(txttype.getText()));
			play.setPlay_lang_id(Integer.parseInt(txtlang.getText()));
			play.setPlay_introduction("test");
			play.setPlay_length(Integer.parseInt(txtlength.getText()));
			play.setPlay_ticket_price(Integer.parseInt(txtticketPrice.getText()));
			play.setPlay_status(Integer.parseInt(txtstatus.getText()));

			playSrv.add(play);
			this.setVisible(false);
			rst=true;
			getParent().setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "数据不完整");
		}		
	}
}
