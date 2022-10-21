package com.example.beerapp.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.appWidgetBackground
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.appwidget.lazy.items
import androidx.glance.background
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxHeight
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.layout.width
import androidx.glance.text.Text
import com.example.beerapp.R
import com.example.beerapp.model.Beer
import com.example.beerapp.ui.theme.Purple200

class BeerSelectionWidget : GlanceAppWidget() {

    @Composable
    override fun Content() {
        WidgetContent()
    }

    @Composable
    private fun WidgetContent() {
        Box(
            modifier = GlanceModifier
                .fillMaxSize()
                .cornerRadius(12.dp)
                .appWidgetBackground()
                .background(color = Purple200)
                .padding(8.dp)
        ) {
            BeerList(makeDummyBeerList())
        }
    }

    @Composable
    private fun BeerList(beerList: List<Beer>) {
        LazyColumn {
            items(items = beerList) { beer ->
                BeerItem(beer)
            }
        }
    }

    @Composable
    private fun BeerItem(beer: Beer) {
        Column(modifier = GlanceModifier.width(100.dp).fillMaxHeight()) {
            Text(text = beer.name)
            Image(
                provider = ImageProvider(R.drawable.ic_info),
                contentDescription = null
            )
        }
    }

    @Preview
    @Composable
    private fun WidgetContentPreview() {
        WidgetContent()
    }
}