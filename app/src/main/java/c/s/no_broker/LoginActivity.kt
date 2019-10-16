package c.s.no_broker

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.content.pm.PackageManager
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.app.LoaderManager.LoaderCallbacks
import android.content.CursorLoader
import android.content.Loader
import android.database.Cursor
import android.net.Uri
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.TextView

import java.util.ArrayList
import android.Manifest.permission.READ_CONTACTS
import android.content.Intent
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity() {
    var rq: RequestQueue? = null
    var stringreq: StringRequest? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        rq = Volley.newRequestQueue(this)
        loginbutton.setOnClickListener(){
            post_data()
        }



    }
    fun post_data(){

        try{
        val mobile_no:String= lphone.text.toString()
        val password:String=lpassword.text.toString()
        Log.e("cred1",mobile_no)
        Log.e("cred2",password)
        val url = "https://nobroker-web.herokuapp.com/api/login"
        stringreq=object:StringRequest(Request.Method.POST, url, Response.Listener<String> { response ->

            val parser=JSONObject(response)
            Log.e("first", parser.toString())
            Log.e("response", response)

            val user_id = parser.getString("user_id")
            val email = parser.getString("email")
            val mobile_no = parser.getString("mobile_no")
            val user_type  = parser.getString("user_type")
            val name  = parser.getString("name")
            val preferences = PreferenceManager.getDefaultSharedPreferences(this)
            val editor = preferences.edit()
            editor.putString("mobile_no", mobile_no.toString())
            editor.putString("name", name.toString())
            editor.putString("email", email.toString())
                editor.putString("user_type", user_id.toString())
                editor.putString("user_type", user_type.toString())
            editor.putBoolean("status", true)
            editor.commit()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)



        }, Response.ErrorListener { error ->
            Log.e("Volley Error: ", error.toString())
        }) {
            override fun getParams(): Map<String, String> {
                //Creating HashMap
                val params = HashMap<String, String>()

                Log.e("first", "point4")
                params["mobile_no"] = mobile_no.toString()
                params["password"] = password.toString()


                return params
            }

        }}
        catch (e:Throwable){
            Toast.makeText(this,"Phone number or password is wrong ", Toast.LENGTH_SHORT).show()
        }

        Log.e("first", "point5")




        rq!!.add(stringreq)
        Log.e("first","point7")

    }


}
