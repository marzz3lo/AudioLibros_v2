package com.mostudios.audiolibros;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

/**
 * Created by marzzelo on 21/1/2017.
 */

public class MiAppWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int widgetId : appWidgetIds) {
            actualizaWidget(context, widgetId);
        }
    }

    public static void actualizaWidget(Context context, int widgetId) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
        remoteViews.setTextViewText(R.id.widget_autor, "Ultimo Autor");
        remoteViews.setTextViewText(R.id.widget_titulo, "Ultimo Titulo");

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                intent, 0);
        remoteViews.setOnClickPendingIntent(R.id.btnPlay, pendingIntent);

        AppWidgetManager.getInstance(context).updateAppWidget(widgetId, remoteViews);
    }
}
