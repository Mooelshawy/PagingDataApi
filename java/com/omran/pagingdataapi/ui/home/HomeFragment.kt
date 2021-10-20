package com.omran.pagingdataapi.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.omran.pagingdataapi.R
import com.omran.pagingdataapi.adapter.MyClickListener
import com.omran.pagingdataapi.adapter.PassengersAdapter
import com.omran.pagingdataapi.databinding.FragmentHomeBinding
import com.omran.pagingdataapi.extras.App
import com.omran.pagingdataapi.pageing.PassengersLoadStateAdapter
import com.omran.pagingdataapi.remote.ApiServices
import com.omran.pagingdataapi.remote.Builder
import kotlinx.coroutines.flow.collectLatest

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    )= FragmentHomeBinding.inflate(inflater, container, false).also {
        binding = it
    }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = PassengersViewModelFactory()
      homeViewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)

        val passengersAdapter = PassengersAdapter()
        binding.mainRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.mainRecycler.setHasFixedSize(true)

        binding.mainRecycler.adapter = passengersAdapter

        binding.mainRecycler.adapter = passengersAdapter.withLoadStateHeaderAndFooter(
            header = PassengersLoadStateAdapter { passengersAdapter.retry() },
            footer = PassengersLoadStateAdapter { passengersAdapter.retry() }
        )


        lifecycleScope.launchWhenCreated {

            //flow collector
            homeViewModel.getListData().collectLatest { pagedData ->
                passengersAdapter.submitData(pagedData)
            }
        }

        ItemClicked()
    }

    private fun ItemClicked(){
        binding.mainRecycler.addOnItemTouchListener(
            MyClickListener(
                App.instance(),
            binding.mainRecycler, object : MyClickListener.OnItemClickListener{
                override fun onItemClick(view: View?, position: Int) {
                    view!!.findNavController().navigate(R.id.action_nav_home_to_detailsFragmentsAirline)
                }

                override fun onLongItemClick(view: View?, position: Int) {
                   Toast.makeText(App.instance(),"not yet",Toast.LENGTH_SHORT).show()
                }
            })
        )
    }

}