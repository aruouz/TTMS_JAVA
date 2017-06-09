package xupt.se.ttms.view.play;

import java.awt.Color;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import xupt.se.ttms.model.Dict;
import xupt.se.ttms.model.Play;
import xupt.se.ttms.service.DictSrv;
import xupt.se.ttms.service.PlaySrv;
import xupt.se.ttms.view.play.PlayTableMouseListener;
import xupt.se.ttms.view.studio.StudioSelectUI;
import xupt.se.ttms.view.play.PlayTable;
import xupt.se.ttms.view.play.PlayTable;
import xupt.se.ttms.view.tmpl.MainUITmpl;

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
				List<Dict> list1=DictSrv.Fetch_Value(play.getPlay_type_id());
				data[i][1] = list1.get(0).getDict_value();
				List<Dict> list2=DictSrv.Fetch_Value(play.getPlay_lang_id());
				data[i][2] = list2.get(0).getDict_value();
				data[i][3] = play.getPlay_name();
				data[i][4] = play.getPlay_introduction();
				data[i][5] = Integer.toString(play.getPlay_length());
				data[i][6] = play.getPlay_ticket_price()+"";
				data[i][7] = PlaySrv.status(play.getPlay_status());
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

	public Play getPlay() {
		// TODO Auto-generated method stub
		return null;
	}
}

public class PlayMgrUI  extends MainUITmpl{
	private static final long serialVersionUID = 1L;
	private Play play=new Play();
	private JLabel ca1 = null; // 界面提示
	// 用来放表格的滚动控件
	private JScrollPane jsc;
	// 查找的提示和输出
	private JLabel hint;
	private JTextField input;

	// 查找，编辑和删除按钮
	private JButton btnAdd, btnEdit, btnDel, btnQuery;
	
	PlayTable tms;
	
	public PlayMgrUI() {
		// TODO 自动生成的构造函数存根
	}

	// To be override by the detailed business block interface
	@Override
	protected void initContent() {
		Rectangle rect = contPan.getBounds();

		ca1 = new JLabel("剧目管理", JLabel.CENTER);
		ca1.setBounds(0, 5, rect.width, 30);
		ca1.setFont(new java.awt.Font("宋体", 1, 20));
		ca1.setForeground(Color.blue);
		contPan.add(ca1);

		jsc = new JScrollPane();
		jsc.setBounds(0, 40, rect.width, rect.height - 90);
		contPan.add(jsc);

		hint = new JLabel("请输入剧目名称:", JLabel.RIGHT);
		hint.setBounds(60, rect.height - 45, 150, 30);
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
		PlayAddUI addStud = new PlayAddUI();
		addStud.setWindowName("添加剧目");
		addStud.toFront();
		addStud.setModal(true);
		addStud.setVisible(true);

		if (addStud.getReturnStatus()) {
			showTable();
		}
	}

	private void btnModClicked() {
		Play play = tms.getPlay();
		if(null== play){
			JOptionPane.showMessageDialog(null, "请选择要修改的演出厅");
			return; 
		}
		PlayEditUI modStu = new PlayEditUI(play);
		modStu.setWindowName("修改剧目信息");
		modStu.toFront();
		modStu.setModal(true);
		modStu.setVisible(true);
		if (modStu.getReturnStatus()) {
			showTable();
		}
	}

	private void btnDelClicked() {
		int confirm = JOptionPane.showConfirmDialog(null, "确认删除所选？", "删除", JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			PlaySrv playSrv = new PlaySrv();
			playSrv.delete(play.getPlay_id());
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
		PlayTable tms = new PlayTable(play);
		Object[] in = { "剧目ID", "类型", "语言", "名称", "简介","时长","票价","演出状态"};
		List<Play> playList = new PlaySrv().FetchALL();

		tms.createTable(jsc, in, playList);
		jsc.repaint();
	}

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		PlayMgrUI frmPlayMgr = new PlayMgrUI();
		frmPlayMgr.setVisible(true);
	}

}
