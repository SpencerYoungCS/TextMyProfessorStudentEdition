package com.example.textmyprofessorstudent

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.databinding.DataBindingUtil
import androidx.navigation.ui.NavigationUI
import com.example.textmyprofessorstudent.databinding.FragmentTitleBinding
import kotlin.random.Random

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class TitleFragment : Fragment() {
    private val noReplaceList = mutableListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding = DataBindingUtil.inflate<FragmentTitleBinding>(inflater,
            R.layout.fragment_title,container,false)

        binding.startBtn.setOnClickListener{view: View ->
            view.findNavController().navigate(R.id.action_titleFragment_to_joinRoomFragment)
        }

        return binding.root
    }

}
