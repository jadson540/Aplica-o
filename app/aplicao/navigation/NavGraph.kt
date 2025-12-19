package com.example.aplicao.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.aplicao.data.api.PostApi
import com.example.aplicao.data.api.TranslationApi
import com.example.aplicao.data.repository.PostRepository
import com.example.aplicao.ui.detail.PostDetailScreen
import com.example.aplicao.ui.detail.PostDetailViewModel
import com.example.aplicao.ui.list.PostListScreen
import com.example.aplicao.ui.list.PostListViewModel
import com.example.aplicao.ui.settings.SettingsScreen
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun NavGraph(navController: NavHostController) {

    val dictionaryRetrofit = Retrofit.Builder()
        .baseUrl("https://api.dictionaryapi.dev/api/v2/entries/en/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val translationRetrofit = Retrofit.Builder()
        .baseUrl("https://api.mymemory.translated.net/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val repository = PostRepository(
        dictionaryRetrofit.create(PostApi::class.java),
        translationRetrofit.create(TranslationApi::class.java)
    )

    val listViewModel = remember { PostListViewModel(repository) }
    val detailViewModel = remember { PostDetailViewModel(repository) }

    NavHost(navController, startDestination = "list") {

        composable("list") {
            PostListScreen(
                viewModel = listViewModel,
                onPostClick = { id ->
                    navController.navigate("detail/$id")
                },
                onSettingsClick = {
                    navController.navigate("settings")
                }
            )
        }

        composable(
            "detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            val id = it.arguments?.getInt("id") ?: 0

            PostDetailScreen(
                postId = id,
                viewModel = detailViewModel,
                onBack = { navController.popBackStack() }
            )
        }

        composable("settings") {
            SettingsScreen(
                onBack = { navController.popBackStack() }
            )
        }

    }
}