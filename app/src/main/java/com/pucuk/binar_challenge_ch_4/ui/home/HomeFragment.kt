package com.pucuk.binar_challenge_ch_4.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pucuk.binar_challenge_ch_4.R
import com.pucuk.binar_challenge_ch_4.data.model.Note
import com.pucuk.binar_challenge_ch_4.data.model.User
import com.pucuk.binar_challenge_ch_4.data.preferences.NotePreferences
import com.pucuk.binar_challenge_ch_4.databinding.FragmentDeleteBinding
import com.pucuk.binar_challenge_ch_4.databinding.FragmentHomeBinding
import com.pucuk.binar_challenge_ch_4.ui.MyViewModels
import com.pucuk.binar_challenge_ch_4.ui.adapter.NotesAdapter


class HomeFragment : Fragment(), NotesAdapter.ItemClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MyViewModels
    private lateinit var user: User
    private lateinit var notePreferences: NotePreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        notePreferences = NotePreferences(requireContext())
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MyViewModels::class.java]
        user = arguments?.getParcelable<User>("USER_ENTITY")!!
        if (notePreferences.getString("KEY_FILTER").isNullOrEmpty()) {
            viewModel.getAllNotes(user.id!!)
        } else if (notePreferences.getString("KEY_FILTER").equals("ASCENDING", true)) {
            viewModel.getAllNotesAsc(user.id!!)
        } else if (notePreferences.getString("KEY_FILTER").equals("DESCENDING", true)) {
            viewModel.getAllNotesDesc(user.id!!)
        }
        binding.fabAddData.setOnClickListener {
            val bundle = Bundle().apply {
                putParcelable("USER_ENTITY", user)
            }
            findNavController().navigate(R.id.action_homeFragment_to_insertFragment, bundle)
        }
        viewModel.notes.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                binding.apply {
                    emptyState.isVisible = false
                    notesRecyclerView.isVisible = true
                    notesRecyclerView.setHasFixedSize(true)
                    notesRecyclerView.adapter = NotesAdapter(it, this@HomeFragment)
                    notesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                }
            } else {
                binding.apply {
                    emptyState.isVisible = true
                    notesRecyclerView.isVisible = false
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun editNote(noteEntity: Note) {
        val bundle = Bundle().apply {
            putParcelable("NOTE_ENTITY", noteEntity)
            putParcelable("USER_ENTITY", user)
        }
        findNavController().navigate(R.id.action_homeFragment_to_updateFragment, bundle)
    }

    override fun deleteNote(noteEntity: Note) {
        val bundle = Bundle().apply {
            putParcelable("NOTE_ENTITY", noteEntity)
            putParcelable("USER_ENTITY", user)
        }
        findNavController().navigate(R.id.action_homeFragment_to_deleteFragment, bundle)
    }


}