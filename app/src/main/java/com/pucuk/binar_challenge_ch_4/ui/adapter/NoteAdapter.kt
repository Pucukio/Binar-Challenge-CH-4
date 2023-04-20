package com.pucuk.binar_challenge_ch_4.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pucuk.binar_challenge_ch_4.data.model.Note
import com.pucuk.binar_challenge_ch_4.databinding.ItemNoteBinding

class NotesAdapter(
    private val list: List<Note>,
    private val itemClickListener: ItemClickListener
) :
    RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(noteEntity: Note) {
            binding.apply {
                note = noteEntity
                itemNoteClick = itemClickListener
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder =
        NoteViewHolder(ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount(): Int = list.size

    interface ItemClickListener {
        fun editNote(noteEntity: Note)
        fun deleteNote(noteEntity: Note)
    }
}