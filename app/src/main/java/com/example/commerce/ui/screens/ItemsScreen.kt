package com.example.commerce.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
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
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.commerce.R
import com.example.commerce.data.domain.ItemDomain
import com.example.commerce.domain.UiState
import com.example.commerce.viewmodel.ItemsViewModel


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
            LazyColumn(
                modifier = modifier.fillMaxSize()
            ) {
                items(itemState.response) {
                    ProductItem(it)
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
fun ImgItem(imageRes: String?, imgDescription: String) {
    var imageLoadResult by remember {
        mutableStateOf<Result<Painter>?>(null)
    }
    val painter = rememberAsyncImagePainter(
        model = imageRes,
        onSuccess = {
            imageLoadResult =
                if (it.painter.intrinsicSize.width > 1 && it.painter.intrinsicSize.height > 1) {
                    Result.success(it.painter)
                } else {
                    Result.failure(Exception("Invalid image size"))
                }
        },
        onError = {
            it.result.throwable.printStackTrace()
            imageLoadResult = Result.failure(it.result.throwable)
        }
    )

    val result = imageLoadResult
    if (result != null) {
        Image(
            painter = if (result.isSuccess) painter else {
                painterResource(R.drawable.baseline_broken_image_24)
            },
            contentDescription = imgDescription,
            modifier = Modifier
                .width(100.dp)
                .height(120.dp)
                .padding(12.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Fit
        )
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
                shape = RoundedCornerShape(14.dp)
            ),
        shape = RoundedCornerShape(14.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .padding(end = 12.dp)
                    .border(
                        width = 0.5.dp,
                        color = Color(0xFFDCDCDC),
                        shape = RoundedCornerShape(12.dp)
                    )
            ) {
                ImgItem("https://www.we-areunited.com/assets/united-commerce/images/items/"+item.image_path, item.nombre.toString())
            }
            Column(
                modifier = Modifier
                    .padding(top = 8.dp, end = 8.dp, bottom = 8.dp)
            ) {
                TextItem("Id Item: ${item.nombre}", fontWeight = FontWeight.SemiBold)
                val CustomGray = Color(0xFF4F4F4F) // Converted to hex
                TextItem(
                    "Description: ${item.descripcion .toString()}",
                    textSize = 12.sp,
                    textColor = CustomGray,
                )
                val CustomGreen = Color(0xFF08850E)
                TextItem(
                    "$${item.costo}", textColor = CustomGreen, fontWeight = FontWeight.Bold
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
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