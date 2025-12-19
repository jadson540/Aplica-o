package com.example.aplicao.ui.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(
    onBack: () -> Unit,
    viewModel: SettingsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val darkMode by viewModel.darkModeEnabled.collectAsState()
    val translationEnabled by viewModel.translationEnabled.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 32.dp,
                start = 16.dp,
                end = 16.dp,
                bottom = 16.dp
            )
    ) {

        Text(
            text = "Configurações",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Modo escuro")
            Switch(
                checked = darkMode,
                onCheckedChange = { viewModel.toggleDarkMode() }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Tradução automática")
            Switch(
                checked = translationEnabled,
                onCheckedChange = { viewModel.toggleTranslation() }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Divider()

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Sobre o aplicativo",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Aplicativo de dicionário desenvolvido para fins acadêmicos, utilizando APIs públicas e arquitetura MVVM."
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Voltar")
        }
    }
}
