package xupt.se.ttms.view.play;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import xupt.se.ttms.model.Play;
import xupt.se.ttms.service.PlaySrv;
import xupt.se.ttms.view.studio.ImageJPanel;
import xupt.se.ttms.view.tmpl.PopUITmpl;

public class PlayAddUI extends PopUITmpl implements ActionListener {
	private JButton btnCancel, btnSave; 	//取消，保存按鈕

	protected boolean rst=false; 				//操作结果
	private JLabel lblName, lbltype, lbllang,lblintroduction,lbllength,lblticketPrice,lblstatus;
	protected JTextField txtName, txttype, txtlang,txtintroduction,txtlength,txtticketPrice,txtstatus;

	public PlayAddUI() {
	}

	@Override
	protected void initContent(){
		this.setTitle("添加剧目信息");

		lblName = new JLabel("剧目名称：");
		lblName.setBounds(60, 30, 80, 30);
		contPan.add(lblName);
		txtName = new JTextField();
		txtName.setBounds(150, 30, 120, 30);
		contPan.add(txtName);

		lbltype = new JLabel("类型：");
		lbltype.setBounds(60, 80, 50, 30);
		contPan.add(lbltype);
		txttype = new JTextField();
		txttype.setBounds(150, 80, 120, 30);
		contPan.add(txttype);

		lbllang = new JLabel("语言：");
		lbllang.setBounds(60, 130, 90, 30);
		contPan.add(lbllang);
		txtlang = new JTextField();
		txtlang.setBounds(150, 130, 120, 30);
		contPan.add(txtlang);
		
		lblintroduction = new JLabel("剧目简介：");
		lblintroduction.setBounds(60, 180, 90, 30);
		contPan.add(lblintroduction);
		txtintroduction = new JTextField();
		txtintroduction.setBounds(150, 180, 120, 30);
		contPan.add(txtintroduction);
		
		lbllength = new JLabel("时长：");
		lbllength.setBounds(60, 230, 90, 30);
		contPan.add(lbllength);
		txtlength = new JTextField();
		txtlength.setBounds(150, 230, 120, 30);
		contPan.add(txtlength);
		
		lblticketPrice = new JLabel("票价：");
		lblticketPrice.setBounds(60, 280, 90, 30);
		contPan.add(lblticketPrice);
		txtticketPrice = new JTextField();
		txtticketPrice.setBounds(150, 280, 120, 30);
		contPan.add(txtticketPrice);
		
		lblstatus = new JLabel("状态：");
		lblstatus.setBounds(60, 330, 90, 30);
		contPan.add(lblstatus);
		txtstatus = new JTextField();
		txtstatus.setBounds(150, 330, 120, 30);
		contPan.add(txtstatus);

		btnSave = new JButton("保存");

		btnSave.addActionListener(this);
		btnSave.setBounds(60, 380, 60, 30);
		contPan.add(btnSave);

		btnCancel = new JButton("取消");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(180, 380, 60, 30);
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
