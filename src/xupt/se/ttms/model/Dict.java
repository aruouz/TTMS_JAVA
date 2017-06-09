package xupt.se.ttms.model;

public class Dict {
		private int dict_id;
		private int dict_parent;
		private int dict_index;
		private String dict_name;
		private String dict_value;
		
		public int getDict_id(){
			return dict_id;
		}
		
		public void setDict_id(int id){
			this.dict_id = id;
		}
		
		public int getDict_parent(){
			return dict_parent;
		}
		
		public void setDict_parent(int dict_parent){
			this.dict_parent = dict_parent;
		}
		
		public int getDict_index(){
			return dict_index;
		}
		
		public void setDict_index(int dict_index){
			this.dict_index = dict_index;
		}
		
		public String getDict_name(){
			return dict_name;
		}
		
		public void setDict_name(String dict_name){
			this.dict_name = dict_name;
		}
		
		public String getDict_value(){
			return dict_value;
		}
		
		public void setDcit_value(String dict_value){
			this.dict_value = dict_value;
		}
		
}
