package com.example.vsharko.monkeydetector.networking.networkingDI

import com.example.vsharko.monkeydetector.networking.helper.NetworkingHelper
import com.example.vsharko.monkeydetector.networking.helper.NetworkingHelperImpl
import com.example.vsharko.monkeydetector.networking.Service
import com.example.vsharko.monkeydetector.utils.Constants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [OkHttpModule::class])
class NetworkModule {

    @Singleton
    @Provides
    fun provideNetworkingHelper(service: Service): NetworkingHelper {
        return NetworkingHelperImpl(service)
    }

    @Singleton
    @Provides
    fun provideRestClient(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(Constants.NEWS_API_BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Singleton
    @Provides
    fun provideNewsAPIService(retrofit: Retrofit): Service {
        return retrofit.create<Service>(Service::class.java)
    }
}