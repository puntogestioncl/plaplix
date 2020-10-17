package cl.puntogestion.plaplix.db.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ProductEntity::class], version = 1)
abstract class DataBaseProduct: RoomDatabase() {
        abstract fun productDao(): ProductDao

    companion object{
        @Volatile
        private var INSTANCE: DataBaseProduct? = null
        fun getDataBaseInstance(context: Context): DataBaseProduct{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBaseProduct::class.java,
                    "soccer_database")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}