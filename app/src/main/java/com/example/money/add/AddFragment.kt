package com.example.money.add


import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.money.R
import com.example.money.database.PersonDatabase
import com.example.money.databinding.FragmentAddBinding

/**
 * A simple [Fragment] subclass.
 */
class AddFragment : Fragment() {

    private lateinit var addViewModel: AddViewModel
    private lateinit var binding: FragmentAddBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        this.binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_add, container, false)

        val application = (this.activity)?.application
        val dataSource = application?.let { PersonDatabase.getInstance(it).personDatabaseDao }
        val viewModelFactory = dataSource?.let { AddViewModelFactory(it,application) }

        addViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(AddViewModel::class.java)

        addViewModel.addComplete.observe(this, Observer {
            if(it){
                Toast.makeText(activity,"Add Success", Toast.LENGTH_SHORT).show()
                findNavController().navigate(
                    AddFragmentDirections
                        .actionAddFragmentToMenuFragment("",""))
            }
        })

        addViewModel.goBackToMenu.observe(this, Observer {
            if (it){
                findNavController().navigate(
                    AddFragmentDirections
                    .actionAddFragmentToMenuFragment("",""))
            }
        })

        binding.addViewModel = addViewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}
