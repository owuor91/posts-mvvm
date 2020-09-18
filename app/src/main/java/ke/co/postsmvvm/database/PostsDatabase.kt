package ke.co.postsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ke.co.postsmvvm.models.Post

@Database(entities = arrayOf(Post::class), version = 1)
abstract class PostsDatabase : RoomDatabase() {
    abstract fun postsDao(): PostsDao

    companion object {
        private var dbInstance: PostsDatabase? = null
        fun getInstance(context: Context): PostsDatabase {
            if (dbInstance == null) {
                dbInstance =
                    Room.databaseBuilder(context, PostsDatabase::class.java, "posts-db").build()
            }
            return dbInstance as PostsDatabase
        }
    }
}