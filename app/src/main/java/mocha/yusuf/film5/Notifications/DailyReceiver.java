package mocha.yusuf.film5.Notifications;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

import mocha.yusuf.film5.Activity.MainActivity;
import mocha.yusuf.film5.R;

public class DailyReceiver extends BroadcastReceiver {
    public final static int NOTIFICATION_ID = 501;
    public static final String EXTRA_MESSAGE_PREF = "my_message";
    public static final String EXTRA_TYPE_PREF = "my_type";
    public static final String TAG = "DailyReceiver_TAG";
    public DailyReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        sendNotification(context, context.getResources().getString(R.string.message_daily), intent.getStringExtra(EXTRA_MESSAGE_PREF),NOTIFICATION_ID);
    }

    private void sendNotification(Context context, String title, String desc, int id) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(
                Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = TaskStackBuilder.create(context)
                .addNextIntent(intent)
                .getPendingIntent(NOTIFICATION_ID,PendingIntent.FLAG_UPDATE_CURRENT);
        Uri uriTone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,desc)
                .setSmallIcon(R.drawable.ic_movie_black_24dp)
                .setContentTitle(title)
                .setContentText(desc)
                .setContentIntent(pendingIntent)
                .setColor(ContextCompat.getColor(context, android.R.color.transparent))
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setSound(uriTone);
        if (notificationManager != null) {
            notificationManager.notify(id, builder.build());
        }
        Log.i(TAG, "sendNotification: ");
    }

    public void setAlarm(Context context, String type, String time, String message) {
        cancelAlarm(context);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context,DailyReceiver.class);
        intent.putExtra(EXTRA_MESSAGE_PREF,message);
        intent.putExtra(EXTRA_TYPE_PREF,type);
        String[] timeArray = time.split(":");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]));
        calendar.set(Calendar.SECOND,0);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,NOTIFICATION_ID,intent,0);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

//        Toast.makeText(context, R.string.on_movie_release_reminder, Toast.LENGTH_SHORT).show();
    }

    public void cancelAlarm(Context context){
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context,DailyReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,NOTIFICATION_ID,intent,0);
        alarmManager.cancel(pendingIntent);

//        Toast.makeText(context, R.string.off_movie_release_reminder, Toast.LENGTH_SHORT).show();
    }
}
