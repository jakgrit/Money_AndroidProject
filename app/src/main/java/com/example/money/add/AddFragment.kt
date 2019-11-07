package com.example.money.add


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.money.R
import com.example.money.databinding.FragmentAddBinding

/**
 * A simple [Fragment] subclass.
 */
class AddFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentAddBinding>(inflater, R.layout.fragment_add, container, false)
        binding.apply {
            cancelBtn.setOnClickListener { thisView ->
                thisView.findNavController().navigate(R.id.action_addFragment_to_menuFragment)
            }
        }
        return binding.root
    }


}
