package com.example.votingapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.votingapp.databinding.FragmentAdminOrVoterBinding


class AdminOrVoter : Fragment() {
    private lateinit var binding: FragmentAdminOrVoterBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentAdminOrVoterBinding>(inflater,R.layout.fragment_admin_or_voter,container,false)

        return binding.root
    }

}