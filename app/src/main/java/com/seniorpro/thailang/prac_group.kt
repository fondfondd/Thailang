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

private  var idWord = arrayListOf<String>("ga1", "sorry", "maipenrai","goodbye","thankyou")
private var thaiNames = arrayListOf<String>("กา", "ขอโทษ", "ไม่เป็นไร","ลาก่อน","ขอบคุณ")

class prac_group : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prac_group)

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
        private val names = arrayListOf<String>("ga", "Sorry", "You're welcome","Goodbye","Thank you")


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
