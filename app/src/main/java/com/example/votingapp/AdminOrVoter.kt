package com.example.votingapp

import android.graphics.DiscretePathEffect
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.votingapp.databinding.FragmentAdminOrVoterBinding
import kotlinx.coroutines.*
import org.web3j.abi.datatypes.Bool
import org.web3j.crypto.Credentials
import java.math.BigInteger
import java.net.Inet4Address


class AdminOrVoter : Fragment() {
    private lateinit var binding: FragmentAdminOrVoterBinding
    private lateinit var asAdminOrVoter: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentAdminOrVoterBinding>(inflater,R.layout.fragment_admin_or_voter,container,false)

        binding.buttonAsAdmin.setOnClickListener {
            asAdmin()
        }

        binding.buttonAsVoter.setOnClickListener {
            asVoter()
        }
        binding.buttonGo.setOnClickListener {
            if(asAdminOrVoter == "voter"){
                CoroutineScope(Dispatchers.Main).launch(Dispatchers.IO) {
                    var res = checkIsRegisteredVoter(binding.editTextAdminOrVoter.text.toString())
                    withContext(Dispatchers.Main) {
                        if (res) {
                            toast("Account Verified!! You are a registered Voter!!!")
                            view?.findNavController()?.navigate(R.id.action_adminOrVoter_to_voter)
                        } else {
                            toast("Account is not registered!! Please contact Admin to get registered!!!")
                        }
                    }
                }
            }
            else if(asAdminOrVoter == "admin"){
                CoroutineScope(Dispatchers.Main).launch(Dispatchers.IO) {
                    var res = checkIsAdmin(binding.editTextAdminOrVoter.text.toString())
                    //navigate to admin
                    withContext(Dispatchers.Main) {
                        if (res) {
                            toast("Account Verified!! You are the Administrator of the Contract!!!")
                            view?.findNavController()?.navigate(R.id.action_adminOrVoter_to_admin)
                        } else {
                            toast("Account is not registered!! You are not the Administrator!!!")
                        }
                    }
                }
            }
        }
        return binding.root
    }

    private fun asVoter() {
        asAdminOrVoter = "voter"
        binding.editTextAdminOrVoter.visibility = View.VISIBLE
        binding.textViewAdminOrVoter.visibility = View.VISIBLE
        binding.textViewAdminOrVoter.text = "Please enter Voter Account Address:"
        binding.editTextAdminOrVoter.hint = "Voter Account Address"
        binding.editTextAdminOrVoter.requestFocus()
        binding.buttonGo.visibility = View.VISIBLE
    }

    private fun asAdmin() {
        asAdminOrVoter = "admin"
        binding.editTextAdminOrVoter.visibility = View.VISIBLE
        binding.textViewAdminOrVoter.visibility = View.VISIBLE
        binding.textViewAdminOrVoter.text = "Please enter Admin Account Address"
        binding.editTextAdminOrVoter.hint = "Admin Account Address"
        binding.editTextAdminOrVoter.requestFocus()
        binding.buttonGo.visibility = View.VISIBLE
    }
    private suspend fun checkIsAdmin(inputAddress: String): Boolean {
        var res: Boolean = false

        var job: Job = CoroutineScope(Dispatchers.Main).launch(Dispatchers.IO) {

                val result:String
                result = try {
                    val simpleVoter = SimpleVoting.load(
                        CONTRACT_ADDRESS,
                        web3j,
                        credentials,
                        getGasPrice(),
                        getGasLimit()
                    )
                    val simpleVoting = simpleVoter.isAdministrator(inputAddress).sendAsync()
                    res = true
                    simpleVoting.get().toString()

                } catch (e: Exception) {
                     "Error reading the smart contract. Error: " + e.message

                }
                withContext(Dispatchers.Main){
                    Log.i("AV checkisAdmin","${result}")
                }


        }
        job.join()
        return res
    }

    private suspend fun checkIsRegisteredVoter(inputAddress: String): Boolean {
        var res: Boolean = false
        val job: Job = CoroutineScope(Dispatchers.Main).launch(Dispatchers.IO) {
            var result: String
            try {
                val simpleVoter = SimpleVoting.load(CONTRACT_ADDRESS, web3j, credentials, getGasPrice(), getGasLimit())
                val simpleVoting = simpleVoter.isRegisteredVoter(inputAddress).sendAsync()
                res = true
                result = simpleVoting.get().toString()
            } catch (e: Exception) {
                result = "Error reading the smart contract. Error: " + e.message
            }

            withContext(Dispatchers.Main) {
                Log.i("AV check is reg voter","${result}")
            }
        }
        job.join()
        return res
    }

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