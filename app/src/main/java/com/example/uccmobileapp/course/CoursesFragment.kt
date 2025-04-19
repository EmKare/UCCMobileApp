package com.example.uccmobileapp.course

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uccmobileapp.databinding.FragmentCoursesBinding

class CoursesFragment(private val courses : List<Course>) : Fragment() {

    private var _binding: FragmentCoursesBinding? = null
    private val binding get() = _binding!!

    private lateinit var courseAdapter: CoursesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCoursesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        courseAdapter = CoursesAdapter(this, courses)

        binding.coursesRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = courseAdapter
            addItemDecoration(DividerItemDecoration(binding.root.context, DividerItemDecoration.VERTICAL))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}