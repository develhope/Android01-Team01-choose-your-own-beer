import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestClient {
    private val gsonBuilder = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit =
        Retrofit.Builder().baseUrl("https://api.punkapi.com/v2/").addConverterFactory(
            GsonConverterFactory.create(gsonBuilder)
        ).build()

    }

