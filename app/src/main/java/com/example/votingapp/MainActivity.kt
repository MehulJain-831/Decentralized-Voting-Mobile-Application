package com.example.votingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.votingapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.http.HttpService

const val MINIMUM_GAS_LIMIT = 21000
lateinit var PRIVATE_KEY_ROPSTEN : String
const val ROPSTEN_INFURA_URL = "https://ropsten.infura.io/v3/9329235a1a714b3e810e4ee4013887e4"
lateinit var CONTRACT_ADDRESS : String
var web3j: Web3j? = null
lateinit var credentials: Credentials


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        CoroutineScope(Dispatchers.Main).launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                toast("Initializing Web3j")
            }
            val result = initializeWeb3J()
            withContext(Dispatchers.Main) {
                toast(result)
            }
        }
    }
    private fun initializeWeb3J(): String {
        val infuraHttpService: HttpService
        val result: String
        result = try {
            infuraHttpService = HttpService(ROPSTEN_INFURA_URL)
            web3j = Web3j.build(infuraHttpService)
            "Success initializing web3j/infura"
        } catch (e: Exception) {
            val exception = e.toString()
            "Error initializing web3j/infura. Error: $exception"
        }

        return result
    }

    private fun toast(text: String) {

        Toast.makeText(this, text, Toast.LENGTH_LONG).show()

    }
}