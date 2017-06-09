package xupt.se.ttms.service;

import xupt.se.ttms.model.Dict;

public class test {
	public static void main(String[] args){
	
	System.out.println(DictSrv.Fetch_value_id("国语").get(0).getDict_id());
	}
}
