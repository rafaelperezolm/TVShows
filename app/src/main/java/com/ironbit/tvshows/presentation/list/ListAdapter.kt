package com.ironbit.tvshows.presentation.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.ironbit.tvshows.R
import com.ironbit.tvshows.domain.detail.DetailItem
import com.ironbit.tvshows.framework.common.DEFAULT_404
import com.ironbit.tvshows.framework.common.utils.ClickListenerAdapter
import com.squareup.picasso.Picasso

class ListAdapter(
    private var dataSet: List<DetailItem>,
    private val clickListener: ClickListenerAdapter<DetailItem>
) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    // Provide a reference to the type of views that you are using
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val listRowContainer: ConstraintLayout
        val listRowImage: ImageView
        val listRowName: TextView
        val listRowNetworkName: TextView
        val listRowAirdate: TextView
        val listRowAirtime: TextView

        init {
            listRowContainer = view.findViewById(R.id.list_row_container)
            listRowImage = view.findViewById(R.id.list_row_image)
            listRowName = view.findViewById(R.id.list_row_name)
            listRowNetworkName = view.findViewById(R.id.list_row_network_name)
            listRowAirdate = view.findViewById(R.id.list_row_airdate)
            listRowAirtime = view.findViewById(R.id.list_row_airtime)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_row_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        Picasso.get().load(dataSet[position].imageMedium.ifEmpty { null })
            .into(viewHolder.listRowImage)
        viewHolder.listRowName.text = dataSet[position].name
        viewHolder.listRowNetworkName.text = dataSet[position].networkName
        viewHolder.listRowAirdate.text =
            dataSet[position].airDate.ifEmpty { dataSet[position].scheduleTimes }
        viewHolder.listRowAirtime.text = dataSet[position].airTime.ifEmpty {
            dataSet[position].scheduleDays.toString().replace("[", "").replace("]", "")
        }
        viewHolder.listRowContainer.setOnClickListener {
            clickListener.onItemClicked(position = position, item = dataSet[position])
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}