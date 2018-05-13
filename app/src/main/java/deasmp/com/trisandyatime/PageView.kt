package deasmp.com.trisandyatime

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout

/**
 * Created by DEAS on 09/04/2018.
 */
class PageView : PagerAdapter {

    lateinit var con : Context
    lateinit var path : IntArray
    lateinit var inflater : LayoutInflater

    constructor(con: Context, path: IntArray) : super() {
        this.con = con
        this.path = path
    }

    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
        return view == `object` as RelativeLayout
    }

    override fun getCount(): Int {
        return path.size
    }

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        var img : ImageView
        inflater = con.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var rv : View = inflater.inflate(R.layout.swipe_fragment,container,false)
        img = rv.findViewById<ImageView>(R.id.img)
        img.setImageResource(path[position])

        container!!.addView(rv)
        return rv
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        container!!.removeView(`object` as RelativeLayout)
    }
}