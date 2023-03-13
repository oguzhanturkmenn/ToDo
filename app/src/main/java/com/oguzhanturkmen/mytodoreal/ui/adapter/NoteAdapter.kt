package com.oguzhanturkmen.mytodoreal.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.oguzhanturkmen.mytodoreal.R
import com.oguzhanturkmen.mytodoreal.data.entity.NoteData
import com.oguzhanturkmen.mytodoreal.databinding.NoteRowBinding
import com.oguzhanturkmen.mytodoreal.ui.note.AllNoteFragmentDirections
import com.oguzhanturkmen.mytodoreal.util.gecisYap
import java.util.*

class NoteAdapter(var mContext: Context) :
    RecyclerView.Adapter<NoteAdapter.NoteHolder>() {


    class NoteHolder(var binding: NoteRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(p: NoteData) {
            binding.not = p
            binding.executePendingBindings()
        }
    }



    private val differCallback =
        object : DiffUtil.ItemCallback<NoteData>() {
            override fun areItemsTheSame(oldItem: NoteData, newItem: NoteData): Boolean {
                return oldItem.note_id == newItem.note_id &&
                        oldItem.note_body == newItem.note_body &&
                        oldItem.note_title == newItem.note_title &&
                        oldItem.note_date == newItem.note_date
            }

            override fun areContentsTheSame(oldItem: NoteData, newItem: NoteData): Boolean {
                return oldItem == newItem
            }

        }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val binding: NoteRowBinding = DataBindingUtil.inflate(
            LayoutInflater.from(mContext),
            R.layout.note_row, parent, false
        )
        return NoteHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val currentNote = differ.currentList[position]
        holder.bind(currentNote)

        holder.binding.cardView.setOnClickListener {
            val gecis = AllNoteFragmentDirections.actionAllNoteFragmentToUpdateFragment(currentNote)
            Navigation.gecisYap(it,gecis)
        }

        val random = Random()
        val color = Color.argb(
                255, random.nextInt(256),
                random.nextInt(256), random.nextInt(256)
            )
        holder.binding.ibColor.setBackgroundColor(color)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}