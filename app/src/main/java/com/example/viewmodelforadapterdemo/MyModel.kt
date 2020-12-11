package com.example.viewmodelforadapterdemo

data class MyModel(
    // id needed for viewModel to identify the model
    val id: String,
    // number of times the dialog has been shown for this item
    val dialogCounter: Int
)
