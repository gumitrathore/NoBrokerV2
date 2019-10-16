package c.s.no_broker

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var id:String?=null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                var newFrag: Fragment?=null
                val fm=supportFragmentManager
                val ft=fm.beginTransaction()
                val oldfragment=fm.findFragmentByTag(""+id)
                if(oldfragment!=null)
                    ft.hide(oldfragment)
                newFrag=fm.findFragmentByTag("1")
                if(newFrag==null){
                    newFrag=calculate()
                    ft.add(R.id.container,newFrag,"1")
                }
                ft.show( newFrag );
                ft.commit();
                id= 1.toString()

                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_dashboard -> {
                var newFrag: Fragment?=null
                val fm=supportFragmentManager
                val ft=fm.beginTransaction()
                val oldfragment=fm.findFragmentByTag(""+id)
                if(oldfragment!=null)
                    ft.hide(oldfragment)
                newFrag=fm.findFragmentByTag("2")
                if(newFrag==null){
                    newFrag=interact()
                    ft.add(R.id.container,newFrag,"2")
                }
                ft.show( newFrag );
                ft.commit();
                id= 2.toString()

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                var newFrag: Fragment?=null
                val fm=supportFragmentManager
                val ft=fm.beginTransaction()
                val oldfragment=fm.findFragmentByTag(""+id)
                if(oldfragment!=null)
                    ft.hide(oldfragment)
                newFrag=fm.findFragmentByTag("3")
                if(newFrag==null){
                    newFrag=list()
                    ft.add(R.id.container,newFrag,"3")
                }
                ft.show( newFrag );
                ft.commit();
                id= 3.toString()

                return@OnNavigationItemSelectedListener true
            }
            R.id.analysis->{
                var newFrag: Fragment?=null
                val fm=supportFragmentManager
                val ft=fm.beginTransaction()
                val oldfragment=fm.findFragmentByTag(""+id)
                if(oldfragment!=null)
                    ft.hide(oldfragment)
                newFrag=fm.findFragmentByTag("4")
                if(newFrag==null){
                    newFrag=Analysis()
                    ft.add(R.id.container,newFrag,"4")
                }
                ft.show( newFrag );
                ft.commit();
                id= 4.toString()

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fm=supportFragmentManager
        val ft=fm.beginTransaction()
        val fragment=calculate()
        id= 1.toString()
        ft.replace(R.id.container,fragment,id).addToBackStack(null).commit();
        id= 1.toString()
        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val status = preferences.getBoolean("status", false)
        if (status==false){
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
