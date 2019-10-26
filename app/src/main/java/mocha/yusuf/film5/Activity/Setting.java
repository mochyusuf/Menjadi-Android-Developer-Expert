package mocha.yusuf.film5.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import mocha.yusuf.film5.Notifications.DailyReceiver;
import mocha.yusuf.film5.Notifications.MovieReceiver;
import mocha.yusuf.film5.Notifications.Preference;
import mocha.yusuf.film5.R;

public class Setting extends AppCompatActivity {

    private Switch dailyReminder;
    private Switch releaseReminder;
    public static final String KEY_HEADER_UPCOMING_REMINDER = "upcomingReminder";
    public static final String KEY_HEADER_DAILY_REMINDER = "dailyReminder";
    public static final String KEY_FIELD_UPCOMING_REMINDER = "checkedUpcoming";
    public static final String KEY_FIELD_DAILY_REMINDER = "checkedDaily";
    public static final String TYPE_REMINDER_PREF = "reminderAlarm";
    public static final String TYPE_REMINDER_RECIEVE = "reminderAlarmRelease";

    public DailyReceiver dailyReceiver;
    public MovieReceiver movieReceiver;
    public Preference preference;
    public SharedPreferences ReleaseReminder, DailyReminder;
    public SharedPreferences.Editor editorReleaseReminder, editorDailyReminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dailyReminder = findViewById(R.id.daily_reminder);
        releaseReminder = findViewById(R.id.release_Reminder);

        movieReceiver = new MovieReceiver();
        dailyReceiver = new DailyReceiver();
        preference = new Preference(this);
        setPreference();

        TextView local_setting = findViewById(R.id.local_setting);
        local_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(mIntent);
            }
        });

        dailyReminder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editorDailyReminder = DailyReminder.edit();
                if (isChecked) {
                    editorDailyReminder.putBoolean(KEY_FIELD_DAILY_REMINDER, true);
                    editorDailyReminder.commit();
                    dailyReminderOn();
                } else {
                    editorDailyReminder.putBoolean(KEY_FIELD_DAILY_REMINDER, false);
                    editorDailyReminder.commit();
                    dailyReminderOff();
                }
            }
        });

        releaseReminder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editorReleaseReminder = ReleaseReminder.edit();
                if (isChecked) {
                    editorReleaseReminder.putBoolean(KEY_FIELD_UPCOMING_REMINDER, true);
                    editorReleaseReminder.commit();
                    releaseReminderOn();
                } else {
                    editorReleaseReminder.putBoolean(KEY_FIELD_UPCOMING_REMINDER, false);
                    editorReleaseReminder.commit();
                    releaseReminderOff();
                }
            }
        });
    }

    private void releaseReminderOn() {
        String time = "08:00";
        String message = getResources().getString(R.string.release_movie_message);
        preference.setReminderReleaseTime(time);
        preference.setReminderReleaseMessage(message);
        movieReceiver.setAlarm(Setting.this, TYPE_REMINDER_PREF, time, message);
    }

    private void releaseReminderOff() {
        movieReceiver.cancelAlarm(Setting.this);
    }

    private void dailyReminderOn() {
        String time = "07:00";
        String message = getResources().getString(R.string.daily_reminder);
        preference.setReminderDailyTime(time);
        preference.setReminderDailyMessage(message);
        dailyReceiver.setAlarm(Setting.this, TYPE_REMINDER_RECIEVE, time, message);
    }

    private void dailyReminderOff() {
        dailyReceiver.cancelAlarm(Setting.this);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private void setPreference() {
        ReleaseReminder = getSharedPreferences(KEY_HEADER_UPCOMING_REMINDER, MODE_PRIVATE);
        DailyReminder = getSharedPreferences(KEY_HEADER_DAILY_REMINDER, MODE_PRIVATE);
        boolean checkUpcomingReminder = ReleaseReminder.getBoolean(KEY_FIELD_UPCOMING_REMINDER, false);
        releaseReminder.setChecked(checkUpcomingReminder);
        boolean checkDailyReminder = DailyReminder.getBoolean(KEY_FIELD_DAILY_REMINDER, false);
        dailyReminder.setChecked(checkDailyReminder);
    }
}
