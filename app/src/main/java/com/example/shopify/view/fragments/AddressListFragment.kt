package com.example.shopify.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopify.R
import com.example.shopify.adapter.AddressListAdapter
import com.example.shopify.databinding.FragmentAddressListBinding
import com.example.shopify.interfaces.OnClickDeleteItem
import com.example.shopify.viewmodel.AddressViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddressListFragment : Fragment(), OnClickDeleteItem {
    private lateinit var binding: FragmentAddressListBinding
    private val addressViewModel: AddressViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddressListBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddAddress.setOnClickListener{
            findNavController().navigate(R.id.action_addressListFragment_to_addressFragment)
        }

        addressViewModel.getAllAddressess()
        addressViewModel.addressList.observe(viewLifecycleOwner) { result ->
            binding.rvAddress.layoutManager = LinearLayoutManager(requireContext())
            binding.rvAddress.adapter = AddressListAdapter(result, this)
        }


    }



    override fun onClickDeleteItem(id: Int) {
        addressViewModel.removeAddress(id)
        addressViewModel.getAllAddressess()
    }

}