package windydang.com.demo.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by windydang on 12/21/17.
 */

public class OnMessageReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "message sent", Toast.LENGTH_SHORT).show();
    }
}
