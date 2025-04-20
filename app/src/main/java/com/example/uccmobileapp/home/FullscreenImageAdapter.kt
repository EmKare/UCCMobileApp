package com.example.uccmobileapp.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.uccmobileapp.databinding.ImageItemBinding

// Adapter for displaying a list of images in fullscreen mode with a click listener
class FullscreenImageAdapter(
    private val images: List<Int>,    // List of image resource IDs to display
    private val onTap: () -> Unit     // A callback function to be invoked when an image is tapped
) : RecyclerView.Adapter<FullscreenImageAdapter.FullscreenViewHolder>() {

    // ViewHolder for an individual image item in the RecyclerView
    inner class FullscreenViewHolder(val binding: ImageItemBinding) : RecyclerView.ViewHolder(binding.root) {

        // Binds an image resource to the view and sets a click listener
        fun bind(imageRes: Int) {
            binding.galleryImageView.setImageResource(imageRes)  // Set the image source
            binding.galleryImageView.setOnClickListener {       // Set the onClick listener
                onTap()  // Invoke the callback function when the image is tapped
            }
        }
    }

    // Creates a new ViewHolder by inflating the layout and returning the binding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FullscreenViewHolder {
        val binding = ImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FullscreenViewHolder(binding)  // Return the ViewHolder with the binding
    }

    // Binds the data (image) to the ViewHolder at the given position
    override fun onBindViewHolder(holder: FullscreenViewHolder, position: Int) {
        holder.bind(images[position])  // Bind the image resource ID at the current position
    }

    // Returns the total number of items in the dataset (images list)
    override fun getItemCount(): Int = images.size
}
