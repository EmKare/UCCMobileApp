package com.example.uccmobileapp.course

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.uccmobileapp.databinding.FragmentCoursesBinding

class CoursesFragment : Fragment() {

    private var _binding: FragmentCoursesBinding? = null
    private val binding get() = _binding!!

    private lateinit var courseAdapter: CoursesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoursesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        //binding.coursesViewPager.adapter = CoursesAdapter(this, ITCourses)
        courseAdapter = CoursesAdapter(this, ITCourses)

        binding.coursesViewPager.apply {
            adapter = courseAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            // Add page margin for better spacing
            setPageTransformer { page, position ->
                page.translationX = position * -1
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}