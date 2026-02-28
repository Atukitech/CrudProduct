package com.example.profile_app


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.profile_app.databinding.ItemNoteBinding

class NoteAdapter(
    private  val list: List<Note>,
    private  val onEdit: (Note)-> Unit,
    private  val onDelete:(Note)-> Unit,


    ): RecyclerView.Adapter<NoteAdapter.ViewHolder> (

){

    inner class ViewHolder( val binding: ItemNoteBinding): RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoteAdapter.ViewHolder {
        val binding= ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)


    }

    override fun onBindViewHolder(holder: NoteAdapter.ViewHolder, position: Int) {
        val note = list[position]
        holder.binding.name.text = note.name
        holder.binding.quantity.text = note.quantity
        holder.binding.categoryy.text = note.category
        holder.binding.price.text = note.price
        holder.binding.available.text = note.available
        holder.binding.editBtn.setOnClickListener {
            onEdit(note)

        }
        holder.binding.deleteBtn.setOnClickListener {
            onDelete(note)
        }
    }

    override fun getItemCount(): Int =list.size


}