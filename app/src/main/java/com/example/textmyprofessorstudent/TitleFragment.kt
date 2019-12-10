package com.example.textmyprofessorstudent

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.databinding.DataBindingUtil
import com.example.textmyprofessorstudent.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {
    private val noReplaceList = mutableListOf<Int>()

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding = DataBindingUtil.inflate<FragmentTitleBinding>(inflater,
            R.layout.fragment_title,container,false)

        // Changed onclicklistener to on touch (feels more responsive)
        // and added glow effect
        binding.startBtn.setOnTouchListener { v, event ->
            val action = event.action
            when(action){
                MotionEvent.ACTION_DOWN -> {
                    binding.startBtn.setImageResource(R.mipmap.startbtnglow_foreground)
                }

                MotionEvent.ACTION_UP -> {
                    v.findNavController().navigate(R.id.action_titleFragment_to_joinRoomFragment)
                }

                MotionEvent.ACTION_MOVE -> {}

                else -> {

                    binding.startBtn.setImageResource(R.mipmap.startbutton_foreground)
                }


            }
            true
        }

        return binding.root
    }

}
