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

        val application = (this.activity)!!.application
        val dataSource = PersonDatabase.getInstance(application).personDatabaseDao
        val viewModelFactory = AddViewModelFactory(dataSource,application)

        addViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(AddViewModel::class.java)

        addViewModel.addComplete.observe(this, Observer {
            if(it){
                Toast.makeText(activity,"Add Success", Toast.LENGTH_SHORT).show()
                findNavController().navigate(
                    AddFragmentDirections
                        .actionAddFragmentToMenuFragment())
            }
        })

        setHasOptionsMenu(true)

        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.drop_menu, menu)

        if (null == getShareIntent().resolveActivity(activity!!.packageManager)) {
            menu?.findItem(R.id.share)?.setVisible(false)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item!!.itemId) {
            R.id.share -> shareSuccess()
        }

//        return super.onOptionsItemSelected(item)
        return NavigationUI.onNavDestinationSelected(item!!, view!!.findNavController()) || super.onOptionsItemSelected(item)
    }

    private fun getShareIntent() : Intent {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain")
            .putExtra(Intent.EXTRA_TEXT,"Shared")
        return shareIntent
    }

    private fun shareSuccess() {
        startActivity(getShareIntent())
    }
}
