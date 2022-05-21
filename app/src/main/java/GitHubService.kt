
import com.google.firebase.database.core.Repo

import retrofit2.http.GET
import retrofit2.http.Path


    interface GitHubService {
        @GET("users/{user}")
        suspend fun listRepos(@Path("user") user: String): List<Repo>
    }
