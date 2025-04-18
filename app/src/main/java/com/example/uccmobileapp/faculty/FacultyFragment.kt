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

class FacultyFragment(private val facultyMembers : List<FacultyMember>) : Fragment() {

    private var _binding: FragmentFacultyBinding? = null
    private val binding get() = _binding!!

    private lateinit var handler : Handler
    private lateinit var facultyAdapter: FacultyMembersAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFacultyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        facultyAdapter = FacultyMembersAdapter(this, facultyMembers)
        handler = Handler(Looper.getMainLooper())
        val autoScrollRunnable = object : Runnable {
            override fun run() {
                val nextItem = (binding.facultyViewPager.currentItem + 1) % facultyMembers.size
                binding.facultyViewPager.setCurrentItem(nextItem, true)
                handler.postDelayed(this, 5000) // every 5 seconds
            }
        }
        handler.postDelayed(autoScrollRunnable, 5000)

        var isUserScrolling = false
        val resumeDelayMillis = 6000L

        binding.facultyViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)

                if (state == ViewPager2.SCROLL_STATE_DRAGGING) {
                    isUserScrolling = true
                    handler.removeCallbacks(autoScrollRunnable)
                    binding.leftArrow.visibility = View.GONE
                    binding.rightArrow.visibility = View.GONE
                } else if (state == ViewPager2.SCROLL_STATE_IDLE && isUserScrolling) {
                    isUserScrolling = false
                    handler.postDelayed(autoScrollRunnable, resumeDelayMillis)
                    binding.leftArrow.visibility = View.VISIBLE
                    binding.rightArrow.visibility = View.VISIBLE
                }
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                if (position == facultyMembers.size - 1) {
                    handler.postDelayed({
                        binding.facultyViewPager.setCurrentItem(0, false)
                    }, 1000)
                }
            }
        })

        binding.facultyViewPager.apply {
            adapter = facultyAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            setPageTransformer { page, position ->
                page.translationX = position * -1
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

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacksAndMessages(null)
        _binding = null
    }

}