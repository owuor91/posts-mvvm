package ke.co.postsmvvm.models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Comments")
data class Comment(
    var postId: Int,
    @PrimaryKey @NonNull var id: Int,
    var name: String,
    var email: String,
    var body: String
)