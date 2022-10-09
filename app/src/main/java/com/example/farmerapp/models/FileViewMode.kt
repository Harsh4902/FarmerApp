package com.example.farmerapp.models

import androidx.lifecycle.ViewModel
import com.example.farmerapp.repo.FileRepository
import java.io.File

class FileViewMode (
    private val repository: FileRepository = FileRepository()
 ) : ViewModel() {

     suspend fun uploadImage(file: File){

         repository.uploadImage(file)

     }

 }