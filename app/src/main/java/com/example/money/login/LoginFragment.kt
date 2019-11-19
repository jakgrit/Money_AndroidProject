package com.example.money.login


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

import com.example.money.R
import com.example.money.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_login,container,false)

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        binding.loginViewModel = loginViewModel
        binding.lifecycleOwner = this

        loginViewModel.checkLogin.observe(this, Observer<Boolean> { hasFinished ->
            if(hasFinished) findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToMenuFragment(
                    loginViewModel.userName.value.toString(),
                    loginViewModel.passWord.value.toString(),
                    0
                )
            ) else{
                Toast.makeText(this.context, "Login Failed", Toast.LENGTH_SHORT).show()
            }
        })

        return binding.root
    }
}
