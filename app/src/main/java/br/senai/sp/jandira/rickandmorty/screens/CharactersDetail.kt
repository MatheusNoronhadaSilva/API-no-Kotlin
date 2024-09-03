package br.senai.sp.jandira.rickandmorty.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import br.senai.sp.jandira.rickandmorty.model.Character
import br.senai.sp.jandira.rickandmorty.service.RetrofitFactory
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun CharacterDetail(controleDeNavegacao: NavHostController, i: Int) {

    val id = remember {
        mutableStateOf(0)
    }
    id.value = i

    //List<Char>

    var character = remember {
        mutableStateOf(Character())
    }

    //Efetuar a chamada para a API
    val callCharacter = RetrofitFactory()
        .getCharacterService().getCharacterById(id.value)

    callCharacter.enqueue(object : Callback<Character> {
        override fun onResponse(p0: Call<Character>, p1: Response<Character>) {
            character.value = p1.body()!!
        }

        override fun onFailure(p0: Call<Character>, p1: Throwable) {
            TODO("Not yet implemented")
        }
    })


    Column {

        AsyncImage(
            model = character.value.image,
            contentDescription = "",
        )
        Text(text = character.value.name)
        Text(text = character.value.origin?.name ?: "")
        Text(text = character.value.species)
        Text(text = character.value.status)
        Text(text = character.value.gender)

    }
}