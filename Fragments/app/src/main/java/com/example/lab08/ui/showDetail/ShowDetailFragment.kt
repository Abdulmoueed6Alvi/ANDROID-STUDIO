package com.example.lab08.ui.showDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.lab08.databinding.FragmentShowdetailBinding
import com.example.lab08.ui.Login.LoginViewModel
import com.example.lab08.ui.DataEntry.DataEntryViewModel

class ShowDetailFragment : Fragment() {

    private var _binding: FragmentShowdetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(ShowDetailViewModel::class.java)

        _binding = FragmentShowdetailBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.text
        slideshowViewModel.text.observe(viewLifecycleOwner) {
            textView.text = """
    Name: ${DataEntryViewModel.name}
    
    Age: ${DataEntryViewModel.age}
    
    User Name: ${LoginViewModel.name}
    
    Password: ${LoginViewModel.pass}
    
""".trimIndent()


        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}