package com.omran.pagingdataapi.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.omran.pagingdataapi.databinding.ItemPassengerBinding
import com.omran.pagingdataapi.model.Passenger
import com.omran.pagingdataapi.utils.Utils
import com.omran.pagingdataapi.utils.Utils.Companion.loadImage
import kotlin.properties.Delegates

class PassengersAdapter  :
    PagingDataAdapter<Passenger, PassengersAdapter.PassengersViewHolder>(PassengersComparator) {




    class PassengersViewHolder(private val binding: ItemPassengerBinding) : RecyclerView.ViewHolder(binding.root) {



        @SuppressLint("SetTextI18n")
            fun bindPassenger(item: Passenger) = with(binding) {


                Log.d("///////  adapter", "onViewCreated:${item.name} ")

                imageViewAirlinesLogo.loadImage(item.airline[itemId.toInt()+1].logo)
              textViewHeadquarters.text = item.airline[itemId.toInt()+1].head_quaters
                textViewNameWithTrips.text = "${item.name}, ${item.trips} Trips"
            }


    }

    override fun onBindViewHolder(holder: PassengersViewHolder, position: Int) {


        val item = getItem(position)
        item?.let { holder.bindPassenger(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PassengersViewHolder {
        return PassengersViewHolder(
            ItemPassengerBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }


    object PassengersComparator : DiffUtil.ItemCallback<Passenger>() {
        override fun areItemsTheSame(oldItem: Passenger, newItem: Passenger): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(oldItem: Passenger, newItem: Passenger): Boolean {
            return oldItem == newItem
        }

    }
}

