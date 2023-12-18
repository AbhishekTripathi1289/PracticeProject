package com.example.myapplication.ui.theme.androidwidgets.activity

import android.os.Bundle
import android.os.CountDownTimer
import android.util.TimeUtils
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityLayoutProgrameticallyBinding
import com.example.myapplication.ui.theme.androidwidgets.activity.mix.MyDialogFragment
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Timer
import java.util.TimerTask
import java.util.concurrent.TimeUnit




/*Mix Activity */
class LayoutProgrameticallyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLayoutProgrameticallyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLayoutProgrameticallyBinding.inflate(layoutInflater)
        setContentView(binding.root)





        binding.button2.setOnClickListener{

        var dialgo = MyDialogFragment()
            dialgo.show(supportFragmentManager, "MyDialog")
        }


       /* var date = Date(Calendar.getInstance().timeInMillis)

        var ab = TimeUnit.MILLISECONDS.toDays(date.time)
        var formate = SimpleDateFormat("dd-mm-yyyy hh:mm:ss")*/
         //var datestr =  formate.format(date)
       /* val time = formate.parse(datestr)
        var formateSecond = SimpleDateFormat("dd-mm-yyyy hh:mm:ss")
        var dateSecond = Date(time.time)
        var datestrSecond = formateSecond.format(dateSecond)
*/
      /*  var formate = SimpleDateFormat("dd-mm-yyyy hh:mm:ss")
        var date = Calendar.getInstance()
        date.add(Calendar.YEAR , 2)





        object : CountDownTimer(50000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
           *//*     binding.textView.setText(
                    "seconds remaining: " + SimpleDateFormat("mm:ss:SS").format(
                        Date(
                            millisUntilFinished
                        )
                    )
                )*//*

                binding.textView.setText(
                 millisUntilFinished.toString()
                )
            }

            override fun onFinish() {
                binding.textView.setText("done!")
            }
        }.start()*/

      /*  var counter = 0
        var timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                runOnUiThread{
                    binding.textView.text = "$counter"
                }
                counter++

            }
        }, 3000, 1000)
*/

     /*   var cal = Calendar.getInstance()
        cal.time = Date(System.currentTimeMillis())

        binding.textViewSecond.text =  cal.get(Calendar.DAY_OF_MONTH).toString()*/

    }

    private fun inflateLayout() {


        var selectedPos = 0

        listOf<String>("item 1", "item 2" , "item 3" ,"item 4" ,"item 5" ).forEachIndexed { index, item ->

           /* var layout = CustomeLayoutBinding.inflate(layoutInflater)
            val finalPos = index
            layout.textView.text = item
            layout.textView.setOnClickListener{

                handleSelectedBackground(binding.linearLayout, selectedPos, false)
                selectedPos = finalPos
                handleSelectedBackground(binding.linearLayout, selectedPos, true)
            }*/

         /*   var layoutParms = ConstraintLayout.LayoutParams(300, 300)
            layoutParms.marginStart = 20
            layoutParms.marginEnd = 20

            layout.root.layoutParams = layoutParms*/
            //layout.constraintLayout.layoutParams = layoutParms


           // layout.textView.layoutParams = layoutParms

            //layoutParms.setMargins(20, 200, 50, 60)

            //layout.textView.setPadding(20, 40, 50, 60)



            var textView = TextView(this)

            var layoutParms : ViewGroup.LayoutParams= ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT   )
            textView.setBackgroundColor(ContextCompat.getColor(this, R.color.purple_500))
            textView.setTextColor(ContextCompat.getColor(this, R.color.white))

            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_delete, 0)

            textView.text = item

            textView.layoutParams = layoutParms

           // binding.linearLayout.addView(textView)

        }

    }

    private fun handleSelectedBackground(linearLayout: LinearLayout, index: Int, b: Boolean) {

        var itemView = linearLayout.getChildAt(index)

        if(b)
        {
            itemView.setBackgroundColor(ContextCompat.getColor(this, R.color.black))
        }
        else{
            itemView.setBackgroundColor(ContextCompat.getColor(this, R.color.white))

        }

    }
}