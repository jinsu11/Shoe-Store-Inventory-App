package kr.co.jinsu.shoestore

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import kr.co.jinsu.shoestore.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var callback: OnBackPressedCallback

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mainActivity = requireActivity() as MainActivity

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        binding.buttonSignUp.setOnClickListener {
            if(binding.editTextTextEmailAddress.text.isBlank()){
                Toast.makeText(requireContext(), "Enter an Email", Toast.LENGTH_SHORT).show()
            } else if(binding.editTextTextPassword.text.isBlank()){
                Toast.makeText(requireContext(), "Enter a Password", Toast.LENGTH_SHORT).show()
            } else {
                mainActivity.viewModel.email = binding.editTextTextEmailAddress.text.toString()
                mainActivity.viewModel.password = binding.editTextTextPassword.text.toString()
                Toast.makeText(requireContext(), "You successfully signed up!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonSignIn.setOnClickListener { v: View ->
            if(binding.editTextTextEmailAddress.text.isBlank()) {
                Toast.makeText(requireContext(), "Enter an Email", Toast.LENGTH_SHORT).show()
            } else if(binding.editTextTextPassword.text.isBlank()){
                Toast.makeText(requireContext(), "Enter a Password", Toast.LENGTH_SHORT).show()
            } else if(binding.editTextTextEmailAddress.text.toString() != mainActivity.viewModel.email){
                Toast.makeText(requireContext(), "Wrong Email", Toast.LENGTH_SHORT).show()
            } else if(binding.editTextTextPassword.text.toString() != mainActivity.viewModel.password){
                Toast.makeText(requireContext(), "Wrong Password", Toast.LENGTH_SHORT).show()
            } else {
                v.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
            }
        }

        return binding.root
    }

    private var backPressedTime: Long = 0
    private val FINISH_TIME = 2000

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                val tempTime: Long = System.currentTimeMillis()
                val intervalTime: Long = tempTime - backPressedTime

                if(intervalTime < FINISH_TIME){
                    requireActivity().finish()
                } else{
                    Toast.makeText(requireContext(), "Press back again to exit", Toast.LENGTH_SHORT).show()
                    backPressedTime = tempTime
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }
}