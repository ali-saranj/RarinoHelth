package com.raino.helth

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import rarinohelth.composeapp.generated.resources.Res
import rarinohelth.composeapp.generated.resources.compose_multiplatform
import com.raino.helth.data.di.AppModule
import com.raino.helth.viewmodel.MainViewModel
import org.koin.core.context.startKoin
import org.koin.compose.viewmodel.koinViewModel



@Composable
fun MainScreen() {
    val viewModel = koinViewModel<MainViewModel>()
    val state by viewModel.state.collectAsState()

    state.error?.let {
        Text(it)
    }
    state.message?.let {
        Text(it)
    }

}
@Composable
@Preview
fun App() {
    initKoin()
    MaterialTheme {
        Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            MainScreen()
        }
    }
}

fun initKoin() {
    startKoin {
        modules(AppModule.provideModule())
    }
}
