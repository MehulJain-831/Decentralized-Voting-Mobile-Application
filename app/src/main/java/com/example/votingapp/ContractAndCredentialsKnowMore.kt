package com.example.votingapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.votingapp.databinding.FragmentContractAndCredentialsKnowMoreBinding

class ContractAndCredentialsKnowMore : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding: FragmentContractAndCredentialsKnowMoreBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_contract_and_credentials_know_more,container,false)
        binding.buttonHome.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_contractAndCredentialsKnowMore_to_contractAndCredential)
        }
        return binding.root
    }


}