package kr.ac.dankook.mobile.bspark.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnSay = findViewById<Button>(R.id.btnSay)
        btnSay.setOnClickListener{
            var TextSay = findViewById<TextView>(R.id.TextSay)
            TextSay.setText("Hello, BeomSoon Park")
        }
    }
}