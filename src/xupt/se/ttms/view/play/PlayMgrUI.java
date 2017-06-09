package xupt.se.ttms.view.play;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.lang.model.type.TypeKind;
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
import xupt.se.ttms.model.Dict;
import xupt.se.ttms.model.Play;
import xupt.se.ttms.service.DictSrv;
import xupt.se.ttms.service.PlaySrv;
import xupt.se.ttms.view.play.PlayTable;
import xupt.se.ttms.view.play.PlayMgrUI;
import xupt.se.ttms.view.play.PlaySelectUI;
import xupt.se.ttms.view.play.PlayAddUI;
import xupt.se.ttms.view.play.PlayEditUI;
import xupt.se.ttms.view.play.PlayTable;
import xupt.se.ttms.view.tmpl.*;

class PlayTable {
	private static final long serialVersionUID = 1L;
	private JTable jt;

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
		TableColumnModel columnModel = jt.getColumnModel();
		jt.setRowHeight(30);
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
				data[7] = PlaySrv.status(play.getPlay_status());
				tabModel.addRow(data);
			}
			jt.invalidate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
public class PlayMgrUI extends MainUITmpl {
	private static final long serialVersionUID = 1L;
	private JLabel ca1 = null; 

	private JScrollPane jsc;
	
	private JLabel hint;
	private JTextField input;
	// 查找，编辑和删除按钮
	private JButton btnAdd, btnEdit, btnDel, btnQuery;
	PlayTable tms; 
	public PlayMgrUI() {
			
	}

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
		btnQuery.setBackground(new Color(255,246,143));
		btnQuery.setBounds(440, rect.height - 45, 60, 30);
		btnQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				btnQueryClicked();
			}
		});
		contPan.add(btnQuery);
		
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
		
		tms = new PlayTable(jsc);
		
		showTable();
	}
	private void btnAddClicked() {
		PlayAddUI addPlayUI=null;
		addPlayUI = new PlayAddUI();
		addPlayUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addPlayUI.setWindowName("添加剧目");
		addPlayUI.toFront();
		addPlayUI.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
		addPlayUI.setVisible(true);
		if (addPlayUI.getReturnStatus()) {
			tms = new PlayTable(jsc);
			
			
			showTable();
		}
		
	}
	private void btnModClicked() {
		Play play = tms.getPlay();
		if(null== play){
			JOptionPane.showMessageDialog(null, "请选择要修改的剧目");
			return; 
		}
		PlayEditUI modPlayUI = new PlayEditUI(play);
		modPlayUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		modPlayUI.setWindowName("修改剧目");
		modPlayUI.initData(play);
		modPlayUI.toFront();
		modPlayUI.setModal(true);
		modPlayUI.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
		modPlayUI.setVisible(true);
		if (modPlayUI.getReturnStatus()) {	
			showTable();		
		}
//		contPan.removeAll();
//			contPan.repaint();
		
	}
	private void btnDelClicked() {
		Play play = tms.getPlay();
		if(null== play){
			JOptionPane.showMessageDialog(null, "请选择要删除的剧目");
			return; 
		}		
		int confirm = JOptionPane.showConfirmDialog(null, "确认删除所选？", "删除", JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			PlaySrv playSrv = new PlaySrv();
			playSrv.delete(play.getPlay_id());
			showTable();
		}
	}
	private void btnQueryClicked() {
		String play_name = input.getText();
		if (!play_name.equals("")) {
			//请自行补充
			PlaySelectUI SelectPlayUI = null;
			SelectPlayUI = new PlaySelectUI();
			SelectPlayUI.Select(play_name);
			SelectPlayUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			SelectPlayUI.setWindowName("剧目信息");
			SelectPlayUI.toFront();
			SelectPlayUI.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
			SelectPlayUI.setVisible(true);			
		} else {
			JOptionPane.showMessageDialog(null, "请输入检索条件");
		}
	}
	private void showTable() {
		List<Play> playList = new PlaySrv().FetchALL();
		tms.showPlayList(playList);
	}
	public static void main(String[] args) {
		PlayMgrUI frmPlayMgr = new PlayMgrUI();
		frmPlayMgr.setVisible(true);
	}
}