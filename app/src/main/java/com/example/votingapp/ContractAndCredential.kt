package com.example.votingapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.votingapp.databinding.FragmentContractAndCredentialBinding
import okhttp3.Credentials

class ContractAndCredential : Fragment() {
    private lateinit var binding: FragmentContractAndCredentialBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentContractAndCredentialBinding>(inflater,R.layout.fragment_contract_and_credential,container,false)
        binding.buttonVerify.setOnClickListener {
            if(binding.editTextContractAddress.text.toString() == null ||binding.editTextCredentials.text.toString() == null){
                toast("Contract Address or Credentials are null")
            }
            else{
                CONTRACT_ADDRESS = binding.editTextContractAddress.text.toString()
                PRIVATE_KEY_ROPSTEN = binding.editTextCredentials.text.toString()
                if(checkCredentials()){
                    toast("Contract and Credentials found and verified successfully!!!")
                    //navigate to next screen
                }
                else{
                    //invalid credentials or contract
                }

            }
        }
        return binding.root
    }

    private fun checkCredentials(): Boolean{
        Log.i("CAC Private Key Frag","${PRIVATE_KEY_ROPSTEN}")
        var res = false
        try {
            credentials = org.web3j.crypto.Credentials.create(PRIVATE_KEY_ROPSTEN)
            res = true
        }catch (e : Exception){
            toast("Credentials are Invalid!!! : ${e.message}")
        }
        return res
    }

    private fun toast(text: String) {
        activity?.runOnUiThread{
            Toast.makeText(activity, text, Toast.LENGTH_LONG).show()
        }
    }
}