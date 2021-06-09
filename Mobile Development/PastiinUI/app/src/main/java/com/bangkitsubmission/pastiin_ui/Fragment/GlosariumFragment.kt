package com.bangkitsubmission.pastiin_ui.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.bind
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkitsubmission.pastiin_ui.Adapter.AdapterGlorasium
import com.bangkitsubmission.pastiin_ui.R
import com.bangkitsubmission.pastiin_ui.databinding.FragmentGlosariumBinding
import com.bangkitsubmission.pastiin_ui.repository.Repository
import com.bangkitsubmission.pastiin_ui.viewmodel.MainViewModel
import com.bangkitsubmission.pastiin_ui.viewmodel.MainViewModelFactory


class GlosariumFragment : Fragment() {

    private lateinit var binding: FragmentGlosariumBinding

    // private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val Myadapter by lazy { AdapterGlorasium() }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGlosariumBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)



        viewModel.getListFruit()
        viewModel.myListFruitGlorasium.observe(viewLifecycleOwner, { response ->
            Myadapter.setFruit(response.body())

            Log.d("List Fruit", response.body().toString())
            Myadapter.notifyDataSetChanged()
        })

        with(binding.rvGlorasium) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = Myadapter
        }
    }
}
