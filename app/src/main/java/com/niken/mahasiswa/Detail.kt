package com.niken.mahasiswa

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.niken.mahasiswa.databinding.ActivityDetailBinding
import org.w3c.dom.Text

class Detail : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var txtNamaDetail : TextView
    private lateinit var txtNimDetail : TextView
    private lateinit var txtJurusanDetail : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val nama = intent.getStringExtra("nama")
        val nim = intent.getStringExtra("nim")
        val jurusan = intent.getStringExtra("jurusan")

        findViewById<TextView>(R.id.txtNamaDetail).text = nama
        findViewById<TextView>(R.id.txtNimDetail).text = nim
        findViewById<TextView>(R.id.txtJurusanDetail).text = jurusan


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}