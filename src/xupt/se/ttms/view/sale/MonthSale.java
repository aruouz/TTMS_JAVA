package xupt.se.ttms.view.sale;


import javax.swing.table.*;

import xupt.se.ttms.view.tmpl.ImagePanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
public class MonthSale extends JFrame {
private JTable table=null;
private JPanel jp=new JPanel();

private static final long serialVersionUID = 1L;
private int frmWidth=800;
private int frmHeight=700;

public MonthSale(){
	this.setSize(500, 200);
	//this.setLocationRelativeTo(null);
	//this.setResizable(false);
	jp.setBackground(new Color(70 ,130 ,180));
	
	//this.setLayout(null);
	/*this.addWindowListener(new WindowAdapter(){
		public void windowClosing(WindowEvent e){
			onWindowClosing();
		}
	});		
	*/
	
}

private JTable getTable(){
if(table==null){
table=new JTable();
table.setRowHeight(35);
String[] columns={"序号","月份","座位总数（个）","销售总量（张）","销售金额","上座率","本剧剧目排名"};
int[] columnWidth={60,60,90,90,80,80,100};
DefaultTableModel model=new DefaultTableModel(columns,7);
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
jp.add(myt,BorderLayout.NORTH);
jp.add(table,BorderLayout.CENTER);
this.add(jp);
this.setTitle("月报表");
this.setBounds(10, 10,1000, 500);
this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
this.setVisible(true);
this.setResizable(false);
}
public static void main(String[] args) {
MonthSale aa=new MonthSale();
aa.showWindow();

}
}
