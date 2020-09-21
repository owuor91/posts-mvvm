package ke.co.postsmvvm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ke.co.postsmvvm.models.Post

@Database(entities = arrayOf(Post::class), version = 1)
abstract class PostAppDatabase: RoomDatabase() {
    abstract fun postDao(): PostsDao

    companion object{
        private var dbInstance: PostAppDatabase? = null

        fun getDbInstance(context: Context): PostAppDatabase{
            if(dbInstance==null){
                dbInstance = Room.databaseBuilder(context, PostAppDatabase::class.java, "postapp-db").build()
            }

            return dbInstance as PostAppDatabase
        }
    }
}