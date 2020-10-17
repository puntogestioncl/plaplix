package cl.puntogestion.plaplix.db

import android.util.Log
import androidx.core.net.toUri
import cl.puntogestion.plaplix.db.remote.Product
import cl.puntogestion.plaplix.db.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryDetail {

    fun loadApi(){
        val call = RetrofitClient.retrofitInstance()
            .getAllProducts()

        call.enqueue(object : Callback<Product> {
            override fun onFailure(call: Call<Product>, t: Throwable) {
                Log.d("API--", "${t.message}" )
            }

            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                Log.d("API++", "${response.body()}")
                Log.d("API++", "${response.toString().toUri() }")
            }

        })

    }
}