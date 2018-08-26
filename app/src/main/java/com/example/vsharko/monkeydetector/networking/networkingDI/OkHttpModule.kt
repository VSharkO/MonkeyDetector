package com.example.vsharko.monkeydetector.networking.networkingDI

import com.example.vsharko.monkeydetector.BuildConfig
import com.example.vsharko.monkeydetector.utils.Constants
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import javax.inject.Singleton

@Module
class OkHttpModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: Interceptor, logging: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder().addInterceptor(interceptor)
        if (BuildConfig.DEBUG) {
            okHttpClientBuilder.addInterceptor(logging)
        }
        return okHttpClientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideInterceptor(): Interceptor {
        return Interceptor { chain ->
            val original = chain.request()
            val httpUrl = original.url()

            val newHttpUrl = httpUrl.newBuilder()
                    .addPathSegments(Constants.TYPE)
                    .addPathSegments(Constants.VERSION)
                    .addPathSegments(Constants.ACTION)
                    .addPathSegments(Constants.FIRST_KEY_NUMBER)
                    .addPathSegment(Constants.URL)
                    .addQueryParameter(Constants.QUERY_KEY,Constants.QUERY_VALUE)
                    .build()

            val requestBuilder = original.newBuilder()
                    .header(Constants.PREDICTION_KEY, Constants.PREDICTION_VALUE)
                    .header(Constants.CONTENT_TYPE,Constants.FORMAT_TYPE)
                    .url(newHttpUrl)
            val request = requestBuilder.build()
            chain.proceed(request)
        }
    }

    @Singleton
    @Provides
    fun provideLogging(): HttpLoggingInterceptor {
        val logger = { message : String -> Timber.d(message) }

        val logging = HttpLoggingInterceptor(logger)
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }
}