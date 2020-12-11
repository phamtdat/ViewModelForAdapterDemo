package com.example.viewmodelforadapterdemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    private val myModels = listOf(
        MyModel("m_0", 0),
        MyModel("m_1", 0),
        MyModel("m_2", 0),
        MyModel("m_3", 0),
        MyModel("m_4", 0),
    ).map { model ->
        // map the list to key-value pair, to be able to convert it to MutableMap,
        // for easier access through id
        model.id to model
    }.toMap().toMutableMap()

    /** LiveData to deliver [myModels] and its updates to the activity */
    val myModelsLiveData = MutableLiveData<List<MyModel>>(
        myModels.values.toList()
    )

    fun updateModel(modelId: String) {
        val currentModel = myModels[modelId]
        currentModel?.let { safeModel ->
            // update counter of the model by 1
            myModels[modelId] = MyModel(safeModel.id, safeModel.dialogCounter + 1)
            // notify observers about new data
            myModelsLiveData.postValue(myModels.values.toList())
        }
    }
}
