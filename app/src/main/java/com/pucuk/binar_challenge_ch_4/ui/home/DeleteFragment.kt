package com.pucuk.binar_challenge_ch_4.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.pucuk.binar_challenge_ch_4.R
import com.pucuk.binar_challenge_ch_4.data.model.Note
import com.pucuk.binar_challenge_ch_4.data.model.User
import com.pucuk.binar_challenge_ch_4.databinding.FragmentDeleteBinding
import com.pucuk.binar_challenge_ch_4.databinding.FragmentRegisterBinding
import com.pucuk.binar_challenge_ch_4.ui.MyViewModels

class DeleteFragment : DialogFragment() {

    private var _binding: FragmentDeleteBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MyViewModels

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeleteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MyViewModels::class.java]
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
        val params = arguments?.getParcelable<User>("USER_ENTITY")
        val params2 = arguments?.getParcelable<Note>("NOTE_ENTITY")
        binding.apply {
            btnDelete.setOnClickListener {
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle(R.string.dialog_title_delete)
                    .setMessage(R.string.dialog_message_delete)
                    .setPositiveButton(R.string.delete) { dialog, which ->
                        viewModel.deleteNote(
                            Note(
                                params2?.id, params2?.title!!, params2.content,
                                params?.id!!
                            )
                        )
                        val bundle = Bundle().apply {
                            putParcelable("USER_ENTITY", params)
                        }
                        findNavController().navigate(R.id.homeFragment, bundle)
                    }
                    .setNegativeButton(R.string.txt_cancel) { dialog, which ->
                        dialog.dismiss()
                    }
                    .show()
            }
            btnCancel.setOnClickListener {
                dismiss()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}