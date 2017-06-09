package xupt.se.ttms.view.studio;

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

public class StudioSelectUI extends PopUITmpl implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2047131747041243534L;

	private JButton btnCancel;
	
	protected boolean rst = false;
	private JLabel lblname,lblrow,lblcolumn,lbldesciption;
	protected JLabel txtname,txtrow,txtcolumn,txtdescription;
	
	Object data[] = new Object[4];
	protected void Select(String s){
		StudioSrv studio = new StudioSrv();
		List<Studio> pList = null;
		pList = new LinkedList<Studio>();
		pList = studio.Fetch_name(s);
		this.setTitle("演出厅信息");
		Iterator<Studio> itr = pList.iterator();
		while (itr.hasNext()){
			Studio stu = itr.next();
			data[0] = stu.getName();
			data[1] = stu.getRowCount();
			data[2] = stu.getColCount();
			data[3] = stu.getIntroduction();
			System.out.println(data[0].toString()+","+data[1].toString()+","+data[3].toString());
		}
	
	
		lblname = new JLabel("演出厅名称:");
		lblname.setBounds(260, 30,80, 30);
		contPan.add(lblname);
		txtname = new JLabel(data[0].toString());
		txtname.setBounds(350,30,200,30);
		contPan.add(txtname);
		
		lblrow = new JLabel("行数:");
		lblrow.setBounds(260, 80, 80, 30);
		contPan.add(lblrow);
		txtrow = new JLabel(data[1].toString());
		txtrow.setBounds(350, 80, 100, 30);
		contPan.add(txtrow);
		
		lblcolumn = new JLabel("列数:");
		lblcolumn.setBounds(260,120,80,30);
		contPan.add(lblcolumn);
		txtcolumn = new JLabel(data[2].toString());
		txtcolumn.setBounds(350,120,200,30);
		contPan.add(txtcolumn);
		
		lbldesciption = new JLabel("简介:");
		lbldesciption.setBounds(260, 170, 80, 30);
		contPan.add(lbldesciption);
		txtdescription = new JLabel(data[3].toString());
		txtdescription.setBounds(350,160,200,50);
		contPan.add(txtdescription);
		
		btnCancel = new JButton("关闭");
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
	


	

