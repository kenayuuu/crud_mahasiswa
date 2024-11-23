package com.niken.mahasiswa

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.niken.mahasiswa.databinding.ActivityUpdateDataBinding
import com.niken.mahasiswa.helper.MahasiswaDatabaseHelper
import com.niken.mahasiswa.model.ModelMahasiswa

class UpdateData : AppCompatActivity() {

    private lateinit var binding : ActivityUpdateDataBinding
    private lateinit var db : MahasiswaDatabaseHelper

    private var  noteId : Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = MahasiswaDatabaseHelper(this)
        noteId = intent.getIntExtra("note_id", -1)
        if(noteId == -1){
            finish()
            return
        }

        val note = db.getNoteById(noteId)
        binding.etEditNama.setText(note.nama)
        binding.etEditNim.setText(note.nim)
        binding.etEditJurusan.setText(note.jurusan)

        binding.btnUpdate.setOnClickListener(){
            val newNama = binding.etEditNama.text.toString()
            val newNim = binding.etEditNim.text.toString()
            val newJurusan = binding.etEditJurusan.text.toString()

            val updateNote = ModelMahasiswa(noteId, newNama, newNim, newJurusan)
            db.updateNote(updateNote)
            finish()
            Toast.makeText(this,"Update berhasil", Toast.LENGTH_SHORT).show()
        }
    }
}