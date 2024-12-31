package com.dicoding.myrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.myrecyclerview.databinding.ItemRowFoodBinding

class ListFoodAdapter(private val listFood: ArrayList<Food>): RecyclerView.Adapter<ListFoodAdapter.ListViewHolder>()  {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    //dibawah jika tidak menggunakan viewBinding
    // class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    class ListViewHolder(var binding: ItemRowFoodBinding) : RecyclerView.ViewHolder(binding.root){

//dibawah jika tidak menggunakan view Binding
        //        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
//        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
//        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  ListViewHolder(binding)
        //dibawah jika tidak menggunakan viewBinding
//        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_hero, parent, false)
//        return ListHeroAdapter.ListViewHolder(view)
    }

    override fun getItemCount(): Int = listFood.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo) = listFood[position]
//        holder.binding.imgItemPhoto.setImageResource(photo)

        holder.binding.tvItemName.text = name
        holder.binding.tvItemDescription.text = description
        Glide.with(holder.itemView.context)
            .load(photo) // URL Gambar
            .into(holder.binding.imgItemPhoto) // imageView mana yang akan diterapkan
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listFood[holder.adapterPosition]) }

    }
    interface OnItemClickCallback {
        fun onItemClicked(data: Food)
    }
}