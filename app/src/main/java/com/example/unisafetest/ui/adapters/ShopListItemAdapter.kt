package com.example.unisafetest.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.unisafetest.core.ShoppingList
import com.example.unisafetest.databinding.RvShoppingListsPreviewBinding
import com.example.unisafetest.ui.listeners.OnShoppingListClickListener

class ShopListItemAdapter() :
    RecyclerView.Adapter<ShopListItemAdapter.ItemViewHolder>() {
    private var data = ArrayList<ShoppingList>()
    private lateinit var listener: OnShoppingListClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = RvShoppingListsPreviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val rvItem = data.elementAt(position)
        holder.bind(rvItem)
    }

    fun setClickListener(listener: OnShoppingListClickListener) {
        this.listener = listener
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: ArrayList<ShoppingList>) {
        this.data = data
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(rvItemBinding: RvShoppingListsPreviewBinding) :
        RecyclerView.ViewHolder(rvItemBinding.root) {
        private val binding = rvItemBinding

        fun bind(rvItem: ShoppingList) {
            binding.tvName.text = rvItem.name
            binding.tvCreated.text = rvItem.created

            binding.btnEdit.setOnClickListener {
                listener.onShopListClick(rvItem.id!!)
            }

            binding.btnRemove.setOnClickListener {
                listener.onShopListRemove(rvItem.id!!)
            }
        }
    }
}
