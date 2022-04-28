package com.example.chooseyourownbeer.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.chooseyourownbeer.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.settingsBtn.setOnClickListener {
            Toast.makeText(context, "User settings here.", Toast.LENGTH_SHORT).show()
        }
        binding.logoutBtn.setOnClickListener {
            Toast.makeText(context, "You succesfully logged out!", Toast.LENGTH_SHORT).show()
        }
        binding.contactBtn.setOnClickListener {
            Toast.makeText(context, "Thank you! We'll reach out to you.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}