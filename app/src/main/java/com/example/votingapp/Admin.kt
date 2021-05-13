package com.example.votingapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.votingapp.databinding.FragmentAdminBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.BigInteger


class Admin : Fragment() {

    private lateinit var binding: FragmentAdminBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentAdminBinding>(inflater,R.layout.fragment_admin,container,false)


        //getters

        binding.buttonGetProposalDescription.setOnClickListener {
            val inputIndex = binding.editTextGetProposalDescription.text.toString()
            if(inputIndex == null){
                toast("Index is null!! Please provide an index")
            }
            else{
                GetProposalDesc(inputIndex)
            }
        }
        binding.buttonGetTotalProposal.setOnClickListener {
            GetProposalNumber()
        }
        binding.buttonGetWinningProposalDescription.setOnClickListener {
            GetWinningPropDesc()
        }
        binding.buttonGetWinningProposalID.setOnClickListener {
            GetWinningPropID()
        }
        binding.buttonGetWorkflowStatus.setOnClickListener {
            GetWorkFlowStatus()
        }
        binding.buttonGetIsRegisteredVoter.setOnClickListener {
            val inputIndex = binding.editTextGetIsRegisteredVoter.text.toString()
            if(inputIndex == null){
                toast("Account Address is null!! Please provide an Account Address")
            }
            else{
                GetIsRegisteredVoter(inputIndex)
            }
        }

        //setters
        binding.buttonSetRegisterProposal.setOnClickListener {

        }
        binding.buttonSetRegisterVoter.setOnClickListener {

        }
        binding.buttonSetStartProposalRegistration.setOnClickListener {

        }
        binding.buttonSetEndProposalRegistration.setOnClickListener {

        }
        binding.buttonSetStartVotingSession.setOnClickListener {

        }
        binding.buttonSetEndVotingSession.setOnClickListener {

        }
        binding.buttonSetTallyVotes.setOnClickListener {

        }

        return binding.root
    }
    private fun GetProposalDesc(index: String) {
        CoroutineScope(Dispatchers.Main).launch(Dispatchers.IO) {

            val result: String
            result = try {
                val simpleVoter = SimpleVoting.load(CONTRACT_ADDRESS, web3j, credentials, getGasPrice(), getGasLimit())
                val simpleVoting = simpleVoter.getProposalDescription(index.toBigInteger()).sendAsync()
                simpleVoting.get()
            } catch (e: Exception) {
                "Error reading the smart contract. Error: " + e.message
            }

            withContext(Dispatchers.Main) {
                Log.i("Get","${result}")
                toast(result)
            }
        }
    }
    private fun GetProposalNumber() {
        CoroutineScope(Dispatchers.Main).launch(Dispatchers.IO) {

            val result: String
            result = try {
                val simpleVoter = SimpleVoting.load(CONTRACT_ADDRESS, web3j, credentials, getGasPrice(), getGasLimit())
                val simpleVoting = simpleVoter.proposalsNumber.sendAsync()
                simpleVoting.get().toString()
            } catch (e: Exception) {
                "Error reading the smart contract. Error: " + e.message
            }

            withContext(Dispatchers.Main) {
                Log.i("GetProposalNumbers","${result}")
                toast(result)
            }
        }
    }

    private fun GetWinningPropDesc() {
        CoroutineScope(Dispatchers.Main).launch(Dispatchers.IO) {

            val result: String
            result = try {
                val simpleVoter = SimpleVoting.load(CONTRACT_ADDRESS, web3j, credentials, getGasPrice(), getGasLimit())
                val simpleVoting = simpleVoter.winningProposalDescription.sendAsync()
                simpleVoting.get().toString()
            } catch (e: Exception) {
                "Error reading the smart contract. Error: " + e.message
            }

            withContext(Dispatchers.Main) {
                Log.i("GetWinningProposalDesc","${result}")
                toast(result)
            }
        }
    }

    private fun GetWinningPropID() {
        CoroutineScope(Dispatchers.Main).launch(Dispatchers.IO) {

            val result: String
            result = try {
                val simpleVoter = SimpleVoting.load(CONTRACT_ADDRESS, web3j, credentials, getGasPrice(), getGasLimit())
                val simpleVoting = simpleVoter.winningProposalId.sendAsync()
                simpleVoting.get().toString()
            } catch (e: Exception) {
                "Error reading the smart contract. Error: " + e.message
            }

            withContext(Dispatchers.Main) {
                Log.i("GetWinningProposalID","${result}")
                toast(result)
            }
        }
    }

    private fun GetWorkFlowStatus() {
        CoroutineScope(Dispatchers.Main).launch(Dispatchers.IO) {

            val result: String
            result = try {
                val simpleVoter = SimpleVoting.load(CONTRACT_ADDRESS, web3j, credentials, getGasPrice(), getGasLimit())
                val simpleVoting = simpleVoter.workflowStatus.sendAsync()
                simpleVoting.get().toString()
            } catch (e: Exception) {
                "Error reading the smart contract. Error: " + e.message
            }

            withContext(Dispatchers.Main) {
                Log.i("GetWorkFlowStatus","${result}")
//                binding.textViewGetWorkFlowStatus.visibility = View.VISIBLE
//                binding.textViewGetWorkFlowStatus.text = result
                var resultStr :String
                resultStr = when(result){
                    "0"-> "Voter Registration Phase"
                    "1"-> "Proposal Registration Phase Starts"
                    "2"-> "Proposal Registration Phase Ends"
                    "3"-> "Voting Session Starts"
                    "4"-> "Voting Session Ends"
                    "5"-> "Results announced"
                    else-> result
                }
                toast(resultStr)
            }
        }
    }
    private fun GetIsRegisteredVoter(inputAdd: String) {
        CoroutineScope(Dispatchers.Main).launch(Dispatchers.IO) {

            val result: String
            result = try {
                val simpleVoter = SimpleVoting.load(CONTRACT_ADDRESS, web3j, credentials, getGasPrice(), getGasLimit())
                val simpleVoting = simpleVoter.isRegisteredVoter(inputAdd).sendAsync()
                simpleVoting.get().toString()
            } catch (e: Exception) {
                "Error reading the smart contract. Error: " + e.message
            }

            withContext(Dispatchers.Main) {
                Log.i("GetIsAdmin","${result}")
                var resultStr = when(result.toString()){
                    "true" -> "The Voter is Registered!!!"
                    "false" -> "The Voter is not Registered!!!"
                    else -> result.toString()
                }
                toast(resultStr)
            }
        }
    }



    ////////////////////////////////////
    private fun getGasPrice(): BigInteger {
        val gasPriceGwei = 50
        val gasPriceWei = BigInteger.valueOf(gasPriceGwei + 1000000000L)
        Log.d("wat", "getGasPrice: $gasPriceGwei")
        return gasPriceWei
    }

    private fun getGasLimit(): BigInteger {
        return 100000.bigInteger()
    }

    private fun Int.bigInteger(): BigInteger {
        return BigInteger(toString())
    }
    private fun toast(text: String) {
        activity?.runOnUiThread{
            Toast.makeText(activity, text, Toast.LENGTH_LONG).show()
        }
    }

}