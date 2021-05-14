package com.example.votingapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.votingapp.databinding.FragmentAdminOrVoterKnowMoreBinding

class AdminOrVoterKnowMore : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var binding: FragmentAdminOrVoterKnowMoreBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_admin_or_voter_know_more,container,false)
        binding.buttonBack.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_aminOrVoterKnowMore_to_adminOrVoter)
        }
        return binding.root
    }

}