package th.in.dt.mcn;
import android.app.NotificationManager;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.support.v4.app.NotificationCompat;

import th.in.dt.mcn.R;

public class SettingsActivity extends PreferenceActivity {

    public static class SettingsFragment extends PreferenceFragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.prefs);
            findPreference("test").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    sendNotification();
                    return true;
                }
            });
        }
        private void sendNotification() {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity())
                    .setSmallIcon(R.drawable.common_signin_btn_icon_focus_light)
                    .setContentTitle("My notification")
                    .setContentText("Hello World!");
            NotificationManager manager = (NotificationManager)getActivity().getSystemService(NOTIFICATION_SERVICE);
            manager.notify(1, builder.build());
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction()
            .replace(android.R.id.content, new SettingsFragment())
            .commit();
    }
}
