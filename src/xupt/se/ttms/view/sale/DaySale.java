package xupt.se.ttms.view.sale;
import javax.swing.table.*;

import xupt.se.ttms.model.Sale;
import xupt.se.ttms.view.tmpl.ImagePanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;
public class DaySale extends JFrame {
private JTable table=null;

private JPanel jp=new JPanel();
private static final long serialVersionUID = 1L;
private int frmWidth=800;
private int frmHeight=700;
//protected final ImagePanel headPan = new ImagePanel("resource/image/img1.png");
public   DaySale(){
	this.setSize(500, 200);
	//this.setLocationRelativeTo(null);
	//this.setResizable(false);
	
	//this.setLayout(null);
	/*this.addWindowListener(new WindowAdapter(){
		public void windowClosing(WindowEvent e){
			onWindowClosing();
		}
	});		
	*/
	jp.setBackground(Color.pink);
	//headPan.setBounds(1, 1,150, 500);
	//headPan.setLayout(null);
	//this.add(headPan);
}

private JTable getTable(){
if(table==null){
table=new JTable();
String[] columns={"序号","上映日期","剧目名称","座位总数（个）","销售总量（张）","销售金额","上座率","本剧剧目排名"};
int[] columnWidth={60,60,80,90,90,80,80,150};
DefaultTableModel model=new DefaultTableModel(columns,8);
table.setRowHeight(30);//设置表格行高，jt为JTbale
table.setModel(model);
TableColumnModel columnModel=table.getColumnModel();
int count=columnModel.getColumnCount();
for(int i=0;i<count;i++){
javax.swing.table.TableColumn column=columnModel.getColumn(i);
column.setPreferredWidth(columnWidth[i]);
}
}
return table;
}
private void showWindow(){
this.getTable();
JTableHeader  myt=table.getTableHeader();
jp.add(myt,BorderLayout.CENTER);
jp.add(table);
this.add(jp);
this.setTitle("日报表");
this.setBounds(10, 10,1000, 500);
this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
this.setVisible(true);
this.setResizable(false);
}
public static void main(String[] args) {
DaySale  aa=new DaySale();
aa.showWindow();
}
}
