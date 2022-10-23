package com.example.pass.Adpter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pass.databinding.PassItemBinding
import com.example.pass.room.model.PassItem

class AdapterPass (private var listPass:ArrayList<PassItem>,private var listener: OnClicListener): RecyclerView.Adapter<AdapterPass.ViewHolder>() {


    fun addItem(passItem: PassItem){
        listPass.add(passItem)
      notifyDataSetChanged()
    }

    fun clearList(){
        listPass.clear()
        notifyDataSetChanged()
    }


    inner class ViewHolder (var bindingItem:PassItemBinding): RecyclerView.ViewHolder(bindingItem.root){
        fun add(passItem: PassItem){
            bindingItem.textItemName.text = passItem.name
            itemView.setOnClickListener(){
                listener.clic((passItem.id).toLong())
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(PassItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.add(listPass[position])
       
    }

    override fun getItemCount(): Int {
      return  listPass.size
    }

    interface OnClicListener{
        fun clic(id:Long)
    }
}

