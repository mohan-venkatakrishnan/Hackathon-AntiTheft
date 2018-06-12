package in.antitheft.hackathon_security;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RemoteViews;

import java.util.Random;

/**
 * Created by intel on 21-02-2015.
 */
public class lockscreenwidget extends AppWidgetProvider {
    public static String YOUR_AWESOME_ACTION = "YourAwesomeAction";
    @Override
    public void onUpdate(Context context, AppWidgetManager am, int[] appWidgetIds){

        final int N = appWidgetIds.length;

        // Perform this loop procedure for each App Widget that belongs to this provider
        for (int i=0; i<N; i++) {
            int appWidgetId = appWidgetIds[i];

            // Create an Intent to launch ExampleActivity
            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

            // Get the layout for the App Widget and attach an on-click listener
            // to the button
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            views.setOnClickPendingIntent(R.id.b, pendingIntent);

            // Tell the AppWidgetManager to perform an update on the current app widget
            am.updateAppWidget(appWidgetId, views);

        }
    }


}