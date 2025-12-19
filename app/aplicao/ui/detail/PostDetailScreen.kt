package com.example.aplicao.ui.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aplicao.data.model.Post
import kotlinx.coroutines.flow.collectLatest

@Composable
fun PostDetailScreen(
    postId: Int? = null,
    word: String? = null,
    viewModel: PostDetailViewModel,
    onBack: () -> Unit
) {
    val postState = remember { mutableStateOf<Post?>(null) }

    LaunchedEffect(postId, word) {
        when {
            postId != null -> viewModel.loadById(postId)
            word != null -> viewModel.loadByWord(word)
        }

        viewModel.post.collectLatest { post ->
            postState.value = post
        }
}

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        postState.value?.let { post ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = post.title,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0D47A1)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Inglês → Português",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Divider()
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Definição",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = post.body,
                        style = MaterialTheme.typography.bodyLarge,
                        lineHeight = 22.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Definição obtida através de API pública de dicionário.",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = onBack,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Voltar")
                    }
                }
            }
        }
    }
}
