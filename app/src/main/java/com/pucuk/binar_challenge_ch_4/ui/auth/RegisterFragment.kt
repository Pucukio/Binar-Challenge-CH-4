package com.pucuk.binar_challenge_ch_4.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.pucuk.binar_challenge_ch_4.R
import com.pucuk.binar_challenge_ch_4.data.model.User
import com.pucuk.binar_challenge_ch_4.databinding.FragmentLoginBinding
import com.pucuk.binar_challenge_ch_4.databinding.FragmentRegisterBinding
import com.pucuk.binar_challenge_ch_4.ui.MyViewModels

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MyViewModels

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MyViewModels::class.java]
        binding.apply {
            btnRegister.setOnClickListener {
                val username = etUsername.text.toString().trim()
                val password = etPassword.text.toString().trim()
                val confirmpassword = etConfirmpassword.text.toString().trim()
                if (password.length >= 5 && password == confirmpassword) {
                    viewModel.insertUser(User(username = username, password = password))
                    Toast.makeText(requireContext(), "Success Register", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                } else {
                    Toast.makeText(requireContext(), "Failed Register", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
