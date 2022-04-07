package com.akbararyo.ghibliapps.core.di

import androidx.room.Room
import com.akbararyo.ghibliapps.core.data.source.MoviesRepository
import com.akbararyo.ghibliapps.core.data.source.local.LocalDataSource
import com.akbararyo.ghibliapps.core.data.source.local.room.MoviesDatabase
import com.akbararyo.ghibliapps.core.data.source.remote.RemoteDataSource
import com.akbararyo.ghibliapps.core.data.source.remote.network.ApiService
import com.akbararyo.ghibliapps.core.domain.repository.MoviesRepositoryImpl
import com.akbararyo.ghibliapps.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MoviesDatabase>().moviesDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("ghibliaryo".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            MoviesDatabase::class.java, "Movies.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = "ghibliapi.herokuapp.com"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/M7eir9qCtRzt4w22FgQrxM9bMpn1tezOOtzblURVegE=")
            .add(hostname, "sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=")
            .add(hostname, "sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI=")
            .add(hostname, "sha256/KwccWaCgrnaw6tsrrSO61FgLacNgG2MMLq8GE6+oP5I=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://ghibliapi.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<MoviesRepositoryImpl> {
        MoviesRepository(
            get(),
            get(),
            get()
        )
    }
}