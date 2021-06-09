package com.bangkitsubmission.pastiin_ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bangkitsubmission.pastiin_ui.Fragment.ResultFragment_Failed
import com.bangkitsubmission.pastiin_ui.Fragment.Result_Success
import com.bangkitsubmission.pastiin_ui.databinding.ActivityMainBinding
import com.bangkitsubmission.pastiin_ui.repository.Repository
import com.bangkitsubmission.pastiin_ui.viewmodel.MainViewModel
import com.bangkitsubmission.pastiin_ui.viewmodel.MainViewModelFactory


class ResultActivity : AppCompatActivity() {
    lateinit var message:String
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val bundle = intent.extras
        message = bundle!!.getString("id").toString()

//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getFruitData(message)
                viewModel.myGetFruitData.observe(this, Observer { responses ->
           if (responses.isSuccessful){

               var a = responses.body()?.name_Fruit.toString()

               var b = responses.body()?.fact_Fruit.toString()

               var c = responses.body()?.pict_Fruit.toString()

               val fragment = Result_Success.newInstance(a,b,c)

               //kalo ada buka fragment success

//               Log.d("Response",responses.body().toString())
//               Toast.makeText(this, responses.body().toString(), Toast.LENGTH_SHORT).show()
               loadFragment(fragment)
           }
            else{
               //kalo ga ada buka fragment fail
               loadFragment(ResultFragment_Failed())
                Log.d("Failed Response",responses.errorBody().toString())
            }
        })


    }
    private fun loadFragment(localfragment: Fragment?): Boolean {
        if (localfragment !=null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fl_container_result, localfragment)
                .commit()
            return true
        }
        return false
    }
}