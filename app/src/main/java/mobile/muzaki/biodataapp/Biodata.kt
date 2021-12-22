package mobile.muzaki.biodataapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Biodata (
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val nim : String,
    val nama : String,
    val prodi : String,
    val alamat : String,
)