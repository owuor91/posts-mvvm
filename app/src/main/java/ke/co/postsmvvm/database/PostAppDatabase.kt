package ke.co.postsmvvm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ke.co.postsmvvm.models.Comment
import ke.co.postsmvvm.models.Post

@Database(entities = arrayOf(Post::class, Comment::class), version = 2)
abstract class PostAppDatabase : RoomDatabase() {
    abstract fun postDao(): PostsDao

    abstract fun commentsDao(): CommentsDao

    companion object {
        private var dbInstance: PostAppDatabase? = null

        fun getDbInstance(context: Context): PostAppDatabase {
            if (dbInstance == null) {
                dbInstance =
                    Room.databaseBuilder(context, PostAppDatabase::class.java, "postapp-db")
                        .fallbackToDestructiveMigration().build()
            }

            return dbInstance as PostAppDatabase
        }
    }
}