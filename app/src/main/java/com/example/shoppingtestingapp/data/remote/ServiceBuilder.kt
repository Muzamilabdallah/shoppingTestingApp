package com.acwad.gazk.Webservice


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.example.shoppingtestingapp.data.remote.AddCookiesInterceptor
import com.example.shoppingtestingapp.data.remote.ApiService
import com.example.shoppingtestingapp.data.remote.ReceivedCookiesInterceptor
import com.example.shoppingtestingapp.util.Constant
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieHandler


class ServiceBuilder(val mContext: Context) {

    operator fun invoke(): ApiService {

        val cacheSize = (5 * 1024 * 1024).toLong()
        val myCache = Cache(mContext.cacheDir, cacheSize)


        val okHttpClient = OkHttpClient.Builder()  // Specify the cache we created earlier.
            .cache(myCache)

            .addInterceptor { chain ->



                var request = chain.request()


                request = if (hasNetwork(mContext)!!)

                    request.newBuilder().removeHeader("Pragma").header(
                        "Cache-Control",
                        "public, max-age=" + 60 * 10
                    ).addHeader("Content-Type", "application/json")
                        .addHeader("Accept", " application/json").addHeader(
                            "X-Device",
                            Constant.androidV
                        )
                        .build()
                else



                    request.newBuilder().removeHeader("Pragma").header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                    ).addHeader("Content-Type", "application/json")
                        .addHeader("Accept", " application/json").addHeader(
                            "X-Device",
                            Constant.androidV
                        )
                        .build()

                chain.proceed(request)
            }

            .build()


        var builder =   OkHttpClient.Builder();

        builder.addInterceptor(  AddCookiesInterceptor(mContext));
        builder.addInterceptor(  ReceivedCookiesInterceptor(mContext));



        val gson = GsonBuilder()
            .setLenient()
            .create()

        fun getRetrofit(): Retrofit = Retrofit.Builder().baseUrl(Constant.BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(gson)
            ).client(okHttpClient)

            .build()

        var ServiceApi: ApiService = getRetrofit().create(ApiService::class.java)

        return ServiceApi

    }

    fun hasNetwork(context: Context): Boolean? {
        var isConnected: Boolean? = false // Initial Value
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true
        return isConnected
    }
}




