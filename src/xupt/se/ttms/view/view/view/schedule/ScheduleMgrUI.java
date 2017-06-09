package xupt.se.ttms.view.view.view.schedule;



import java.awt.Label;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.util.List;
import java.util.Date;
import java.util.Iterator;

import xupt.se.ttms.dao.StudioDAO;
import xupt.se.ttms.model.Play;
import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.service.PlaySrv;
import xupt.se.ttms.service.ScheduleSrv;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.view.schedule.ScheduleAddUI;
import xupt.se.ttms.view.schedule.ScheduleEditUI;
import xupt.se.ttms.view.schedule.ScheduleMgrUI;
import xupt.se.ttms.view.schedule.ScheduleTable;
import xupt.se.ttms.view.schedule.ScheduleTableMouseListener;
import xupt.se.ttms.view.tmpl.*;

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

public class ScheduleMgrUI extends MainUITmpl {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Schedule sched=new Schedule();
	private JLabel ca1 = null; // 界面提示
	// 用来放表格的滚动控件
	private JScrollPane jsc;
	// 查找的提示和输出
	private JLabel hint;
	private JTextField input;

	// 查找，编辑和删除按钮
	private JButton btnAdd, btnEdit, btnDel, btnQuery;

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
		btnQuery.setBounds(440, rect.height - 45, 60, 30);
		btnQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				btnQueryClicked();
			}
		});
		contPan.add(btnQuery);

		btnAdd = new JButton("添加");
		btnAdd.setBounds(rect.width - 220, rect.height - 45, 60, 30);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				btnAddClicked();
			}
		});
		contPan.add(btnAdd);

		btnEdit = new JButton("修改");
		btnEdit.setBounds(rect.width - 150, rect.height - 45, 60, 30);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				btnModClicked();
			}
		});
		contPan.add(btnEdit);

		btnDel = new JButton("删除");
		btnDel.setBounds(rect.width - 80, rect.height - 45, 60, 30);
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				btnDelClicked();
			}
		});
		contPan.add(btnDel);
		contPan.add(ca1);
		showTable();
	}

	private void btnAddClicked() {
		ScheduleAddUI addSched = new ScheduleAddUI();
		addSched.setWindowName("添加演出计划");
		addSched.toFront();
		addSched.setModal(true);
		addSched.setVisible(true);

		if (addSched.getReturnStatus()) {
			showTable();
		}
	}

	private void btnModClicked() {
			
		ScheduleEditUI modSched = new ScheduleEditUI(sched);
		modSched.setWindowName("修改演出计划");
		modSched.toFront();
		modSched.setModal(true);
		modSched.setVisible(true);
		if (modSched.getReturnStatus()) {
			showTable();
		}
	}

	private void btnDelClicked() {
		int confirm = JOptionPane.showConfirmDialog(null, "确认删除所选？", "删除", JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			ScheduleSrv schedSrv = new ScheduleSrv();
			schedSrv.delete(sched.getSched_id());
			showTable();
		}
	}

	private void btnQueryClicked() {
		if (!input.getText().equals("")) {


		} else {
			JOptionPane.showMessageDialog(null, "请输入检索条件");
		}
	}

	public void showTable() {
		ScheduleTable tms = new ScheduleTable(sched);
		Object[] in = { "演出计划ID", "演出厅", "剧目", "演出时间", "票价" };
		List<Schedule> schedList = new ScheduleSrv().FetchAll();
		tms.createTable(jsc, in, schedList);
		jsc.repaint();
	}
	
	public static void main(String[] args) {
		ScheduleMgrUI frmSchedMgr = new ScheduleMgrUI();
		frmSchedMgr.setVisible(true);
	}
}





