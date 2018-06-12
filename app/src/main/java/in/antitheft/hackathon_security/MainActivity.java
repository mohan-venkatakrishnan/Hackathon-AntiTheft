package in.antitheft.hackathon_security;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pushbots.push.Pushbots;


public class MainActivity extends Activity {
    Button btnNum,btnText,btnem;
    EditText etNum,etText;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Text = "textKey";
    public static final String Num = "numKey";
    SharedPreferences sharedpreferences;
    // GPSTracker class
    GPSTracker gps;
    private Button startService;
    private Button stopService;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Pushbots.sharedInstance().init(this);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        etNum=(EditText)findViewById(R.id.edittext1);
        etText=(EditText)findViewById(R.id.edittext2);
        btnNum=(Button)findViewById(R.id.button1);
        btnText=(Button)findViewById(R.id.button2);
btnem=(Button)findViewById(R.id.button3);
        if (sharedpreferences.contains(Text))
        {
            String text = sharedpreferences.getString(Text, "");
            etText.setText("Change Saved Text");

        }
        if (sharedpreferences.contains(Num))
        {
            String num = sharedpreferences.getString(Num, "");
            etNum.setText("Change Saved Number");

        }
        btnNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String p  = etNum.getText().toString();
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(Num, p);
                editor.commit();
                Toast.makeText(getBaseContext(),"Number saved",Toast.LENGTH_SHORT).show();
            }
        });
        btnText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String p  = etText.getText().toString();
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(Text, p);
                editor.commit();
                Toast.makeText(getBaseContext(),"Text saved",Toast.LENGTH_SHORT).show();
            }
        });
        btnem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/html");
                intent.putExtra(Intent.EXTRA_EMAIL, "catchsabaribalan@gmail.com");

                startActivity(Intent.createChooser(intent, "Send Email"));}
        });

    }

}

