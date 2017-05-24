package xupt.se.ttms.view.play;

import javax.swing.JOptionPane;


import xupt.se.ttms.model.Play;
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
		txttype.setText(Integer.toString(play.getPlay_type_id()));
		txtlang.setText(Integer.toString(play.getPlay_lang_id()));
		txtintroduction.setText(play.getPlay_introduction());
		txtlength.setText(Integer.toString(play.getPlay_length()));
		txtticketPrice.setText(play.getPlay_ticket_price()+"");
		txtstatus.setText(Integer.toString(play.getPlay_status()));
	}

	@Override
protected void btnSaveClicked(){
		
		if (txtName.getText() != null && txttype.getText() != null && txtlang.getText() != null && txtintroduction.getText() != null && txtlength .getText() != null && txtticketPrice.getText() != null && txtstatus.getText() != null ) {
			PlaySrv playSrv = new PlaySrv();
			Play play=new Play();
			play.setPlay_name(txtName.getText());
			play.setPlay_type_id(Integer.parseInt(txttype.getText()));
			play.setPlay_lang_id(Integer.parseInt(txtlang.getText()));
			play.setPlay_introduction("test");
			play.setPlay_length(Integer.parseInt(txtlength.getText()));
			play.setPlay_ticket_price(Integer.parseInt(txtticketPrice.getText()));
			play.setPlay_status(Integer.parseInt(txtstatus.getText()));

			playSrv.modify(play);
			this.setVisible(false);
			rst=true;
			getParent().setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "数据不完整");
		}		
	}
}
