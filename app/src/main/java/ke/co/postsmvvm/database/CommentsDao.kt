package ke.co.postsmvvm.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ke.co.postsmvvm.models.Comment

@Dao
interface CommentsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComment(comment: Comment)

    @Query("SELECT * FROM comments")
    fun getComments(): LiveData<List<Comment>>

    @Query("SELECT * FROM comments WHERE postId = :postId")
    fun getCommentsByPostId(postId: Int): LiveData<List<Comment>>
}