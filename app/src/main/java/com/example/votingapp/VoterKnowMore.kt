package com.example.votingapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.votingapp.databinding.FragmentVoterKnowMoreBinding

class VoterKnowMore : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding : FragmentVoterKnowMoreBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_voter_know_more,container,false)


        return binding.root
    }


}