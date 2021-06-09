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



class HomeFragment : Fragment() {
    lateinit var rootview : View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        return rootview
    }

    companion object {
        const val ARG_NAME: String = "NAME HERE"

        fun newInstance(message: String): Any {
            val fragment = Result_Success()

            val bundle = Bundle().apply {

                putString(HomeFragment.ARG_NAME, message)
            }
            fragment.arguments = bundle

            return fragment
        }

    }
}