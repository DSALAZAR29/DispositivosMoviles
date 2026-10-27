package edu.ucaldas.parcial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import edu.ucaldas.parcial.ui.theme.ParcialTheme

// Paso 1.1: Modelo de datos con los campos requeridos y el bando para la pantalla inicial
data class Personaje(
    val id: Int,
    val titulo: String,
    val categoria: String,
    val descripcionCorta: String,
    val descripcionLarga: String,
    val bando: String
)

// Paso 1.2: Lista de 12 elementos con 3 categorías
val listaPersonajes = listOf(
    Personaje(1, "Spider-Man", "Marvel", "El trepamuros", "Peter Parker fue mordido por una araña radiactiva...", "Heroe"),
    Personaje(2, "Batman", "DC", "El caballero de la noche", "Bruce Wayne protege Ciudad Gótica usando su intelecto...", "Heroe"),
    Personaje(3, "Invincible", "Image", "Mark Grayson", "Hijo de Omni-Man, descubre sus poderes en la adolescencia...", "Heroe"),
    Personaje(4, "Wonder Woman", "DC", "Princesa Amazona", "Diana de Themyscira, guerrera inmortal...", "Heroe"),
    Personaje(5, "Iron Man", "Marvel", "Genio millonario", "Tony Stark construye una armadura tras ser secuestrado...", "Heroe"),
    Personaje(6, "Spawn", "Image", "Hellspawn", "Al Simmons regresa de la muerte con poderes demoníacos...", "Heroe"),
    Personaje(7, "Joker", "DC", "Príncipe payaso", "Archienemigo de Batman, un genio criminal impredecible...", "Villano"),
    Personaje(8, "Thanos", "Marvel", "El Titán Loco", "Busca recolectar las gemas del infinito para equilibrar el universo...", "Villano"),
    Personaje(9, "Lex Luthor", "DC", "Mente maestra", "Multimillonario humano que odia a Superman...", "Villano"),
    Personaje(10, "Magneto", "Marvel", "Maestro del magnetismo", "Mutante que cree en la superioridad de su especie...", "Villano"),
    Personaje(11, "Omni-Man", "Image", "Conquistador Viltrumita", "Padre de Invincible con una agenda oculta...", "Villano"),
    Personaje(12, "Green Goblin", "Marvel", "Duende Verde", "Norman Osborn enloquecido por su propio suero...", "Villano")
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ParcialTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Llamamos a nuestra navegación dentro del Scaffold
                    Box(modifier = Modifier.padding(innerPadding)) {
                        AppNavigation()
                    }
                }
            }
        }
    }
}

// Pantalla inicial extra solicitada por el profesor
@Composable
fun InicioScreen(onNavigate: (String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Universo de Personajes",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth().height(120.dp).clickable { onNavigate("Heroe") }
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Text("SUPERHÉROES", style = MaterialTheme.typography.titleLarge)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth().height(120.dp).clickable { onNavigate("Villano") }
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Text("SUPERVILLANOS", style = MaterialTheme.typography.titleLarge)
            }
        }
    }
}

// Paso 1.3 y 1.4: Lista con LazyColumn, campo de búsqueda y pulido visual (Íconos)[cite: 1]
@Composable
fun ListaScreen(elementos: List<Personaje>, onElementoClick: (Int) -> Unit) {
    var query by remember { mutableStateOf("") }

    val filtrados = remember(query, elementos) {
        if (query.isBlank()) elementos
        else elementos.filter { it.titulo.contains(query, ignoreCase = true) }
    }

    Column {
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Buscar personaje") },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )
        LazyColumn {
            items(filtrados, key = { it.id }) { personaje ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable { onElementoClick(personaje.id) }
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Ícono agregado para el pulido visual
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Ícono de personaje",
                            modifier = Modifier.size(48.dp).padding(end = 16.dp),
                            tint = if (personaje.bando == "Heroe") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                        )
                        Column {
                            Text(personaje.titulo, style = MaterialTheme.typography.titleMedium)
                            Text(personaje.descripcionCorta, style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        }
    }
}

// Paso 2.4 y Parte 5: Pantalla de Detalle con pulido visual (Ícono grande)[cite: 1]
@Composable
fun DetalleScreen(personaje: Personaje, onBack: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Ícono agregado para el pulido visual
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = "Ícono gigante de personaje",
            modifier = Modifier.size(120.dp).padding(bottom = 16.dp),
            tint = if (personaje.bando == "Heroe") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
        )
        Text(personaje.titulo, style = MaterialTheme.typography.headlineMedium)
        Text("Categoría: ${personaje.categoria} | Bando: ${personaje.bando}", style = MaterialTheme.typography.labelLarge)
        Spacer(Modifier.height(16.dp))
        Text(personaje.descripcionLarga, style = MaterialTheme.typography.bodyLarge)
        Spacer(Modifier.height(24.dp))
        Button(onClick = onBack) { Text("Volver") }
    }
}

// Paso 2.2 y 3.3: Configuración de NavHost y corrección del bug[cite: 1]
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "inicio") {

        composable("inicio") {
            InicioScreen(onNavigate = { bando ->
                navController.navigate("lista/$bando")
            })
        }

        composable(
            route = "lista/{bando}",
            arguments = listOf(navArgument("bando") { type = NavType.StringType })
        ) { backStackEntry ->
            val bando = backStackEntry.arguments?.getString("bando") ?: "Heroe"
            val personajesFiltrados = listaPersonajes.filter { it.bando == bando }

            ListaScreen(
                elementos = personajesFiltrados,
                onElementoClick = { id ->
                    // Corrección: launchSingleTop = true evita destinos duplicados[cite: 1]
                    navController.navigate("detalle/$id") {
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(
            route = "detalle/{personajeId}",
            arguments = listOf(navArgument("personajeId") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("personajeId") ?: 0
            val personaje = listaPersonajes.firstOrNull { it.id == id }

            if (personaje != null) {
                DetalleScreen(
                    personaje = personaje,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}