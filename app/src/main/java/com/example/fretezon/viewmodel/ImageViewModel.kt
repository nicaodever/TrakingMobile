import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.io.IOException

class ImageViewModel : ViewModel() {

    private val storageRef: StorageReference = FirebaseStorage.getInstance().reference

    // Assuming you have a list of image paths in your storage
    val imageUrls = mutableListOf<String>()

    suspend fun fetchImageUrls(imagePaths: List<String>) {
        viewModelScope.launch {
            imagePaths.forEach { imagePath ->
                try {
                    val imageRef = storageRef.child(imagePath)
                    val downloadUrl = imageRef.downloadUrl.await()
                    imageUrls.add(downloadUrl.toString())
                } catch (e: IOException) {
                    // Handle exceptions, such as image not found or network errors
                    println("Error fetching image URL for $imagePath: ${e.message}")
                    // Consider adding error handling, like a placeholder image
                } catch (e: Exception) {
                    // Catch any other exceptions during the process
                    println("Generic error fetching image URL for $imagePath: ${e.message}")
                }
            }
        }
    }
}
