package com.example.beerapp.ui.home

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.beerapp.model.Beer
import com.example.beerapp.ui.theme.BeerAppTheme

@Composable
fun BeerItem(beer: Beer, onBeerClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .padding(all = 8.dp)
            .clickable {
                onBeerClick(beer.id)
            }
    ) {
        Image(
            painter = rememberImagePainter(beer.imageUrl),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(
                text = beer.name,
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = beer.description,
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun BeerItemPreview() {
    BeerAppTheme {
        BeerItem(
            Beer(
                "fakeId",
                "Name",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d1/MW-Icon-CheckMark.svg/240px-MW-Icon-CheckMark.svg.png"
            )
        ) {}
    }
}
