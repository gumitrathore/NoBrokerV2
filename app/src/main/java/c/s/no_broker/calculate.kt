package c.s.no_broker

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.android.volley.*
import com.android.volley.Request.Method.POST
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_calculate.*
import org.json.JSONObject


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [calculate.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [calculate.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class calculate : Fragment() {
    var mView:View?=null
    var rq: RequestQueue? = null
    var stringreq: StringRequest? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mView =inflater.inflate(R.layout.fragment_calculate, container, false)
        attachadapter()
        rq = Volley.newRequestQueue(context)
        val button=mView!!.findViewById<Button>(R.id.cal_button)
        button.setOnClickListener(){
            senddata()
        }

        return mView
    }


    fun attachadapter(){
        var leasetype=arrayOf("family","anyone","company")
        val leaseadapter = ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, leasetype)
        leaseadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val leasespinner=mView!!.findViewById<Spinner>(R.id.lease_type)
        leasespinner.adapter=leaseadapter
        var bhktype=arrayOf("1","2","3")
        val bhkadapter = ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, bhktype)
        bhkadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val bhkspinner=mView!!.findViewById<Spinner>(R.id.bhk_spinner)
        bhkspinner.adapter=bhkadapter
        var directiontype=arrayOf("N","S","W","E","NE","SE","SW","NW")
        val directionadapter = ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, directiontype)
        directionadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val directionspinner=mView!!.findViewById<Spinner>(R.id.direction_face)
        directionspinner.adapter=directionadapter
        var watertype=arrayOf("Corporation","Corporation Bore Well","Bore well")
        val wateradapter = ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, watertype)
        wateradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val waterspinner=mView!!.findViewById<Spinner>(R.id.water)
        waterspinner.adapter=wateradapter
        var buildingtype=arrayOf("Apartment","Flat","House")
        val buildadapter = ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, buildingtype)
        buildadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val buildspinner=mView!!.findViewById<Spinner>(R.id.buildingtype)
        buildspinner.adapter=buildadapter


    }
    fun senddata(){
        val url=""
       // var jsonBody = JSONObject()
        stringreq= object: StringRequest (Request.Method.POST,url,Response.Listener<String>{response ->
            val obj= JSONObject(response)
            val parentview=mView!!.findViewById<View>(R.id.parentCalc)
            initiatePopoupWindow(parentview,obj.getString("name"),obj.getString("price"))

        },Response.ErrorListener { error ->
            Log.e("Volley error", error.message)

        }){
            override fun getParams(): Map<String, String> {
                //Creating HashMap
                val params = HashMap<String, String>()

                Log.e("first", "point4")
                params["name"] = pxname.text.toString()
                params["lease_type"] = lease_type.selectedItem.toString()
                params["size"]= sizeprop.text.toString()

                params["gym"]= (if(gym.isChecked) 1 else 0).toString()
                params["lift"]= (if(lift.isChecked) 1 else 0).toString()
                params["internet"]= (if(internet.isChecked) 1 else 0).toString()
                params["ac"]= (if(ac.isChecked) 1 else 0).toString()
                params["club"]= (if(club.isChecked) 1 else 0).toString()
                params["security"]= (if(security.isChecked) 1 else 0).toString()
                params["intercom"]= (if(incom.isChecked) 1 else 0).toString()
                params["park"]= (if(park.isChecked) 1 else 0).toString()
                params["gp"]= (if(gp.isChecked) 1 else 0).toString()
                params["cpa"]= (if(cpa.isChecked) 1 else 0).toString()
                params["rwh"]= (if(rwh.isChecked) 1 else 0).toString()
                params["fs"]= (if(fs.isChecked) 1 else 0).toString()
                params["sc"]= (if(sc.isChecked) 1 else 0).toString()
                params["pool"]= (if(pool.isChecked) 1 else 0).toString()
                params["pool"]= (if(pool.isChecked) 1 else 0).toString()
                params["age"]= age.text.toString()
                params["bath"]= bath.text.toString()
                params["cupboard"]= cupboard.text.toString()
                params["direction"]=direction_face.selectedItem.toString()
                params["floor"]= floor.text.toString()
                params["hk"]= (if(hk.isChecked) 1 else 0).toString()
                params["ag"]= (if(ag.isChecked) 1 else 0).toString()
                params["ah"]= (if(ah.isChecked) 1 else 0).toString()
                params["servant"]= (if(servant.isChecked) 1 else 0).toString()
                params["fs"]= (if(fs.isChecked) 1 else 0).toString()
                params["balcony"]= balconies.text.toString()
                params["deposits"]= deposit.text.toString()
                if(buildingtype.selectedItem.equals("Apartment"))
                    params["building_type"]= "ap"
                if(buildingtype.selectedItem.equals("Flat"))
                    params["building_type"]= "if"
                if(buildingtype.selectedItem.equals("House"))
                    params["building_type"]= "ih"
                if(furn1.isChecked)
                    params["furnished"]= "1"
                else{
                    params["furnished"]= "0"
                }
                if(vehicle2.isChecked)
                    params["vehicle"] ="t"
                else if(vehicle4.isChecked)
                    params["vehicle"] ="f"
                else
                    params["vehicle"] ="b"
                params["floorstay"]= floorst.text.toString()
                params["face_direction"] =direction_face.selectedItem.toString()



                return params
            }

        }
        rq!!.add(stringreq)

    }
    private fun initiatePopoupWindow(v:View,name:String,price:String){
        try{

            val inflater=context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val customView=inflater.inflate(R.layout.calculated_price,null)
            var tname=customView.findViewById<TextView>(R.id.cname)
            var tprice=customView.findViewById<TextView>(R.id.price)
            var tbutton=customView.findViewById<Button>(R.id.calbutton)

            var mPopupWindow = PopupWindow(customView,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            )
            tname.text = name
            tprice.text = price
            tbutton.setOnClickListener{
                mPopupWindow.dismiss()
            }


            mPopupWindow.isFocusable = true
            mPopupWindow.isTouchable = true
            mPopupWindow.showAtLocation(v, Gravity.CENTER,0,0)
            mPopupWindow.update()


        }
        catch(e: Exception){
            Log.e("blehpop2",e.message)
        }
    }


}
