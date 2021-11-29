package com.example.beerapp.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.beerapp.R
import com.example.beerapp.util.collectAsStateInLifecycle

@Composable
fun DetailScreen(
    beerId: String,
    onToolbarTitleFetched: (String) -> Unit,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val beerName by viewModel.selectedBeerName.collectAsStateInLifecycle()
    val beer by remember(beerId) { viewModel.getBeerDetailedInfo(beerId) }
        .collectAsStateInLifecycle()

    LaunchedEffect(beerName) {
        if (!beerName.isNullOrEmpty()) {
            onToolbarTitleFetched.invoke(beerName ?: "")
        }
    }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
            .verticalScroll(scrollState)
    ) {
        Text(text = stringResource(id = R.string.name_info, beer?.name ?: ""))
        Text(text = stringResource(id = R.string.description_info, beer?.description ?: ""))

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = stringResource(id = R.string.ibu_info, String.format("%.2f", beer?.ibu)))
        Text(text = stringResource(id = R.string.ph_info, String.format("%.2f", beer?.ph)))

        Spacer(modifier = Modifier.height(16.dp))

        // image
        Image(
            painter = rememberImagePainter(beer?.imageUrl),
            contentDescription = null,
            modifier = Modifier
                .height(180.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = stringResource(id = R.string.food_info))

        repeat(beer?.foodPairing?.size ?: 0) {
            Text(text = beer?.foodPairing?.get(it) ?: "", Modifier.padding(horizontal = 8.dp))
        }
    }
}
