package com.niken.mahasiswa

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.niken.mahasiswa.databinding.ActivityAddDataBinding
import com.niken.mahasiswa.helper.MahasiswaDatabaseHelper
import com.niken.mahasiswa.model.ModelMahasiswa

class AddData : AppCompatActivity() {
    private lateinit var binding : ActivityAddDataBinding
    private lateinit var db: MahasiswaDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = MahasiswaDatabaseHelper(this)

        binding.saveButton.setOnClickListener {
            val nama = binding.titleEditText.text.toString()
            val nim = binding.titleEditText1.text.toString()
            val jurusan = binding.titleEditText2.text.toString()
            val mhs = ModelMahasiswa(0, nama, nim, jurusan)
            db.insertNote(mhs)
            finish()
            Toast.makeText(this, "Catatan disimpan", Toast.LENGTH_SHORT).show()
        }
    }
}

