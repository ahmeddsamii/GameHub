package com.example.presentation.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil3.compose.AsyncImage
import com.example.domain.entity.Game
import com.example.domain.entity.Genre
import com.example.presentation.shared.base.toErrorState
import com.example.presentation.shared.component.EmptyState
import com.example.presentation.shared.component.ErrorContent
import com.example.presentation.shared.component.LoadingState
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(viewModel: HomeScreenViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val pagedGames = state.filteredGames.collectAsLazyPagingItems()
    val pagedGenres = state.genres.collectAsLazyPagingItems()

    val gamesRefreshState = pagedGames.loadState.refresh
    val genresRefreshState = pagedGenres.loadState.refresh

    when {
        gamesRefreshState is LoadState.Error -> {
            ErrorContent(
                error = gamesRefreshState.toErrorState(),
                onRetryClick = viewModel::onClickRetry
            )
        }

        genresRefreshState is LoadState.Error -> {
            ErrorContent(
                error = genresRefreshState.toErrorState(),
                onRetryClick = viewModel::onClickRetry
            )
        }

        else -> {
            HomeScreenContent(
                pagedGames = pagedGames,
                pagedGenres = pagedGenres,
                interactionListener = viewModel
            )
        }
    }
}

@Composable
private fun HomeScreenContent(
    pagedGames: LazyPagingItems<Game>,
    pagedGenres: LazyPagingItems<Genre>,
    interactionListener: HomeInteractionListener
) {
    var selectedGenreId by remember { mutableStateOf<Int?>(null) }
    val gamesRefreshState = pagedGames.loadState.refresh

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {

        stickyHeader {
            SearchField(
                onSearch = interactionListener::onSearch,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White)
            )
        }

        item {
            GenreFilterRow(
                genres = pagedGenres,
                selectedGenreId = selectedGenreId,
                onGenreClick = { id, slug ->
                    selectedGenreId = if (selectedGenreId == id) null else id
                    interactionListener.onClickGenre(slug)
                }
            )
        }

        if (gamesRefreshState is LoadState.Loading) {
            item {
                LoadingState(modifier = Modifier.fillParentMaxSize())
            }
        } else {
            items(
                count = pagedGames.itemCount,
                key = pagedGames.itemKey { it.id }
            ) { index ->
                pagedGames[index]?.let { game ->
                    GameCard(
                        name = game.name,
                        imageUrl = game.imageUrl,
                        rating = game.rating,
                        onClick = { /* TODO: Handle game click */ },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        if (pagedGames.itemCount == 0 && gamesRefreshState is LoadState.NotLoading && gamesRefreshState.toErrorState() == null) {
            item{ EmptyState(modifier = Modifier.fillParentMaxSize()) }
        }
    }
}

@Composable
private fun SearchField(
    onSearch: (query: String) -> Unit,
    modifier: Modifier = Modifier
) {
    var query by remember { mutableStateOf("") }

    TextField(
        modifier = modifier,
        placeholder = { Text("Search For Games...") },
        value = query,
        onValueChange = {
            query = it
            onSearch(it)
        },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Composable
private fun GenreFilterRow(
    genres: LazyPagingItems<Genre>,
    selectedGenreId: Int?,
    onGenreClick: (id: Int, slug: String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier
            .height(56.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            count = genres.itemCount,
            key = genres.itemKey { it.id }
        ) { index ->
            genres[index]?.let { genre ->
                FilterChip(
                    selected = selectedGenreId == genre.id,
                    onClick = {
                        onGenreClick(genre.id, genre.slug)
                    },
                    label = { Text(genre.name) }
                )
            }
        }
    }
}

@Composable
private fun GameCard(
    name: String,
    imageUrl: String,
    rating: Double,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color.LightGray)
            .clickable(onClick = onClick)
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = "Game: $name",
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier.align(Alignment.CenterVertically),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "⭐ $rating",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}