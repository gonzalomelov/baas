package uy.trueques_beta.negocio;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.*;//JsonParseException;


public class Factory {
		private static UsuarioCtrl usuarioCtrl;
		private static ObjetoCtrl objetoCtrl;
		private static OfertaCtrl ofertaCtrl;
		private static TruequeCtrl truequeCtrl;
		private static RSSCtrl rssCtrl;

		private Factory() {
		}

		public static UsuarioCtrl getUsuarioCtrl() {
			if (usuarioCtrl == null) {
				
				usuarioCtrl = new UsuarioCtrl();
			}
			return usuarioCtrl;
		}
		
		public static RSSCtrl getRSSCtrl() {
			if (rssCtrl == null) {
				
				rssCtrl = new RSSCtrl();
			}
			return rssCtrl;
		}
		
		public static ObjetoCtrl getObjetoCtrl() {
			if (objetoCtrl == null) {
				
				objetoCtrl = new ObjetoCtrl();
			}
			return objetoCtrl;
		}
		
		public static OfertaCtrl getOfertaCtrl() {
			if (ofertaCtrl == null) {
				
				ofertaCtrl = new OfertaCtrl();
			}
			return ofertaCtrl;
		}
		
		public static TruequeCtrl getTruequeCtrl() {
			if (truequeCtrl == null) {
				
				truequeCtrl = new TruequeCtrl();
			}
			return truequeCtrl;
		}
		
		
		//+++++
		public static Gson getGson(){
			GsonBuilder builder = new GsonBuilder();  
			builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {  
			    // Year in 4, month in 2, day in 2, hour in 24, minutes in hour, seconds in minute, timezone in 4  
			    final DateFormat df = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss Z");  
			    @Override  
			    public Date deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException {
			    	return new Date(json.getAsJsonPrimitive().getAsLong()); 
//			        try {  
//			            return df.parse(json.getAsString());  
//			        } catch (final java.text.ParseException e) {  
//			            e.printStackTrace();  
//			            return null;  
//			        }  
			    }  
			});  
			return builder.create();
		}

}
