package com.example.commerce.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.commerce.R
import com.example.commerce.data.domain.ItemDomain
import com.example.commerce.domain.UiState
import com.example.commerce.viewmodel.ItemsViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest


@Composable
fun ItemsScreen(
    viewModel: ItemsViewModel,
    modifier: Modifier
) {
    val itemState = viewModel.itemState.collectAsStateWithLifecycle().value
    when (itemState) {
        is UiState.ERROR -> {
            Log.e("NewsScreen", "${itemState.error.localizedMessage}")
        }

        UiState.LOADING -> {
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }

        is UiState.SUCCESS -> {
            CocktailListScreen(itemState.response)
        }
    }
}

@Composable
fun CocktailListScreen(response: List<ItemDomain>) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(top = 18.dp)
                .fillMaxSize()
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = "Inventory",
                    fontSize = 28.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Normal, // Regular weight
                )
                Row {
                    IconButton(
                        onClick = { }
                    ) {
                        Icon(
                            modifier = Modifier.size(40.dp),
                            painter = painterResource(R.drawable.ic_search_50dp),
                            contentDescription = "Search",
                            tint = Color.White
                        )
                    }
                    Box(
                        modifier = Modifier
                            .padding(end = 12.dp)
                            .size(48.dp)
                            .clickable { }
                            .border(
                                1.dp,
                                color = Color(0xFFDCDCDC),
                                CircleShape
                            ), // Add a circular border
                        contentAlignment = Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_search_50dp),
                            contentDescription = "Search Icon",
                            tint = Color.Black,
                            modifier = Modifier
                                .size(24.dp)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(Color.Black, shape = CircleShape)
                            .clickable { },
                        contentAlignment = Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_basket_48),
                            contentDescription = "Home Icon",
                            tint = Color.White, // Icon color
                            modifier = Modifier
                                .size(24.dp) // Set the icon size
                                .background(Color.Black, shape = CircleShape)
                        )
                    }
                }
            }

            Card(
                modifier = Modifier
                    .padding(start = 12.dp, end = 12.dp)
                    .fillMaxHeight(0.92f)
                    .border(
                        width = 0.5.dp,
                        color = Color(0xFFDCDCDC),
                        shape = RoundedCornerShape(14.dp)
                    ),
                shape = RoundedCornerShape(14.dp),
            ){
                LazyColumn(
                    modifier = Modifier
                        .background(Color(0xFFEFEFEF))
                        .fillMaxSize()
                ) {
                    items(response.size) {
                        ProductItem(response[it])
                    }
                }
            }
        }
    }
}



@Composable
fun ProductItem(item: ItemDomain) {
    Card(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp, top = 8.dp)
            .fillMaxWidth()
            .border(
                width = 0.5.dp,
                color = Color(0xFFDCDCDC),
                shape = RoundedCornerShape(14.dp),
            ),
        shape = RoundedCornerShape(14.dp),
    ) {
        Row(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .padding(12.dp)
                    .border(
                        width = 0.5.dp,
                        color = Color(0xFFDCDCDC),
                        shape = RoundedCornerShape(12.dp)
                    )
            ) {
                ItemImg(item)
            }
            Column(
                modifier = Modifier
            ) {
                TextItem("${item.nombre}", fontWeight = FontWeight.SemiBold)
                val CustomGray = Color(0xFF4F4F4F) // Converted to hex
                TextItem(
                    item.descripcion .toString(),
                    textSize = 12.sp,
                    textColor = CustomGray,
                )
                val CustomGreen = Color(0xFF08850E)
                TextItem(
                    "$${item.costo}", textColor = CustomGreen, fontWeight = FontWeight.Bold
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Spacer(modifier = Modifier.weight(1f)) // Push the IconButton to the end

                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color.Black, shape = CircleShape)
                            .clickable { },
                        contentAlignment = Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_add_50),
                            contentDescription = "Add Icon",
                            tint = Color.White,
                            modifier = Modifier
                                .size(18.dp)
                                .background(Color.Black, shape = CircleShape)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TextItem(
    textData: String,
    textSize: TextUnit = 14.sp,
    textColor: Color = Color.Black,
    fontWeight: FontWeight = FontWeight.Normal
) {
    Text(
        text = textData,
        fontSize = textSize,
        color = textColor,
        style = LocalTextStyle.current.merge(
            TextStyle(
                lineHeight = 1.4.em
            )
        ),
        fontWeight = fontWeight,
    )
}


@Composable
fun ItemImg(
    item: ItemDomain
){
    Card {
        AsyncImage(
            contentDescription = item.nombre,
            modifier = Modifier
                .width(100.dp)
                .height(120.dp)
                .background(Color.White),
            contentScale = ContentScale.Fit,
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://www.we-areunited.com/assets/united-commerce/images/items/${item.image_path}").error(R.drawable.baseline_broken_image_24).build()
        )
    }
}