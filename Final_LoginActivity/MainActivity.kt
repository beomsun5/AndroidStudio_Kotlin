package kr.ac.dankook.mobile.bspark.finalexam_submit

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intentMain = Intent(this, SubActivity::class.java)

        val sharedPref = getSharedPreferences("kr.ac.dankook.mobile.SHARED_PREF", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        val usernameTxt = findViewById<EditText>(R.id.usernameText)
        val passwordTxt = findViewById<EditText>(R.id.passwordText)
        val loginBtn = findViewById<Button>(R.id.loginBtn)

        loginBtn.setOnClickListener {
            val rusername:String? = sharedPref.getString("username", "unknown")
            val rpassword:String? = sharedPref.getString("password", "****")
            val rid = sharedPref.getInt("id", -1)
            if (rid == -1){ // No saved information
                editor.putString("username", usernameTxt.getText().toString())
                editor.putString("password", passwordTxt.getText().toString())
                editor.putInt("id", 1)
                editor.apply()

                intentMain.putExtra("username", usernameTxt.getText().toString())
                intentMain.putExtra("password", passwordTxt.getText().toString())
                intentMain.putExtra("result", "New user logged in")
                startActivity(intentMain)
            }
            else{
                // Invalid User
                if (rusername != usernameTxt.getText().toString()){
                    intentMain.putExtra("username", usernameTxt.getText().toString())
                    intentMain.putExtra("password", passwordTxt.getText().toString())
                    intentMain.putExtra("result", "Invalid username")
                    startActivity(intentMain)
                }
                // Wrong password
                else if (rpassword != passwordTxt.getText().toString()){
                    intentMain.putExtra("username", usernameTxt.getText().toString())
                    intentMain.putExtra("password", passwordTxt.getText().toString())
                    intentMain.putExtra("result", "Invalid password")
                    startActivity(intentMain)
                }
                // Login Successful
                else{
                    intentMain.putExtra("username", usernameTxt.getText().toString())
                    intentMain.putExtra("password", passwordTxt.getText().toString())
                    intentMain.putExtra("result", "User successfully logged in")
                    startActivity(intentMain)
                }
            }

        }
    }

    override fun onResume() {
        super.onResume()
        val usernameText = findViewById<EditText>(R.id.usernameText)
        val passwordText = findViewById<EditText>(R.id.passwordText)
        usernameText.setText("")
        passwordText.setText("")
    }
}