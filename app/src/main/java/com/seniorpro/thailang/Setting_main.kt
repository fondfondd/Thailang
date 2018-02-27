package com.seniorpro.thailang

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Button
import android.widget.RadioButton



class Setting_main : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_main)

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

        val toolbar = findViewById<Toolbar>(R.id.toolBar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
    }

    val radio_male = findViewById<View>(R.id.radio_male) as RadioButton
    val radio_female = findViewById<View>(R.id.radio_famale) as RadioButton
    fun onRadioButtonClicked(view: View) {
        // Is the button now checked?
        val checked = (view as RadioButton).isChecked
//        // Check which radio button was clicked
//        when (view.getId()) {
//            R.id.radio_male -> {
//                if (checked)
//                // Pirates are the best
//                // break
//                    if (checked)
//                    // Ninjas rule
//                    //break
//            }
//            R.id.radio_famale -> if (checked)
//            //break
//        }
    }




}
