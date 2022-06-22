package com.example.fetchrewardstest.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchrewardstest.databinding.ListItemHomeBinding
import com.example.fetchrewardstest.models.Item

class HomeScreenAdapter : RecyclerView.Adapter<ViewHolder>() {

    private var items: List<Item> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun hasItems(list: List<Item>) {
        items = list
        notifyDataSetChanged()
    }
}

class ViewHolder(private val binding: ListItemHomeBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Item) {
        binding.id.text = "id: ${item.id}"
        binding.listId.text = "listId: ${item.listId}"
        binding.name.text = item.name
    }
}