package com.pucuk.binar_challenge_ch_4.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.pucuk.binar_challenge_ch_4.R
import com.pucuk.binar_challenge_ch_4.data.model.Note
import com.pucuk.binar_challenge_ch_4.data.model.User
import com.pucuk.binar_challenge_ch_4.databinding.FragmentDeleteBinding
import com.pucuk.binar_challenge_ch_4.databinding.FragmentInsertBinding
import com.pucuk.binar_challenge_ch_4.ui.MyViewModels


class InsertFragment : DialogFragment() {

    private var _binding: FragmentInsertBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MyViewModels

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInsertBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MyViewModels::class.java]
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
        val params = arguments?.getParcelable<User>("USER_ENTITY")
        binding.apply {
            btnAdd.setOnClickListener {
                val title = etEditTitle.text.toString().trim()
                val desc = etEditDescription.text.toString().trim()
                // Cek title dan desc tidak boleh kosong
                if (title.isNotEmpty() && desc.isNotEmpty()) {
                    viewModel.insertNote(Note(title = title, content = desc, userId = params?.id!!))
                    val bundle = Bundle().apply {
                        putParcelable("USER_ENTITY", params)
                    }
                    findNavController().navigate(R.id.homeFragment, bundle)
                    Toast.makeText(requireContext(), "Catatan Berhasil Dibuat", Toast.LENGTH_SHORT).show()
                } else {
                    // Alert Kolom Kosong
                    val errorMsg = getString(R.string.error_empty_field)
                    if (title.isEmpty()) etEditTitle.error = errorMsg
                    if (desc.isEmpty()) etEditDescription.error = errorMsg
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}