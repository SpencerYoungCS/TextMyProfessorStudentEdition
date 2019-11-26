package com.example.textmyprofessorstudent

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.databinding.DataBindingUtil
import com.example.textmyprofessorstudent.databinding.FragmentTitleBinding

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
