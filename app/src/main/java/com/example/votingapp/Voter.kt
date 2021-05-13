package com.example.votingapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.votingapp.databinding.FragmentVoterBinding


class Voter : Fragment() {

    private lateinit var binding: FragmentVoterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentVoterBinding>(inflater,R.layout.fragment_voter,container,false)

        return binding.root
    }

}