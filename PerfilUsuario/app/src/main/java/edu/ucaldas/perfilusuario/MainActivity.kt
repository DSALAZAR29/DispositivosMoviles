package edu.ucaldas.perfilusuario

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.ucaldas.perfilusuario.ui.theme.PerfilUsuarioTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            PerfilUsuarioTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    PerfilScreen()
                }
            }
        }
    }
}

@Composable
fun PerfilScreen() {

    val logros = remember {
        mutableStateListOf("Primer commit")
    }

    var publicaciones by rememberSaveable {
        mutableStateOf(0)
    }

    var nombre by rememberSaveable {
        mutableStateOf("Ana Gómez")
    }

    var editando by rememberSaveable {
        mutableStateOf(false)
    }

    var borrador by rememberSaveable {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .size(96.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "AG",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = nombre.ifBlank { "Usuario sin nombre" },
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "Ingeniería de Sistemas",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                borrador = nombre
                editando = !editando
            }
        ) {
            Text(
                text = if (editando) {
                    "Cancelar"
                } else {
                    "Editar perfil"
                }
            )
        }

        AnimatedVisibility(
            visible = editando
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            ) {

                OutlinedTextField(
                    value = borrador,
                    onValueChange = {
                        borrador = it
                    },
                    label = {
                        Text("Nombre")
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedButton(
                    onClick = {
                        nombre = borrador.ifBlank {
                            "Usuario sin nombre"
                        }

                        editando = false
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Guardar")
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Publicaciones",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = publicaciones.toString(),
                    style = MaterialTheme.typography.displaySmall,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Button(
                        onClick = {
                            if (publicaciones > 0) {
                                publicaciones--
                            }
                        },
                        enabled = publicaciones > 0
                    ) {
                        Text("-")
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Button(
                        onClick = {
                            publicaciones++
                        }
                    ) {
                        Text("+")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Logros",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        logros.forEach { logro ->
            Text(
                text = "• $logro",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                logros.add("Nuevo logro ${logros.size + 1}")

                Log.d(
                    "Taller5",
                    "Tamaño de la lista: ${logros.size}"
                )
            }
        ) {
            Text("Agregar logro")
        }
    }
}

@Preview(
    showBackground = true,
    name = "Perfil"
)
@Composable
fun PerfilScreenPreview() {
    PerfilUsuarioTheme {
        PerfilScreen()
    }
}