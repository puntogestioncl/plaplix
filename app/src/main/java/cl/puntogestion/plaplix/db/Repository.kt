package cl.puntogestion.plaplix.db

import android.content.Context
import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import cl.puntogestion.plaplix.db.local.DataBaseProduct
import cl.puntogestion.plaplix.db.local.ProductDao
import cl.puntogestion.plaplix.db.local.ProductEntity
import cl.puntogestion.plaplix.db.remote.Product
import cl.puntogestion.plaplix.db.remote.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class Repository(context: Context) {

    private var instanceDb: DataBaseProduct = DataBaseProduct.getDataBaseInstance(context)
    private var productDao: ProductDao = instanceDb.productDao()

    var getAllProduct: LiveData<List<ProductEntity>> = productDao.getAllProduct()

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

                converterResponse(response.body())
            }

        })

    }

    private var listProduct = ArrayList<ProductEntity>()

    private fun converterResponse(products: Product?) {
        products?.map {
            listProduct.add(
                ProductEntity(it.id, it.image, it.name, it.price)
            )
        }

        CoroutineScope(Dispatchers.IO).launch {
                productDao.insertProducts(listProduct)
        }
    }
}