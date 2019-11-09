package com.example.money.add


import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.money.R
import com.example.money.databinding.FragmentAddBinding

/**
 * A simple [Fragment] subclass.
 */
class AddFragment : Fragment() {

    private lateinit var viewModel: AddViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentAddBinding>(inflater, R.layout.fragment_add, container, false)

        viewModel = ViewModelProviders.of(this).get(AddViewModel::class.java)

        binding.apply {
            saveBtn.setOnClickListener {
                storeTxt(binding)
            }
            cancelBtn.setOnClickListener { thisView ->
                thisView.findNavController().navigate(R.id.action_addFragment_to_menuFragment)
            }
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun storeTxt(fragmentAdd: FragmentAddBinding) {
        val first_name = fragmentAdd.firstnameTxt.text.toString()
        val last_name = fragmentAdd.lastnameTxt.text.toString()
        val amout = fragmentAdd.amoutTxt.text.toString().toDouble()

        viewModel.testLog()

        viewModel.first_name = first_name
        viewModel.last_name = last_name
        viewModel.amout = amout
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
