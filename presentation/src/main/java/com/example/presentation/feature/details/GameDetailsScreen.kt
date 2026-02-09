package com.example.presentation.feature.details

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.domain.entity.GameDetails
import com.example.presentation.R
import com.example.presentation.navigation.LocalNavController
import com.example.presentation.shared.component.ErrorContent
import com.example.presentation.shared.component.LoadingState
import com.example.presentation.utils.Listen
import org.koin.androidx.compose.koinViewModel

@Composable
fun GameDetailsScreen(
    viewModel: GameDetailsViewModel = koinViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val effect by viewModel.effect.collectAsStateWithLifecycle(initialValue = null)

    val navController = LocalNavController.current

    effect?.Listen { currentEffect ->
        when (currentEffect) {
            GameDetailsEffect.NavigateBack -> {
                navController.navigateUp()
            }
        }
    }

    when {
        state.isLoading -> {
            LoadingState(modifier = Modifier.fillMaxSize())
        }

        state.error != null -> {
            ErrorContent(
                error = state.error,
                onRetryClick = { viewModel.onClickRetry() }
            )
        }

        else -> {
            GameDetailsScreenContent(state, viewModel)
        }
    }
}

@Composable
fun GameDetailsScreenContent(
    state: GameDetailsUiState,
    listener: GameDetailsInteractionListener
) {
    state.gameDetails?.let { details ->

        Box(modifier = Modifier.fillMaxSize()) {

            AsyncImage(
                model = details.backgroundImageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .zIndex(-1f)
                    .height(320.dp),
                contentScale = ContentScale.Crop
            )

            AsyncImage(
                model = R.drawable.ic_back,
                contentDescription = "Back Icon",
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp)
                    .size(48.dp)
                    .align(Alignment.TopStart)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .border(1.dp, Color.LightGray, RoundedCornerShape(16.dp))
                    .clickable { listener.onClickBack() }
                    .padding(8.dp)
            )

            GameDetailsData(
                details = details, modifier = Modifier
                    .fillMaxSize()
                    .zIndex(1f)
                    .padding(top = 280.dp)
            )
        }
    }
}

@Composable
fun GameDetailsData(
    details: GameDetails,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(
                            topStart = 32.dp,
                            topEnd = 32.dp
                        )
                    )
                    .padding(20.dp)
            ) {

                Text(
                    text = details.name,
                    fontSize = 22.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    MetaDataCard(
                        content = "Release Date: ${details.releaseDate}",
                        backgroundColor = Color.Cyan,
                    )

                    MetaDataCard(
                        content = "Rating: ${details.rating}",
                        backgroundColor = Color.Magenta,
                    )
                }

                Spacer(Modifier.height(20.dp))

                Text(
                    text = "About",
                    fontSize = 20.sp,
                    color = Color.Black
                )

                Spacer(Modifier.height(10.dp))

                Text(
                    text = details.description,
                    fontSize = 16.sp,
                    lineHeight = 22.sp,
                    color = Color.DarkGray
                )
            }
        }
    }
}

@Composable
fun MetaDataCard(
    content: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Text(
        text = content,
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 16.dp, vertical = 8.dp),
        fontWeight = FontWeight.SemiBold
    )
}
