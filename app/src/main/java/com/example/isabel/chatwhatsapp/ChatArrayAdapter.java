package com.example.isabel.chatwhatsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by AndroidDeveloper01 on 28/3/16.
 */
public class ChatArrayAdapter extends ArrayAdapter<MainActivity.ChatMessage> {
    private TextView chatText;
    private List<MainActivity.ChatMessage>
            chatMessageList = new ArrayList<MainActivity.ChatMessage>();
    private Context context;

    @Override
    public void add(MainActivity.ChatMessage object) {
        chatMessageList.add(object);
        super.add(object);
    }

    public ChatArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        this.context = context;
    }

    public int getCount() {
        return this.chatMessageList.size();
    }

    public MainActivity.ChatMessage getItem(int index) {
        return this.chatMessageList.get(index);
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        MainActivity.ChatMessage chatMessageObj = getItem(position);
        View row = convertView;
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        row = inflater.inflate(R.layout.right, parent, false);

        String mensajito = chatMessageObj.message;


        Pattern p = Pattern.compile("joel");
        Matcher m = p.matcher(mensajito);

        if (m.find()) {
            row = inflater.inflate(R.layout.left, parent, false);

        }

/** otra manera de hacerlo dinamicamente sin interaccion del usuario
 *
        if (chatMessageObj.left) {

          row = inflater.inflate(R.layout.left, parent, false);



        }else{
            row = inflater.inflate(R.layout.left, parent, false);
        }
*/

        chatText = (TextView) row.findViewById(R.id.msgr);
        chatText.setText(chatMessageObj.message);
        return row;
    }


}
