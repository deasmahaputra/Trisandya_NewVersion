package deasmp.com.trisandyatime

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.BottomNavigationView
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import java.util.*

/**
 * Created by DEAS on 07/04/2018.
 */
class HomeActivity : AppCompatActivity() {

    lateinit var toolbar : Toolbar
    //lateinit var textTest : TextView
    lateinit var bottomNav : BottomNavigationView
    lateinit var dotsLayout : LinearLayout
    lateinit var mPager : ViewPager
    lateinit var closeBotton : ImageView
    lateinit var adapter : PageView

    var currentPage : Int = 0
    lateinit var timer : Timer
    val DELAY_MS : Long = 1000
    val PERIOD_MS : Long = 1000


    var path : IntArray = intArrayOf(R.drawable.img_one, R.drawable.img_two, R.drawable.img_three, R.drawable.img_four)
    lateinit var dots : Array<ImageView>

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_activity)

        var builder : AlertDialog.Builder = AlertDialog.Builder(this)
        var inflater : LayoutInflater = layoutInflater
        var view : View = inflater.inflate(R.layout.material_popup, null)
        closeBotton = view.findViewById<ImageView>(R.id.closeBtn)
        builder.setView(view)

        var dialog : Dialog = builder.create()
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //dialog.window.attributes.windowAnimations = R.style.AnnimDialog
        dialog.show()
        closeBotton.setOnClickListener(View.OnClickListener {
            dialog!!.dismiss()
        })

        toolbar = findViewById<Toolbar>(R.id.toolbar)
        //textTest = findViewById<TextView>(R.id.texttest)
        bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        mPager = findViewById<ViewPager>(R.id.pager)
        dotsLayout = findViewById<LinearLayout>(R.id.dots)
        setSupportActionBar(toolbar)

        adapter = PageView(this, path)
        mPager.adapter = adapter
        creaateDots(0)
        updatePage()

        mPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                currentPage = position
                creaateDots(position)

            }

        })


//        val face = Typeface.createFromAsset(assets, "fonts/fa-light-300.ttf")
//        textTest.typeface = face
//        textTest.setText("\uf34e")

        bottomNav.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.home ->
                        Toast.makeText(application, "Home", Toast.LENGTH_SHORT).show()
            }
            true
        }


    }

    fun creaateDots(position: Int){
        if(dotsLayout != null){
            dotsLayout.removeAllViews()
        }
        dots = Array(path.size, {i -> ImageView(this) })
        for(i in 0..path.size - 1){
            dots[i] = ImageView(this)

            if(i == position){
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.active_dots))
            }else{
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.inactive_dots))
            }

            var params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)

            params.setMargins(4,0,4,0)
            dotsLayout.addView(dots[i], params)
        }
    }

    fun updatePage(){
        var handler = Handler()
        val Update : Runnable = Runnable {
            if(currentPage == path.size){
                currentPage = 0
            }
            mPager.setCurrentItem(currentPage++, true)
        }
        timer = Timer()
        timer.schedule(object : TimerTask(){
            override fun run() {
                handler.post(Update)
            }
        },DELAY_MS,PERIOD_MS)
    }
}