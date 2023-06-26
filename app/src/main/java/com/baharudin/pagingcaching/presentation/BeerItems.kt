package com.baharudin.pagingcaching.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.PrimaryKey
import com.baharudin.pagingcaching.domain.Beer
import com.baharudin.pagingcaching.ui.theme.PagingCachingTheme

@Composable
fun BeerItems(
    beer : Beer,
    modifier: Modifier = Modifier
) {

}

@Preview
@Composable
fun BeerItemPreview(){
    PagingCachingTheme {
        BeerItems(
            beer = Beer(
                id = 1,
                name = "Vodka",
                tagline = "You good bro",
                firstBrewed = "08/04/2002",
                description = "This is goog beer if you sinners",
                imageUrl = null
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}