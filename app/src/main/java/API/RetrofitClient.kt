package API

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("/api/login")
    fun login(@Field("email") email: String, @Field("password") password: String): Call<String>
}