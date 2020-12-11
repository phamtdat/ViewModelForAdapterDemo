package com.example.viewmodelforadapterdemo

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_model.view.*

class ModelViewHolder(
    private val view: View,
    private val onItemClicked: (MyModel) -> Unit
) : RecyclerView.ViewHolder(view) {
    private var model: MyModel? = null
    fun setData(model: MyModel) {
        this.model = model
        view.cl_model_item_container?.setOnClickListener {
            // set up clickListener to forward the model (that this viewHolder holds) on click
            this.model?.let { safeModel -> onItemClicked(safeModel) }
        }
        val textToShow = "${model.id}: ${model.dialogCounter}"
        view.tv_model_name?.text = textToShow
    }
}
