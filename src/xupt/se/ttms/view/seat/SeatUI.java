package xupt.se.ttms.view.seat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import xupt.se.ttms.model.Seat;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.service.SeatSrv;
import xupt.se.ttms.service.StudioSrv;

public class SeatUI extends JFrame implements ActionListener {
	
	private Studio st;
	private  int c,r;
	protected boolean rst=false; 				//操作结果
	JButton btnSave = new JButton("保存");
	JButton btndelete = new JButton("取消");
	
	private JLabel lblname,lblrow,lblcolumn,lbldesciption;
	private JLabel txtname,txtrow,txtcolumn,txtdescription;
	
	public static  JPanel bottom = new JPanel();
	public static  JPanel sPanel = new JPanel();
	
	private static int sate[][] = new int [100][100];
	int m,n;
	
	public JPanel get_bottom()
	{
		return bottom;
	}
	

	public JPanel get_sPanel()
	{
		return sPanel;
	}
	
	
	public SeatUI(Studio studio) {   //有参构造函数
		 st=studio;
		 getRC();  //获取行列值
		 
		 this.setTitle("座位管理");
	        List<Seat> seatList = new SeatSrv().Fetch(st.getID());
	        
	        Iterator<Seat> itr = seatList.iterator();
			while (itr.hasNext()) {
				Seat s = itr.next();
				
				sate[s.getSeat_row()][s.getSeat_column()] = s.getSeat_status();
				
			}
//		 for (int i = 1; i <= c; i++) {   //初始化座位
//	            for (int j = 1; j <= r; j++) {
//	            	sate[i][j] = 1;
//	            }
//	            
//		 }
		initsPanel();
	
		view_site();
	
	}
	
	public void getRC()
	{
		c=st.getColCount();
		r=st.getRowCount();
	}
	
	public void initsPanel()
	{
		

		//sPanel.setSize(200, 200);
		lblname = new JLabel("演出厅名称:");
//		lblname.setBounds(-100,50,100,50);  //
		sPanel.add(lblname);
		txtname = new JLabel(st.getName().toString());
//		txtname.setBounds(350,30,200,30);
		sPanel.add(txtname);
		
		lblrow = new JLabel("座位行数:");
		sPanel.add(lblrow);
		txtrow = new JLabel(Integer.toString(st.getRowCount()));
		sPanel.add(txtrow);
		

		lblcolumn = new JLabel("座位列数:");
		sPanel.add(lblcolumn);
		txtcolumn = new JLabel(Integer.toString(st.getColCount()));
		sPanel.add(txtcolumn);
		
		lbldesciption = new JLabel("演出厅简介:");
		sPanel.add(lbldesciption);
		txtdescription = new JLabel(st.getIntroduction().toString());
		sPanel.add(txtdescription);
		
	}
	
	public void view_site() {
		
        JLabel jLabel = new JLabel();
       
        
        
        for (int i = 1; i <= c; i++) {
            for (int j = 1; j <= r; j++) {
                if (sate[i][j] == 1) {
                	ImageIcon img1=new ImageIcon("resource/image/seat1.png");
                    jLabel = new JLabel(img1);
                    img1.setImage(img1.getImage().getScaledInstance(30,30,SeatUI.CROSSHAIR_CURSOR));
                    
                    jLabel.setBounds(60+60 * (i-1), 80+ 40 * (j-1),60,30);
                    jLabel.setText((i+","+j));
                }
                if (sate[i][j] == -1) {
                	ImageIcon img2=new ImageIcon("resource/image/jinzhi.png");
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
                         m = Integer.parseInt(sourceStrArray[0]);//[String]待转换的字符串
                         n = Integer.parseInt(sourceStrArray[1]);//[String]待转换的字符串
                        

                        if (sate[m][n] == 1){
//                        	SeatSrv ss = new SeatSrv();
//                        	
//                        	List<Seat> sl =  SeatSrv.Fetch_r_c(m, n,st.getID());
//                        	Seat s=sl.get(0);
//                        	s.setSeat_status(-1);
//                        	ss.modify(s);
                        	
                          sate[m][n] = -1;
                        	
                          bottom.removeAll();
                          view_site();
                          bottom.repaint();
                      }
                      else{
                      	  sate[m][n] = 1;
                          bottom.removeAll();
                          view_site();
                          bottom.repaint();
                      }
                    }
                });
                
        		btnSave.addActionListener(this);
        		btnSave.setBackground(new Color(255,246,143));
        		btnSave.setBounds(300, 20, 60, 30);
        		bottom.add(btnSave);
        		
        		
        		btndelete.addActionListener(this);
        		btndelete.setBackground(new Color(255,246,143));
        		btndelete.setBounds(400, 20, 60, 30);
         		bottom.add(btndelete);
                bottom.setLayout(null);  
            }
        }

    	
}
	



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		
		if (e.getSource() == btndelete) {
			rst=false;
			this.setVisible(false);
		}
		else if (e.getSource() == btnSave) {
//			JOptionPane.showMessageDialog(null, "确认要修改座位状态吗？");
			btnSaveClicked();
//		return;
		}
	}

	

	private void btnSaveClicked() {    //在数据库中保存状态
		// TODO 自动生成的方法存根
		
		 for (int i = 1; i <= c; i++) {
	            for (int j = 1; j <= r; j++) {
	                
	                if (sate[i][j] == -1) {
	                	SeatSrv ss = new SeatSrv();
                    	
                    	List<Seat> sl =  SeatSrv.Fetch_r_c(m, n,st.getID());
                    	Seat s=sl.get(0);
                    	s.setSeat_status(-1);
                    	ss.modify(s);

	                }
	                
	                else{
	                	SeatSrv ss = new SeatSrv();
                    	
                    	List<Seat> sl =  SeatSrv.Fetch_r_c(m, n,st.getID());
                    	Seat s=sl.get(0);
                    	s.setSeat_status(1);
                    	ss.modify(s);
	                }
	           }
	
		}
		
		rst=false;
		this.setVisible(false);
   }
	
}
