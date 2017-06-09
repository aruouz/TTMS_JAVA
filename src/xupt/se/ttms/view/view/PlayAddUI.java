package xupt.se.ttms.view.play;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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

import xupt.se.ttms.model.Dict;
import xupt.se.ttms.model.Play;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.service.DictSrv;
import xupt.se.ttms.service.PlaySrv;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.view.studio.ImageJPanel;
import xupt.se.ttms.view.tmpl.MainUITmpl;
import xupt.se.ttms.view.tmpl.PopUITmpl;

public class PlayAddUI extends PopUITmpl implements ActionListener {
	private JButton btnCancel, btnSave; 	//取消，保存按鈕

	protected boolean rst=false; 				//操作结果

	private JLabel lblName, lbltype, lbllang,lblintroduction,lbllength,lblticketPrice,lblstatus;
	protected JTextField txtName,txtlength,txtticketPrice;
	protected JComboBox<String> jcbtype,jcblan,jcbl,jcbsta;

	protected JTextArea txtintroduction;

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
        jcbtype = new JComboBox(str1);  
        java.util.List<Dict> listt = DictSrv.Fetch_Type_id(4);
		for(int i = 0;i<listt.size();i++){
			Dict dict = new Dict();
			dict = listt.get(i);
			jcbtype.addItem(DictSrv.Fetch_Value(dict.getDict_id()).get(0).getDict_value());
		}
        contPan.add(lbltype);  
        contPan.add(jcbtype);    
        lbltype.setBounds(260, 80, 50, 30);
        jcbtype.setBounds(350, 80, 120, 30);
        this.add(contPan);
        this.setVisible(true);  
	    

        lbllang = new JLabel("语言：");  
        String str2[] = {};  
        jcblan = new JComboBox(str2);  
        java.util.List<Dict> listlan = DictSrv.Fetch_Type_id(5);
		for(int i = 0;i<listlan.size();i++){
			Dict dict = new Dict();
			dict = listlan.get(i);
			jcblan.addItem(DictSrv.Fetch_Value(dict.getDict_id()).get(0).getDict_value());
		}
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
	
		lbllength = new JLabel("时长(分钟)："); 
		String str3[] = {};
        jcbl= new JComboBox(str3);  
        contPan.add(lbllength);  
        contPan.add(jcbl);    
        for(int i1=20;i1<500;i1++){
        	jcbl.addItem(""+i1);
        	
        }
        lbllength.setBounds(260, 300, 90, 30);
        jcbl.setBounds(350, 300, 120, 30);
        this.add(contPan);
        this.setVisible(true);
		
		lblticketPrice = new JLabel("票价：");
		lblticketPrice.setBounds(260, 350, 90, 30);
		contPan.add(lblticketPrice);
		txtticketPrice = new JTextField();
		txtticketPrice.setBounds(350, 350, 120, 30);
		contPan.add(txtticketPrice);
		
		lblstatus = new JLabel("状态：");  
        String str4[] = {"未安排演出","已安排演出","下线"};  
        jcbsta = new JComboBox(str4);  
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
		PlaySrv playSrv = new PlaySrv();
		Play play=new Play();
		play.setPlay_name(txtName.getText());
		java.util.List<Dict> list1=DictSrv.Fetch_value_id(jcbtype.getSelectedItem().toString());
		play.setPlay_type_id(list1.get(0).getDict_id());
		java.util.List<Dict> list2=DictSrv.Fetch_value_id(jcblan.getSelectedItem().toString());
		play.setPlay_lang_id(list2.get(0).getDict_id());
		play.setPlay_introduction(txtintroduction.getText());
		play.setPlay_length(Integer.parseInt(jcbl.getSelectedItem().toString()));
		play.setPlay_ticket_price(Integer.parseInt(txtticketPrice.getText()));
		int list3=PlaySrv.r_status(jcbsta.getSelectedItem().toString());
		play.setPlay_status(list3);

		playSrv.add(play);
		this.setVisible(false);
		rst=true;
		getParent().setVisible(true);
	 	
	}
}
