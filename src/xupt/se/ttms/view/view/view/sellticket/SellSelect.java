package xupt.se.ttms.view.sellticket;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import xupt.se.ttms.model.Play;
import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.service.PlaySrv;
import xupt.se.ttms.service.ScheduleSrv;
import xupt.se.ttms.service.StudioSrv;



class PlayTableMouseListener extends MouseAdapter {

	private JTable jt;
	private static Play play;

	public Play getPlay() {
		return play;
	}

	public PlayTableMouseListener(JTable jt, Object[] number, Play play) {
		this.play = play;
		this.jt = jt;
	}

	// 监听到行号，将所选行的内容依次赋到 play对象，以便传有值对象到修改面板进行修改
	public void mouseClicked(MouseEvent event) {
		int row = jt.getSelectedRow();
		play.setPlay_id(Integer.parseInt(jt.getValueAt(row, 0).toString()));
		play.setPlay_type_id(Integer.parseInt(jt.getValueAt(row, 1).toString()));
		play.setPlay_lang_id(Integer.parseInt(jt.getValueAt(row, 2).toString()));
		play.setPlay_name(jt.getValueAt(row, 3).toString());
		
		if (jt.getValueAt(row, 4) != null)
			play.setPlay_introduction(jt.getValueAt(row, 4).toString());
		else
			play.setPlay_introduction("");
		System.out.println(jt.getValueAt(row, 1).toString());
		play.setPlay_length(Integer.parseInt(jt.getValueAt(row, 5).toString()));	
		play.setPlay_ticket_price(Integer.parseInt(jt.getValueAt(row, 6).toString()));
		play.setPlay_status(Integer.parseInt(jt.getValueAt(row, 7).toString()));
		
		
	}
}

class PlayTable {

	public Play getPlay() {
		return play;
	}


	private Play play;
	private JTable jt = null;

	public PlayTable(Play play) {
		this.play = play;
	}

	
	// 创建JTable
	public void createTable(JScrollPane jp, Object[] columnNames, List<Play> playList) {
		try {

			Object data[][] = new Object[playList.size()][columnNames.length];

			Iterator<Play> itr = playList.iterator();
			int i = 0;
			while (itr.hasNext()) {
				Play play = itr.next();
				data[i][0] = Integer.toString(play.getPlay_id());
				data[i][1] = Integer.toString(play.getPlay_type_id());
				data[i][2] = Integer.toString(play.getPlay_lang_id());
				data[i][3] = play.getPlay_name();
				data[i][4] = play.getPlay_introduction();
				data[i][5] = Integer.toString(play.getPlay_length());
				data[i][6] = play.getPlay_ticket_price()+"";
				data[i][7] = Integer.toString(play.getPlay_status());
				i++;
			}

			// 生成JTable
			jt = new JTable(data, columnNames);
			jt.setBounds(0, 0, 700, 450);

			// 添加鼠标监听，监听到所选行
			PlayTableMouseListener tml = new PlayTableMouseListener(jt, columnNames, play);
			jt.addMouseListener(tml);

			// 设置可调整列宽
			jt.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

			jp.add(jt);
			jp.setViewportView(jt);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


class ScheduleTableMouseListener extends MouseAdapter {

	private JTable jt;
	private static Schedule sched;

	public Schedule getSchedule() {
		return sched;
	}

	public ScheduleTableMouseListener(JTable jt, Object[] number, Schedule sched) {
		this.sched = sched;
		this.jt = jt;
	}
	
	// 监听到行号，将所选行的内容依次赋到 sched对象，以便传有值对象到修改面板进行修改
		public void mouseClicked(MouseEvent event) {
			int row = jt.getSelectedRow();
			sched.setSched_id(Integer.parseInt(jt.getValueAt(row, 0).toString()));
			sched.setStudio_id(Integer.parseInt(jt.getValueAt(row, 1).toString()));
			sched.setPlay_id(Integer.parseInt(jt.getValueAt(row, 2).toString())); // 0
			sched.setSched_time(jt.getValueAt(row, 3).toString());
			sched.setSched_ticket_price(Double.parseDouble(jt.getValueAt(row,4).toString()));
			System.out.println(jt.getValueAt(row, 1).toString());
		}
	}

class ScheduleTable {

	private Schedule sched;
	private JTable jt = null;

	public ScheduleTable(Schedule sched) {
		this.sched = sched;
	}

	// 创建JTable
	public void createTable(JScrollPane jp, Object[] columnNames, List<Schedule> schedList) {
		try {

			Object data[][] = new Object[schedList.size()][columnNames.length];

			Iterator<Schedule> itr = schedList.iterator();
			int i = 0;
			while (itr.hasNext()) {
				Schedule sched = itr.next();
				data[i][0] = Integer.toString(sched.getSched_id());
				List<Studio> list1=StudioSrv.Fetch_id(sched.getStudio_id());
				data[i][1] = list1.get(0).getName();
				List<Play> list2=PlaySrv.Fetch_id(sched.getPlay_id());
				data[i][2] = list2.get(0).getPlay_name();
				data[i][3] = sched.getSched_time().toString();
				data[i][4] = Double.toString(sched.getSched_ticket_price());
				i++;
			}

			// 生成JTable
			jt = new JTable(data, columnNames);
			jt.setBounds(0, 0, 700, 450);

			// 添加鼠标监听，监听到所选行
			ScheduleTableMouseListener tml = new ScheduleTableMouseListener(jt, columnNames, sched);
			jt.addMouseListener(tml);

			// 设置可调整列宽
			jt.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

			jp.add(jt);
			jp.setViewportView(jt);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


public class SellSelect extends JFrame{
	
	    private JTabbedPane tabbedPane;
	    JScrollPane jsc = new JScrollPane();
	    JScrollPane js = new JScrollPane();
	   
	    private int count = 0;
	    private Play play=new Play();
	    private Schedule sched=new Schedule();
	    private JPanel panel1 = new JPanel();
	    private JPanel panel2 = new JPanel();
	    private JPanel panel3 = new JPanel();

	public SellSelect() {
		// TODO 自动生成的构造函数存根
		
		
		
		   //添加选项卡
		   tabbedPane = new JTabbedPane();
		   

	       tabbedPane.addTab("剧目信息", panel1);

	       tabbedPane.addTab("演出计划", panel2);

	       tabbedPane.addTab("购票", panel3);
	       
	       
           //添加选项卡面板
	       panel1.setLayout(new BorderLayout());
	       add(tabbedPane, "Center");
	       panel1.add(jsc,BorderLayout.CENTER);
	       
	       panel2.setLayout(new BorderLayout());
	       add(tabbedPane, "Center");
	       panel2.add(js,BorderLayout.CENTER);
	       
	       tabbedPane.addChangeListener(new ChangeListener() { //添加监听器

	           @Override

	           public void stateChanged(ChangeEvent e) {

	              // TODO Auto-generated method stub

	              int n = tabbedPane.getSelectedIndex();

	              loadTab(n);

	           }

	       });

	       loadTab(0);
	}
	
	
	
	 private void loadTab(int n) {

	       String title = tabbedPane.getTitleAt(n);

//	       String countString = String.valueOf(count ++);
	       if(count==0)
	       {
	    	   showTable();
	       }
	       if(count==1)
	       {
	    	   showTableS();
	       }
	       
	       count++;
	       
//	       String msg = "this is " + title + ", load at " + countString + " times";
//	       tabbedPane.setComponentAt(n, new JLabel(msg));

	    }

	 public void showTable() {
			PlayTable tms = new PlayTable(play);
			Object[] in = { "play_id", "play_type_id", "play_lang_id", "play_name", "play_introduction","play_length","play_ticket_price","play_status"};
			List<Play> playList = new PlaySrv().FetchALL();

			tms.createTable(jsc, in, playList);
			jsc.repaint();
		}
	 
	 public void showTableS() {
			ScheduleTable tms = new ScheduleTable(sched);
			Object[] in = { "sched_id", "studio_id", "play_id", "sched_time", "sched_ticket_price" };
			List<Schedule> schedList = new ScheduleSrv().FetchAll();

			tms.createTable(js, in, schedList);
			js.repaint();
		}
		

		
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
        SellSelect s = new SellSelect();
        s.setSize(1024, 700);
        s.setVisible(true);
	}

}
