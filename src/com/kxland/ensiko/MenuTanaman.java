package com.kxland.ensiko;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MenuTanaman extends Activity{
	private ListView listView1;
    int item_pilihan;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);

        //initialisasi paket Adapter
        //TODO: menu icon sayur
        pilihan pilihan_data[] = new pilihan[]
                {
                        new pilihan(R.drawable.ensiko, "Buah"),                        
                        new pilihan(R.drawable.ensiko_sayur_36, "Sayur")
                };

        pilihanAdapter adapter = new pilihanAdapter(this,
                R.layout.listview_item_row, pilihan_data);

        listView1 = (ListView)findViewById(R.id.listView1);

        
        View header = getLayoutInflater().inflate(R.layout.list_view_header_row_ensikomenu, null);
        listView1.addHeaderView(header);

        listView1.setAdapter(adapter);

        //listen to listview events
        listView1.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                item_pilihan = position;
                //item_pilihan= Integer.toString(position);
                //Toast.makeText(getBaseContext(),  "<>" +item_pilihan, Toast.LENGTH_LONG).show();
                
                Intent intent;
                
                switch (position) {

                case 1:
                	//define a new Intent for the second Activity, ensiko buah
                	intent = new Intent(getApplicationContext(), Ensiko.class);
                	intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                	getApplicationContext().startActivity(intent);
                	break;
                	//ensiko sayur
                case 2:
                	intent= new Intent(getApplicationContext(), EnsikoSayur.class);
                	intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                	getApplicationContext().startActivity(intent);
                	break;               

                }
            }
        });

    }
   
}
