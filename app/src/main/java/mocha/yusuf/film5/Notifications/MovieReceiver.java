package mocha.yusuf.film5.Notifications;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import mocha.yusuf.film5.API.APIService;
import mocha.yusuf.film5.API.APIUrl;
import mocha.yusuf.film5.Activity.MainActivity;
import mocha.yusuf.film5.BuildConfig;
import mocha.yusuf.film5.Model.ListMovieModel;
import mocha.yusuf.film5.Model.MovieModel;
import mocha.yusuf.film5.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieReceiver extends BroadcastReceiver {
    public static final String EXTRA_MESSAGE_RECIEVE = "my_messageRelease";
    public static final String EXTRA_TYPE_RECIEVE = "my_typeRelease";
    public final static int NOTIFICATION_ID_MOVIE = 602;
    public static final String TAG = "MovieReceiver_TAG";
    private static final String CHANNEL_ID = "Channel_Movie";
    private static final String CHANNEL_NAME = "Movie channel";

    public MovieReceiver() {

    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        APIService apiService = APIUrl.getClient().create(APIService.class);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date_lte = formatter.format(new Date());
        String date_gte = formatter.format(new Date());
        Call<ListMovieModel> call = apiService.upcoming_movie(BuildConfig.API_KEY, date_lte, date_gte);
        call.enqueue(new Callback<ListMovieModel>() {
            @Override
            public void onResponse(Call<ListMovieModel> call, Response<ListMovieModel> response) {
                List<MovieModel> items = response.body().getResults();
                int notifId = 603;
                for (int i = 0;i<items.size();i++){
                    String title = items.get(i).getTitle();
                    String message = items.get(i).getOverview();
                    sendNotification(context, title, message, notifId+i);
                }
            }

            @Override
            public void onFailure(Call<ListMovieModel> call, Throwable t) {
                Log.d("getUpCommingMovie", "onFailure: " + t.toString());
            }
        });
    }

    private void sendNotification(Context context, String title, String desc, int id) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(
                Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Uri uriTone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_movie_black_24dp)
                .setContentTitle(title)
                .setContentText(desc)
                .setContentIntent(pendingIntent)
                .setColor(ContextCompat.getColor(context, android.R.color.transparent))
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setAutoCancel(true)
                .setSound(uriTone);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT);

            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{1000, 1000, 1000, 1000, 1000});

            builder.setChannelId(CHANNEL_ID);

            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }

        if (notificationManager != null) {
            notificationManager.notify(id, builder.build());
        }
    }

    public void setAlarm(Context context, String type, String time, String message) {
        cancelAlarm(context);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, MovieReceiver.class);
        intent.putExtra(EXTRA_MESSAGE_RECIEVE, message);
        intent.putExtra(EXTRA_TYPE_RECIEVE, type);
        String[] timeArray = time.split(":");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]));
        calendar.set(Calendar.SECOND, 0);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, NOTIFICATION_ID_MOVIE, intent, 0);

        if (alarmManager != null) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }
    }

    public void cancelAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, MovieReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, NOTIFICATION_ID_MOVIE, intent, 0);

        if (alarmManager != null) {
            alarmManager.cancel(pendingIntent);
        }
    }
}
