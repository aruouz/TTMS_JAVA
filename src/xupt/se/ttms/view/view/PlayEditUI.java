package xupt.se.ttms.view.play;

import java.util.List;

import javax.swing.JOptionPane;

import xupt.se.ttms.model.Dict;
import xupt.se.ttms.model.Play;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.service.DictSrv;
import xupt.se.ttms.service.PlaySrv;
import xupt.se.ttms.view.play.PlayAddUI;

public class PlayEditUI extends PlayAddUI {
	private static final long serialVersionUID = 1L;
	private Play ply;

	public PlayEditUI(Play play) {
		initData(play);
	}

	void initData(Play play) {
		this.setTitle("修改剧目信息");
		txtName.setText(play.getPlay_name());
		List<Dict> list1=DictSrv.Fetch_Value(play.getPlay_type_id());
		jcbtype.setSelectedItem(list1.get(0).getDict_value()); 
		List<Dict> list2=DictSrv.Fetch_Value(play.getPlay_lang_id());
		jcblan.setSelectedItem(list2.get(0).getDict_value());
		txtintroduction.setText(play.getPlay_introduction());
		jcbl.setSelectedItem(Integer.toString(play.getPlay_length()));
		txtticketPrice.setText(play.getPlay_ticket_price()+"");
		jcbsta.setSelectedItem(PlaySrv.status(play.getPlay_status()));
	}

	@Override
	protected void btnSaveClicked(){
		PlaySrv playSrv = new PlaySrv();
		Play play=ply;
		play.setPlay_name(txtName.getText());
		java.util.List<Dict> list1=DictSrv.Fetch_value_id(jcbtype.getSelectedItem().toString());
		play.setPlay_type_id(list1.get(0).getDict_id());
		java.util.List<Dict> list2=DictSrv.Fetch_value_id(jcblan.getSelectedItem().toString());
		play.setPlay_lang_id(list2.get(0).getDict_id());
		play.setPlay_introduction(txtintroduction.getText());
		play.setPlay_length(Integer.parseInt(jcbl.getSelectedItem().toString()));
		play.setPlay_ticket_price(Integer.parseInt(txtticketPrice.getText()));
		int list3=PlaySrv.r_status(jcbsta.getSelectedItem().toString());
		play.setPlay_status(list3);

		playSrv.modify(play);
		this.setVisible(false);
		rst=true;
	}
}
