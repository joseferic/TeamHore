package com.bangkitsubmission.pastiin_ui.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.bangkitsubmission.pastiin_ui.MainActivity
import com.bangkitsubmission.pastiin_ui.R
import com.bangkitsubmission.pastiin_ui.SetImageActivity


class ResultFragment_Failed : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var v = inflater.inflate(R.layout.fragment_result__failed, container, false)

        var btnback = v.findViewById<Button>(R.id.btnback)
        var btnhome = v.findViewById<Button>(R.id.btnhome)
        btnback.setOnClickListener {
            val backIntent = Intent(activity, SetImageActivity::class.java)
            backIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(backIntent)
            activity?.finish()
        }
        btnhome.setOnClickListener {
            val backIntent = Intent(activity, MainActivity::class.java)
            backIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(backIntent)
            activity?.finish()
        }
        return v
    }

    companion object {

    }
}