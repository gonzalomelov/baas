package uy.trueques_beta.negocio;

import java.util.ArrayList;
import java.util.List;

import uy.trueques_beta.entities.RSS;
import android.content.Context;

public class RSSCtrl {

	public List<RSS> getListaRSS(Context context){
		List<RSS> list = new ArrayList<RSS>();
		
		for(int i=0; i<20; i++){
			list.add(new RSS("RSS ["+i+"]","Hola soy el RSS nº "+i+""));
		}
		return list;
	}
}
