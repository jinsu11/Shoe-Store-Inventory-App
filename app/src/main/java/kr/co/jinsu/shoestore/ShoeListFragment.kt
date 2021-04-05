package kr.co.jinsu.shoestore

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kr.co.jinsu.shoestore.databinding.FragmentShoeListBinding
import kr.co.jinsu.shoestore.databinding.ItemShoeBinding
import timber.log.Timber

class ShoeListFragment: Fragment() {

    private lateinit var binding: FragmentShoeListBinding
    private lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.i("ShoeListFragment", "onCreateView 호출")
        mainActivity = requireActivity() as MainActivity
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)
        binding.floatingActionButton.setOnClickListener { v: View ->
            v.findNavController().navigate(ShoeListFragmentDirections.actionNavigationShoeListToNavigationShoeDetail()) }

        mainActivity.viewModel.shoeList.observe(viewLifecycleOwner, { newShoeList ->
            binding.containerShoes.removeAllViews()
            for (shoe in newShoeList) {
                val itemBinding: ItemShoeBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_shoe, binding.containerShoes, false)
                itemBinding.shoe = shoe
                binding.containerShoes.addView(itemBinding.root)
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }


}