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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
	private Spinner spinner;
	private boolean soyTrueque;
 	
	private TextView puntaje;
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
		}else{//SOY EL QUE PUBLICO EL TRUEQUE
			if(t.getObjeto().getDuenio().getMail().equals(mail)){
				this.soyTrueque=true;
				//++ TRUEQUE
				nombre = (TextView)findViewById(R.id.LblObjetoTrueque);
				nombre.setText(t.getObjeto().getNombre());
				
				valor = (TextView)findViewById(R.id.LblValor);
				valor.setText("$"+t.getObjeto().getValor());
				fecha = (TextView)findViewById(R.id.LblFecha);
				fecha.setText("El día "+t.getFechaFin().getDate()+"-"+(t.getFechaFin().getMonth()+1)+"-"+(t.getFechaFin().getYear()+1900));
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
				
				//Puntaje
				spinner = (Spinner)findViewById(R.id.puntos_spinner);
				if (t.getPuntosGanadora()==0){
					puntaje = (TextView)findViewById(R.id.LblPuntaje);
					puntaje.setText("Puntua la oferta: ");
				}else{
					spinner.setVisibility(View.GONE);
					if(t.getPuntosTrueque()>0){
						puntaje = (TextView)findViewById(R.id.LblPuntaje);
						puntaje.setText("Te dieron "+t.getPuntosTrueque()+" pts por este trueque");
					}
					else{
						puntaje = (TextView)findViewById(R.id.LblPuntaje);
						puntaje.setText("Aun no te han puntuado");
					}
				}
			}else{//SOY EL OFERTANTE
				this.soyTrueque=false;
				//++ TRUEQUE
				nombre = (TextView)findViewById(R.id.LblObjetoTrueque);
				nombre.setText(t.getGanadora().getObjeto().getNombre());
				
				valor = (TextView)findViewById(R.id.LblValor);
				valor.setText("$"+t.getGanadora().getObjeto().getValor());
				fecha = (TextView)findViewById(R.id.LblFecha);
				fecha.setText("El día "+t.getFechaFin().getDate()+"-"+(t.getFechaFin().getMonth()+1)+"-"+(t.getFechaFin().getYear()+1900));
				desc = (TextView)findViewById(R.id.LblDesc);
				desc.setText(t.getGanadora().getObjeto().getDescripcion());
				
				//++ OFERTA
				objOfer = (TextView)findViewById(R.id.LblObjetoOferta);
				objOfer.setText(t.getObjeto().getNombre());
				duenio = (TextView)findViewById(R.id.LblDuenioOferta);
				duenio.setText(t.getObjeto().getDuenio().getNombre());
				valorOfer = (TextView)findViewById(R.id.LblValorOferta);
				valorOfer.setText("$"+t.getObjeto().getValor());
				descOfer = (TextView)findViewById(R.id.LblDescOferta);
				descOfer.setText(t.getObjeto().getDescripcion());
				ubicacion = (TextView)findViewById(R.id.LblUbicacion);
				ubicacion.setText(t.getUbicacion());
				
				//Puntaje
				spinner = (Spinner)findViewById(R.id.puntos_spinner);
				if (t.getPuntosTrueque()==0){
					puntaje = (TextView)findViewById(R.id.LblPuntaje);
					puntaje.setText("Puntua el trueque: ");
				}else{
					spinner.setVisibility(View.GONE);
					if(t.getPuntosGanadora()>0){
						puntaje = (TextView)findViewById(R.id.LblPuntaje);
						puntaje.setText("Te dieron "+t.getPuntosGanadora()+" pts por esta oferta");
					}
					else{
						puntaje = (TextView)findViewById(R.id.LblPuntaje);
						puntaje.setText("Aún no te han puntuado");
					}
				}
			}
		}
		//Adaptador SPINNER
		final String[] items = new String[]{"Puntos","1","2","3","4","5"};
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
		adaptador.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
         
        spinner.setAdapter(adaptador);

        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
                        //lblMensaje.setText("Seleccionado: " + items[position]);
                	//Factory.getTruequeCtrl().
                	if (position!=0){
	                	if(soyTrueque)
	                		t.puntuarGanadora(position);
	                	else
	                		t.puntuarTrueque(position);
	                	//Toast.makeText(, "Puntuaste en "+position+" el trueque", Toast.LENGTH_LONG);
	                	Intent intent =new Intent(VistaTruequeHecho.this, VistaTruequeHecho.class);
	                	intent.putExtra("idTrueque", idTrueque);
	                	startActivity(intent);
	                	finish();
	                	//v.setVisibility(View.GONE);
                	}
                }

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.vista_trueque_hecho, menu);
		return true;
	}

}
