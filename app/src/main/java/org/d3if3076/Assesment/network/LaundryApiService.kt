package org.d3if3076.Assesment.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.d3if3076.Assesment.model.Laundry
import org.d3if3076.Assesment.model.OpStatus
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

private const val BASE_URL = "https://unspoken.my.id/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface LaundryApiService {
    @GET("api_reiko.php")
    suspend fun getLaundry(
        @Header("Authorization") userId: String
    ): List<Laundry>

    @Multipart
    @POST("api_reiko.php")
    suspend fun postLaundry(
        @Header("Authorization") userId: String,
        @Part("nama") nama: RequestBody,
        @Part("berat") berat: RequestBody,
        @Part("jenis") jenis: RequestBody,
        @Part image: MultipartBody.Part
    ): OpStatus

    @DELETE("api_reiko.php")
    suspend fun deleteLaundry(
        @Header("Authorization") userId: String,
        @Query("id") id: Int
    ): OpStatus
}

object LaundryApi {
    val service: LaundryApiService by lazy {
        retrofit.create(LaundryApiService::class.java)
    }

    fun getLaundryUrl(imageId: String): String {
        return "${BASE_URL}image.php?id=$imageId"
    }

    enum class ApiStatus { LOADING, SUCCESS, FAILED }
}
