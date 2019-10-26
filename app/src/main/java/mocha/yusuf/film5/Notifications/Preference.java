package mocha.yusuf.film5.Notifications;

import android.content.Context;
import android.content.SharedPreferences;

public class Preference {
    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public final static String PREF_NAME = "my_reminderPreferences";
    public final static String KEY_REMINDER_DAILY = "my_dailyReminder";
    public final static String KEY_REMINDER_MESSAGE_Release = "my_reminderMessageRelease";
    public final static String KEY_REMINDER_MESSAGE_Daily = "my_reminderMessageDaily";
    public Preference(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    public void setReminderReleaseTime(String time){
        editor.putString(KEY_REMINDER_DAILY,time);
        editor.commit();
    }
    public void setReminderReleaseMessage (String message){
        editor.putString(KEY_REMINDER_MESSAGE_Release,message);
    }
    public void setReminderDailyTime(String time){
        editor.putString(KEY_REMINDER_DAILY,time);
        editor.commit();
    }
    public void setReminderDailyMessage(String message){
        editor.putString(KEY_REMINDER_MESSAGE_Daily,message);
    }
}
