package com.oguzhanturkmen.mytodoreal.ui.addnote

import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.oguzhanturkmen.mytodoreal.R
import com.oguzhanturkmen.mytodoreal.data.entity.NoteData
import com.oguzhanturkmen.mytodoreal.databinding.FragmentAddNoteBinding
import com.oguzhanturkmen.mytodoreal.util.gecisYap
import com.oguzhanturkmen.mytodoreal.util.makeToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate

@AndroidEntryPoint
class AddNoteFragment : Fragment() {
    private lateinit var binding: FragmentAddNoteBinding
    private lateinit var viewModel: AddNoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[AddNoteViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_add_note,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addNoteFragment = this


        //menüyü görüntülemek için
        setUpMenu()
    }

    fun getCurrentTime(){
        val currentTime = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDate.now()
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        val cur = currentTime.toString()

    }

    fun saveNote(){
        val currentTime = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDate.now()
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        val cur = currentTime.toString()

        val noteTitle = binding.etNoteTitle.text.toString().trim()
        val noteBody = binding.etNoteBody.text.toString().trim()

        if (noteTitle.isNotEmpty()){
            viewModel.saveNote(noteTitle,noteBody,cur)
            Snackbar.make(requireView(),"Note Saved Succesfully", Snackbar.LENGTH_SHORT).show()
            Navigation.gecisYap(requireView(),R.id.action_addNoteFragment_to_allNoteFragment)
        }else makeToast(requireContext(),"Please Enter Note Title!")
    }

    fun setUpMenu(){
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.add_note_menu,menu)
                menu.findItem(R.id.menu_save)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    when(menuItem.itemId){
                        R.id.menu_save -> {
                            saveNote()
                            return true
                        }else -> return false
                    }
            }

        },viewLifecycleOwner, Lifecycle.State.RESUMED)
    }


}