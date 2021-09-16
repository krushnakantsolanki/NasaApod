package com.nasa.nasaapod

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nasa.nasaapod.databinding.ActivityMainBinding
import com.nasa.nasaapod.ui.NasaApodFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).root)
        if (savedInstanceState == null) {
            val fm = supportFragmentManager
            val ft = fm.beginTransaction()
            ft.add(R.id.container, NasaApodFragment.newInstance())
            ft.commit()
        }
    }

}