package com.bangkitsubmission.pastiin_ui.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bangkitsubmission.pastiin_ui.R
import com.bumptech.glide.Glide


class Result_Success : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v = inflater.inflate(R.layout.fragment_result__success, container, false)
        // Inflate the layout for this fragment


        var img = v.findViewById<ImageView>(R.id.resultimageplaceholder)
        var name = v.findViewById<TextView>(R.id.SensitivityResult)
        var desc = v.findViewById<TextView>(R.id.DescriptionLabel)

        Glide.with(this)
            .load(arguments?.getString(ARG_PICTURE))
            .into(img)
        img.setClipToOutline(true)

        name.text = arguments?.getString(ARG_FRUITS)
        desc.text = arguments?.getString(ARG_DESC)
        return v
    }

    companion object {
       const val ARG_FRUITS = "THIS DATA"
        const val ARG_DESC = "THAT DATA"
        const val ARG_PICTURE = "THOSE DATA"
        fun newInstance(a: String, b: String, c: String): Result_Success {
            val fragment = Result_Success()

            val bundle = Bundle().apply {
                putString(ARG_FRUITS, a)
                putString(ARG_DESC, b)
                putString(ARG_PICTURE, c)
            }
            fragment.arguments = bundle

            return fragment
        }
    }
}