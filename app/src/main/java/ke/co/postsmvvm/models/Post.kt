package ke.co.postsmvvm.models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Posts")
data class Post(
    val userId: Int,
    @PrimaryKey @NonNull val id: Int,
    val title: String,
    val body: String
)