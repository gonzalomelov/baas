package uy.trueques_beta.activity;

import uy.com.group05.baassdk.GCMService;
import uy.com.group05.baassdk.SDKFactory;
import uy.trueques_beta.R;
import uy.trueques_beta.R.id;
import uy.trueques_beta.R.layout;
import uy.trueques_beta.R.menu;
import uy.trueques_beta.activity.Registrar.RegistroTask;
import uy.trueques_beta.entities.Usuario;
import uy.trueques_beta.negocio.Factory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
 
public class Perfil extends Fragment {//Activity {

	private PerfilTask mAuthTask = null;
	
	private String mail;
	private TextView lblNombre;
	private TextView lblEmail;
	private TextView lblRealizados;
	private TextView lblPublicados;
	private TextView lblAceptados;
	private ImageView imgPts;
	private TextView imgText;
	private TextView lblBloq;
	private PerfilListener listener;
	
	 @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
 
        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }
	 
	@Override
	public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
		SharedPreferences prefs = this.getActivity().getSharedPreferences("TruequesData",Context.MODE_PRIVATE);
		this.mail = prefs.getString("mail", "");
		
		Usuario u = Factory.getUsuarioCtrl().getUsuario(Perfil.this.getActivity(), mail);
		
		mAuthTask = null;
		//showProgress(false);

		if (u != null) {
			
			SDKFactory.getGCMService(Perfil.this.getActivity());
			
			//Seteo los datos del usuario
			lblNombre = (TextView)getView().findViewById(R.id.LblNombre);
			lblNombre.setText(u.getNombre());
			lblEmail = (TextView)getView().findViewById(R.id.LblEmail);
			lblEmail.setText(u.getMail());
			
			lblRealizados = (TextView)getView().findViewById(R.id.LblRealizados);
			lblRealizados.setText(String.valueOf(u.getRealizados()));
			lblAceptados = (TextView)getView().findViewById(R.id.LblAceptados);
			lblAceptados.setText(String.valueOf(u.getAceptados()));
			lblPublicados = (TextView)getView().findViewById(R.id.LblPublicados);
			lblPublicados.setText(String.valueOf(u.getPublicados()));
			//Puntaje
			imgText = (TextView)getView().findViewById(R.id.LblImgText);
			imgText.setText("Tu promedio es "+u.getPuntaje()+"! Pts.");
			imgPts = (ImageView)getView().findViewById(R.id.imagePuntaje);
			
			switch(Math.round(u.getPuntaje())){
				case 0:
					imgPts.setImageResource(R.drawable.ic_pts0);
					break;
				case 1:
					imgPts.setImageResource(R.drawable.ic_pts1);
					break;
				case 2:
					imgPts.setImageResource(R.drawable.ic_pts2);
					break;
				case 3:
					imgPts.setImageResource(R.drawable.ic_pts3);
					break;
				case 4:
					imgPts.setImageResource(R.drawable.ic_pts4);
					break;
				case 5:
					imgPts.setImageResource(R.drawable.ic_pts5);
					break;
			}
			
			if(u.isBloqueado()){
				lblBloq = (TextView)getView().findViewById(R.id.LblBloqueado);
				lblBloq.setVisibility(View.VISIBLE);;
			}
		} else {
			//finish();
			Toast.makeText(Perfil.this.getActivity().getBaseContext(), "U = NULL", Toast.LENGTH_SHORT).show();
			Log.i("[Perfil]:", "no se pudo cargar el usuario");
		}
		
//		Usuario u = Factory.getUsuarioCtrl().getUsuario(mail);
//		if(u==null){
//			Toast.makeText(this.getActivity().getBaseContext(), "U = NULL", Toast.LENGTH_SHORT).show();
//			//finish();
//		}
//		
//		//Seteo los datos del usuario
//		lblNombre = (TextView)getView().findViewById(R.id.LblNombre);
//		lblNombre.setText(u.getNombre());
//		lblEmail = (TextView)getView().findViewById(R.id.LblEmail);
//		lblEmail.setText(u.getMail());
//		
//		lblRealizados = (TextView)getView().findViewById(R.id.LblRealizados);
//		lblRealizados.setText(String.valueOf(u.getRealizados()));
//		lblAceptados = (TextView)getView().findViewById(R.id.LblAceptados);
//		lblAceptados.setText(String.valueOf(u.getAceptados()));
//		lblPublicados = (TextView)getView().findViewById(R.id.LblPublicados);
//		lblPublicados.setText(String.valueOf(u.getPublicados()));
//		//Puntaje
//		imgText = (TextView)getView().findViewById(R.id.LblImgText);
//		imgText.setText("Tu promedio es "+u.getPuntaje()+"! Pts.");
//		imgPts = (ImageView)getView().findViewById(R.id.imagePuntaje);
//		
//		switch(Math.round(u.getPuntaje())){
//			case 0:
//				imgPts.setImageResource(R.drawable.ic_pts0);
//				break;
//			case 1:
//				imgPts.setImageResource(R.drawable.ic_pts1);
//				break;
//			case 2:
//				imgPts.setImageResource(R.drawable.ic_pts2);
//				break;
//			case 3:
//				imgPts.setImageResource(R.drawable.ic_pts3);
//				break;
//			case 4:
//				imgPts.setImageResource(R.drawable.ic_pts4);
//				break;
//			case 5:
//				imgPts.setImageResource(R.drawable.ic_pts5);
//				break;
//		}
//		
//		if(u.isBloqueado()){
//			lblBloq = (TextView)getView().findViewById(R.id.LblBloqueado);
//			lblBloq.setVisibility(View.VISIBLE);;
//		}
	}
	
	public interface PerfilListener {
        void onPerfilSeleccionado(String accion);
    }
 
    public void setPerfilListener(PerfilListener listener) {
        this.listener=listener;
    }
    
    
    //############# AsyncTask para traer datos del usuario
    public class PerfilTask extends AsyncTask<Void, Void, Boolean> {
		//private boolean isChk = false;
		private Usuario u;
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
		
		@Override
		protected Boolean doInBackground(Void... params) {
			u = Factory.getUsuarioCtrl().getUsuario(Perfil.this.getActivity(), mail);
			return u!=null;
		}

		@Override
		protected void onPostExecute(final Boolean success) {
			mAuthTask = null;
			//showProgress(false);

			if (success) {
				
				//Seteo los datos del usuario
				lblNombre = (TextView)getView().findViewById(R.id.LblNombre);
				lblNombre.setText(u.getNombre());
				lblEmail = (TextView)getView().findViewById(R.id.LblEmail);
				lblEmail.setText(u.getMail());
				
				lblRealizados = (TextView)getView().findViewById(R.id.LblRealizados);
				lblRealizados.setText(String.valueOf(u.getRealizados()));
				lblAceptados = (TextView)getView().findViewById(R.id.LblAceptados);
				lblAceptados.setText(String.valueOf(u.getAceptados()));
				lblPublicados = (TextView)getView().findViewById(R.id.LblPublicados);
				lblPublicados.setText(String.valueOf(u.getPublicados()));
				//Puntaje
				imgText = (TextView)getView().findViewById(R.id.LblImgText);
				imgText.setText("Tu promedio es "+u.getPuntaje()+"! Pts.");
				imgPts = (ImageView)getView().findViewById(R.id.imagePuntaje);
				
				switch(Math.round(u.getPuntaje())){
					case 0:
						imgPts.setImageResource(R.drawable.ic_pts0);
						break;
					case 1:
						imgPts.setImageResource(R.drawable.ic_pts1);
						break;
					case 2:
						imgPts.setImageResource(R.drawable.ic_pts2);
						break;
					case 3:
						imgPts.setImageResource(R.drawable.ic_pts3);
						break;
					case 4:
						imgPts.setImageResource(R.drawable.ic_pts4);
						break;
					case 5:
						imgPts.setImageResource(R.drawable.ic_pts5);
						break;
				}
				
				if(u.isBloqueado()){
					lblBloq = (TextView)getView().findViewById(R.id.LblBloqueado);
					lblBloq.setVisibility(View.VISIBLE);;
				}
			} else {
				//finish();
				Toast.makeText(Perfil.this.getActivity().getBaseContext(), "U = NULL", Toast.LENGTH_SHORT).show();
				Log.i("[Perfil]:", "no se pudo cargar el usuario");
			}
		}

		@Override
		protected void onCancelled() {
			mAuthTask = null;
			//showProgress(false);
		}
	}

}
