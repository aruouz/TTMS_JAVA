package xupt.se.ttms.view.sellticket;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.mysql.fabric.xmlrpc.base.Data;

import xupt.se.ttms.model.Play;
import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.model.Seat;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.model.Ticket;
import xupt.se.ttms.service.PlaySrv;
import xupt.se.ttms.service.SeatSrv;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.service.TicketSrv;
import xupt.se.ttms.test.Play_test;
import xupt.se.ttms.view.seat.SeatUI;

public class SellTicketUI extends JFrame{

	private static Schedule scl ;
	private Seat seat;
	private static Studio stu;
	private  int c,r;
	private static int ticPicure[][] =new int [100][100];
	private static int state[][]= new int[100][100];
	private static  JPanel bottom = new JPanel();
	private static int sate[][] = new int [20][20];
	private static int rr;
	private static int cc;
	private static JLabel playName,Date,playTime,seatRC,ticket_price;
	private static JLabel txtplayName,txtDate,txtplayTime,txtseatRC,txtticket_price;
	private static Play play_t=new Play();
	private static JButton  con = new JButton("确定购买");
	private static int tic_id;
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static String createdate;
	private static java.util.Date date;
	public SellTicketUI(JPanel bottom){
		this.bottom = bottom;
	}
	
	public void getSchedule_r(Schedule scl){
		this.scl = scl;
		 play_t=PlaySrv.Fetch_id(scl.getPlay_id()).get(0);
		 List<Seat> list = SeatSrv.Fetch(scl.getStudio_id());
		 List<Ticket> t_List = TicketSrv.Fetch_sched(scl.getSched_id());
		 cc=StudioSrv.Fetch_id(scl.getStudio_id()).get(0).getRowCount();
		 rr=StudioSrv.Fetch_id(scl.getStudio_id()).get(0).getColCount();
	        for(int i=0; i<list.size();i++){
	        	 Seat set = list.get(i);
	        	 Ticket tic = t_List.get(i);
	        	 int r=set.getSeat_row();
	        	 int c=set.getSeat_column();
	        	 
	        	 System.out.println(tic.getTicket_id());
	        	 state[r][c]=tic.getTicket_id();
	        	 ticPicure[r][c]=tic.getTicket_status();
	        	 
	        }
	        
	}
	void getCR(Studio stu)  //获取座位的行列
	{
		 c=stu.getColCount();
		 r=stu.getRowCount();
	}
	
	public JPanel re(){
		return bottom;
	}
	
	public static void addMessage() {
		playName = new JLabel("剧目名称：");
		playName.setBounds(700, 80, 100, 30);
		txtplayName=new JLabel(play_t.getPlay_name());
		txtplayName.setBounds(800, 80, 100, 30);
		
		Date = new JLabel("演出时间：");
		Date.setBounds(700, 110, 100, 30);
		txtDate=new JLabel(scl.getSched_time());
		txtDate.setBounds(800, 110, 150, 30);
		
		playTime = new JLabel("电影时长：");
		playTime.setBounds(700, 140, 100, 30);
		txtplayTime=new JLabel(play_t.getPlay_length()+"");
		txtplayTime.setBounds(800, 140, 100, 30);
		
		seatRC = new JLabel("座位：");
		seatRC.setBounds(700, 170, 100, 30);
    	
		
		ticket_price = new JLabel("票价：");
		ticket_price.setBounds(700, 200, 100, 30);
    	txtticket_price = new JLabel(scl.getSched_ticket_price()+"");
    	txtticket_price.setBounds(800, 200, 100, 30);
    	
    	con.addActionListener(new ActionListener() {
			
    		public void actionPerformed(ActionEvent Event) {
				btnClicked();
			}

			private void btnClicked() {
				System.out.println(tic_id);
				Ticket tic=TicketSrv.Fetch_ID(tic_id).get(0);
				tic.setTicket_status(9);
				TicketSrv.modify(tic);
				Sellitem frmSchedMgr = new Sellitem(tic_id,date);
				frmSchedMgr.addMess();
				frmSchedMgr.setVisible(true);
			}
		});
    	
    	con.setBounds(760, 260, 120, 40);
    	con.setBackground(new Color(255,246,143));
		bottom.add(playName);
		bottom.add(txtplayName);
		
		bottom.add(Date);
		bottom.add(txtDate);
		
		bottom.add(playTime);
		bottom.add(txtplayTime);
		
		bottom.add(seatRC);
		
		bottom.add(ticket_price);
		bottom.add(con);
		
		
		

		
		
	}
	
	 @SuppressWarnings("deprecation")
	public static void view_site() {
			
	        JLabel jLabel = new JLabel();
	        addMessage();
	        for(int i=1;i<=rr;i++){
	        	for(int j=1;j<=cc;j++){
	        		if (ticPicure[i][j] == 0) {
	                	ImageIcon img1=new ImageIcon("resource/image/seat1.png");
	                    jLabel = new JLabel(img1);
	                    img1.setImage(img1.getImage().getScaledInstance(30,30,SeatUI.CROSSHAIR_CURSOR));
	                    jLabel.setBounds(60+60* (i-1), 80+ 40 * (j-1),60,30);
	                    jLabel.setText((i+","+j));
	                   
	                }
	                
	                if (ticPicure[i][j] == 1 || ticPicure[i][j] ==9) {
	                	ImageIcon img2=new ImageIcon("resource/image/seat3.jpg");
	                    jLabel = new JLabel(img2);
	                    img2.setImage(img2.getImage().getScaledInstance(30,30,SeatUI.CROSSHAIR_CURSOR));
	                    
	                    jLabel = new JLabel(img2);
	                    jLabel.setBounds(60+60 * (i-1), 80+ 40 * (j-1),60,30);
	                    jLabel.setText((i+","+j));

	                }
	        	
	               
	                
	                bottom.add(jLabel);


	                JLabel finalJLabel = jLabel;
	                
	                jLabel.addMouseListener(new MouseAdapter() {
	                    @Override
	                    public void mouseClicked(MouseEvent mouseEvent) {
	                        String[] sourceStrArray = finalJLabel.getText().split(",");
	                      int   m = Integer.parseInt(sourceStrArray[0]);//[String]待转换的字符串
	                      int   n = Integer.parseInt(sourceStrArray[1]);//[String]待转换的字符串
	                      
	              			
	                  if (ticPicure[m][n] == 0){

	                        ticPicure[m][n] = 1;
	                        date = new java.util.Date();
	                        createdate = df.format(date);
	                        tic_id=state[m][n];
	                        TicketSrv.lock(state[m][n],createdate);	                        
	                         bottom.removeAll();
	                          view_site();
//	                          addMessage();
	                          bottom.repaint();
	                         
	                      }
	                  
	                    
	                    
  	              		else if (ticPicure[m][n] == 1 ){

	                        ticPicure[m][n] = 0;
	                        tic_id=state[m][n];
	                        TicketSrv.unlock(state[m][n]);	                        
	                        bottom.removeAll();
	                        view_site();
//	                        addMessage();
	                        bottom.repaint();
	                    }
	                  	txtseatRC = new JLabel(n+"排"+m+"座");
  	                    txtseatRC.setBounds(800, 170, 100, 30);
  	              		bottom.add(txtseatRC);
  	              		bottom.add(txtticket_price);
	                     
	                    
	                    }
	                });
	                
	                
	                bottom.setLayout(null);
	                
	                
	        	}
	        		
           }
        }

    	


}
