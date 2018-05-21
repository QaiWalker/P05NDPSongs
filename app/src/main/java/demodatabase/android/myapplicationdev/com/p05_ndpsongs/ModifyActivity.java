package demodatabase.android.myapplicationdev.com.p05_ndpsongs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ModifyActivity extends AppCompatActivity {

    EditText etEditSongTitle, etEditYear, etEditSingers;
    TextView tvShowEditID;
    RadioGroup rg;
    Button btnUpdate, btnDelete, btnCancel;
    RadioButton radio1, radio2, radio3, radio4, radio5;
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
        tvShowEditID.setText(""+data.getId());
        etEditSongTitle.setText(data.getTitle());
        etEditSingers.setText(data.getSingers());
        etEditYear.setText(""+data.getYear());
        if (data.getStars() == 5){
            radio5 = findViewById(R.id.radio5);
            radio5.setChecked(true);
        }
        else if (data.getStars() == 4){
            radio4 = findViewById(R.id.radio4);
            radio4.setChecked(true);
        }
        else if (data.getStars() == 3){
            radio3 = findViewById(R.id.radio3);
            radio3.setChecked(true);
        }
        else if (data.getStars() == 2){
            radio2 = findViewById(R.id.radio2);
            radio2.setChecked(true);
        }
        else {
            radio1 = findViewById(R.id.radio1);
            radio1.setChecked(true);
        }


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
    private int getStars() {
        int stars = 1;
        switch (rg.getCheckedRadioButtonId()) {
            case R.id.radio1:
                stars = 1;
                break;
            case R.id.radio2:
                stars = 2;
                break;
            case R.id.radio3:
                stars = 3;
                break;
            case R.id.radio4:
                stars = 4;
                break;
            case R.id.radio5:
                stars = 5;
                break;
        }
        return stars;
    }

}
