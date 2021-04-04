package kr.co.jinsu.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kr.co.jinsu.shoestore.databinding.FragmentInstructionBinding

class InstructionFragment: Fragment() {

    private lateinit var binding: FragmentInstructionBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_instruction, container, false)
        binding.buttonNextInstruction.setOnClickListener {
            findNavController().navigate(InstructionFragmentDirections.actionInstructionFragmentToShoeLIstFragment())
        }
        return binding.root
    }
}