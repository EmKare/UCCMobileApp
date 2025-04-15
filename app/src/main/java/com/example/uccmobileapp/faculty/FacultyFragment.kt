package com.example.uccmobileapp.faculty

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uccmobileapp.databinding.FragmentFacultyBinding


class FacultyFragment : Fragment() {

    private var _binding: FragmentFacultyBinding? = null
    private val binding get() = _binding!!

    private lateinit var facultyAdapter: FacultyMemberAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFacultyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        facultyAdapter = FacultyMemberAdapter(this, FacultyMembers)

        binding.facultyRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = facultyAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}