package com.example.beerapp.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.beerapp.R
import com.example.beerapp.util.collectAsStateInLifecycle

@Composable
fun DetailScreen(
    beerId: String,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val beer by remember(beerId) { viewModel.getBeer(beerId) }.collectAsStateInLifecycle()

    Column {
        Text(text = stringResource(id = R.string.name_info, beer?.name ?: ""))
        Text(text = stringResource(id = R.string.description_info, beer?.description ?: ""))
    }
}
