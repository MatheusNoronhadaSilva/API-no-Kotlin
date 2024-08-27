package br.senai.sp.jandira.rickandmorty.service

import br.senai.sp.jandira.rickandmorty.model.Character
import retrofit2.Call
import br.senai.sp.jandira.rickandmorty.model.Result
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterService {

    //https://rickandmortyapi.com/api/character
    @GET("character")
    fun getAllCharacters(): Call<Result>

    @GET("character/{id}")
    fun getCharacterById(@Path("id") batata: Int): Call<Character>
}