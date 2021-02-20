package com.example.imageupload

import android.content.Intent
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.io.File


const val REQUEST_IMAGE_GET = 1

class UploadActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)

    }

    fun getImage(view: View) {

        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "image/*"
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_GET)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && null != data) {
            val selectedImageUri = data.data
            selectedImageUri?.let { upload(it) }
        } else {
            Toast.makeText(this, "You have not selected and image", Toast.LENGTH_SHORT).show()
        }
    }

    private fun upload(uri: Uri) {
        var storage = Firebase.storage
        val storageRef = storage.reference
        val riversRef = storageRef.child("images/${uri.lastPathSegment}")
        var uploadTask = riversRef.putFile(uri)

        uploadTask.addOnFailureListener {
            Toast.makeText(this, "Unsuccessful",Toast.LENGTH_LONG).show()
        }.addOnSuccessListener { taskSnapshot ->
            Toast.makeText(this, "Image Upload Successful",Toast.LENGTH_LONG).show()
            val downloadUri = taskSnapshot.uploadSessionUri.toString()
            val el:EditText = findViewById(R.id.url_link)
            el.setText(downloadUri)
        }
    }

}

