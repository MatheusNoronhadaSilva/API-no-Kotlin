package br.senai.sp.jandira.rickandmorty

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.senai.sp.jandira.rickandmorty.service.RetrofitFactory
import br.senai.sp.jandira.rickandmorty.ui.theme.RickAndMortyTheme
import br.senai.sp.jandira.rickandmorty.model.Character
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RickAndMortyTheme {
                ListAllCharacters()
            }
        }
    }
}

@Composable
fun ListAllCharacters(modifier: Modifier = Modifier) {

    val characterList = remember {
        mutableStateOf(listOf<Character>())
    }
    //List<Char>

    val character = remember {
        mutableStateOf(Character())
    }

    val id = remember {
        mutableStateOf("")
    }

    //Efetuar a chamada para a API


    Column {

        OutlinedTextField(
            value = id.value,
            onValueChange = {id.value = it}
        )

        Button(onClick = {

            //Efetuar a chamada para a API
            val callCharacterById = RetrofitFactory()
            .getCharacterService()
            .getCharacterById(id.value.toInt())


            //p0: envio
            //p1: resposta


            callCharacterById.enqueue(object : Callback<Character> {
                override fun onResponse(p0: Call<Character>, p1: Response<Character>) {
                    character.value = p1.body()!!
                    Log.i(
                        "RICK_MORTY",
                        "${character.value.name} - ${character.value.origin!!.name}"
                    )
                }

                override fun onFailure(p0: Call<Character>, p1: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }) {
            Text(text = "Buscar")
        }

        Text(text = character.value.name)
        Text(text = character.value.origin?.name ?: "")
        Text(text = character.value.species)
        Text(text = character.value.status)
        Text(text = character.value.gender)

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RickAndMortyTheme {
        Greeting("Android")
    }
}