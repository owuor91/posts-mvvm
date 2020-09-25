package ke.co.postsmvvm.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ke.co.postsmvvm.models.Post

@Dao
interface PostsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPost(post: Post)

    @Query("SELECT * FROM posts")
    fun getPosts(): LiveData<List<Post>>

    @Query("SELECT * FROM posts WHERE id = :postId")
    fun getPostById(postId: Int): LiveData<Post>
}