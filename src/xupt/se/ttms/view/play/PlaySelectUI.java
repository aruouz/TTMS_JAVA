package xupt.se.ttms.view.play;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import xupt.se.ttms.model.*;
import xupt.se.ttms.service.*;
import xupt.se.ttms.dao.*;
import xupt.se.ttms.view.tmpl.*;

public class PlaySelectUI extends PopUITmpl implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2047131747041243534L;

	private JButton btnCancel;
	
	protected boolean rst = false;
	private JLabel lbl,lblname,lbltype,lbllan,lbllong,lblprice,lblstatus;
	protected JLabel txt,txtname,txttype,txtlan,txtlong,txtprice,txtstatus;
	
	Object data[] = new Object[8];
	protected void Select(String s){
		PlaySrv play = new PlaySrv();


		List<Play> pList = play.Fetch_name(s);
		this.setTitle("剧目信息");
		Iterator<Play> itr = pList.iterator();
		while (itr.hasNext()){
			Play play1 = itr.next();
			
			data[0] = Integer.toString(play1.getPlay_id());
			List<Dict> list1=DictSrv.Fetch_Value(play1.getPlay_type_id());
			data[1] = list1.get(0).getDict_value();
			List<Dict> list2=DictSrv.Fetch_Value(play1.getPlay_lang_id());
			data[2] = list2.get(0).getDict_value();
			data[3] = play1.getPlay_name();
			data[4] = play1.getPlay_introduction();
			data[5] = Integer.toString(play1.getPlay_length());
			data[6] = play1.getPlay_ticket_price()+"";
			data[7] = PlaySrv.status(play1.getPlay_status());
//			System.out.println(data[1].toString()+","+data[2].toString()+","+data[3].toString()+","+data[4].toString()+","+data[5]+","+data[6]+","+data[7].toString());
		}
	
	
		lblname = new JLabel("剧目名称:");
		lblname.setBounds(260, 30, 80, 30);
		contPan.add(lblname);
		txtname = new JLabel(data[3].toString());
		txtname.setBounds(350,30,200,30);
		contPan.add(txtname);
		
		lbltype = new JLabel("类型:");
		lbltype.setBounds(260, 80, 80, 30);
		contPan.add(lbltype);
		txttype = new JLabel(data[1].toString());
		txttype.setBounds(350, 80, 100, 30);
		contPan.add(txttype);
		
		lbllan = new JLabel("语言:");
		lbllan.setBounds(260,130,80,30);
		contPan.add(lbllan);
		txtlan = new JLabel(data[2].toString());
		txtlan.setBounds(350,130,200,30);
		contPan.add(txtlan);
		
		lbllong = new JLabel("时长:");
		lbllong.setBounds(260, 180, 80, 30);
		contPan.add(lbllong);
		txtlong = new JLabel(data[5].toString());
		txtlong.setBounds(350,180,200,30);
		contPan.add(txtlong);
		
		lblprice = new JLabel("票价:");
		lblprice.setBounds(260, 230, 80, 30);
		contPan.add(lblprice);
		txtprice = new JLabel(data[6].toString());
		txtprice.setBounds(350,230,200,30);
		contPan.add(txtprice);
		
		lblstatus = new JLabel("演出状态:");
		lblstatus.setBounds(260, 280, 80, 30);
		contPan.add(lblstatus);
		txtstatus = new JLabel(data[7].toString());
		txtstatus.setBounds(350,280,200,30);
		contPan.add(txtstatus);
		
		lbl = new JLabel("简介:");
		lbl.setBounds(260, 330, 80, 30);
		contPan.add(lbl);
		txt = new JLabel(data[4].toString());
		txt.setBounds(350,330,200,30);
		contPan.add(txt);
		
		btnCancel = new JButton("关闭");
		btnCancel.setBackground(new Color(255,246,143));
		btnCancel.addActionListener(this);
		btnCancel.setBounds(350,380,60,30);
		contPan.add(btnCancel);
   }
	
	public void actionPerformed(ActionEvent e) {  //设置关闭按钮作用
		if (e.getSource() == btnCancel) {
			rst=false;
			this.setVisible(false);
		} 
	}
}
	


	

