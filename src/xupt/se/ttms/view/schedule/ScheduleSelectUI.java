package xupt.se.ttms.view.schedule;

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

public class ScheduleSelectUI extends PopUITmpl implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2047131747041243534L;

	private JButton btnCancel;
	
	protected boolean rst = false;
	private JLabel lblSched_id,lblStudio_name,lblPlay_name,lblSched_time,lblSched_ticket_price;
	protected JLabel txtSched_id,txtStudio_name,txtPlay_name,txtSched_time,txtSched_ticket_price;
	
	Object data[] = new Object[5];
	protected void Select(int s){
		ScheduleSrv schedule = new ScheduleSrv();
		List<Schedule> pList = null;
		pList = new LinkedList<Schedule>();
		pList = schedule.Fetch_name(s);
		this.setTitle("演出厅信息");
		Iterator<Schedule> itr = pList.iterator();
		while (itr.hasNext()){
			Schedule sched = itr.next();
			data[0] = sched.getSched_id();
			List<Studio> list1=StudioSrv.Fetch_id(sched.getStudio_id());
			data[1] = list1.get(0).getName();
			List<Play> list2=PlaySrv.Fetch_id(sched.getPlay_id());
			data[2] = list2.get(0).getPlay_name();
			data[3] = sched.getSched_time();
			data[4] = Double.toString(sched.getSched_ticket_price());
			
		}
	
	
		lblSched_id = new JLabel("演出计划ID:");
		lblSched_id.setBounds(260, 30,80, 30);
		contPan.add(lblSched_id);
		txtSched_id = new JLabel(data[0].toString());
		txtSched_id.setBounds(350,30,200,30);
		contPan.add(txtSched_id);
		
		
		lblStudio_name = new JLabel("演出厅名称:");
		lblStudio_name.setBounds(260, 80, 80, 30);
		contPan.add(lblStudio_name);
		txtStudio_name= new JLabel(data[1].toString());
		txtStudio_name.setBounds(350, 80, 100, 30);
		contPan.add(txtStudio_name);
		
		lblPlay_name = new JLabel("剧目名称:");
		lblPlay_name.setBounds(260,130,80,30);
		contPan.add(lblPlay_name);
		txtPlay_name = new JLabel(data[2].toString());
		txtPlay_name.setBounds(350,130,200,30);
		contPan.add(txtPlay_name);
		
		lblSched_time = new JLabel("时间:");
		lblSched_time.setBounds(260, 180, 80, 30);
		contPan.add(lblSched_time);
		txtSched_time = new JLabel(data[3].toString());
		txtSched_time.setBounds(350,180,200,50);
		contPan.add(txtSched_time);
		
		lblSched_ticket_price = new JLabel("票价:");
		lblSched_ticket_price.setBounds(260, 230, 80, 30);
		contPan.add(lblSched_ticket_price);
		txtSched_ticket_price = new JLabel(data[4].toString());
		txtSched_ticket_price.setBounds(350,230,200,50);
		contPan.add(txtSched_ticket_price);
		
		btnCancel = new JButton("关闭");
		btnCancel.setBackground(new Color(255,246,143));
		btnCancel.addActionListener(this);
		btnCancel.setBounds(350,270,60,30);
		contPan.add(btnCancel);
   }
	
	public void actionPerformed(ActionEvent e) {  //设置关闭按钮作用
		if (e.getSource() == btnCancel) {
			rst=false;
			this.setVisible(false);
		} 
	}
}
	


	

