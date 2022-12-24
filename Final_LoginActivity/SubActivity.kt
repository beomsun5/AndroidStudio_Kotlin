package kr.ac.dankook.mobile.bspark.finalexam_submit

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        val usernameTxt = findViewById<TextView>(R.id.usernameTxt)
        val passwordTxt = findViewById<TextView>(R.id.passwordTxt)
        val loginResultTxt = findViewById<TextView>(R.id.loginResultTxt)
        val returnBtn = findViewById<Button>(R.id.returnBtn)
        val intentSub = intent

        val pusername = intentSub.getStringExtra("username")
        val ppassword = intentSub.getStringExtra("password")
        val loginResult = intentSub.getStringExtra("result")

        usernameTxt.setText(pusername)
        passwordTxt.setText(ppassword)
        loginResultTxt.setText(loginResult)


        returnBtn.setOnClickListener {
            //val outIntent = Intent(this, MainActivity::class.java)
            //outIntent.putExtra("initUsername", "")
            //outIntent.putExtra("initPassword", "")
            //setResult(Activity.RESULT_OK, outIntent)
            finish()
        }
    }
}