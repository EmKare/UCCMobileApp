package com.example.uccmobileapp.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.uccmobileapp.databinding.ImageItemBinding

class FullscreenImageAdapter(private val images: List<Int>, private val onTap: () -> Unit) : RecyclerView.Adapter<FullscreenImageAdapter.FullscreenViewHolder>() {

    inner class FullscreenViewHolder(val binding: ImageItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(imageRes: Int) {
            binding.galleryImageView.setImageResource(imageRes)
            binding.galleryImageView.setOnClickListener {
                onTap()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FullscreenViewHolder {
        val binding = ImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FullscreenViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FullscreenViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int = images.size
}
