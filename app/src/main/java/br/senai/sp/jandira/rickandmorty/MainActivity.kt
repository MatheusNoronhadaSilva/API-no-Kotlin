package br.senai.sp.jandira.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.senai.sp.jandira.rickandmorty.screens.CharacterDetail
import br.senai.sp.jandira.rickandmorty.screens.CharacterList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent{
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                val controleDeNavegacao = rememberNavController()
                NavHost(navController = controleDeNavegacao, startDestination = "personagens") {
                    composable("personagens") { CharacterList(controleDeNavegacao) }
                    composable(
                        route = "detalhePersonagem/{id}",
                        arguments = listOf(navArgument("id") { type = NavType.IntType })
                    ) { backStackEntry ->
                        CharacterDetail(
                            controleDeNavegacao,
                            backStackEntry.arguments?.getInt("id") ?: 0
                        )
                    }
                }
            }
            }
        }
    }