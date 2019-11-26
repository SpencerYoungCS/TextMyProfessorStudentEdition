package com.example.textmyprofessorstudent

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.textmyprofessorstudent.databinding.FragmentJoinRoomBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class JoinRoomFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentJoinRoomBinding>(inflater,
            R.layout.fragment_join_room,container,false)
        // Inflate the layout for this fragment

        database = FirebaseDatabase.getInstance().reference
        auth = FirebaseAuth.getInstance()

        auth.signInAnonymously()

        binding.joinRoomBtn.setOnClickListener{
                view: View ->
            val postListener = object : ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // Get Post object and use the values to update the UI
                    if(dataSnapshot.child("chat-rooms").hasChild(binding.roomID.text.toString())){
                        roomJoinedToast()
                        view.findNavController().navigate(JoinRoomFragmentDirections.actionJoinRoomFragmentToChatRoomFragment(binding.roomID.text.toString()))
                        database.removeEventListener(this)
                    }
                    else{
                        roomDNEToast()
                        // Remove the database EventListener
                        database.removeEventListener(this)
                    }
                }
                override fun onCancelled(databaseError: DatabaseError) {
                    // Getting Post failed, log a message
                    Log.w(ContentValues.TAG, "loadPost:onCancelled", databaseError.toException())
                }
            }
            // Add database Event Listener
            database.addValueEventListener(postListener)
        }


//        return inflater.inflate(R.layout.fragment_create_room, container, false)
        return binding.root
    }

    // Toasts for easy implementation
    fun roomTakenToast(){
        Toast.makeText(this.context,"Room is Taken", Toast. LENGTH_SHORT).show()
    }

    fun roomCreatedToast(){
        Toast.makeText(this.context,"Room Created", Toast. LENGTH_SHORT).show()
    }

    fun roomJoinedToast(){
        Toast.makeText(this.context,"Room Joined", Toast. LENGTH_SHORT).show()
    }

    fun roomDNEToast(){
        Toast.makeText(this.context,"Room Does Not Exist", Toast. LENGTH_SHORT).show()
    }

}