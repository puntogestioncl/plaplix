package cl.puntogestion.plaplix.db.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class ProductEntity(
    @PrimaryKey
    val id: Int,
    val image: String,
    val name: String,
    val price: Int
)