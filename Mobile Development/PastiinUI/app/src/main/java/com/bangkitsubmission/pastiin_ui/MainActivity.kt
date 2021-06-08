package com.bangkitsubmission.pastiin_ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import com.bangkitsubmission.pastiin_ui.Fragment.CompositionFragment
import com.bangkitsubmission.pastiin_ui.Fragment.HistoryFragment
import com.bangkitsubmission.pastiin_ui.Fragment.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener   {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()
        var btnnav: BottomNavigationView = findViewById(R.id.btnnavview)
        btnnav.setSelectedItemId(R.id.home_menu);
        loadFragment(HomeFragment())
        btnnav.setOnNavigationItemSelectedListener(this)



    }

    private fun loadFragment(localfragment: Fragment?): Boolean {
        if (localfragment !=null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fl_container, localfragment)
                .commit()
            return true
        }
        return false
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var localfragment: Fragment? = null
        when(item.itemId){
            R.id.composition_menu-> localfragment = CompositionFragment()
            R.id.home_menu -> localfragment = HomeFragment()
            R.id.history_menu-> localfragment = HistoryFragment()
        }

       return loadFragment(localfragment)
    }


}


