package windydang.com.demo.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import windydang.com.demo.Model.Message;
import windydang.com.demo.R;

/**
 * Created by windydang on 12/21/17.
 */

public class CustomAdapter extends ArrayAdapter<Message> {
    private Context context;
    private int resource;
    private ArrayList<Message> arrMessage;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Message> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.arrMessage = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_item_message, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.tv_sender = convertView.findViewById(R.id.tvSender);
            viewHolder.tv_content = convertView.findViewById(R.id.tvContent);
            viewHolder.tv_time = convertView.findViewById(R.id.tvTime);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Message message = arrMessage.get(position);

        viewHolder.tv_sender.setText(message.getName());
        viewHolder.tv_content.setText(message.getContent());
        viewHolder.tv_time.setText(message.getTime());

        return convertView;
    }

    public class ViewHolder {
        TextView tv_sender;
        TextView tv_content;
        TextView tv_time;
    }
}
