package com.example.fretezon.services

import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID


fun uploadImage(imageUri: Uri): String {
    val storageRef = Firebase.storage.reference
    val imageRef = storageRef.child("images/${UUID.randomUUID()}.jpg")
    var url: String = ""
    val uploadTask = imageRef.putFile(imageUri)

    uploadTask.addOnSuccessListener { taskSnapshot ->
        // Upload concluído com sucesso.  Agora, obtenha a URL.

        imageRef.downloadUrl.addOnSuccessListener { uri ->
            // URL obtida com sucesso. Atualize a UI na thread principal.
            Handler(Looper.getMainLooper()).post {
                // Atualiza a UI com a URL
                // Ex: viewModel.imageUrl = uri.toString()
                Log.d("FirebaseStorage", "URL da imagem: ${uri.toString()}")
                 url = uri.toString()

            }
        }
            .addOnFailureListener { exception ->
                // Lidar com falhas ao obter a URL
                Handler(Looper.getMainLooper()).post {
                    Log.e("FirebaseStorage", "Erro ao obter URL: ${exception.message}")
                    // Exibir mensagem de erro para o usuário na UI
                }
            }
    }
        .addOnFailureListener { exception ->
            // Lidar com falhas no upload
            Handler(Looper.getMainLooper()).post {
                Log.e("FirebaseStorage", "Erro no upload: ${exception.message}")
                // Exibir mensagem de erro para o usuário na UI
            }
        }
        .addOnProgressListener { taskSnapshot ->
            // Opcional: Mostrar progresso do upload
            val progress = (100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount).toInt()
            Handler(Looper.getMainLooper()).post {
                // Atualiza a UI com o progresso
                // Ex: viewModel.uploadProgress = progress
            }
        }
    return url
}

