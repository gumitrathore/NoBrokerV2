package c.s.no_broker

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_calculate.*
import kotlinx.android.synthetic.main.fragment_interact.*
import org.json.JSONArray
import org.json.JSONObject


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [interact.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [interact.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class interact : Fragment() {
    var mView:View?=null
    var rq: RequestQueue? = null
    var list:MutableList<interactModel>?=null
    var recyclerView: RecyclerView? = null
    var layoutManager: RecyclerView.LayoutManager? = null
    var mAdapter: RecyclerView.Adapter<*>? = null
    var progd: ProgressDialog?=null




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mView=inflater.inflate(R.layout.fragment_interact, container, false)
        val button=mView!!.findViewById<Button>(R.id.sort)
        rq = Volley.newRequestQueue(context)
        progd= ProgressDialog(context)
        recyclerView = mView!!.findViewById<RecyclerView>(R.id.irecycler)
        recyclerView!!.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(context)
        recyclerView!!.setLayoutManager(layoutManager)
        list= mutableListOf<interactModel>()
        button.setOnClickListener{
            progd!!.setMessage("Loading")
            progd!!.show()
            progd!!.setCanceledOnTouchOutside(false)
            enquiry()
        }
        // Inflate the layout for this fragment
        return mView
    }

fun enquiry(){
    val BHK=ixroom.text.toString()
    var furnishing:Boolean?=null
    if(fern1.isChecked)
        furnishing=true
    if(fern2.isChecked)
        furnishing=false
    var type:String?=null
    if(family.isChecked)
        type="family"
    if(company.isChecked)
        type="company"
    if(any.isChecked)
        type="anyone"
    var jsonarr= JSONObject()

    val url = "https://reqres.in/api/users"
    var jsonArrayReq=object: StringRequest(Request.Method.POST,url,Response.Listener<String> { response ->
      Log.e("ab",response.toString())
        val parser=JSONObject(response)
        val model=interactModel()
        model.setdate(parser.getString("createdAt"))
        model.setid(parser.getString("id"))
        model.setjob(parser.getString("position"))
        model.setname(parser.getString("name"))
        list!!.add(model)
        if(list!=null){

            mAdapter = interactAdapter(context!!, list!!)

            recyclerView!!.setAdapter(mAdapter)}
            progd!!.dismiss()

    },Response.ErrorListener { error ->
        Log.e("Volley error",error.toString())
        progd!!.dismiss()
    }){

        override fun getParams(): Map<String, String> {
            //Creating HashMap
            val params = HashMap<String, String>()

            Log.e("first", "point4")
            params["name"] = BHK.toString()
            params["position"] = type.toString()


            return params
    }



}
    rq!!.add(jsonArrayReq)

}
}

