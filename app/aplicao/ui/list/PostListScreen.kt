package com.example.aplicao.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PostListScreen(
    viewModel: PostListViewModel,
    onPostClick: (Int) -> Unit,
    onSettingsClick: () -> Unit
) {
    val state = viewModel.uiState.collectAsState().value
    val searchResult = viewModel.searchResult.collectAsState().value
    var searchText by remember { mutableStateOf("") }

    LazyColumn(modifier = Modifier.fillMaxSize()) {


        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF0D47A1))
                    .padding(
                        top = 45.dp,
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 16.dp
                    )
            ) {
                Text(
                    text = "Dicionário Interativo",
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "Consulta de definições em inglês com tradução",
                    color = Color.White.copy(alpha = 0.9f),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }


        item {
            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                label = { Text("Pesquisar palavra") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            )
        }


        item {
            Button(
                onClick = {
                    if (searchText.isNotBlank()) {
                        viewModel.searchWord(searchText)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            ) {
                Text("Buscar")
            }
        }

        searchResult?.let { post ->
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {

                        Text(
                            text = post.title,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(text = post.body)

                        Spacer(modifier = Modifier.height(12.dp))

                        OutlinedButton(
                            onClick = {
                                viewModel.clearSearch()
                                searchText = ""
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Limpar pesquisa")
                        }
                    }
                }
            }
        }

        if (state is UiState.Success) {
            items(state.posts) { post ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                        .clickable { onPostClick(post.id) }
                ) {
                    Text(
                        text = post.title,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }

        item {
            Button(
                onClick = onSettingsClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Text("Configurações")
            }
        }
    }
}
