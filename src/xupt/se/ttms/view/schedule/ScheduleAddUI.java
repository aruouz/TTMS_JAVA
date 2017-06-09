
package xupt.se.ttms.view.schedule;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import xupt.se.ttms.model.Play;
import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.service.PlaySrv;
import xupt.se.ttms.service.ScheduleSrv;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.view.studio.ImageJPanel;
import xupt.se.ttms.view.tmpl.*;

public class ScheduleAddUI extends PopUITmpl implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JButton btnCancel, btnSave; 	//取消，保存按鈕

	protected boolean rst=false; 				//操作结果
	private JLabel lblPlayName, lblStudioName,lblSched_time,lblSched_time1,lblSched_time2,lblSched_time3,lblSched_time4,lblSched_time5,lblSched_ticket_price;
	protected JTextField txtSched_ticket_price;
	protected JComboBox jcbStudioName,jcbPlayName,jcbSched_time,jcbSched_time1,jcbSched_time2,jcbSched_time3, jcbSched_time4, jcbSched_time5;
	public ScheduleAddUI() {
	}
	

	@Override
	protected void initContent(){
		this.setTitle("添加演出演出计划");
		
		lblStudioName = new JLabel("演出厅名称：");  
        String studioName[] = {};  
        jcbStudioName = new JComboBox(studioName);  
        contPan.add(lblStudioName);  
        contPan.add(jcbStudioName);    
        StudioSrv getss=new StudioSrv();
        java.util.List<Studio> listss=getss.FetchAll();
        for(int i=0;i<listss.size();i++){
        	Studio stu=new Studio();
        	stu=listss.get(i);
        	jcbStudioName.addItem(stu.getName());
        }
		lblStudioName.setBounds(260, 30, 80, 30);
        jcbStudioName.setBounds(350, 30, 120, 30);
        this.add(contPan);
        this.setVisible(true);

        lblPlayName = new JLabel("剧目名称：");  
        String playName[] = {};  
        jcbPlayName = new JComboBox(playName);  
        contPan.add(lblPlayName);  
        contPan.add(jcbPlayName);   
        PlaySrv getps=new PlaySrv();
        java.util.List<Play> listps=getps.FetchALL();
        for(int i=0;i<listps.size();i++){
        	Play ply=new Play();
        	ply=listps.get(i);
        	if(ply.getPlay_status()==1 || ply.getPlay_status()==0)
        	jcbPlayName.addItem(ply.getPlay_name());
        }
		lblPlayName.setBounds(260, 80, 80, 30);
        jcbPlayName.setBounds(350, 80, 120, 30);
        this.add(contPan);
        this.setVisible(true);

        lblSched_time= new JLabel("演出时间：");  
        lblSched_time1= new JLabel("年"); 
        lblSched_time2= new JLabel("月"); 
        lblSched_time3= new JLabel("日"); 
        lblSched_time4= new JLabel("时"); 
        lblSched_time5= new JLabel("分"); 
        String sched_time[] = {};  
        jcbSched_time1 = new JComboBox(sched_time); 
        jcbSched_time2= new JComboBox(sched_time);
        jcbSched_time3 = new JComboBox(sched_time);
        jcbSched_time4 = new JComboBox(sched_time);
        jcbSched_time5 = new JComboBox(sched_time);
        contPan.add(lblSched_time);  
        contPan.add(lblSched_time1);  
        contPan.add(lblSched_time2);  
        contPan.add(lblSched_time3);  
        contPan.add(lblSched_time4);  
        contPan.add(lblSched_time5);  
        contPan.add(jcbSched_time1); 
        contPan.add(jcbSched_time2);
        contPan.add(jcbSched_time3);
        contPan.add(jcbSched_time4);
        contPan.add(jcbSched_time5);
        int year=0,month=0;
        for(int i=2017;i<2050;i++)
        	jcbSched_time1.addItem(i);
        
        for(int j=1;j<=12;j++)
        	jcbSched_time2.addItem(j);
        	
      
        for(int k=1;k<=31;k++)
                jcbSched_time3.addItem(k);
        
        for(int h=0;h<=24;h++)
        	jcbSched_time4.addItem(h);
        
        for(int m=0;m<=59;m++)
        	jcbSched_time5.addItem(m);
        	

        lblSched_time.setBounds(40, 130, 80, 30);
        jcbSched_time1.setBounds(100, 130, 90, 30);
        lblSched_time1.setBounds(190, 130, 20, 30);
        jcbSched_time2.setBounds(210, 130, 90, 30);
        lblSched_time2.setBounds(300, 130, 20, 30);
        jcbSched_time3.setBounds(320, 130, 90, 30);
        lblSched_time3.setBounds(410, 130, 20, 30);
		jcbSched_time4.setBounds(430,130,90,30);
		lblSched_time4.setBounds(540, 130, 20, 30);
		jcbSched_time5.setBounds(560,130,90,30);
		lblSched_time5.setBounds(650, 130, 20, 30);
        
        this.add(contPan);
        this.setVisible(true);
        
		lblSched_ticket_price = new JLabel("票价：");
		lblSched_ticket_price.setBounds(260, 180, 90, 30);
		contPan.add(lblSched_ticket_price);
		txtSched_ticket_price = new JTextField();
		txtSched_ticket_price.setBounds(350, 180, 120, 30);
		contPan.add(txtSched_ticket_price);

		btnSave = new JButton("保存");
		btnSave.setBackground(new Color(255,246,143));
		btnSave.addActionListener(this);
		btnSave.setBounds(260, 220, 60, 30);
		contPan.add(btnSave);

		btnCancel = new JButton("取消");
		btnCancel.setBackground(new Color(255,246,143));
		btnCancel.addActionListener(this);
		btnCancel.setBounds(380, 220, 60, 30);
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
			this.setVisible(false);
		} else if (e.getSource() == btnSave) {
			btnSaveClicked();
		}
	}
	
	protected void btnSaveClicked(){
		
			ScheduleSrv schedSrv = new ScheduleSrv();
			Schedule sched=new Schedule();
			java.util.List<Studio> list1=StudioSrv.Fetch_name(jcbStudioName.getSelectedItem().toString());
			sched.setStudio_id(list1.get(0).getID());
			java.util.List<Play> list2=PlaySrv.Fetch_name(jcbPlayName.getSelectedItem().toString());
			sched.setPlay_id(list2.get(0).getPlay_id());
			sched.setSched_time(jcbSched_time1.getSelectedItem().toString()+"-"+jcbSched_time2.getSelectedItem().toString()+"-"+jcbSched_time3.getSelectedItem().toString()+" "+jcbSched_time4.getSelectedItem().toString()+":"+jcbSched_time5.getSelectedItem().toString());
			sched.setSched_ticket_price(Double.parseDouble(txtSched_ticket_price.getText()));


			schedSrv.addscl(sched);
			this.setVisible(false);
			rst=true;
	
	}

}
