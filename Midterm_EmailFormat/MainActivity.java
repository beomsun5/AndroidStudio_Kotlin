package kr.ac.dankook.mobile.bspark.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText text_to = (EditText)findViewById(R.id.text_to);
        EditText text_subject = (EditText)findViewById(R.id.Subject);
        EditText text_msg = (EditText)findViewById(R.id.msgText);
        Button send_btn = (Button)findViewById(R.id.send_btn);

        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1 = text_to.getText().toString();
                String str2 = text_subject.getText().toString();
                String str3 = text_msg.getText().toString();
                text_msg.setText("");
                text_msg.setText("TO : " + str1 + " Subject : " + str2 + " Message : " + str3);
            }
        });
    }
}