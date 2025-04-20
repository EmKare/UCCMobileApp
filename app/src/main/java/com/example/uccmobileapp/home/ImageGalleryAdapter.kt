package com.example.uccmobileapp.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.uccmobileapp.databinding.ImageItemBinding

// Adapter for displaying a gallery of images in a RecyclerView
class ImageGalleryAdapter(private val imageList: List<Int>) : RecyclerView.Adapter<ImageGalleryAdapter.ImageViewHolder>() {

    // ViewHolder for an individual image item in the RecyclerView
    inner class ImageViewHolder(val binding: ImageItemBinding) : RecyclerView.ViewHolder(binding.root) {

        //private var isZoomed = false

        // Bind image data to the view and set up click listener
        fun bind(imageRes: Int) {
            binding.galleryImageView.setImageResource(imageRes) // Set the image resource

            // Set up a click listener to open the image preview dialog when an image is clicked
            binding.galleryImageView.setOnClickListener {
                (binding.root.context as? AppCompatActivity)?.let { activity ->
                    // Create and show the ImagePreviewDialog with the current image list and position
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

    // Create and return a new ViewHolder for an image item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    // Bind the image data to the ViewHolder at the given position
    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(imageList[position]) // Bind the image at the current position
    }

    // Return the total number of items in the image list
    override fun getItemCount(): Int = imageList.size
}
