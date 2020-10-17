package cl.puntogestion.plaplix.db.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(product: List<ProductEntity>)

    @Query("Select * From product")
    fun getAllProduct(): LiveData<List<ProductEntity>>

    @Query("Select * From product WHERE id = :id")
    fun getProduct(id:Int): LiveData<ProductEntity>
}