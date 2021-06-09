package com.bangkitsubmission.pastiin_ui.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bangkitsubmission.pastiin_ui.R

import com.bangkitsubmission.pastiin_ui.SetImageActivity
import com.bangkitsubmission.pastiin_ui.kuis.SelectQuiz



class HomeFragment : Fragment() {
    private var someStateValue: String? = null
    lateinit var rootview : View
    private val NAME_HERE_KEY = "someValueToSave"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        someStateValue = savedInstanceState?.getString(NAME_HERE_KEY)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_home, container, false)
        var btnmodel:Button = rootview.findViewById(R.id.button1)
        btnmodel.setOnClickListener {
            val intent = Intent(activity, SetImageActivity::class.java)
            startActivity(intent)
        }
        var name:TextView = rootview.findViewById(R.id.nameUser)
        name.text = arguments?.getString(HomeFragment.ARG_NAME)
        if (savedInstanceState != null) {
            name.text = someStateValue
        }

        var btnmode12: Button = rootview.findViewById(R.id.button2)
        btnmode12.setOnClickListener {
            val intent = Intent(activity, SelectQuiz::class.java)
            startActivity(intent)
        }

        return rootview
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var name:TextView = rootview.findViewById(R.id.nameUser)
        name.text = arguments?.getString(HomeFragment.ARG_NAME)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(NAME_HERE_KEY, ARG_NAME)
    }

    companion object {
        const val ARG_NAME: String = "NAME HERE"

        fun newInstance(uname: String): Any {
            val fragment = HomeFragment()

            val bundle = Bundle().apply {

                putString(HomeFragment.ARG_NAME, uname)
            }
            fragment.arguments = bundle

            return fragment
        }

    }
}