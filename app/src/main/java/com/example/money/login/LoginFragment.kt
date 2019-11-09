package com.example.money.login


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController

import com.example.money.R
import com.example.money.databinding.FragmentLoginBinding

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentLoginBinding>(inflater,R.layout.fragment_login,container,false)
        binding.loginBtn.setOnClickListener { thisView ->
            thisView.findNavController().navigate(R.id.action_loginFragment_to_menuFragment)
            Toast.makeText(activity, "Login_Success!!", Toast.LENGTH_SHORT).show()
        }
        Log.i("onCreateView", "LoginView")
        return binding.root
    }


}
