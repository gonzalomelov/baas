package uy.trueques_beta.activity;

import uy.trueques_beta.R;
import uy.trueques_beta.R.layout;
import uy.trueques_beta.R.menu;
import uy.trueques_beta.entities.Trueque;
import uy.trueques_beta.negocio.Factory;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class VistaTruequeHecho extends Activity {
	
	private String mail;
	private Trueque t;
	
	private TextView nombre;
	private TextView duenio;
	private TextView fecha;
	private TextView desc;
	private TextView busca;
	private TextView valor;
	private TextView objOfer;
	private TextView descOfer;
	private TextView ubicacion;
	private TextView valorOfer;
	//private Button button;
	private int idTrueque;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vista_trueque_hecho);
		
		//Obtengo el mail del usuario
		Intent intent = this.getIntent();
		this.idTrueque = intent.getIntExtra("idTrueque", -1);//.getString("idTrueque");
		//this.mail = intent.getExtras().getString("mail");
		SharedPreferences prefs = getSharedPreferences("TruequesData",Context.MODE_PRIVATE);
		this.mail = prefs.getString("mail", "");
		
		this.t = Factory.getTruequeCtrl().getTrueque(idTrueque);
		
		if(t==null){
			nombre = (TextView)findViewById(R.id.LblObjetoTrueque);
			nombre.setText("ERROR VUELVA A INTENTAR (id="+idTrueque+")");
		}else{
			//++ TRUEQUE
			nombre = (TextView)findViewById(R.id.LblObjetoTrueque);
			nombre.setText(t.getObjeto().getNombre());
			
			valor = (TextView)findViewById(R.id.LblValor);
			valor.setText("$"+t.getObjeto().getValor());
			fecha = (TextView)findViewById(R.id.LblFecha);
			fecha.setText(t.getFechaFin().getDate()+"-"+(t.getFechaFin().getMonth()+1)+"-"+(t.getFechaFin().getYear()+1900));
			desc = (TextView)findViewById(R.id.LblDesc);
			desc.setText(t.getObjeto().getDescripcion());
			
			//++ OFERTA
			objOfer = (TextView)findViewById(R.id.LblObjetoOferta);
			objOfer.setText(t.getGanadora().getObjeto().getNombre());
			duenio = (TextView)findViewById(R.id.LblDuenioOferta);
			duenio.setText(t.getGanadora().getUsuario().getNombre());
			valorOfer = (TextView)findViewById(R.id.LblValorOferta);
			valorOfer.setText("$"+t.getGanadora().getObjeto().getValor());
			descOfer = (TextView)findViewById(R.id.LblDescOferta);
			descOfer.setText(t.getGanadora().getObjeto().getDescripcion());
			ubicacion = (TextView)findViewById(R.id.LblUbicacion);
			ubicacion.setText(t.getGanadora().getUbicacion());
			
			
			
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.vista_trueque_hecho, menu);
		return true;
	}

}
