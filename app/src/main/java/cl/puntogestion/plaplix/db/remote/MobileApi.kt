package cl.puntogestion.plaplix.db.remote

import retrofit2.Call
import retrofit2.http.GET

interface MobileApi {

    @GET("/Himuravidal/FakeAPIdata/products/")
    fun getAllProducts() : Call<Product>

    @GET("/Himuravidal/FakeAPIdata/details/{id}")
    fun getDetailProduct(id:Int): Call<ProductItem>
}