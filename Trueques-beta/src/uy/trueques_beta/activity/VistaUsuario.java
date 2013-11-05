package uy.trueques_beta.activity;

import uy.trueques_beta.R;
import uy.trueques_beta.R.layout;
import uy.trueques_beta.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class VistaUsuario extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vista_usuario);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.vista_usuario, menu);
		return true;
	}

}
