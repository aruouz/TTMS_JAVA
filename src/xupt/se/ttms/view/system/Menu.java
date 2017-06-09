package xupt.se.ttms.view.system;



import javax.swing.*;

import xupt.se.ttms.view.play.PlayMgrUI;
import xupt.se.ttms.view.sale.SaleUI;
import xupt.se.ttms.view.schedule.ScheduleMgrUI;
import xupt.se.ttms.view.studio.StudioMgrUI;
import xupt.se.ttms.view.user.UserMgrUI;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Menu extends JFrame{
	
	JFrame frame = new JFrame("主菜单");
	JPanel panel = new JPanel();
	JButton[] button = {new JButton("演出厅管理"),new JButton("剧目管理"),new JButton("演出计划"),new JButton("用户管理"), new JButton("销售统计"),new JButton("退出系统")};
	
	public Menu() {
		// TODO 自动生成的构造函数存根
		panel.setBackground(Color.white);
		panel.setLayout(null);
		button[0].setBounds(90, 50, 40, 100);
		button[0].setSize(100,50);
		button[0].setBorder(BorderFactory.createRaisedBevelBorder()); 
		 button[0] .setFont(new  java.awt.Font("字体管家嘉丽丽",  1,  16)); 
		   button[0].setBackground(Color.pink); 
		
		button[1].setBounds(220, 50, 40, 100);
		button[1].setSize(100,50);
		button[1].setBorder(BorderFactory.createRaisedBevelBorder()); 
		 button[1] .setFont(new  java.awt.Font("字体管家嘉丽丽",  1,  16)); 
		   button[1].setBackground(Color.pink); 
		
		button[2].setBounds(350, 50, 40, 100);
		button[2].setSize(100,50);
		button[2].setBorder(BorderFactory.createRaisedBevelBorder()); 
		 button[2] .setFont(new  java.awt.Font("字体管家嘉丽丽",  1,  16)); 
		   button[2].setBackground(Color.pink); 

		button[3].setBounds(90, 150, 40, 100);
		button[3].setSize(100,50);
		button[3].setBorder(BorderFactory.createRaisedBevelBorder()); 
		 button[3] .setFont(new  java.awt.Font("字体管家嘉丽丽",  1,  16)); 
		   button[3].setBackground(Color.pink); 
		button[4].setBounds(220, 150, 40, 100);
		button[4].setSize(100,50);
		button[4].setBorder(BorderFactory.createRaisedBevelBorder()); 
		 button[4] .setFont(new  java.awt.Font("字体管家嘉丽丽",  1,  16)); 
		   button[4].setBackground(Color.pink); 
		button[5].setBounds(350, 150, 40, 100);
		button[5].setSize(100,50);
		button[5].setBorder(BorderFactory.createRaisedBevelBorder()); 
		 button[5] .setFont(new  java.awt.Font("字体管家嘉丽丽",  1,  16)); 
		   button[5].setBackground(Color.pink); 
		
	
		
		
		
		for(int i=0;i<6;i++)
			
			panel.add(button[i]);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE); //关闭窗体退出程序
		frame.setLayout(new BorderLayout());
		frame.setLocation(100, 100);    //设置框架的位置
		frame.add(panel);
		
		dealwithEvent();
		
		frame.setSize(600, 400);
		frame.setVisible(true);
	}
	private void dealwithEvent()
    {
         
        //窗体关闭监听
         frame.addWindowListener(new WindowAdapter(){
             public void windowClosing(WindowEvent e){
                 System.exit(0);
                 
             }
             
         });
         
         button[0].addMouseListener(new MouseAdapter(){

             private int clickCount=1;
             public void mouseEntered(MouseEvent e) 
             {
                 
             }
             public void mouseClicked(MouseEvent e)
             {
                 if(e.getClickCount()==1)
                 { 
                	 
                	 StudioMgrUI s=new StudioMgrUI();
                	 s.setVisible(true);
                	   //关闭当前窗口
                	 }
                
                 
             }
             
         });
         
         button[1].addMouseListener(new MouseAdapter(){

             private int clickCount=1;
             public void mouseEntered(MouseEvent e) 
             {
                 
             }
             public void mouseClicked(MouseEvent e)
             {
                 if(e.getClickCount()==1)
                 {
//                	 
                	 PlayMgrUI p=new PlayMgrUI();
                	 p.setVisible(true);
                	 
                	 }
                
                 
             }
             
         });
         
         button[2].addMouseListener(new MouseAdapter(){

             private int clickCount=1;
             public void mouseEntered(MouseEvent e) 
             {
                 
             }
             public void mouseClicked(MouseEvent e)
             {
                 if(e.getClickCount()==1)
                 {
//                	 System.out.println("单击被执行"+clickCount+++"次");
                	 
                	 	//new UI();//实例化另一个窗口的对象
                	 ScheduleMgrUI s = new ScheduleMgrUI();
                	 s.setVisible(true);
                	 
            ;   //关闭当前窗口
                	 }
                
                 
             }
             
         });
         
         //鼠标进入按钮监听触发事件以及鼠标被双击监听触发事件
         button[3].addMouseListener(new MouseAdapter(){

             private int clickCount=1;
             public void mouseEntered(MouseEvent e) 
             {
//                 System.out.println("鼠标进入到该组件"+count+++"次");
                
             }
             public void mouseClicked(MouseEvent e)
             {
                 if(e.getClickCount()==1)
                 {
//                	 System.out.println("单击被执行"+clickCount+++"次");
                	 
                	 	//new UserManage();//实例化另一个窗口的对象
                	 UserMgrUI u = new UserMgrUI();
                     u.setVisible(true);
                	   //关闭当前窗口
                	 }
                
                 
             }
             
         });
         
         button[4].addMouseListener(new MouseAdapter(){

             private int clickCount=1;
           
             public void mouseClicked(MouseEvent e)
             {
                 if(e.getClickCount()==1)
                 {
//                	 System.out.println("单击被执行"+clickCount+++"次");
                	 
                	 	//new Sale();//实例化另一个窗口的对象
                
                	  SaleUI sale = new SaleUI();
                	  sale.setVisible(true);
                	
                	 }
                
                 
             }
             
         });
         
       
         
         button[5].addMouseListener(new MouseAdapter(){

             private int clickCount=1;
             public void mouseEntered(MouseEvent e) 
             {
//                 System.out.println("鼠标进入到该组件"+count+++"次");
                 
             }
             public void mouseClicked(MouseEvent e)
             {
                 if(e.getClickCount()==1)
                 {
                	 
                	 frame.setVisible(false);   //关闭当前窗口
                 }
                
                 
             }
             
         });
        
    }
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		new Menu();
	}

}



