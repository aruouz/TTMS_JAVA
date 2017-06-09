package xupt.se.ttms.view.play;

import javax.swing.JOptionPane;

import xupt.se.ttms.model.Dict;
import xupt.se.ttms.model.Play;
import xupt.se.ttms.service.DictSrv;
import xupt.se.ttms.service.PlaySrv;
import xupt.se.ttms.view.play.PlayAddUI;

public class PlayEditUI extends PlayAddUI {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8069153395833603461L;

	public PlayEditUI(Play play) {
		initData(play);
	}

	private void initData(Play play) {
		txtName.setText(play.getPlay_name());
		jcbtype.addItem(Integer.toString(play.getPlay_type_id()));
		jcbl.addItem(Integer.toString(play.getPlay_lang_id()));
		txtintroduction.setText(play.getPlay_introduction());
		txtlength.setText(Integer.toString(play.getPlay_length()));
		txtticketPrice.setText(play.getPlay_ticket_price()+"");
		jcbsta.addItem(Integer.toString(play.getPlay_status()));
	}

	@Override
	protected void btnSaveClicked(){
		if (txtName.getText() != null ) {
			PlaySrv playSrv = new PlaySrv();
			Play play=new Play();
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
	
			playSrv.add(play);
			this.setVisible(false);
			rst=true;
			getParent().setVisible(true);
		}
		else {
			JOptionPane.showMessageDialog(null, "数据不完整");
		}	
	}
}
