package windydang.com.demo.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import windydang.com.demo.Adapter.CustomAdapter;
import windydang.com.demo.Data.DBManager;
import windydang.com.demo.Model.Message;
import windydang.com.demo.R;
import windydang.com.demo.Receiver.NetworkChangeReceiver;
import windydang.com.demo.Receiver.OnMessageReceiver;

public class MainActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();
    NetworkChangeReceiver networkChangeReceiver;
    OnMessageReceiver onMessageReceiver;
    private final String SHARED_PREFERENCES_NAME = "sharedpreferences";
    private final String ACTION_MESSAGE = "sendmessage";
    private final String COUNT_MESSAGE = "count";
    private final String NAME = "NAME";
    private final String CONTENT = "CONTENT";
    private final String TIME = "TIME";
    private final int ID = 1;
    private ListView lvMessage;
    private Button btnSend;
    private EditText edtContent;
    private ArrayList<Message> arrayList = new ArrayList<>();
    private DBManager dbManager = new DBManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWidget();
        setEvent();

//        Message message = new Message("02:45", "Phong", "Hello");

//        arrayList.add(message);

        arrayList = (ArrayList<Message>) dbManager.getAllMessage();

        CustomAdapter customAdapter = new CustomAdapter(this, R.layout.row_item_message, arrayList);

        lvMessage.setAdapter(customAdapter);
    }

    private void initBroadcastReceiver() {
//        networkChangeReceiver = new NetworkChangeReceiver();
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
//        registerReceiver(networkChangeReceiver, intentFilter);
        onMessageReceiver = new OnMessageReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_MESSAGE);
        registerReceiver(onMessageReceiver, intentFilter);
    }

    private void getWidget() {
        lvMessage = (ListView) findViewById(R.id.lvMessage);
        btnSend = (Button) findViewById(R.id.btnSend);
        edtContent = (EditText) findViewById(R.id.edtContent);
    }

    public int getCurrentCount() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        int count = sharedPreferences.getInt(COUNT_MESSAGE, 0);
        return count;
    }

    public void setCount(int count) {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(COUNT_MESSAGE, count);
        editor.apply();
    }

    public void setEvent() {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(edtContent.getText().toString())) {
                    int count = getCurrentCount() + 1;
                    setCount(count);
                    String content = edtContent.getText().toString();
                    String time = new SimpleDateFormat("HH:mm").format(new Date());
                    Message message = new Message(time, TAG, content, count);
                    arrayList.add(message);
                    dbManager.addMessage(message);
                    sendMessage(TAG, content, time);
//                    byExtra(TAG, content, time);
                }
            }
        });
    }

//    public void byExtra(String name, String content, String time) {
//        Intent intent = new Intent(MainActivity.this, BotActivity.class);
//        intent.putExtra(NAME, TAG);
//        intent.putExtra(CONTENT, content);
//        intent.putExtra(TIME, time);
//        startActivity(intent);
//    }

    public void sendMessage(String name, String content, String time) {
        Intent intent = new Intent();
        intent.putExtra(NAME, TAG);
        intent.putExtra(CONTENT, content);
        intent.putExtra(TIME, time);
        intent.setAction(ACTION_MESSAGE);
        sendBroadcast(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initBroadcastReceiver();
    }

    @Override
    protected void onStop() {
        super.onStop();
//        unregisterReceiver(networkChangeReceiver);
        unregisterReceiver(onMessageReceiver);
    }
}
