package demodatabase.android.myapplicationdev.com.p05_ndpsongs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ModifyActivity extends AppCompatActivity {

    EditText etEditSongTitle, etEditYear, etEditSingers;
    TextView tvShowEditID;
    RadioGroup rg;
    Button btnUpdate, btnDelete, btnCancel;
    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        tvShowEditID = findViewById(R.id.tvShowEditID);
        etEditSongTitle = findViewById(R.id.etEditSongTitle);
        etEditSingers = findViewById(R.id.etEditSingers);
        etEditYear = findViewById(R.id.etEditYear);
        rg = findViewById(R.id.radioGroupStars);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        Intent i = getIntent();
        data = (Song) i .getSerializableExtra("data");
        tvShowEditID.setText(data.getId());
        etEditSongTitle.setText(data.getTitle());
        etEditSingers.setText(data.getSingers());
        etEditYear.setText(data.getYear());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ModifyActivity.this);
                data.setTitle(etEditSongTitle.getText().toString());
                data.setSingers(etEditSingers.getText().toString());
                data.setYear(Integer.parseInt(etEditYear.getText().toString()));
                dbh.updateSong(data);
                dbh.close();
                setResult(RESULT_OK);
                finish();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ModifyActivity.this);
                dbh.deleteSong(data.getId());
                dbh.close();
                setResult(RESULT_OK);
                finish();
            }
        });
    }

}
