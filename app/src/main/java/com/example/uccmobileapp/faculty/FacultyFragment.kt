package com.example.uccmobileapp.faculty

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.uccmobileapp.databinding.FragmentFacultyBinding

// Fragment to display a carousel of faculty members using a ViewPager2
class FacultyFragment(private val facultyMembers : List<FacultyMember>) : Fragment() {

    // ViewBinding object for interacting with the layout's views
    private var _binding: FragmentFacultyBinding? = null
    private val binding get() = _binding!!

    // Handler for managing automatic scrolling
    private lateinit var handler : Handler
    private lateinit var facultyAdapter: FacultyMembersAdapter

    // Inflates the fragment's layout and initializes the binding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFacultyBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Initializes the ViewPager2, auto-scrolling, and handles user interaction
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initializes the faculty members adapter
        facultyAdapter = FacultyMembersAdapter(this, facultyMembers)

        // Sets up the handler for auto-scrolling
        handler = Handler(Looper.getMainLooper())
        val autoScrollRunnable = object : Runnable {
            override fun run() {
                // Scroll to the next faculty member in the ViewPager2
                val nextItem = (binding.facultyViewPager.currentItem + 1) % facultyMembers.size
                binding.facultyViewPager.setCurrentItem(nextItem, true)
                handler.postDelayed(this, 5000) // Auto-scroll every 5 seconds
            }
        }
        handler.postDelayed(autoScrollRunnable, 5000)

        // Flags to detect user-initiated scrolling
        var isUserScrolling = false
        val resumeDelayMillis = 6000L // Delay before resuming auto-scroll

        // Registers a callback to handle page changes in the ViewPager2
        binding.facultyViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)

                if (state == ViewPager2.SCROLL_STATE_DRAGGING) {
                    // User started scrolling, stop auto-scrolling
                    isUserScrolling = true
                    handler.removeCallbacks(autoScrollRunnable)
                    binding.leftArrow.visibility = View.GONE // Hide navigation arrows during user scroll
                    binding.rightArrow.visibility = View.GONE
                } else if (state == ViewPager2.SCROLL_STATE_IDLE && isUserScrolling) {
                    // User stopped scrolling, resume auto-scrolling after delay
                    isUserScrolling = false
                    handler.postDelayed(autoScrollRunnable, resumeDelayMillis)
                    binding.leftArrow.visibility = View.VISIBLE // Show arrows again
                    binding.rightArrow.visibility = View.VISIBLE
                }
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                // Automatically scrolls back to the first item when reaching the last item
                if (position == facultyMembers.size - 1) {
                    handler.postDelayed({
                        binding.facultyViewPager.setCurrentItem(0, false)
                    }, 1000)
                }
            }
        })

        // Sets up the ViewPager2 with the adapter and page transformer
        binding.facultyViewPager.apply {
            adapter = facultyAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            setPageTransformer { page, position ->
                page.translationX = position * -1 // Slide effect when changing pages
            }
        }

        //binding.facultyViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        //    override fun onPageSelected(position: Int) {
        //        super.onPageSelected(position)
        //        binding.leftArrow.visibility = View.GONE
        //        binding.rightArrow.visibility = View.GONE
        //    }
        //})
    }

    // Cleans up resources when the view is destroyed
    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacksAndMessages(null) // Remove all pending callbacks to stop auto-scrolling
        _binding = null
    }
}
