package xupt.se.ttms.view.sellticket;

import java.awt.BorderLayout;


import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.management.PlatformLoggingMXBean;
import java.util.Iterator;
import java.util.List;

import javax.management.MBeanAttributeInfo;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import xupt.se.ttms.model.Dict;
import xupt.se.ttms.model.Play;
import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.model.Seat;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.model.Ticket;
import xupt.se.ttms.service.DictSrv;
import xupt.se.ttms.service.PlaySrv;
import xupt.se.ttms.service.ScheduleSrv;
import xupt.se.ttms.service.SeatSrv;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.service.TicketSrv;
import xupt.se.ttms.view.seat.SeatUI;
import xupt.se.ttms.view.tmpl.PopUITmpl;

class PlayTable {
	private static final long serialVersionUID = 1L;
	private JTable jt = null;

	public PlayTable(JScrollPane jp) {
		DefaultTableModel tabModel=new DefaultTableModel(){
			private static final long serialVersionUID = 1L;

			@Override              
			public boolean isCellEditable(int row,int column){
				return false;              
			};
		};
		tabModel.addColumn("剧目ID");
		tabModel.addColumn("类型");
		tabModel.addColumn("语言");
		tabModel.addColumn("名称");
		tabModel.addColumn("简介");
		tabModel.addColumn("时长");
		tabModel.addColumn("票价");
		tabModel.addColumn("演出状态");
		jt=new JTable(tabModel);
		jt.setRowHeight(30);
		TableColumnModel columnModel = jt.getColumnModel();
		TableColumn column = columnModel.getColumn(0);
        column.setMinWidth(0);
        column.setMaxWidth(0);
        column.setWidth(0);
        column.setPreferredWidth(0);
        column = columnModel.getColumn(1);
        column.setPreferredWidth(10);
        column = columnModel.getColumn(2);
        column.setPreferredWidth(10);
        column = columnModel.getColumn(3);
        column.setPreferredWidth(10);
        column = columnModel.getColumn(4);
        column.setPreferredWidth(200); 
        column = columnModel.getColumn(5);
        column.setPreferredWidth(10); 
        column = columnModel.getColumn(6);
        column.setPreferredWidth(10); 
        column = columnModel.getColumn(7);
        column.setPreferredWidth(10); 
        jp.add(jt);
		jp.setViewportView(jt);
	}
	public Play getPlay() {
		int row = jt.getSelectedRow();
		if(row>=0){
			Play play = new Play();
			play.setPlay_id(Integer.parseInt(jt.getValueAt(row, 0).toString()));
			int play_type_id=DictSrv.Fetch_value_id((jt.getValueAt(row, 1).toString())).get(0).getDict_id();
			play.setPlay_type_id(play_type_id);
			int play_lang_id=DictSrv.Fetch_value_id((jt.getValueAt(row, 2).toString())).get(0).getDict_id();
			play.setPlay_lang_id(play_lang_id);
			play.setPlay_name(jt.getValueAt(row, 3).toString());
			play.setPlay_introduction(jt.getValueAt(row, 4).toString());
			play.setPlay_length(Integer.parseInt(jt.getValueAt(row, 5).toString()));	
			play.setPlay_ticket_price(Double.parseDouble(jt.getValueAt(row, 6).toString()));
			int status=PlaySrv.r_status(jt.getValueAt(row, 7).toString());
			
			play.setPlay_status(status);
			return play;
		}else{
			return null;
		}
	}
	// 创建JTable
	public void showPlayList(List<Play> playList) {
		try {
			DefaultTableModel tabModel = (DefaultTableModel) jt.getModel();
			tabModel.setRowCount(0);
			
			Iterator<Play> itr = playList.iterator();
			while (itr.hasNext()) {
				Play play = itr.next();
				Object data[] = new Object[8];
				data[0] = Integer.toString(play.getPlay_id());
				List<Dict> list1=DictSrv.Fetch_Value(play.getPlay_type_id());
				data[1] = list1.get(0).getDict_value();
				List<Dict> list2=DictSrv.Fetch_Value(play.getPlay_lang_id());
				data[2] = list2.get(0).getDict_value();
				data[3] = play.getPlay_name();
				data[4] = play.getPlay_introduction();
				data[5] = Integer.toString(play.getPlay_length());
				data[6] = play.getPlay_ticket_price()+"";
				if(play.getPlay_status()==1 || play.getPlay_status()==0);
				data[7] = PlaySrv.status(play.getPlay_status());
				tabModel.addRow(data);
			}
			jt.invalidate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}





class ScheduleTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable jt;

	public ScheduleTable(JScrollPane jp) {
		
		DefaultTableModel tabModel=new DefaultTableModel(){
			private static final long serialVersionUID = 1L;

			@Override              
			public boolean isCellEditable(int row,int column){
				return false;              
			};
		};
		tabModel.addColumn("演出计划ID");
		tabModel.addColumn("演出厅名称");
		tabModel.addColumn("剧目名称");
		tabModel.addColumn("演出时间");
		tabModel.addColumn("票价");
		//初始化列明
		jt=new JTable(tabModel);	
		
		//设置各列的宽度
	    TableColumnModel columnModel = jt.getColumnModel();
	    
	    jt.setRowHeight(30);
		
		jp.add(jt);
		jp.setViewportView(jt);
		
	}
	

	public Schedule getSchedule() {
		int row=jt.getSelectedRow();
		if(row>=0){
			Schedule sched = new Schedule();
			sched.setSched_id(Integer.parseInt(jt.getValueAt(row, 0).toString()));
			sched.setStudio_id(StudioSrv.Fetch_name(jt.getValueAt(row, 1).toString()).get(0).getID());
			sched.setPlay_id(PlaySrv.Fetch_name(jt.getValueAt(row, 2).toString()).get(0).getPlay_id()); 
			sched.setSched_time(jt.getValueAt(row, 3).toString());
			sched.setSched_ticket_price(Double.parseDouble(jt.getValueAt(row, 4).toString()));
			return sched;
		}
		else{
			return null;
		}
			
	}
	
	
			
	// 创建JTable
		public void showScheduelList(List<Schedule> schedList) {
			try {
				DefaultTableModel tabModel = (DefaultTableModel) jt.getModel();
				tabModel.setRowCount(0);
				Iterator<Schedule> itr = schedList.iterator();
				while (itr.hasNext()) {
					Schedule sched = itr.next();
					Object data[] = new Object[5];
					data[0] = Integer.toString(sched.getSched_id());
					List<Studio> list1=StudioSrv.Fetch_id(sched.getStudio_id());
					data[1] = list1.get(0).getName();
					List<Play> list2=PlaySrv.Fetch_id(sched.getPlay_id());
					data[2] = list2.get(0).getPlay_name();
					data[3] = sched.getSched_time().toString();
					data[4] =  Double.toString(sched.getSched_ticket_price());
					tabModel.addRow(data);;
				}
				jt.invalidate();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}






public class SellSelect extends JFrame implements ActionListener{
	    private Schedule scl;
	    private  Play pl;
	    protected boolean rst=false; 	
	    private JTabbedPane tabbedPane;
	    JScrollPane jsc = new JScrollPane();
	    JScrollPane js = new JScrollPane();
	    JPanel sell = new JPanel();
	    private JButton next1 = new JButton("下一步");
	    private JButton next2 = new JButton("下一步");
	   
	    private int count = 0;
	    private static int ticPicure[][] = new int [100][100];
	    
	   
	    
	    private JPanel panel1 = new JPanel();
	    private JPanel panel2 = new JPanel();
	    private JPanel panel3 = new JPanel();
	    private JPanel  bottom = new JPanel();
	    PlayTable mb = new PlayTable(jsc);
	    ScheduleTable tms = new ScheduleTable(js);
	    SellTicketUI mbs = new SellTicketUI(bottom);
	   
	    public void actionPerformed(ActionEvent e) {   //按钮监听器
			if (e.getSource() == next1) {
				rst=false;
				next111();
			} else if (e.getSource() == next2) {
				next22();	
			}

		}
		
	    private void next22() {
	    	scl= tms.getSchedule();
	    	mbs.getSchedule_r(scl);
	    	showTableT();
		}

		private void next111(){
			pl=mb.getPlay();
			showTableS();
		}
		
	    
	    public SellSelect() {    
	
		   //添加选项卡
		   tabbedPane = new JTabbedPane();
		   

	       tabbedPane.addTab("剧目信息", panel1);
	       panel1.setLayout(new BorderLayout());
	       add(tabbedPane, "Center");
	       panel1.add(jsc,BorderLayout.CENTER);
	       next1.setBackground(new Color(255,246,143));
		   next2.setBackground(new Color(255,246,143));
		   
	       next1.addActionListener(this);
	       panel1.add(next1, BorderLayout.SOUTH);

	       tabbedPane.addTab("演出计划", panel2);
 
	       tabbedPane.addTab("演出计划", panel2);
	       
	       tabbedPane.addTab("购票", panel3);
           //添加选项卡面板
	       panel1.setLayout(new BorderLayout());
	       add(tabbedPane, "Center");
	       panel1.add(jsc,BorderLayout.CENTER);
	       next1.addActionListener(this);
	       panel1.add(next1, BorderLayout.SOUTH);
	       
	       
			panel2.setLayout(new BorderLayout());
		    add(tabbedPane, "Center");
		    panel2.add(js,BorderLayout.CENTER);
		    next2.addActionListener(this);
		    panel2.add(next2, BorderLayout.SOUTH);
	       
		    
	       panel3.setLayout(new BorderLayout());
	       add(tabbedPane, "Center");
	      
	       panel3.add(mbs.re());
	       
	       tabbedPane.addChangeListener(new ChangeListener() { //添加监听器

	           @Override

	       public void stateChanged(ChangeEvent e) {
	              int n = tabbedPane.getSelectedIndex();
	              
	              loadTab(n);

	           }

	       });

	       loadTab(0);
	}
	
	
	
	 private void loadTab(int n) {    //标签页的下标转换

	       if(count==0)
	       {
	    	   showTable();
	       }
	       
	       count++;
	    }

	 public void showTable() {    //显示剧目信息表
			List<Play> playList = new PlaySrv().FetchALL();
			mb.showPlayList(playList);
		}
	 
	 public void showTableS() {    //显示演出计划表
			new ScheduleSrv();
			List<Schedule> schedList = ScheduleSrv.Fetch_name(pl.getPlay_id());
			
			tms.showScheduelList(schedList);
			
	 }
	 
	 public void showTableT(){
		  mbs.view_site();
	 }
		
		
//	public static void main(String[] args) {
//		// TODO 自动生成的方法存根
//        SellSelect s = new SellSelect();
//        s.setSize(1024, 700);
//        s.setVisible(true);
//	}

}
