package com.seniorpro.thailang

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.Button

class prac_group : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prac_group)

        val Word1 = findViewById<Button>(R.id.word1)
        Word1.setOnClickListener{
            intent = Intent(this,prac_group_word::class.java)
            startActivity(intent)
        }

        val Word2 = findViewById<Button>(R.id.word2)
        Word2.setOnClickListener{
            intent = Intent(this,prac_group_word::class.java)
            startActivity(intent)
        }

        val Word3 = findViewById<Button>(R.id.word3)
        Word3.setOnClickListener{
            intent = Intent(this,prac_group_word::class.java)
            startActivity(intent)
        }

        val Word4 = findViewById<Button>(R.id.word4)
        Word4.setOnClickListener{
            intent = Intent(this,prac_group_word::class.java)
            startActivity(intent)
        }

        val Word5 = findViewById<Button>(R.id.word5)
        Word5.setOnClickListener{
            intent = Intent(this,prac_group_word::class.java)
            startActivity(intent)
        }

        val Word6 = findViewById<Button>(R.id.word6)
        Word6.setOnClickListener{
            intent = Intent(this,prac_group_word::class.java)
            startActivity(intent)
        }

        val Word7 = findViewById<Button>(R.id.word7)
        Word7.setOnClickListener{
            intent = Intent(this,prac_group_word::class.java)
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
}
