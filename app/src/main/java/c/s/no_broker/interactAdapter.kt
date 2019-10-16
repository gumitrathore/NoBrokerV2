package c.s.no_broker

import android.app.ProgressDialog
import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.PopupWindow
import android.widget.TextView

/**
 * Created by Abhimanyu on 10/12/2018.
 */
class interactAdapter(context: Context, list:MutableList<interactModel>): RecyclerView.Adapter<interactAdapter.ViewHolder>(){
    var context:Context?=null

    var list:MutableList<interactModel>?=null
    init{
        this.context=context
        this.list=list
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder!!.itemView.tag=list!!.get(position)
        val details=list!![position]
        holder.nm.text=details.getname()
        holder.id.text=details.getid()
        holder.job!!.text=details.getjob()
    }

    override fun getItemCount(): Int {
        return list!!.size
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): interactAdapter.ViewHolder{
        val v = LayoutInflater.from(parent!!.getContext()).inflate(R.layout.interact_item, parent, false)
        return ViewHolder(v)
    }
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var nm:TextView
        var job:TextView
        var id:TextView
        var cv:CardView
        init{
            nm=itemView.findViewById<View>(R.id.iname) as TextView
            job=itemView.findViewById<View>(R.id.ijob) as TextView
            id=itemView.findViewById<View>(R.id.iid) as TextView
            cv=itemView.findViewById<View>(R.id.interactcv) as CardView
            itemView.setOnClickListener{
                val data=itemView.getTag() as interactModel
                initiatePopoupWindow(itemView,data.getname(),data.getid(),data.getdate(),data.getjob())

            }

        }

    }
    private fun initiatePopoupWindow(v:View,name:String,id:String,create:String,job:String){
        try{

            val inflater=context!!.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val customView=inflater.inflate(R.layout.interact_details,null)
            var tname=customView.findViewById<TextView>(R.id.pname)
            var tjob=customView.findViewById<TextView>(R.id.pjob)
            var tbutton=customView.findViewById<Button>(R.id.popbutton)
            var tid=customView.findViewById<TextView>(R.id.pid)
            var tdate=customView.findViewById<TextView>(R.id.pcreate)
            var mPopupWindow = PopupWindow(customView,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            )
            tname.text = name
            tjob.text = job
            tid.text=id
            tdate.text=create
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