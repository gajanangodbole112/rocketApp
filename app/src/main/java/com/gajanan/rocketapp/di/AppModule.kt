package com.gajanan.rocketapp.di

import android.app.Application
import androidx.room.Room
import com.gajanan.rocketapp.cache.database.RocketDatabase
import com.gajanan.rocketapp.network.Endpoints
import com.gajanan.rocketapp.network.RetrofitApiInterface
import com.gajanan.rocketapp.utils.Constants.CONNECTION_TIMEOUT
import com.gajanan.rocketapp.utils.Constants.READ_TIMEOUT
import com.gajanan.rocketapp.utils.Constants.WRITE_TIMEOUT
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
        fun provideRetrofit() : Retrofit{
            val gson = GsonBuilder().serializeNulls().create()

            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

            val client: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(logger)
                .connectTimeout(
                    CONNECTION_TIMEOUT.toLong(),
                    TimeUnit.SECONDS
                )
                .readTimeout(
                    READ_TIMEOUT.toLong(),
                    TimeUnit.SECONDS
                )
                .writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build()


            return Retrofit.Builder()
                .baseUrl(Endpoints.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
        }

    @Provides
    @Singleton
    fun forApi(retrofit: Retrofit): RetrofitApiInterface =
        retrofit.create(RetrofitApiInterface::class.java)

    @Provides
    @Singleton
    fun provideDatabase(app: Application): RocketDatabase =
        Room.databaseBuilder(app , RocketDatabase::class.java,"rocket_db")
            .build()


}