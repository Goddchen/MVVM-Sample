package de.goddchen.android.mvvmsample.api

import android.annotation.SuppressLint
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


class ApiGenerator {

    companion object {
        fun generateApi(): GDGxHubApi {
            return Retrofit.Builder()
                    .addCallAdapterFactory(
                            RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .addConverterFactory(GsonConverterFactory.create(GsonBuilder()
                            .registerTypeAdapter(LocalDateTime::class.java, localDateTimeDeserializer)
                            .create()))
                    .baseUrl("https://hub.gdgx.io/api/v1/")
                    .client(getUnsafeOkHttpClient())
                    .build()
                    .create(GDGxHubApi::class.java)
        }

        private val localDateTimeDeserializer: JsonDeserializer<LocalDateTime> =
                JsonDeserializer { json, _, _ ->
                    LocalDateTime.parse(json.asString,
                            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"))
                }

        @SuppressLint("TrustAllX509TrustManager")
        fun getUnsafeOkHttpClient(): OkHttpClient {
            // https://stackoverflow.com/a/25992879/373138
            try {
                // Create a trust manager that does not validate certificate chains
                val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {

                    override fun getAcceptedIssuers(): Array<X509Certificate> {
                        return arrayOf()
                    }

                    @Throws(CertificateException::class)
                    override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                    }

                    @Throws(CertificateException::class)
                    override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                    }
                })
                // Install the all-trusting trust manager
                val sslContext = SSLContext.getInstance("SSL")
                sslContext.init(null, trustAllCerts, java.security.SecureRandom())
                // Create an ssl socket factory with our all-trusting manager
                val sslSocketFactory = sslContext.socketFactory
                val builder = OkHttpClient.Builder()
                builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
                builder.hostnameVerifier { _, _ -> true }
                return builder.build()
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }
    }

}