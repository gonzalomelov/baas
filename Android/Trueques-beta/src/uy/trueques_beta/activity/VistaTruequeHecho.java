package uy.trueques_beta.activity;

import uy.trueques_beta.R;
import uy.trueques_beta.R.layout;
import uy.trueques_beta.R.menu;
import uy.trueques_beta.activity.VistaTrueque.VistaTruequeTask;
import uy.trueques_beta.entities.Trueque;
import uy.trueques_beta.entities.Usuario;
import uy.trueques_beta.negocio.Factory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class VistaTruequeHecho extends Activity {

	private VistaTruequeHechoTask mAuthTask =null;
	private PuntuarTask mAuthTask2 =null;
	private String mail;
	private Trueque t;
	private Usuario u;
	
	private TextView nombre;
	private TextView duenio;
	private TextView fecha;
	private TextView desc;
	private TextView valor;
	private TextView objOfer;
	private TextView descOfer;
	private TextView ubicacion;
	private TextView valorOfer;
	private ImageView img1;
	private ImageView img2;
	private Spinner spinner;
	private boolean soyTrueque;
 	
	private TextView puntaje;
	//private Button button;
	//private int idTrueque;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vista_trueque_hecho);
		
		//Obtengo el mail del usuario
		Intent intent = this.getIntent();
		//this.idTrueque = intent.getIntExtra("idTrueque", -1);//.getString("idTrueque");
		//this.mail = intent.getExtras().getString("mail");
		SharedPreferences prefs = getSharedPreferences("TruequesData",Context.MODE_PRIVATE);
		this.mail = prefs.getString("mail", "");
		
		String truequeJson= intent.getExtras().getString("Trueque");
		this.t = Trueque.fromJson(truequeJson);//Factory.getTruequeCtrl().getTrueque(idTrueque);
		
		if(t==null){
			nombre = (TextView)findViewById(R.id.LblObjetoTrueque);
			nombre.setText("ERROR VUELVA A INTENTAR (json="+truequeJson+")");
		}else{
			if(mAuthTask==null){
				mAuthTask = new VistaTruequeHechoTask();
				mAuthTask.execute((Void) null);
			}
		}
		//Adaptador SPINNER
		final String[] items = new String[]{"Puntos","1","2","3","4","5"};
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
		adaptador.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
         
		spinner = (Spinner)findViewById(R.id.puntos_spinner);
		
        spinner.setAdapter(adaptador);

        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
                	if (position!=0){
                		if(mAuthTask2==null){
                			Integer[] par = {Integer.valueOf(position)};
            				mAuthTask2 = new PuntuarTask();
            				mAuthTask2.execute(par);
            			}
//	                	if(soyTrueque)
//	                		Factory.getTruequeCtrl().puntuarGanadora(t.getIdTrueque(), position);
//	                	else
//	                		Factory.getTruequeCtrl().puntuarTrueque(t.getIdTrueque(),position);
	                	//Toast.makeText(, "Puntuaste en "+position+" el trueque", Toast.LENGTH_LONG);
//	                	Intent intent =new Intent(VistaTruequeHecho.this, VistaTruequeHecho.class);
//	                	intent.putExtra("idTrueque", idTrueque);
//	                	startActivity(intent);
//	                	finish();
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
	

	//+++++++++ ASYNC ++++++++//
	public class VistaTruequeHechoTask extends AsyncTask<Void, Void, Boolean> {
		//private int idTrueque;
		@Override
		protected Boolean doInBackground(Void... params) {
			u = Factory.getUsuarioCtrl().getUsuario(VistaTruequeHecho.this, t.getUsuario());
			return u!=null;
		}

		@Override
		protected void onPostExecute(final Boolean success) {
			mAuthTask = null;
			//showProgress(false);

			if (success) {
	
				//SOY EL QUE PUBLICO EL TRUEQUE
				if(t.getObjeto().getDuenio().equals(mail)){
					soyTrueque=true;
					//++ TRUEQUE
					nombre = (TextView)findViewById(R.id.LblObjetoTrueque);
					nombre.setText(t.getObjeto().getNombre());
					
					valor = (TextView)findViewById(R.id.LblValor);
					valor.setText("$"+t.getObjeto().getValor());
					fecha = (TextView)findViewById(R.id.LblFecha);
					fecha.setText("El día "+t.getFechaFin().getDate()+"-"+(t.getFechaFin().getMonth()+1)+"-"+(t.getFechaFin().getYear()+1900));
					desc = (TextView)findViewById(R.id.LblDesc);
					desc.setText(t.getObjeto().getDescripcion());
					img1 = (ImageView)findViewById(R.id.image1);
					img1.setImageBitmap(t.getImagen());
					
					//++ OFERTA
					objOfer = (TextView)findViewById(R.id.LblObjetoOferta);
					objOfer.setText(t.getGanadora().getObjeto().getNombre());
					duenio = (TextView)findViewById(R.id.LblDuenioOferta);
					//Usuario u = Factory.getUsuarioCtrl().getUsuario(t.getGanadora().getUsuario());
					duenio.setText(u.getNombre());
					valorOfer = (TextView)findViewById(R.id.LblValorOferta);
					valorOfer.setText("$"+t.getGanadora().getObjeto().getValor());
					descOfer = (TextView)findViewById(R.id.LblDescOferta);
					descOfer.setText(t.getGanadora().getObjeto().getDescripcion());
					ubicacion = (TextView)findViewById(R.id.LblUbicacion);
					ubicacion.setText(t.getGanadora().getUbicacion());
					img2 = (ImageView)findViewById(R.id.image2);
					img2.setImageBitmap(t.getGanadora().getImagen());
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
					soyTrueque=false;
					//++ TRUEQUE
					nombre = (TextView)findViewById(R.id.LblObjetoTrueque);
					nombre.setText(t.getGanadora().getObjeto().getNombre());
					
					valor = (TextView)findViewById(R.id.LblValor);
					valor.setText("$"+t.getGanadora().getObjeto().getValor());
					fecha = (TextView)findViewById(R.id.LblFecha);
					fecha.setText("El día "+t.getFechaFin().getDate()+"-"+(t.getFechaFin().getMonth()+1)+"-"+(t.getFechaFin().getYear()+1900));
					desc = (TextView)findViewById(R.id.LblDesc);
					desc.setText(t.getGanadora().getObjeto().getDescripcion());
					img1 = (ImageView)findViewById(R.id.image1);
					img1.setImageBitmap(t.getGanadora().getImagen());
					
					//++ OFERTA
					objOfer = (TextView)findViewById(R.id.LblObjetoOferta);
					objOfer.setText(t.getObjeto().getNombre());
					duenio = (TextView)findViewById(R.id.LblDuenioOferta);
					//Usuario u = Factory.getUsuarioCtrl().getUsuario((t.getObjeto().getDuenio()));
					duenio.setText(u.getNombre());
					valorOfer = (TextView)findViewById(R.id.LblValorOferta);
					valorOfer.setText("$"+t.getObjeto().getValor());
					descOfer = (TextView)findViewById(R.id.LblDescOferta);
					descOfer.setText(t.getObjeto().getDescripcion());
					ubicacion = (TextView)findViewById(R.id.LblUbicacion);
					ubicacion.setText(t.getUbicacion());
					img2 = (ImageView)findViewById(R.id.image2);
					img2.setImageBitmap(t.getImagen());
					
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
			}else{
				Log.i("[VistaTruequeHecho]:", "ERROR");
				Toast.makeText(VistaTruequeHecho.this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
			}
		}
		
		
		@Override
		protected void onCancelled() {
			mAuthTask = null;
			//showProgress(false);
		}
	}	


	//++++++++++ASYNC 2 +++++++++//
	public class PuntuarTask extends AsyncTask<Integer, Void, Boolean> {
		
		@Override
		protected Boolean doInBackground(Integer... params) {
			if(soyTrueque)
        		Factory.getTruequeCtrl().puntuarGanadora(VistaTruequeHecho.this, t.getIdTrueque(), params[0]);
        	else
        		Factory.getTruequeCtrl().puntuarTrueque(VistaTruequeHecho.this, t.getIdTrueque(), params[0]);
			return true;
		}
		
		@Override
		protected void onPostExecute(final Boolean success) {
			mAuthTask = null;
			//showProgress(false);
		
			if (success) {
				Intent intent =new Intent(VistaTruequeHecho.this, VistaTruequeHecho.class);
            	intent.putExtra("Trueque", t.toJson());
            	startActivity(intent);
            	VistaTruequeHecho.this.finish();
			} else {
				Log.i("[VistaTruequeHecho]:", "Error al puntuar");
				Toast.makeText(VistaTruequeHecho.this, "Error al puntuar", Toast.LENGTH_SHORT).show();
			}
		}
		
		@Override
		protected void onCancelled() {
			mAuthTask = null;
			//showProgress(false);
			}
		}	
		
}
