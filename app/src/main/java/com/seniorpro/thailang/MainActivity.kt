package com.seniorpro.thailang


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.app.Activity;
import android.content.Intent
import android.support.v7.widget.Toolbar
import android.view.Menu;
import android.view.View.OnClickListener;
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         val learnbutton = findViewById<Button>(R.id.learnbt)
        learnbutton.setOnClickListener{
            intent = Intent(this,Learn_main::class.java)
            startActivity(intent)
        }

        val pracbutton = findViewById<Button>(R.id.practbt)
        pracbutton.setOnClickListener{
            intent = Intent(this,Prac_main::class.java)
            startActivity(intent)
        }

        val testbutton = findViewById<Button>(R.id.testbt)
        testbutton.setOnClickListener{
            intent = Intent(this,Test_main::class.java)
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



    }
}
