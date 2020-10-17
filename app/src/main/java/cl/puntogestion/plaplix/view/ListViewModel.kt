package cl.puntogestion.plaplix.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cl.puntogestion.plaplix.db.Repository
import cl.puntogestion.plaplix.db.local.ProductEntity

class ListViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: Repository = Repository(application)

    var datos = repository.loadApi()
    var getAllProduct: LiveData<List<ProductEntity>> = repository.getAllProduct

    val selected = MutableLiveData<ProductEntity>()

    fun select(product: ProductEntity?) {
        selected.value =  product
    }
}