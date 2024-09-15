package com.example.mvvmprojects.apps.acontactManager.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmprojects.R
import com.example.mvvmprojects.apps.acontactManager.room.Contact
import com.example.mvvmprojects.databinding.ContactItemBinding

class ContactRecyclerAdapter(private val contactList: List<Contact>,
    private val clickListener: (Contact) -> Unit ): RecyclerView.Adapter<ContactRecyclerAdapter.MyViewHolder>() {

        class MyViewHolder(val binding :ContactItemBinding ): RecyclerView.ViewHolder(binding.root){

            fun bind(contact: Contact, clickListener: (Contact) -> Unit) {
                binding.tvName.text = contact.name
                binding.tvEmail.text = contact.email

                binding.listItemLayout.setOnClickListener{
                    clickListener(contact)
                }
            }

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ContactItemBinding = DataBindingUtil.inflate(layoutInflater,
            R.layout.contact_item, parent, false)

        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(contactList[position], clickListener)
    }


}