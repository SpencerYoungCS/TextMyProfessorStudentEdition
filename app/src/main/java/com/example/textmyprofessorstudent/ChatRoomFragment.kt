package com.example.textmyprofessorstudent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.textmyprofessorstudent.databinding.FragmentChatRoomBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class ChatRoomFragment : Fragment() {

    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding = DataBindingUtil.inflate<FragmentChatRoomBinding>(inflater,
            R.layout.fragment_chat_room,container,false)

        database = FirebaseDatabase.getInstance().reference

        val args = ChatRoomFragmentArgs.fromBundle(arguments!!)
        val room_id = args.roomid

        binding.chatBox.layoutManager = LinearLayoutManager(this.context)
        binding.chatBox.adapter = MessageAdapter(database.child("chat-rooms").child(room_id), binding.chatBox)

        // Send Button Listener
        binding.sendBtn.setOnClickListener{
            //The editText that the student will use as input
            val text = binding.inputMsgText.text.toString()
            //Creates a new entry in the database in "chat-rooms" with name "Professor at *DATE*" and sets the value to the input

            // Do nothing if the the field is blank
            if(text.isEmpty()) {
                Toast.makeText(this.context,"Enter a message", Toast. LENGTH_SHORT).show()
            }
            else {

                val date = Date()
                val msg = Message(time = date.toString(), user = "Student", text = text)
                val autoGenKey = database.child("chat-rooms").child(room_id).push()
                val key: String = autoGenKey.key.toString()
                database.child("chat-rooms").child(room_id).child(key).setValue(msg)
//            Log.d(TAG, "onChildAdded:" + DataSnapshot.getValue(Message::class.javaObjectType)!!)

                //Clear the text after submitting
                binding.inputMsgText.setText("")
            }
        }

        // Text Editor IME Action Listener
        binding.inputMsgText.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_SEND ){
                val text = binding.inputMsgText.text.toString()
                //Creates a new entry in the database in "chat-rooms" with name "Professor at *DATE*" and sets the value to the input

                // Do nothing if the the field is blank
                if(text.isEmpty()) {
                    Toast.makeText(this.context,"Enter a message",Toast. LENGTH_SHORT).show()
                }
                else {

                    val date = Date()
                    val msg = Message(time = date.toString(), user = "Student", text = text)
                    val autoGenKey = database.child("chat-rooms").child(room_id).push()
                    val key: String = autoGenKey.key.toString()
                    database.child("chat-rooms").child(room_id).child(key).setValue(msg)
                    //Clear the text after submitting
                    binding.inputMsgText.setText("")
                }
                true
            }
            else{
                false
            }
        }

        return binding.root
    }

}
