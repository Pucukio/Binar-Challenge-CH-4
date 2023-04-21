package com.pucuk.binar_challenge_ch_4.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.pucuk.binar_challenge_ch_4.R
import com.pucuk.binar_challenge_ch_4.data.model.User
import com.pucuk.binar_challenge_ch_4.databinding.FragmentLoginBinding
import com.pucuk.binar_challenge_ch_4.ui.MyViewModels

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MyViewModels

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MyViewModels::class.java]
        binding.apply {
            btnLogin.setOnClickListener { mView ->
                val username = etUsernamme.text.toString().trim()
                val password = etPassword.text.toString().trim()
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.getUser(username, password)
                    viewModel.user.observe(viewLifecycleOwner) {
                        if (it != null) {
                            if (it.username == username && it.password == password) {
                                Toast.makeText(requireContext(), "Success Login", Toast.LENGTH_SHORT).show()
                                val bundle = Bundle().apply {
                                    putParcelable("USER_ENTITY", it)
                                }
                                findNavController()
                                    .navigate(R.id.action_loginFragment_to_homeFragment, bundle)
                            } else {
                                Toast.makeText(requireContext(), "Username or password is incorrect", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(requireContext(), "Failed Login", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
            tvRegister.setOnClickListener {
                it.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}