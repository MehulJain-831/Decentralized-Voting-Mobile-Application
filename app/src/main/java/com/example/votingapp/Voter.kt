package com.example.votingapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.votingapp.databinding.FragmentVoterBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.BigInteger
import java.util.concurrent.TimeUnit


class Voter : Fragment() {

    private lateinit var binding: FragmentVoterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentVoterBinding>(inflater,R.layout.fragment_voter,container,false)

        binding.buttonAbout.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_voter_to_voterKnowMore)
        }
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

        //setters

        binding.buttonSetVote.setOnClickListener {
            val propID = binding.editTextSetVote.text.toString()
            if(propID == null){
                toast("Proposal Index is null!!! Please provide a proposal index to vote")
            }
            else {
                Vote(propID)
            }
        }


        return binding.root
    }

    //getter methods
    private fun GetProposalDesc(index: String) {
        CoroutineScope(Dispatchers.Main).launch(Dispatchers.IO) {

            val result: String
            result = try {
                val simpleVoter = SimpleVoting.load(CONTRACT_ADDRESS, web3j, credentials, getGasPrice(), getGasLimit())
                val simpleVoting = simpleVoter.getProposalDescription(index.toBigInteger()).sendAsync()
                "Description of proposal is " + simpleVoting.get()
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
                "Number of proposals are : " + simpleVoting.get().toString()
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
                "Winning Proposal Description is : " + simpleVoting.get().toString()
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
                "Winning Proposal ID is " + simpleVoting.get().toString()
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
    //setter methods
    private fun Vote(propID : String) {
        CoroutineScope(Dispatchers.Main).launch(Dispatchers.IO) {

            val result: String
            result = try {
                val simpleVoter = SimpleVoting.load(CONTRACT_ADDRESS, web3j, credentials, getGasPrice(), getGasLimit())
                val transactionReceipt = simpleVoter.vote(propID.toBigInteger()).sendAsync().get(3, TimeUnit.MINUTES)
                "Successfully Voted for the proposal. Gas used: " + transactionReceipt.gasUsed
            } catch (e: Exception) {
                "Error during transaction. Error: " + e.message
            }

            withContext(Dispatchers.Main) {
                Log.i("Set Vote","${result}")
                toast(result)
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