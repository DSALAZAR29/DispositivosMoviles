package edu.ucaldas.lazycolumn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import edu.ucaldas.lazycolumn.ui.theme.LazyColumnTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LazyColumnTheme {
                ListaNumeros()
            }
        }
    }
}

@androidx.compose.runtime.Composable
fun ListaNumeros() {

    val numeros = (1..50).toList()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(numeros) { numero ->
            Text(
                text = "Número $numero"
            )
        }
    }
}