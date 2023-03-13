package com.oguzhanturkmen.mytodoreal.ui.start

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.oguzhanturkmen.mytodoreal.R
import com.oguzhanturkmen.mytodoreal.util.gecisYap


class StartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val time = object : CountDownTimer(2750,1000){
            override fun onTick(p0: Long) {
            }

            override fun onFinish() {
                Navigation.gecisYap(view,R.id.action_startFragment_to_allNoteFragment)
            }

        }
        time.start()

    }
}