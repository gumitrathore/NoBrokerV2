package c.s.no_broker

/**
 * Created by Abhimanyu on 10/12/2018.
 */
class interactModel{
    var nm:String?=null
    var job:String?=null
    var date:String?=null
    var id:String?=null
    fun getid():String{
        return id!!
    }
    fun getname():String{
        return nm!!
    }
    fun getjob():String{
        return job!!
    }
    fun getdate():String{
        return date!!
    }
    fun setname(nm:String){
        this.nm=nm
    }
    fun setjob(job:String){
        this.job=job
    }
    fun setdate(date:String){
        this.date=date
    }
    fun setid(id:String){
        this.id=id
    }

}