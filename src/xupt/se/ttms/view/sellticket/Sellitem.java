package xupt.se.ttms.view.sellticket;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import xupt.se.ttms.model.Sale;
import xupt.se.ttms.model.Sale_item;
import xupt.se.ttms.model.Ticket;
import xupt.se.ttms.service.SaleSrv;
import xupt.se.ttms.service.Sale_itemSrv;
import xupt.se.ttms.service.TicketSrv;
import xupt.se.ttms.view.tmpl.PopUITmpl;

public class Sellitem extends PopUITmpl implements ActionListener{
	private JButton btnSave; 	//取消，保存按鈕
	
	protected boolean rst=false; 				//操作结果
	
	private static JLabel lblIDTicket;
	private JLabel lblIDep;
	private JLabel lblticket_price;
	private JLabel lblTime;
	private JLabel lblMoney;
	private JLabel lblcharge;
	private JLabel lblOrder;
	private JLabel lblStatus;
    private Date time;
	private JTextField textMoney;
	private JLabel  txtIDep,txtticket_price,textTime,txtcharge,txtIDTicket;
	private JButton buttonPrint,buttonBack,buy_change;
	private Ticket tic = new Ticket();;
	private Double charge_i=0.0;
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat dfs = new SimpleDateFormat("yyyyMMddHHmmss");
	
	private static String createdate;
	private static String locked;
	private Double pDo;
	

	public void addMess()
	{   
		
		System.out.println(tic.getTicket_id());
		lblIDTicket = new JLabel("票单号：");
		lblIDTicket.setBounds(250,30, 80, 30);
		contPan.add(lblIDTicket);
		txtIDTicket = new JLabel(tic.getTicket_id()+"");
		txtIDTicket.setBounds(350,30, 80, 30);
		contPan.add(txtIDTicket);
		
		lblIDep = new JLabel("职工号：");
		lblIDep.setBounds(250,70, 80, 30);
		contPan.add(lblIDep);
		txtIDep = new JLabel("1");
		txtIDep.setBounds(350,70, 80, 30);
		contPan.add(txtIDep);
		
		lblticket_price = new JLabel("票价：");
		lblticket_price.setBounds(250,110, 80, 30);
		contPan.add(lblticket_price);
		txtticket_price = new JLabel(tic.getTicket_price()+"");
		txtticket_price.setBounds(350,110, 80, 30);
		contPan.add(txtticket_price);
		
		lblTime = new JLabel("销售时间：");
		lblTime.setBounds(250,150, 80, 30);
		contPan.add(lblTime);
		textTime = new JLabel(locked);
		textTime.setBounds(350,150,130, 30);
		contPan.add(textTime);
		
		lblMoney = new JLabel("收款：");
		lblMoney.setBounds(250,190, 80, 30);
		contPan.add(lblMoney);
		textMoney = new JTextField(20);
		textMoney.setBounds(350,190, 80, 30);
		contPan.add(textMoney);
		
		
		lblcharge = new JLabel("找零：");
		lblcharge.setBounds(250,230, 80, 30);
		contPan.add(lblcharge);
		
		txtcharge = new JLabel(charge_i+"");	
		txtcharge.setBounds(350,230, 80, 30);
		contPan.add(txtcharge);
		
		
		buy_change = new JButton("确认");
		buy_change.setBounds(460,190, 80, 30);
		
		buy_change.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {   //按钮监听器
					if (e.getSource() == buy_change) {
						buy();
						txtcharge = new JLabel(charge_i+"");	
						txtcharge.setBounds(350,230, 80, 30);
						contPan.add(txtcharge);
						
					} 

				}
				
		
		
			
		});

		
		contPan.add(buy_change);
		
		lblOrder = new JLabel("销售订单：订单票");
		lblOrder.setBounds(250,270, 120, 30);
		contPan.add(lblOrder);
		
		lblStatus = new JLabel("销售状态：已售出");
		lblStatus.setBounds(250,310, 120, 30);
		contPan.add(lblStatus);
		
		buttonPrint = new JButton("打印");
		buttonPrint.setBounds(260, 350, 60, 30);
		buttonPrint.addActionListener(new ActionListener() {
			
		

			@Override
			public void actionPerformed(ActionEvent e) {
				Sale sale = new Sale();
				Sale_item isale = new Sale_item();  
				sale.setEmp_id(1);
				sale.setSale_change(charge_i);
				sale.setSale_payment(Double.parseDouble(textMoney.getText()));
				sale.setSale_time(locked);
				SaleSrv.add(sale);
				java.util.Date date = new java.util.Date();
				
				int i= (int) date.getTime();
				int t= (int) time.getTime();
				if((i-t)<600000){
				tic.setTicket_status(9);
				}
				else
				{  tic.setTicket_status(0);
						setVisible(false);
						return;
				}
					
				isale.setSale_item_price(tic.getTicket_price());
				isale.setTicket_id(tic.getTicket_id());
				isale.setSale_id(SaleSrv.Fetch_id(sale.getEmp_id(),locked).get(0).getSale_ID());
				Sale_itemSrv.add(isale);
				
				
				
			}
			
		});
		
		
		contPan.add(buttonPrint);
		
		
		buttonBack = new JButton("返回");
		buttonBack.setBounds(360, 350, 60, 30);
		buttonBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				setVisible(false);
				
			}
		});
		contPan.add(buttonBack);
		
		
	}
	
	
	public void buy(){
		Double r =Double.parseDouble(textMoney.getText());
		charge_i =r-pDo;
		contPan.remove(txtcharge);
		contPan.repaint();
	}
	
	public Sellitem(int tic_id,Date time)
	{
		System.out.println(tic_id);
		this.tic=TicketSrv.Fetch_ID(tic_id).get(0);
		this.time=time;
		pDo=tic.getTicket_price();
		locked = df.format(time);
		contPan.setLayout(null);
		this.add(contPan);
		
	}
	
//	public static void main(String[] args) {
//		Sellitem frmSchedMgr = new Sellitem();
//		
////		System.out.println("hahhahahhahahhah44444444444");
//		frmSchedMgr.addMess();
//		frmSchedMgr.setVisible(true);
//	}
//	@Override
//	public void actionPerformed(ActionEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//	
	
}
