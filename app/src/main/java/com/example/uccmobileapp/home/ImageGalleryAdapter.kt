package com.example.uccmobileapp.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.uccmobileapp.databinding.ImageItemBinding

class ImageGalleryAdapter(private val imageList: List<Int> ) : RecyclerView.Adapter<ImageGalleryAdapter.ImageViewHolder>() {

    inner class ImageViewHolder(val binding: ImageItemBinding) : RecyclerView.ViewHolder(binding.root) {
        //private var isZoomed = false

        fun bind(imageRes: Int) {
            binding.galleryImageView.setImageResource(imageRes)

            binding.galleryImageView.setOnClickListener {
                (binding.root.context as? AppCompatActivity)?.let { activity ->
                    val dialog = ImagePreviewDialog(imageList, adapterPosition)
                    dialog.show(activity.supportFragmentManager, "ImagePreview")
                }

            }

            //binding.galleryImageView.setOnDoubleTapListener
            //isZoomed = !isZoomed
            //if (isZoomed) {
            //    binding.galleryImageView.setScale(2.5f, true)
            //} else {
            //    binding.galleryImageView.setScale(1.0f, true)
            //}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(imageList[position])
    }

    override fun getItemCount(): Int = imageList.size
}
