package com.todandlorna.dev.android;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.Random;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;
import android.media.MediaPlayer;

public class RtGWhatService extends Service {

	public RtGWhatService(){
		
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStart(intent, startId);
		Log.i(RtGWhatWidgetProvider.WIDGETTAG, "onStartCommand");
			playSound(intent);
		stopSelf(startId);
		
		return START_STICKY;
	}

	/*
	private String getRandomMood() {
		Random r = new Random(Calendar.getInstance().getTimeInMillis());
		int pos = r.nextInt(moods.size());
		return moods.get(pos);
	}
	*/

	private void playSound(Intent intent) {
		Log.i(RtGWhatWidgetProvider.WIDGETTAG, "This is the intent " + intent);
		if (intent != null){
			String requestedAction = intent.getAction();
			Log.i(RtGWhatWidgetProvider.WIDGETTAG, "This is the action " + requestedAction);
			if (requestedAction != null){
				int widgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, 0);

				Log.i(RtGWhatWidgetProvider.WIDGETTAG, "This is the playing sound to widget " + widgetId);

				AppWidgetManager appWidgetMan = AppWidgetManager.getInstance(this);
				RemoteViews views = new RemoteViews(this.getPackageName(),R.layout.widgetlayout);
				//views.setTextViewText(R.id.widgetMood, "yeah...");
				MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.catchphrase);
				mp.start();
				
				appWidgetMan.updateAppWidget(widgetId, views);
				
				Log.i(RtGWhatWidgetProvider.WIDGETTAG, " RtGWhat updated!");
			}
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
