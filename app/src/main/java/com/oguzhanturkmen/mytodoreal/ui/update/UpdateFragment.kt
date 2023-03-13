package com.oguzhanturkmen.mytodoreal.ui.update

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.oguzhanturkmen.mytodoreal.R
import com.oguzhanturkmen.mytodoreal.databinding.FragmentUpdateBinding
import com.oguzhanturkmen.mytodoreal.util.gecisYap
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateFragment : Fragment() {
    private lateinit var binding: FragmentUpdateBinding
    private lateinit var viewModel: UpdateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[UpdateViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_update, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.updateFragment = this

        takeNoteData()
        setUpMenu()
    }

    private fun takeNoteData() {
        val bundle: UpdateFragmentArgs by navArgs()
        val gelenKisi = bundle.data
        binding.not = gelenKisi
    }

    private fun setUpMenu(){
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.update_menu,menu)
                menu.findItem(R.id.menu_update)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when(menuItem.itemId){
                    R.id.menu_update -> {
                        Navigation.gecisYap(requireView(),R.id.notificationFragment)
                        return true
                    }else -> return false
                }
            }

        },viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    fun fabDoneClicked(note_id: Int, note_title: String, note_body: String,note_date:String) {
        viewModel.updatedNote(note_id, note_title, note_body,note_date)
        Navigation.gecisYap(requireView(), R.id.action_updateFragment_to_allNoteFragment)

    }

}