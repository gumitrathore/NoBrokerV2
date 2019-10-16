package c.s.no_broker

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONObject



class Register : AppCompatActivity() {

    var rq: RequestQueue? = null
    var stringreq: StringRequest? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        rq = Volley.newRequestQueue(this)
        registerbutton.setOnClickListener(){
            registerpost()
        }

    }
    private fun registerpost(){

            val user_type: String
            val name = rname.text.toString()
            val mobile_no = rphone.text.toString()
            val email = remail.text.toString()
            val password = rpassword.text.toString()
            if (rbld.isChecked)
                user_type = "L"
            else
                user_type = "T"
            val url = "nobroker-web.herokuapp.com/api/register"
            stringreq = object : StringRequest(Request.Method.POST, url, Response.Listener<String> { response ->
                val parser = JSONObject(response)
                val status = parser.getString("status")
                val msg = parser.getString("message")
                if (status == "1") {
                    val preferences = PreferenceManager.getDefaultSharedPreferences(this)
                    val editor = preferences.edit()
                    editor.putString("mobile_no", mobile_no.toString())
                    editor.putString("name", name.toString())
                    editor.putString("email", email.toString())
                    editor.putBoolean("status", true)
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)


                } else {
                    rname.text = Editable.Factory.getInstance().newEditable("")
                    rphone.text = Editable.Factory.getInstance().newEditable("")
                    remail.text = Editable.Factory.getInstance().newEditable("")
                    rpassword.text = Editable.Factory.getInstance().newEditable("")

                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

                }

            }, Response.ErrorListener { error ->
                Log.e("Volley Error: ", error.toString())
            }) {
                override fun getParams(): Map<String, String> {
                    //Creating HashMap
                    val params = HashMap<String, String>()

                    Log.e("first", "point4")
                    params["name"] = name.toString()
                    params["mobile_no"] = mobile_no.toString()
                    params["email"] = email.toString()
                    params["user_type"] = user_type.toString()
                    params["password"] = password.toString()



                    return params

                }


            }



    }
}

