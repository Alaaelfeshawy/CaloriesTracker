package com.example.tracker_data.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tracker_data.local.TrackerDatabase
import com.example.tracker_data.local.dao.TrackerDao
import com.example.tracker_data.remote.OpenFoodApi
import com.example.tracker_data.repository.TrackerRepositoryImpl
import com.example.tracker_domain.repository.ITrackerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TrackerModule {
    @Provides
    @Singleton
    fun provideOkHttpClient():OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }
    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .baseUrl(OpenFoodApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
    }
    @Provides
    @Singleton
    fun provideOpenFoodApi(retrofit: Retrofit):OpenFoodApi{
        return retrofit.create(OpenFoodApi::class.java)
    }
    @Provides
    @Singleton
    fun provideDatabase(app:Application):TrackerDao{
        return Room.databaseBuilder(
            app,
            TrackerDatabase::class.java,
            "tracker_db"
        ).build().dao()
    }
    @Provides
    @Singleton
    fun provideTrackerRepository(dao: TrackerDao, api: OpenFoodApi, ): ITrackerRepository {
        return TrackerRepositoryImpl(dao, api)
    }
}