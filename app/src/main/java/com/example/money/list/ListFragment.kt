package com.example.money.list


import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.money.R
import com.example.money.database.PersonDatabase
import com.example.money.databinding.FragmentListBinding
import com.example.money.debtor.DebtorViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentListBinding>(inflater, R.layout.fragment_list, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = PersonDatabase.getInstance(application).personDatabaseDao
        val viewModelFactory = ListViewModelFactory(dataSource,application)

        val adapter = ListPersonAdapter()

        binding.viewShow.adapter = adapter

        val listViewModel = ViewModelProviders.of(this,
            viewModelFactory)
            .get(ListViewModel::class.java)

        listViewModel.personAll.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })


        binding.listViewModel = listViewModel
        binding.lifecycleOwner = this

        return binding.root
    }

}
