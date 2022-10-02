package com.example.beerapp.widget

import androidx.compose.runtime.Composable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.text.Text

class BeerSelectionWidget : GlanceAppWidget() {

    @Composable
    override fun Content() {
        Text(text = "Hello world!")
    }
}