package com.pucuk.binar_challenge_ch_4.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.pucuk.binar_challenge_ch_4.R
import com.pucuk.binar_challenge_ch_4.databinding.FragmentSplashBinding
import com.pucuk.binar_challenge_ch_4.databinding.FragmentUpdateBinding
import com.pucuk.binar_challenge_ch_4.ui.MyViewModels

class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(layoutInflater, container, false)
        loading()
        return binding.root
    }

    private fun loading() {
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        }, 2000L)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}