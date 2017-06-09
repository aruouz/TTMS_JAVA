package xupt.se.ttms.view.schedule;


import java.awt.Color;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import java.util.List;
import java.util.Iterator;

import xupt.se.ttms.model.Play;
import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.service.PlaySrv;
import xupt.se.ttms.service.ScheduleSrv;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.service.ScheduleSrv;
import xupt.se.ttms.view.tmpl.*;


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
	    //隐藏ID这一列
       /* TableColumn column = columnModel.getColumn(0);
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
        column.setPreferredWidth(200);   */     

		
		jp.add(jt);
		jp.setViewportView(jt);
		
	}
	
	/*sched_id = Integer.parseInt(jt.getValueAt(row, 0).toString());

	sched.setSched_id(sched_id);
		play_id= PlaySrv.Fetch_name(jt.getValueAt(row, 2).toString()).get(0).getPlay_id();
		sched.setPlay_id(play_id);
		studio_id = StudioSrv.Fetch_name(jt.getValueAt(row, 1).toString()).get(0).getID();
		sched.setStudio_id(studio_id);
		sched.setPlay_id(Integer.parseInt(jt.getValueAt(row, 2).toString())); // 0
		sched.setSched_time(jt.getValueAt(row, 3).toString());
		sched.setSched_ticket_price(Double.parseDouble(jt.getValueAt(row, 4).toString()));
		sched_time = jt.getValueAt(row, 3).toString();*/


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
	
	
	
	
	/*while (itr.hasNext()) {
				Schedule sched = itr.next();
				data[i][0] = Integer.toString(sched.getSched_id());
				List<Studio> list1=StudioSrv.Fetch_id(sched.getStudio_id());
				data[i][1] = list1.get(0).getName();
				List<Play> list2=PlaySrv.Fetch_id(sched.getPlay_id());
				data[i][2] = list2.get(0).getPlay_name();
				data[i][3] = sched.getSched_time().toString();
				data[i][4] = Double.toString(sched.getSched_ticket_price());
				i++;*/
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






public class ScheduleMgrUI extends MainUITmpl {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel ca1 = null; // 界面提示
	// 用来放表格的滚动控件
	private JScrollPane jsc;
	// 查找的提示和输出
	private JLabel hint;
	private JTextField input;

	// 查找，编辑和删除按钮
	private JButton btnAdd, btnEdit, btnDel, btnQuery,btnM;
	
	ScheduleTable tms; //显示演出厅列表


	public ScheduleMgrUI() {
		
	}

	// To be override by the detailed business block interface
	@Override
	protected void initContent() {
		Rectangle rect = contPan.getBounds();

		ca1 = new JLabel("演出计划管理", JLabel.CENTER);
		ca1.setBounds(0, 5, rect.width, 30);
		ca1.setFont(new java.awt.Font("宋体", 1, 20));
		ca1.setForeground(Color.blue);
		contPan.add(ca1);

		jsc = new JScrollPane();
		jsc.setBounds(0, 40, rect.width, rect.height - 90);
		contPan.add(jsc);

		hint = new JLabel("请输入需要查找演出计划的剧目名称：", JLabel.RIGHT);
		hint.setBounds(5, rect.height - 45, 220, 30);
		contPan.add(hint);

		input = new JTextField();
		input.setBounds(220, rect.height - 45, 200, 30);
		contPan.add(input);

		// 查找 ，删除和编辑的按钮，其中含有相关的事件处理！
		btnQuery = new JButton("查找");
		btnQuery.setBackground(new Color(255,246,143));
		btnQuery.setBounds(440, rect.height - 45, 60, 30);
		btnQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				btnQueryClicked();
			}
		});
		contPan.add(btnQuery);

		/*btnM = new JButton("座位管理");
		btnM.setBounds(rect.width - 350, rect.height - 45, 100, 30);
		btnM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				btnMClicked();
			}
		});
		contPan.add(btnM);*/
		
		
		btnAdd = new JButton("添加");
		btnAdd.setBackground(new Color(255,246,143));
		btnAdd.setBounds(rect.width - 220, rect.height - 45, 60, 30);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				btnAddClicked();
			}
		});
		contPan.add(btnAdd);

		btnEdit = new JButton("修改");
		btnEdit.setBackground(new Color(255,246,143));
		btnEdit.setBounds(rect.width - 150, rect.height - 45, 60, 30);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				btnModClicked();
			}
		});
		contPan.add(btnEdit);

		btnDel = new JButton("删除");
		btnDel.setBackground(new Color(255,246,143));
		btnDel.setBounds(rect.width - 80, rect.height - 45, 60, 30);
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				btnDelClicked();
			}
		});
		contPan.add(btnDel);
		contPan.add(ca1);
		
		tms = new ScheduleTable(jsc);
		
		showTable();
	}

	private void btnAddClicked() {

		ScheduleAddUI addSchedUI=null;
		
		addSchedUI = new ScheduleAddUI();
		addSchedUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addSchedUI.setWindowName("添加演出计划");
		addSchedUI.toFront();
		
		addSchedUI.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
		
		
		
		addSchedUI.setVisible(true);
		
		System.out.println("hahhahahhahahhah");
		showTable();
			
		
		
	}
	
/*
		if(null== tms.getStudio()){
			JOptionPane.showMessageDialog(null, "请选择要管理座位的演出厅");
			return; 
		}
		
		  SeatUI seats = new SeatUI(tms.getStudio());
		  seats.setLayout(null);
		  
		  SeatUI.sPanel.removeAll();
		  SeatUI.bottom.removeAll();
		  seats.initsPanel();
		  seats.view_site();	
		  
		  SeatUI.sPanel.setBounds(50, 50, 120, 120);
		  seats.add(SeatUI.sPanel);
		  
		  SeatUI.bottom.setBounds(140, 140, 700, 600);
		  seats.add(SeatUI.bottom);
		  
		 
	     
	      seats.setSize(1024, 700);
	      seats.setVisible(true);
	      
		
	}

	*/
	private void btnModClicked() {
		Schedule sched = tms.getSchedule();
		if(null== sched){
			JOptionPane.showMessageDialog(null, "请选择要修改的演出计划");
			return; 
		}
		
		ScheduleEditUI modSchedUI = new ScheduleEditUI(sched);
		modSchedUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		modSchedUI.setWindowName("修改演出计划");
		modSchedUI.initData(sched);
		modSchedUI.toFront();
		modSchedUI.setModal(true);
		modSchedUI.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
		modSchedUI.setVisible(true);

		if (modSchedUI.getReturnStatus()) {
			showTable();
		}	
	}

	private void btnDelClicked() {
		Schedule sched = tms.getSchedule();
		if(null== sched){
			JOptionPane.showMessageDialog(null, "请选择要删除的演出计划");
			return; 
		}		
		
		int confirm = JOptionPane.showConfirmDialog(null, "确认删除所选？", "删除", JOptionPane.YES_NO_OPTION);
		
		if (confirm == JOptionPane.YES_OPTION) {
			ScheduleSrv schedSrv = new ScheduleSrv();
			schedSrv.delete(sched.getSched_id());
			showTable();
		}
	}

	private void btnQueryClicked() {
		String play_name = input.getText();
		if (!play_name.equals("")) {
			
			ScheduleSelectUI SelectScheduleUI = null;
			SelectScheduleUI = new ScheduleSelectUI();
			SelectScheduleUI.Select(PlaySrv.Fetch_name(play_name.toString()).get(0).getPlay_id());
			SelectScheduleUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			SelectScheduleUI.setWindowName("演出计划信息");
			SelectScheduleUI.toFront();
			SelectScheduleUI.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
			SelectScheduleUI.setVisible(true);
			
		} else {
			JOptionPane.showMessageDialog(null, "请输入检索条件");
		}
	}

	private void showTable() {
		List<Schedule> schedList = new ScheduleSrv().FetchAll();
		tms.showScheduelList(schedList);
	}
	

	public static void main(String[] args) {
		ScheduleMgrUI frmSchedMgr = new ScheduleMgrUI();
		System.out.println("hahhahahhahahhah44444444444");
		frmSchedMgr.setVisible(true);
	}
}


