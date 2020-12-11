package com.example.viewmodelforadapterdemo

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView

class ModelsAdapter : RecyclerView.Adapter<ModelViewHolder>() {
    // property to hold the data incoming from viewModel
    private var data: List<MyModel>? = null

    // LiveData to deliver the clicked model to the observers (in our case the activity)
    val itemClicked = MutableLiveData<MyModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        // create UI view for each of our models
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_model, parent, false)
        return ModelViewHolder(
            view = view,
            onItemClicked = { modelId ->
                // deliver the clicked model via [itemClicked] to observers
                itemClicked.postValue(modelId)
            }
        )
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        data?.get(position)?.let { safeModel ->
            // tell the viewHolder to fill in correct data to the view
            holder.setData(safeModel)
        }
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    fun setData(newData: List<MyModel>?) {
        Log.d("AAAA", "got data $newData")
        data = newData ?: emptyList()
        notifyDataSetChanged()
    }
}
