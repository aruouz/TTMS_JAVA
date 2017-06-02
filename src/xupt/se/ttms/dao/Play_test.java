package xupt.se.ttms.dao;

import java.util.List;

import xupt.se.ttms.model.Play;
import xupt.se.ttms.service.PlaySrv;

public class Play_test {
	public static void main(String[] args){
	PlaySrv test1 = new PlaySrv();
	Play ply1 = new Play();
	Play ply2 = new Play();
	ply1.setPlay_type_id(1);
	ply1.setPlay_lang_id(1);
	ply1.setPlay_introduction("这是一个电影 嗯 就酱0.0");
	ply1.setPlay_length(120);
	ply1.setPlay_name("aruouz  play");
	ply1.setPlay_status(1);
	ply1.setPlay_ticket_price(20);
	ply1.setPlay_image("d:/1.jpg");
	test1.add(ply1);
	System.out.println("创建成功");
	
	}
	

}
