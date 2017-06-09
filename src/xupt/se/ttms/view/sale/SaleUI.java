package xupt.se.ttms.view.sale;
import java.awt.Color;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.rmi.CORBA.Stub;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import xupt.se.ttms.model.Employee;
import xupt.se.ttms.model.Sale;
import xupt.se.ttms.service.EmployeeSrv;
import xupt.se.ttms.service.SaleSrv;
import xupt.se.ttms.view.tmpl.MainUITmpl;


	class Table {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private final String DateFormat = null;
		private JTable jt;

		public Table(JScrollPane jp) {
			
			DefaultTableModel tabModel=new DefaultTableModel(){
				private static final long serialVersionUID = 1L;

				@Override              
				public boolean isCellEditable(int row,int column){
					return false;              
				};
			};
			tabModel.addColumn("销售单ID");
			tabModel.addColumn("职工姓名");
			tabModel.addColumn("销售时间");
			tabModel.addColumn("收款");
			tabModel.addColumn("找零");
			tabModel.addColumn("销售类型");
			tabModel.addColumn("销售状态");
			//初始化列明
			jt=new JTable(tabModel);	
			
			//设置各列的宽度
		    TableColumnModel columnModel = jt.getColumnModel();
		    
		    //隐藏ID这一列
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

			
			jp.add(jt);
			jp.setViewportView(jt);
			
		}

		public Sale getSale() {
			int rowSel=jt.getSelectedRow();
			if(rowSel>=0){
				Sale sal = new Sale();
				sal.setSale_ID(Integer.parseInt(jt.getValueAt(rowSel, 0).toString()));
				sal.setEmp_id(Integer.parseInt(jt.getValueAt(rowSel, 0).toString()));
				sal.setSale_payment(Double.parseDouble(jt.getValueAt(rowSel, 0).toString()));
				sal.setSale_change(Double.parseDouble(jt.getValueAt(rowSel, 0).toString()));
				sal.setSale_type(Integer.parseInt(jt.getValueAt(rowSel, 0).toString()));
				sal.setSale_status(Integer.parseInt(jt.getValueAt(rowSel, 0).toString()));
				
				return sal;
			}
			else{
				return null;
			}
				
		}
		
		// 创建JTable
			public void showSaleList(List<Sale> sale) {
				try {
					DefaultTableModel tabModel = (DefaultTableModel) jt.getModel();
					tabModel.setRowCount(0);
					
					Iterator<Sale> itr = sale.iterator();
					while (itr.hasNext()) {
						Sale s = itr.next();
						Employee emp =new Employee();
						Object data[] = new Object[7];
						data[0] = Integer.toString(s.getSale_ID());
						data[1] = EmployeeSrv.Fetch_id(s.getEmp_id()).get(0).getcName();
						data[2] = s.getSale_time();
						data[3] = s.getSale_payment()+"";
						data[4] = s.getSale_change()+"";
						data[5] = "销售单";
						data[6] = "已售出";
						tabModel.addRow(data);;
					}
					jt.invalidate();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

 
 public class SaleUI extends MainUITmpl {
	 private static final long serialVersionUID = 1L;
		private JLabel ca1 = null; // 界面提示
		// 用来放表格的滚动控件
		private JScrollPane jsc;
		private JButton btnBack;
		Table tms; //显示演出厅列表

		public SaleUI(){
			
		}
		
		protected void initContent() {
			Rectangle rect = contPan.getBounds();

			ca1 = new JLabel("销售统计", JLabel.CENTER);
			ca1.setBounds(0, 5, rect.width, 30);
			ca1.setFont(new java.awt.Font("宋体", 1, 20));
			ca1.setForeground(Color.black);
			contPan.add(ca1);

			jsc = new JScrollPane();
			jsc.setOpaque(false);
			jsc.getViewport().setOpaque(false);
			jsc.setBounds(0, 40, rect.width, rect.height - 90);
			contPan.add(jsc);


			// 查找 ，删除和编辑的按钮，其中含有相关的事件处理！
			btnBack = new JButton("返回");
			btnBack.setBackground(new Color(255,246,143));
			btnBack.setBounds(440, rect.height - 45, 60, 30);
			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent Event) {
					//
				}
			});
			contPan.add(btnBack);
			
			contPan.add(ca1);
			
			tms = new Table(jsc);
			
			showTable();
		}
		private void showTable() {
			List<Sale> stuList = new SaleSrv().FetchAll();
			tms.showSaleList(stuList);
		}
		public static void main(String[] args) {
			SaleUI frmStuMgr = new SaleUI();
			frmStuMgr.setVisible(true);
		}
}
