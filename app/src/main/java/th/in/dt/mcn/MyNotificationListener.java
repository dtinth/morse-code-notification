package th.in.dt.mcn;

import android.app.Notification;
import android.os.Vibrator;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

/**
 * Created by dtinth on 2015-05-13.
 */
public class MyNotificationListener extends NotificationListenerService {
    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        Vibrator vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        if (vibrator != null) {
            MorseCodeBuilder builder = new MorseCodeBuilder();
            Notification notification = sbn.getNotification();
            String title = notification.extras.getString(Notification.EXTRA_TITLE);

            // blacklist
            if (title.equals("Cable charging")) return;
            if (title.startsWith("Searching using GPS")) return;
            if (title.equals("Chat heads active")) return;

            String text = getInitials(title);
            Log.d("transform", "[" + sbn.getPackageName() + "] " + title + " => " + text);
            builder.add(text);
            long[] result = builder.build();
            if (result.length > 1) {
                String d = ""; for (long c : result) d += " " + c;
                Log.d("meow", text + ": " + d);
                result[0] += 1000;
                vibrator.vibrate(result, -1);
            } else {
                Log.d("meow", text + ": empty result");
            }
        }
    }

    private String getInitials(String text) {
        boolean state = false;
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < text.length(); i ++) {
            boolean status = Character.isAlphabetic(text.charAt(i));
            if (state != status) {
                if (status) out.append(text.charAt(i));
                state = status;
            }
        }
        return out.toString();
    }

}
