package com.example.money.debtor


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
import com.example.money.databinding.FragmentDebtorBinding

/**
 * A simple [Fragment] subclass.
 */
class DebtorFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<
                FragmentDebtorBinding>(inflater,
                R.layout.fragment_debtor,
                container,
                false)

        val application = requireNotNull(this.activity).application
        val dataSource = PersonDatabase.getInstance(application).personDatabaseDao
        val viewModelFactory = DebtorViewModelFactory(dataSource,application)

        val adapter = DebtorPersonAdapter()

        binding.viewShow.adapter = adapter

        val debtorViewModel = ViewModelProviders.of(this,viewModelFactory)
            .get(DebtorViewModel::class.java)

        debtorViewModel.personAll.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })

        setHasOptionsMenu(true)


        binding.debtorViewModel = debtorViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.drop_menu, menu)

        if (null == getShareIntent().resolveActivity(activity!!.packageManager)) {
            menu?.findItem(R.id.share)?.isVisible = false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item!!.itemId) {
            R.id.share -> shareSuccess()
        }
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
