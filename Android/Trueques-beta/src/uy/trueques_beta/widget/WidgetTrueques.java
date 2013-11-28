package uy.trueques_beta.widget;

import android.R;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;
 
public class WidgetTrueques extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context,
                 AppWidgetManager appWidgetManager,
                 int[] appWidgetIds) {
        //Actualizar el widget
        //...
    	//RemoteViews controles = new RemoteViews(context.getPackageName(), R.layout.widget_trueques);
    }
    
    public static void actualizarWidget(Context context,
            AppWidgetManager appWidgetManager, int widgetId)
    {
            //Recuperamos el mensaje personalizado para el widget actual
            SharedPreferences prefs = context.getSharedPreferences("TruequesData", Context.MODE_PRIVATE);
            String mail = prefs.getString("mail", "No logueado");
            
//            //Obtenemos la lista de controles del widget actual
//            RemoteViews controles = new RemoteViews(context.getPackageName(), R.layout.widget_trueques);
//            
//            controles.setTextViewText(R.id.txtMensaje, mail);
//            //Asociamos los 'eventos' al widget
//            Intent intent = new Intent("net.sgoliver.android.widgets.ACTUALIZAR_WIDGET");
//            intent.putExtra(
//                         AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
//            PendingIntent pendingIntent = 
//                    PendingIntent.getBroadcast(context, widgetId, 
//                                    intent, PendingIntent.FLAG_UPDATE_CURRENT);
//            
//            controles.setOnClickPendingIntent(R.id.BtnActualizar, pendingIntent);
//            
//            Intent intent2 = new Intent(context, MainActivity.class);
//            PendingIntent pendingIntent2 = 
//                            PendingIntent.getActivity(context, widgetId, 
//                                            intent2, PendingIntent.FLAG_UPDATE_CURRENT);
//            
//            controles.setOnClickPendingIntent(R.id.FrmWidget, pendingIntent2);
//            
//            //Actualizamos el mensaje en el control del widget
//            controles.setTextViewText(R.id.LblMensaje, mensaje);
//            
//            //Obtenemos la hora actual
//            Calendar calendario = new GregorianCalendar();
//            String hora = calendario.getTime().toLocaleString();
//            
//            //Actualizamos la hora en el control del widget
//            controles.setTextViewText(R.id.LblHora, hora);
//            
//            //Notificamos al manager de la actualización del widget actual
//            appWidgetManager.updateAppWidget(widgetId, controles);
    }
}