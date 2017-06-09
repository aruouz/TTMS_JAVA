package xupt.se.ttms.view.sellticket;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import xupt.se.ttms.model.Seat;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.view.seat.SeatUI;

public class SellTicketUI extends JFrame{

	
	private Seat seat;
	private static Studio stu;
	private  int c,r;
	
	
	private static  JPanel bottom = new JPanel();
	private static int sate[][] = new int [20][20];
	
	public SellTicketUI() {
		
		 for (int i = 1; i < 10; i++) {  //初始化票的状态
	            for (int j = 1; j < 10; j++) {
	            	sate[i][j] = 0;
	            }
	            
		 }
	 
		
      

	}
	
	void getCR(Studio stu)  //获取座位的行列
	{
		 c=stu.getColCount();
		 r=stu.getRowCount();
	}
	
static void view_site() {
		
        JLabel jLabel = new JLabel();
        
        
        for (int i = 1; i <10; i++) {
            for (int j = 1; j < 10; j++) {
            	 if (sate[i][j] == 0) {
                 	ImageIcon img1=new ImageIcon("resource/image/seat1.png");
                     jLabel = new JLabel(img1);
                     img1.setImage(img1.getImage().getScaledInstance(30,30,SeatUI.CROSSHAIR_CURSOR));
                     
                     jLabel.setBounds(60+60 * (i-1), 80+ 40 * (j-1),60,30);
                     jLabel.setText((i+","+j));
                 }
                 if (sate[i][j] == 1) {
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
                        int m = Integer.parseInt(sourceStrArray[0]);//[String]待转换的字符串
                        int n = Integer.parseInt(sourceStrArray[1]);//[String]待转换的字符串
                        if (sate[m][n] == 0){
//                            int i = JOptionPane.showConfirmDialog(null, "购票", "是否选择该座位", JOptionPane.YES_NO_OPTION);
//                            if(i == 0){
//                                sate[m][n] = 1;
//                            }
                            sate[m][n] = 1;
                            bottom.removeAll();
                            view_site();
                            bottom.repaint();
                        }
                        else{
//                            int i = JOptionPane.showConfirmDialog(null, "退票", "是否退票", JOptionPane.YES_NO_OPTION);
//                            if(i == 0){
//                                sate[m][n] = 0;
//                            }
                        	 sate[m][n] = 0;
                            bottom.removeAll();
                            view_site();
                            bottom.repaint();
                        }
                    }
                });
                bottom.setLayout(null);  
             
                
            }
        }
}


	public static void main(String[] args) {
		SellTicketUI seats = new SellTicketUI();

      seats.add(bottom);
      view_site();
      seats.setTitle("购票");
      seats.setSize(1024, 700);
      seats.setVisible(true);
      seats.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      seats.setVisible(true);
	}

}
