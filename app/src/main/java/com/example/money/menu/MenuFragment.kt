package com.example.money.menu


import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.money.R
import com.example.money.databinding.FragmentMenuBinding

/**
 * A simple [Fragment] subclass.
 */
class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val args =
            MenuFragmentArgs.fromBundle(
                arguments!!
            )

        val binding = DataBindingUtil.inflate<FragmentMenuBinding>(inflater, R.layout.fragment_menu, container, false)

        binding.apply {
            addBtn.setOnClickListener { thisView ->
                thisView.findNavController().navigate(R.id.action_menuFragment_to_addFragment)
            }
            detailBtn.setOnClickListener { thisView ->
                thisView.findNavController().navigate(R.id.action_menuFragment_to_listFragment)
            }
            debtorBtn.setOnClickListener { thisView ->
                thisView.findNavController().navigate(R.id.action_menuFragment_to_debtorFragment)
            }
        }
        if(args.status == 0){
            Toast.makeText(activity, "Hello: " + args.userName, Toast.LENGTH_SHORT).show()
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.option_menu, menu)
        inflater.inflate(R.menu.drop_menu, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share -> shareSuccess()
        }

        return NavigationUI.onNavDestinationSelected(item, view!!.findNavController()) || super.onOptionsItemSelected(item)

    }

    private fun getShareIntent() : Intent {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain")
            .putExtra(Intent.EXTRA_TEXT,"สวัสดีครับอาจารย์กบ")
        return shareIntent
    }

    private fun shareSuccess() {
        startActivity(getShareIntent())
    }
}
