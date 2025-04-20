package com.example.uccmobileapp.course

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uccmobileapp.databinding.FragmentCoursesBinding

// Fragment to display the list of courses in a RecyclerView
class CoursesFragment(private val courses : List<Course>) : Fragment() {

    // ViewBinding object for fragment's layout
    private var _binding: FragmentCoursesBinding? = null
    private val binding get() = _binding!!

    // Adapter for binding course data to RecyclerView
    private lateinit var courseAdapter: CoursesAdapter

    // Inflates the fragment layout and initializes the binding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCoursesBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Called after the fragment view is created. Initializes the RecyclerView with data and layout.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initializes the adapter with the list of courses
        courseAdapter = CoursesAdapter(this, courses)

        // Sets up the RecyclerView
        binding.coursesRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext()) // Set vertical layout manager
            adapter = courseAdapter // Assign the adapter to the RecyclerView
            addItemDecoration(DividerItemDecoration(binding.root.context, DividerItemDecoration.VERTICAL)) // Add divider between items
        }
    }

    // Cleanup the binding when the fragment view is destroyed to avoid memory leaks
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
