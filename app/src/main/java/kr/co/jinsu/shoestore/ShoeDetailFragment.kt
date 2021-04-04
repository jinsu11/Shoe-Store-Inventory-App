package kr.co.jinsu.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import kr.co.jinsu.shoestore.databinding.FragmentShoeDetailBinding
import kr.co.jinsu.shoestore.models.Shoe

class ShoeDetailFragment: Fragment() {

    private lateinit var binding: FragmentShoeDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)

        binding.buttonSave.setOnClickListener { v: View ->
            /* require all informations but description */
            if(binding.editShoeName.text.isBlank())
                Toast.makeText(requireContext(), "Enter the shoe name", Toast.LENGTH_SHORT).show()
            else if(binding.editShoeCompany.text.isBlank())
                Toast.makeText(requireContext(), "Enter the company name", Toast.LENGTH_SHORT).show()
            else if(binding.editShoeSize.text.isBlank())
                Toast.makeText(requireContext(), "Enter the shoe size", Toast.LENGTH_SHORT).show()
            else{
                val mainActivity: MainActivity = requireActivity() as MainActivity
                val newShoe = Shoe(binding.editShoeName.text.toString(),
                        binding.editShoeCompany.text.toString(),
                        binding.editShoeSize.text.toString(),
                        binding.editShoeDescription.text.toString())
                mainActivity.viewModel.addShoe(newShoe)
                v.findNavController().navigateUp()
            }
        }
        binding.buttonCancel.setOnClickListener { v: View -> v.findNavController().navigateUp() }

        return binding.root
    }
}