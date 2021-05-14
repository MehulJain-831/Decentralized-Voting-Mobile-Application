package com.example.votingapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.votingapp.databinding.FragmentAdminKnowMoreBinding

class AdminKnowMore : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding: FragmentAdminKnowMoreBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_admin_know_more,container,false)
        binding.buttonback.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_adminKnowMore_to_admin)
        }
        return binding.root
    }

}