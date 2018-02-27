package com.seniorpro.thailang

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.Button
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.row_main.view.*
import android.content.pm.PackageManager
import android.Manifest.permission.RECORD_AUDIO
import android.support.v4.content.ContextCompat
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.widget.Toast
import android.support.v4.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_prac_group.*

private  var idWord = arrayListOf<String>()
private var thaiNames = arrayListOf<String>()
private var names = arrayListOf<String>()


private  val idWord_easy = arrayListOf<String>("eat", "speak", "want", "love", "beautiful")
private val thaiNames_easy = arrayListOf<String>("กิน", "พูด", "อยาก", "รัก", "สวย")
private val names_easy = arrayListOf<String>("eat", "speak", "want", "love", "beautiful")


private  val idWord_medium = arrayListOf<String>("thankyou", "sorry", "nevermind", "hello")
private val thaiNames_medium = arrayListOf<String>("ขอบคุณ", "ขอโทษ", "ไม่เป็นไร", "สวัสดี")
private val names_medium = arrayListOf<String>("Thank you", "Sorry", "Never mind", "Hello")


private  val idWord_hard = arrayListOf<String>("luggage","policestation")
private val thaiNames_hard = arrayListOf<String>("กระเป๋าเดินทาง", "สถานีตำรวจ")
private val names_hard = arrayListOf<String>("luggage","police station")


class prac_group : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prac_group)

        idWord = arrayListOf<String>()
        thaiNames = arrayListOf<String>()
        names = arrayListOf<String>()

        val extras = intent.extras
        val mode = extras.getString("mode")
        if(mode.equals("easy")){
            idWord = idWord_easy
            thaiNames = thaiNames_easy
            names = names_easy
        }else if(mode.equals("medium")){
            idWord = idWord_medium
            thaiNames = thaiNames_medium
            names = names_medium

        }else if(mode.equals("hard")){
            idWord = idWord_hard
            thaiNames = thaiNames_hard
            names = names_hard
        }

        main_listview.adapter = MyCustomAdapter(this)

        // show normal
        main_listview.setOnItemClickListener { adapterView, view, position, id ->
            val item = adapterView.getItemAtPosition(position) as String
            Log.d("Result","Item: $item at position $position")

            val intent = Intent(this, prac_group_word::class.java)
            intent.putExtra("word",item)
            intent.putExtra("thaiWord", thaiNames[position])
            intent.putExtra("idWord", idWord[position])



            startActivity(intent)
        }

        val HomeButton = findViewById<Button>(R.id.homeBt)
        HomeButton.setOnClickListener{
            intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        val SearchButton = findViewById<Button>(R.id.searchBt)
        SearchButton.setOnClickListener{
            intent = Intent(this,Search_main::class.java)
            startActivity(intent)
        }

        val FavButton = findViewById<Button>(R.id.favBt)
        FavButton.setOnClickListener{
            intent = Intent(this,Favor_main::class.java)
            startActivity(intent)
        }

        val HistoryButton = findViewById<Button>(R.id.histrBt)
        HistoryButton.setOnClickListener{
            intent = Intent(this,History_main::class.java)
            startActivity(intent)
        }

        val SettingButton = findViewById<Button>(R.id.settBt)
        SettingButton.setOnClickListener{
            intent = Intent(this,Setting_main::class.java)
            startActivity(intent)
        }

        val toolbar = findViewById<Toolbar>(R.id.toolBar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        finish()
        return true
    }

    private class MyCustomAdapter(context: Context): BaseAdapter(){
        private val mContext: Context


        init {
            //,าจาก this ด้านบน
            mContext = context
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getItem(position: Int): Any {
            return names[position]
        }

        override fun getCount(): Int {
            return names.size
        }

        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
            /*val textView = TextView(mContext)
            textView.text = "Show the message"
            return textView*/
            val greyColor = Color.parseColor("#D7D5D2")
            val rowMain: View
            if(convertView == null){
                val layoutInflator = LayoutInflater.from(viewGroup!!.context)
                rowMain = layoutInflator.inflate(R.layout.row_main,viewGroup,false) //input sub layout
                val viewHolder = ViewHolder(rowMain.name_textview,rowMain.position_textview,rowMain.imageView)
                rowMain.tag = viewHolder
            }else{
                rowMain = convertView
            }

            val viewHolder = rowMain.tag as ViewHolder
            if(position%2 == 0){
                rowMain.setBackgroundColor(greyColor)

                //viewHolder.rowImageView.setImageResource(R.drawable.woman)

            }

            Log.d("Result","Load name_textView")
            viewHolder.nameTextView.text = names.get(position)
            Log.d("Result","Load position_textView")
            viewHolder.positionTextView.text = thaiNames.get(position)

            //remove row
/*
            rowMain.setOnClickListener {
                rowMain.animate().setDuration(1500).alpha(0F).withEndAction({
                    names.removeAt(position)
                    notifyDataSetChanged()
                    rowMain.setAlpha(1.0F)
                })
            }
*/




            return rowMain

        }
        private class ViewHolder(val nameTextView: TextView, val positionTextView: TextView, val rowImageView: ImageView)
    }



}
