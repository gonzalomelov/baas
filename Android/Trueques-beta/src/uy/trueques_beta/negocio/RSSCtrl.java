package uy.trueques_beta.negocio;

import java.util.ArrayList;
import java.util.List;

import sdk.servicios.RSSConsumer;
import uy.trueques_beta.entities.RSS;
import android.content.Context;

public class RSSCtrl {

	public List<RSS> getListaRSS(Context context){
		List<RSS> list = new ArrayList<RSS>();

		try {
			String[][] rss = RSSConsumer.getRSSFeed();
			
			for(int i=0; i<20; i++){
				list.add(new RSS(rss[i][0],rss[i][1]));
			}
			
			int a = rss.length;
		}
		catch (Exception e) {
			
		}
		
		return list;
		
	}
}
