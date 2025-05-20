package com.example.lab08.ui.DataEntry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lab08.databinding.FragmentDataentryBinding

class DataEntryFragment : Fragment() {

    private var _binding: FragmentDataentryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDataentryBinding.inflate(inflater, container, false)

        // Show the name stored in GalleryViewModel
        binding.save.setOnClickListener {
            DataEntryViewModel.name = binding.NameInput.text.toString()
            DataEntryViewModel.age = binding.AgeInput.text.toString()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
