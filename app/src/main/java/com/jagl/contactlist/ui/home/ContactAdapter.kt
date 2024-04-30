package com.jagl.contactlist.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.jagl.contactlist.databinding.ItemContactBinding
import com.jagl.contactlist.databinding.ItemEmptyBinding
import com.jagl.contactlist.ui.items.SearcherItem
import com.jagl.contactlist.ui.items.SearcherItem.ContactItem
import com.jagl.contactlist.ui.items.SearcherItem.Empty

class ContactAdapter(private val onClickListener: (ContactItem) -> Unit) :
    RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    private val data: List<SearcherItem>
        get() = _data
    private var _data = mutableListOf<SearcherItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = when (viewType) {
            1 -> ItemContactBinding.inflate(layoutInflater, parent, false)
            0 -> ItemEmptyBinding.inflate(layoutInflater, parent, false)
            else -> throw Exception()
        }
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = data.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemViewType(position: Int): Int {
        return when (data[position]) {
            is ContactItem -> 1
            is Empty -> 0
        }
    }

    fun updateItems(newData: List<SearcherItem>) {
        _data.clear()
        _data.addAll(newData)
        notifyDataSetChanged()
    }

    fun setEmpty(isInit: Boolean) {
        updateItems(
            listOf(
                Empty(isInit)
            )
        )
    }

    fun updateItem(newItem: SearcherItem, position: Int) {
        _data[position] = newItem
        notifyItemChanged(position)
    }

    inner class ViewHolder(private val binding: ViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SearcherItem) {
            when (item) {
                is ContactItem -> bindContact(item, binding as ItemContactBinding)
                is Empty -> bindEmpty(item, binding as ItemEmptyBinding)
            }
        }


        private fun bindContact(item: ContactItem, binding: ItemContactBinding) {
            val contact = item.contact
            binding.tvFullName.text =
                "${contact.name} ${contact.fatherLastName} ${contact.motherLastName}"
            binding.root.setOnClickListener {
                onClickListener(item)
            }
        }

        private fun bindEmpty(item: Empty, binding: ItemEmptyBinding) {
            val text = if (item.isInitState) "Parece que no tienes ningun contacto.\n¡Agrega uno!"
            else "No se encontraron resultados"
            binding.tvMessage.text = text
        }
    }
}