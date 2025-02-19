package com.example.fretezon.data

import com.example.fretezon.models.Frete
import com.example.fretezon.models.Post
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {
   @GET("fretes/")
   //Response <Post>
   suspend fun getFrete(): List<Frete>

   @POST("/fretes/")
   suspend fun createFrete(
      @Body frete: Frete
   ): Response<Frete>
}