package com.niken.mahasiswa

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.niken.mahasiswa.adapter.MahasiswaAdapter
import com.niken.mahasiswa.databinding.ActivityMainBinding
import com.niken.mahasiswa.helper.MahasiswaDatabaseHelper

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mhsAdapter: MahasiswaAdapter
    private lateinit var db: MahasiswaDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dbHelper = MahasiswaDatabaseHelper(this)
        dbHelper.writableDatabase

        db = MahasiswaDatabaseHelper(this)

        mhsAdapter = MahasiswaAdapter(db.getAllNotes(), { note ->

            val intent = Intent(this, Detail::class.java)
            intent.putExtra("nama", note.nama)
            intent.putExtra("nim", note.nim)
            intent.putExtra("jurusan", note.jurusan)
            startActivity(intent)
        }, this)



        binding.mhsRecycleview.layoutManager = LinearLayoutManager(this)
        binding.mhsRecycleview.adapter = mhsAdapter

        // Tombol tambah catatan
        binding.addButton.setOnClickListener {
            val intent = Intent(this, AddData::class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        val notes = db.getAllNotes()
        mhsAdapter.refreshData(notes)
    }
}