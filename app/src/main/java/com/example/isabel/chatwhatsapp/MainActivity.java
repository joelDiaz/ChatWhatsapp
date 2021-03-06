package com.example.isabel.chatwhatsapp;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ChatArrayAdapter chatArrayAdapter;
    private ListView listView;
    private EditText chatText;
    private Button buttonSend;
    private boolean side = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        buttonSend = (Button) findViewById(R.id.send);
        listView = (ListView) findViewById(R.id.msgview);


        chatArrayAdapter = new ChatArrayAdapter(getApplicationContext(), R.layout.left);
        listView.setAdapter(chatArrayAdapter);


        chatText = (EditText) findViewById(R.id.msg);
        chatText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    return sendChatMessage();
                }
                return false;
            }
        });

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                sendChatMessage();
            }
        });


        /** Para que la listviw pueda ser scrolleable */
        listView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        listView.setAdapter(chatArrayAdapter);


        chatArrayAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                listView.setSelection(chatArrayAdapter.getCount() - 1);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private boolean sendChatMessage() {
        chatArrayAdapter.add(new ChatMessage(side, chatText.getText().toString()));
        chatText.setText("");
        side = !side;
        return true;
    }


    public class ChatMessage {
        public boolean left;
        public String message;

        public ChatMessage(boolean left, String message) {
            super();
            this.left = left;
            this.message = message;

        }


    }
/**
    public void mensajero() {
        new CountDownTimer(3000, 1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                chatArrayAdapter.add(new ChatMessage(side,"hola tio"));

            }

        }.start();


    }
*/
}


/**
 * //        com1 = (ListView)findViewById(R.id.commentario1);
 * //        com2 = (ListView) findViewById(R.id.commentario2);
 * //        send = (ImageButton) findViewById(R.id.Enviar);
 * //        mensaje = (EditText) findViewById(R.id.mensaje);
 * <p/>
 * //        final ArrayList<String> lista = new ArrayList<>();
 * //        final ArrayAdapter<String> adaptador;
 * //        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);
 * //        final ListView selecte = (ListView) findViewById(R.id.commentario1);
 * //        adaptador.setDropDownViewResource(android.R.layout.simple_list_item_1);
 * //        selecte.setAdapter(adaptador);
 * //        Integer numero = 4;
 * <p/>
 * <p/>
 * //        send.setOnClickListener(new View.OnClickListener() {
 * //                                    @Override
 * //                                    public void onClick(View v) {
 * //                                        if ((mensaje.getText().toString().length() > 0)) {
 * //
 * //                                            lista.add(mensaje.getText().toString());
 * //                                            adaptador.notifyDataSetChanged();
 * //                                            mensaje.setText("");
 * //                                        } else if ((mensaje.getText().toString().equals(""))) {
 * //
 * //                                            Toast.makeText(MainActivity.this, "Texto vacio", Toast.LENGTH_SHORT).show();
 * //
 * //                                        }
 * //
 * //                                    }
 * //                                }
 * //
 * //        );
 * <p/>
 * //
 * //        for (int i = 0; i <= numero; i++) {
 * //
 * //            numero = numero - 1;
 * //            System.out.println("numero" + numero);
 * //            /**Segunda lista de comentarios del chat
 * //            final ArrayList<String> lista2 = new ArrayList<>();
 * //            final ArrayAdapter<String> adaptador2;
 * //            adaptador2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista2);
 * //            final ListView selecte2 = (ListView) findViewById(R.id.commentario2);
 * //            adaptador2.setDropDownViewResource(android.R.layout.simple_list_item_1);
 * //            selecte2.setAdapter(adaptador2);
 * //
 * //
 * //            new CountDownTimer(3000, 1000) {
 * //
 * //                public void onTick(long millisUntilFinished) {
 * //
 * //                }
 * //
 * //                public void onFinish() {
 * //                    lista2.add("hello world");
 * //                    adaptador2.notifyDataSetChanged();
 * //
 * //                }
 * //
 * //            }.start();
 * //
 * //
 * //        }
 * //
 */
