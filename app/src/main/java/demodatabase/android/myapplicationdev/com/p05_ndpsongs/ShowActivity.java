package demodatabase.android.myapplicationdev.com.p05_ndpsongs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {

    Button btnFilter;
    ListView lv;
    ArrayList<Song> song;
    ArrayAdapter aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        song = new ArrayList<Song>();

        song.add(new Song("Home", "Kit Chan", 1998, 5));
        song.add(new Song("Our Singapore", "JJ Lin/Dick Lee", 2015, 5));
        song.add(new Song("Future in My Dreams", "SAF Music and Drama Company", 1997, 4));

        lv = (ListView)findViewById(R.id.lv);
        aa = new SongAdapter(this, R.layout.row, song);
        lv.setAdapter(aa);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Intent i = new Intent(ShowActivity.this,
                        ModifyActivity.class);
                Song data = song.get(position);
                i.putExtra("data", data);
                startActivityForResult(i, 9);
            }
        });
    }

}
