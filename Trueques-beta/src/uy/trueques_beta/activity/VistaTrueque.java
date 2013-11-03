package uy.trueques_beta.activity;

import uy.trueques_beta.R;
import uy.trueques_beta.R.layout;
import uy.trueques_beta.R.menu;
import uy.trueques_beta.entities.Trueque;
import uy.trueques_beta.negocio.Factory;
import android.opengl.Visibility;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class VistaTrueque extends Activity {
	
	private String mail;
	private Trueque t;
	
	private TextView nombre;
	private TextView duenio;
	private TextView fecha;
	private TextView desc;
	private TextView busca;
	private TextView valor;
	private TextView minVal;
	private Button button;
	private int idTrueque;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vista_trueque);
		
		//Obtengo el mail del usuario
		Intent intent = this.getIntent();
		this.idTrueque = intent.getIntExtra("idTrueque", -1);//.getString("idTrueque");
		//this.mail = intent.getExtras().getString("mail");
		SharedPreferences prefs = getSharedPreferences("TruequesData",Context.MODE_PRIVATE);
		this.mail = prefs.getString("mail", "");
		
		this.t = Factory.getTruequeCtrl().getTrueque(idTrueque);
		
		if(t==null){
			nombre = (TextView)findViewById(R.id.LblNomTrueque);
			nombre.setText("ERROR VUELVA A INTENTAR (id="+idTrueque+")");
		}else{
			nombre = (TextView)findViewById(R.id.LblNomTrueque);
			nombre.setText(t.getObjeto().getNombre());
			
			duenio = (TextView)findViewById(R.id.LblDuenio);
			duenio.setText(t.getUsuario().getNombre());
			valor = (TextView)findViewById(R.id.LblValor);
			valor.setText("$"+t.getObjeto().getValor());
			fecha = (TextView)findViewById(R.id.LblFecha);
			fecha.setText(t.getFechaIni().getDate()+"-"+(t.getFechaIni().getMonth()+1)+"-"+(t.getFechaIni().getYear()+1900));
			desc = (TextView)findViewById(R.id.LblDesc);
			desc.setText(t.getObjeto().getDescripcion());
			busca = (TextView)findViewById(R.id.LblBusca);
			busca.setText(t.getBuscado());
			minVal = (TextView)findViewById(R.id.LblMinVal);
			minVal.setText("$"+t.getMinVal());
			
			button = (Button)findViewById(R.id.BtnOfertar);
			if (this.mail.equals(t.getUsuario().getMail())){
				//button.setVisibility(View.INVISIBLE);
				button.setText("Ver ofertas pendientes ("+t.getOfertasPendientes().size()+")");
			}
			
			button.setOnClickListener(
					new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							if(t!=null){
								Intent intent = new Intent(VistaTrueque.this, Ofertar.class);
						        intent.putExtra("idTrueque", (int)t.getIdTrueque());
						        intent.putExtra("mail", mail);
								startActivity(intent);
							}
						}
					});
		}
	}
	
//	@Override
//	public void onBackPressed() {
//		// TODO Auto-generated method stub
//		Intent intent = new Intent(VistaTrueque.this, VerTrueques.class);
//		intent.putExtra("mail", ???)
//		startActivity(intent);
//		super.onBackPressed();
//	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.vista_trueque, menu);
		return true;
	}

}