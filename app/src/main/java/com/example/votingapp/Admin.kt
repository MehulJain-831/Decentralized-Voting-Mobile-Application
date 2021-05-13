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
import java.util.concurrent.TimeUnit


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
            if(inputIndex.equals(null)){
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
            if(inputIndex.equals(null)){
                toast("Account Address is null!! Please provide an Account Address")
            }
            else{
                GetIsRegisteredVoter(inputIndex)
            }
        }

        //setters
        binding.buttonSetRegisterProposal.setOnClickListener {
            var propDesc = binding.editTextSetRegisterProposal.text.toString()
            if(propDesc.equals(null)){
                toast("Proposal Description should be non empty")
            }
            else{
                RegisterProposal(propDesc)
            }
        }
        binding.buttonSetRegisterVoter.setOnClickListener {
            var voterAdd = binding.editTextSetRegisterVoter.text.toString()
            if(voterAdd.equals(null)){
                toast("Voter Address is not provided that has to be registered!!")
            }
            else{
                RegisterVoter(voterAdd)
            }
        }
        binding.buttonSetStartProposalRegistration.setOnClickListener {
            StartProposalRegistration()
        }
        binding.buttonSetEndProposalRegistration.setOnClickListener {
            EndProposalRegistration()
        }
        binding.buttonSetStartVotingSession.setOnClickListener {
            StartVotingSession()
        }
        binding.buttonSetEndVotingSession.setOnClickListener {
            EndVotingSession()
        }
        binding.buttonSetTallyVotes.setOnClickListener {
            TallyVotes()
        }

        return binding.root
    }
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
                Log.i("Get", result)
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
                Log.i("GetProposalNumbers", result)
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
                Log.i("GetWinningProposalDesc", result)
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
                Log.i("GetWinningProposalID", result)
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
                Log.i("GetWorkFlowStatus", result)
//                binding.textViewGetWorkFlowStatus.visibility = View.VISIBLE
//                binding.textViewGetWorkFlowStatus.text = result
                val resultStr :String
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
                Log.i("GetIsAdmin", result)
                val resultStr = when (result.toString()) {

                    "true" -> "The Voter is Registered!!!"
                    "false" -> "The Voter is not Registered!!!"
                    else -> result.toString()
                }
                toast(resultStr)
            }
        }
    }

    //setter methods
    private fun RegisterProposal(propDesc : String) {
        CoroutineScope(Dispatchers.Main).launch(Dispatchers.IO) {

            val result: String
            result = try {
                val simpleVoter = SimpleVoting.load(CONTRACT_ADDRESS, web3j, credentials, getGasPrice(), getGasLimit())
                val transactionReceipt = simpleVoter.registerProposal(propDesc).sendAsync().get(3, TimeUnit.MINUTES)
                "Successfully added the proposal. Gas used: " + transactionReceipt.gasUsed
            } catch (e: Exception) {
                "Error during transaction. Error: " + e.message
            }

            withContext(Dispatchers.Main) {
                Log.i("Set Register Prop", result)
                toast(result)
            }
        }
    }

    private fun RegisterVoter(voterAdd: String) {
        CoroutineScope(Dispatchers.Main).launch(Dispatchers.IO) {

            val result: String
            result = try {
                val simpleVoter = SimpleVoting.load(CONTRACT_ADDRESS, web3j, credentials, getGasPrice(), getGasLimit())
                val transactionReceipt = simpleVoter.registerVoter(voterAdd).sendAsync().get(3, TimeUnit.MINUTES)
                "Successfully Registered the Voter. Gas used: " + transactionReceipt.gasUsed
            } catch (e: Exception) {
                "Error during transaction. Error: " + e.message
            }

            withContext(Dispatchers.Main) {
                Log.i("Set Register Voter", result)
                toast(result)
            }
        }
    }
    private fun StartProposalRegistration() {
        CoroutineScope(Dispatchers.Main).launch(Dispatchers.IO) {

            val result: String
            result = try {
                val simpleVoter = SimpleVoting.load(CONTRACT_ADDRESS, web3j, credentials, getGasPrice(), getGasLimit())
                val transactionReceipt = simpleVoter.startProposalsRegistration().sendAsync().get(3, TimeUnit.MINUTES)
                "Successfully Started Proposal Registration Phase. Gas used: " + transactionReceipt.gasUsed
            } catch (e: Exception) {
                "Error during transaction. Error: " + e.message
            }

            withContext(Dispatchers.Main) {
                Log.i("SetProposalReg Start", result)
                toast(result)
            }
        }
    }
    private fun EndProposalRegistration() {
        CoroutineScope(Dispatchers.Main).launch(Dispatchers.IO) {

            val result: String
            result = try {
                val simpleVoter = SimpleVoting.load(CONTRACT_ADDRESS, web3j, credentials, getGasPrice(), getGasLimit())
                val transactionReceipt = simpleVoter.endProposalsRegistration().sendAsync().get(3, TimeUnit.MINUTES)
                "Successfully Ended Proposal Registration Phase. Gas used: " + transactionReceipt.gasUsed
            } catch (e: Exception) {
                "Error during transaction. Error: " + e.message
            }

            withContext(Dispatchers.Main) {
                Log.i("SetProposalReg end", result)
                toast(result)
            }
        }
    }
    private fun StartVotingSession() {
        CoroutineScope(Dispatchers.Main).launch(Dispatchers.IO) {

            val result: String
            result = try {
                val simpleVoter = SimpleVoting.load(CONTRACT_ADDRESS, web3j, credentials, getGasPrice(), getGasLimit())
                val transactionReceipt = simpleVoter.startVotingSession().sendAsync().get(3, TimeUnit.MINUTES)
                "Successfully Started Voting Session Phase. Gas used: " + transactionReceipt.gasUsed
            } catch (e: Exception) {
                "Error during transaction. Error: " + e.message
            }

            withContext(Dispatchers.Main) {
                Log.i("SetVotingSession Start", result)
                toast(result)
            }
        }
    }
    private fun EndVotingSession() {
        CoroutineScope(Dispatchers.Main).launch(Dispatchers.IO) {

            val result: String
            result = try {
                val simpleVoter = SimpleVoting.load(CONTRACT_ADDRESS, web3j, credentials, getGasPrice(), getGasLimit())
                val transactionReceipt = simpleVoter.endVotingSession().sendAsync().get(3, TimeUnit.MINUTES)
                "Successfully Ended Voting Session Phase. Gas used: " + transactionReceipt.gasUsed
            } catch (e: Exception) {
                "Error during transaction. Error: " + e.message
            }

            withContext(Dispatchers.Main) {
                Log.i("SetVotingSession End", result)
                toast(result)
            }
        }
    }
    private fun TallyVotes() {
        CoroutineScope(Dispatchers.Main).launch(Dispatchers.IO) {

            val result: String
            result = try {
                val simpleVoter = SimpleVoting.load(CONTRACT_ADDRESS, web3j, credentials, getGasPrice(), getGasLimit())
                val transactionReceipt = simpleVoter.tallyVotes().sendAsync().get(3, TimeUnit.MINUTES)
                "Successfully tallied the votes. Gas used: " + transactionReceipt.gasUsed
            } catch (e: Exception) {
                "Error during transaction. Error: " + e.message
            }

            withContext(Dispatchers.Main) {
                Log.i("Set Tally votes", result)
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