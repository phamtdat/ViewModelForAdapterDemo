package com.example.viewmodelforadapterdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val viewModel: MainActivityViewModel by viewModels()
    private val adapter = ModelsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv_models?.adapter = adapter
        viewModel.myModelsLiveData.observe(this, { newData ->
            // update the adapter when data are updated
            adapter.setData(newData)
        })
        adapter.itemClicked.observe(this, { clickedModel ->
            // show the dialog when item is clicked
            showMyDialog(clickedModel)
        })
    }

    private fun showMyDialog(clickedModel: MyModel) {
        AlertDialog.Builder(this)
                .setMessage("item clicked: ${clickedModel.id}, count: ${clickedModel.dialogCounter}")
                .setPositiveButton("Increase count") { _, _ ->
                    // update model in viewModel on dialog close
                    viewModel.updateModel(clickedModel.id)
                }
                .setCancelable(false)
                .show()
    }
}
