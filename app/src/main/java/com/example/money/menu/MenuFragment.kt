package com.example.money.menu


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
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
        return binding.root
    }


}
