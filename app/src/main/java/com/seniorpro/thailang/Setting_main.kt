package com.seniorpro.thailang

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RadioButton


class Setting_main : AppCompatActivity() {
    private var gender = "_male"

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


    fun onRadioButtonClicked(view: View) {
        // Is the button now checked?
        val checked = (view as RadioButton).isChecked()

        // Check which radio button was clicked
        when (view.getId()) {
            R.id.rdobtMale -> {
                if (checked)
                // Pirates are the best
                    gender = "_male"
                var prac : prac_group_word = prac_group_word()
                prac.setterGender(gender)
                Log.d("gengenderrrrrr",gender)
            }
            R.id.rdobtFemale -> {
                if (checked)
                    gender = "_female"
                var prac : prac_group_word = prac_group_word()
                prac.setterGender(gender)
                Log.d("gengenderrrrrr",gender)
            }
        }
    }



}
