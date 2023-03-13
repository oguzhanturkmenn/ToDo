package com.oguzhanturkmen.mytodoreal.ui.note

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.oguzhanturkmen.mytodoreal.R
import com.oguzhanturkmen.mytodoreal.data.entity.NoteData
import com.oguzhanturkmen.mytodoreal.databinding.FragmentAllNoteBinding
import com.oguzhanturkmen.mytodoreal.ui.adapter.NoteAdapter
import com.oguzhanturkmen.mytodoreal.util.gecisYap
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AllNoteFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var binding: FragmentAllNoteBinding
    private lateinit var viewModel: AllNoteViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[AllNoteViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_all_note, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        binding.allNoteFragment = this

        setUpMenu()
        setUpView()
        deleteNote()

    }


    private fun deleteNote() {
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            @SuppressLint("NotifyDataSetChanged", "ShowToast")
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                AlertDialog.Builder(activity).apply {
                    setTitle("Delete Note")
                    setMessage("Are you sure you want to delete this note?")
                    setPositiveButton("DELETE") { _, _ ->
                        val deletedNote =
                            binding.adapter?.differ!!.currentList[viewHolder.adapterPosition]
                        viewModel.deleteNote(deletedNote)

                        Snackbar.make(requireView(), "Deleted", Snackbar.LENGTH_SHORT)
                            .setAction("UNDO") {
                                viewModel.saveNoteAgain(deletedNote)
                            }.show()
                    }
                    setNegativeButton("CANCEL") { _, _ ->
                        binding.adapter!!.notifyDataSetChanged()
                    }
                }.create().show()
            }
        }).attachToRecyclerView(binding.recyclerView)
    }

    private fun setUpView() {
        val adapter = NoteAdapter(requireContext())
        binding.recyclerView.apply {
            binding.adapter = adapter
        }

        activity.let {
            viewModel.allNoteList.observe(viewLifecycleOwner) {
                adapter.differ.submitList(it)
                updateUI(it)
            }
        }

    }

    private fun updateUI(note: List<NoteData>) {
        if (note.isNotEmpty()) {
            binding.recyclerView.visibility = View.VISIBLE
            binding.tvNoNoteAvailable.visibility = View.GONE
        } else {
            binding.recyclerView.visibility = View.GONE
            binding.tvNoNoteAvailable.visibility = View.VISIBLE
        }
    }

    private fun setUpMenu() {

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.search_menu, menu)

                val item = menu.findItem(R.id.search_menu)
                val searchView = item.actionView as SearchView
                searchView.setOnQueryTextListener(this@AllNoteFragment)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.search_menu -> {
                        return true
                    }
                    else -> {
                        return false
                    }
                }
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    fun addNoteFabClicked() {
        Navigation.gecisYap(requireView(), R.id.action_allNoteFragment_to_addNoteFragment)

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            searchNote(query)
            return true
        } else {
            return false
        }

    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            searchNote(newText)
            return true
        } else {
            return false
        }
    }

    private fun searchNote(query: String?) {
        val searchQuery = "%$query%"
        viewModel.searchNote(searchQuery)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllNote()
    }


}