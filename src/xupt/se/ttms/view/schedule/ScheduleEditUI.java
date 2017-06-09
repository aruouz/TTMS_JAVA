package xupt.se.ttms.view.schedule;


import javax.swing.JOptionPane;

import xupt.se.ttms.model.Play;
import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.service.PlaySrv;
import xupt.se.ttms.service.ScheduleSrv;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.view.schedule.ScheduleAddUI;;

public class ScheduleEditUI extends ScheduleAddUI{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Schedule schedule;

	public ScheduleEditUI(Schedule sched){
		initData(sched);
	}
	
	public void initData(Schedule sched) {
		if(null== sched){
			return;
		}
		
		
		StudioSrv getss=new StudioSrv();
        java.util.List<Studio> listss=getss.Fetch_id(sched.getStudio_id());
        java.util.List<Studio> listssum=getss.FetchAll();
        int ssum=0;
        for(;ssum<listssum.size();ssum++){
        	if(listss.get(0).getID()==listssum.get(ssum).getID()){
        		jcbStudioName.setSelectedIndex(ssum);
        	}
        }
        
        
        
        
        PlaySrv getps=new PlaySrv();
        java.util.List<Play> listps=getps.Fetch_id(sched.getPlay_id());
        java.util.List<Play> listpsum=getps.FetchALL();
         int psum=0;
        for(;psum<listpsum.size();psum++)
        	if(listps.get(0).getPlay_id()==listpsum.get(psum).getPlay_id())
        		jcbPlayName.setSelectedIndex(psum);
        
        
        
		char[] timeCorrent=sched.getSched_time().toCharArray();
		 
		String y="",m = "",d = "",h = "",f = "";
		
		for(int i=0;i<4;i++)
			y+=timeCorrent[i];
		System.out.println(y);
		for(int j=5;j<7;j++)
			m+=timeCorrent[j];
		for(int k=8;k<10;k++)
			d+=timeCorrent[k];
		for(int p=11;p<13;p++)
			h+=timeCorrent[p];
		for(int q=14;q<16;q++)
			f+=timeCorrent[q];
		
	
		int y1 = Integer.parseInt(y);
		int m1 = Integer.parseInt(m);
		int d1 = Integer.parseInt(d);
		int h1 = Integer.parseInt(h);
		int f1 = Integer.parseInt(f);
		
		
		jcbSched_time1.setSelectedIndex(y1-2017);
		jcbSched_time2.setSelectedIndex(m1-1);
		jcbSched_time3.setSelectedIndex(d1-1);
		jcbSched_time4.setSelectedIndex(h1);
		jcbSched_time5.setSelectedIndex(f1);
		
		
			
		txtSched_ticket_price.setText(Double.toString(sched.getSched_ticket_price()));
		
		
		
		
		schedule=sched;
		this.invalidate();
	}

	@Override
	protected void btnSaveClicked(){

			ScheduleSrv schedSrv = new ScheduleSrv();
			Schedule sched=schedule;
			java.util.List<Studio> list1=StudioSrv.Fetch_name(jcbStudioName.getSelectedItem().toString());
			sched.setStudio_id(list1.get(0).getID());
			java.util.List<Play> list2=PlaySrv.Fetch_name(jcbPlayName.getSelectedItem().toString());
			sched.setPlay_id(list2.get(0).getPlay_id());
			sched.setSched_time(jcbSched_time1.getSelectedItem().toString()+"-"+jcbSched_time2.getSelectedItem().toString()+"-"+jcbSched_time3.getSelectedItem().toString()+" "+jcbSched_time4.getSelectedItem().toString()+":"+jcbSched_time5.getSelectedItem().toString());
			sched.setSched_ticket_price(Double.parseDouble(txtSched_ticket_price.getText()));

			schedSrv.modify(sched);
			this.setVisible(false);
			rst=true;
			getParent().setVisible(true);
			
			
	}
	
}
