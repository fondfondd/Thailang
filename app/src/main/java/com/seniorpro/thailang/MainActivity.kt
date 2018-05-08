package com.seniorpro.thailang


import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.app.Activity;
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.view.Menu;
import android.view.View.OnClickListener;
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val RequestPermissionCode = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (checkPermission()) {
            //
        } else {

            requestPermission()

        }

         val easybutton = findViewById<Button>(R.id.easychap)
        easybutton.setOnClickListener{
            intent = Intent(this,prac_group::class.java)
            intent.putExtra("mode","easy")
            startActivity(intent)
        }

        val medbutton = findViewById<Button>(R.id.mediumchap)
        medbutton.setOnClickListener{
            intent = Intent(this,prac_group::class.java)
            intent.putExtra("mode","medium")
            startActivity(intent)
        }

        val hardbutton = findViewById<Button>(R.id.hardchap)
        hardbutton.setOnClickListener{
            intent = Intent(this,prac_group::class.java)
            intent.putExtra("mode","hard")
            startActivity(intent)
        }

//        supportActionBar!!.setDisplayShowTitleEnabled(false)



    }
    private fun requestPermission() {

        ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO), RequestPermissionCode)
        ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO), RequestPermissionCode)

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            RequestPermissionCode -> if (grantResults.size > 0) {

                val StoragePermission = grantResults[0] == PackageManager.PERMISSION_GRANTED
                val RecordPermission = grantResults[1] == PackageManager.PERMISSION_GRANTED

                if (StoragePermission && RecordPermission) {

                    Toast.makeText(this@MainActivity, "Permission Granted", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this@MainActivity, "Permission Denied", Toast.LENGTH_LONG).show()

                }
            }
        }
    }

    fun checkPermission(): Boolean {

        val result = ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val result1 = ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.RECORD_AUDIO)

        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED
    }
}
