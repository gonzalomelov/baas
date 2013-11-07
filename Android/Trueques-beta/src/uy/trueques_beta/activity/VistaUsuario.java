package uy.trueques_beta.activity;

import uy.trueques_beta.R;
import uy.trueques_beta.R.layout;
import uy.trueques_beta.R.menu;
import uy.trueques_beta.activity.CrearTrueque.CrearTruequeTask;
import uy.trueques_beta.entities.Objeto;
import uy.trueques_beta.entities.Trueque;
import uy.trueques_beta.entities.Usuario;
import uy.trueques_beta.negocio.Factory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class VistaUsuario extends Activity {

	private BloquearUsuarioTask mAuthTask = null;
	private Usuario u;
	private String usuario;
	
	private TextView nombre;
	private TextView mail;
	private TextView trueques;
	private TextView reputacion;
	private ImageView img;
	private Button button;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vista_usuario);
		
		Bundle bundle = this.getIntent().getExtras();
		this.usuario = bundle.getString("mailUsuario");
		
		this.u = Factory.getUsuarioCtrl().getUsuario(this.usuario);
		if(u==null)
			finish();
		
		nombre = (TextView)findViewById(R.id.LblNombre);
		mail = (TextView)findViewById(R.id.LblMail);
		trueques = (TextView)findViewById(R.id.LblTrueques);
		reputacion = (TextView)findViewById(R.id.LblReputacion);
		img = (ImageView)findViewById(R.id.ImgReputacion);
		button = (Button)findViewById(R.id.BtnBloquear);
		
		nombre.setText(u.getNombre());
		mail.setText(u.getMail());
		trueques.setText(""+u.getRealizados());
		reputacion.setText(String.valueOf(u.getPuntaje()));
		
		switch(Math.round(u.getPuntaje())){
		case 0:
			img.setImageResource(R.drawable.ic_pts0);
			break;
		case 1:
			img.setImageResource(R.drawable.ic_pts1);
			break;
		case 2:
			img.setImageResource(R.drawable.ic_pts2);
			break;
		case 3:
			img.setImageResource(R.drawable.ic_pts3);
			break;
		case 4:
			img.setImageResource(R.drawable.ic_pts4);
			break;
		case 5:
			img.setImageResource(R.drawable.ic_pts5);
			break;
	}
		
		if (u.isBloqueado())
			button.setText("Desbloquear");
		
		button.setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						mAuthTask = new BloquearUsuarioTask();
						mAuthTask.execute((Void) null);
					}
				});
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.vista_usuario, menu);
		return true;
	}
	
	
	public class BloquearUsuarioTask extends AsyncTask<Void, Void, Boolean> {
		@Override
		protected Boolean doInBackground(Void... params) {
			if(u.isBloqueado())
				return Factory.getUsuarioCtrl().desbloquear(usuario);
			else
				return Factory.getUsuarioCtrl().bloquear(usuario);
		}

		@Override
		protected void onPostExecute(final Boolean success) {
			mAuthTask = null;
			//showProgress(false);

			if (success) {
				
				Toast.makeText(VistaUsuario.this, "Operacion exitosa", Toast.LENGTH_SHORT).show();
				
				Intent intent = new Intent(VistaUsuario.this, VerUsuarios.class);
				//intent.putExtra("mail", mail);
		    	startActivity(intent);
				//+++
		    	
			} else {
				Toast.makeText(VistaUsuario.this, "Ocurrio un error", Toast.LENGTH_SHORT).show();
//				nombreView.setError("Ocurrio un error");
//				nombreView.requestFocus();
			}
		}

		@Override
		protected void onCancelled() {
			mAuthTask = null;
			//showProgress(false);
		}
	}

}
