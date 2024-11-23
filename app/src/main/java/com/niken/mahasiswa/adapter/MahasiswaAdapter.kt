package com.niken.mahasiswa.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.niken.mahasiswa.R
import com.niken.mahasiswa.UpdateData
import com.niken.mahasiswa.model.ModelMahasiswa
import com.niken.mahasiswa.helper.MahasiswaDatabaseHelper

class MahasiswaAdapter(
    private var mhs: List<ModelMahasiswa>,
    private val onClick: (ModelMahasiswa) -> Unit,
    context: Context
) : RecyclerView.Adapter<MahasiswaAdapter.MahasiswaViewHolder>() {

    private val db : MahasiswaDatabaseHelper = MahasiswaDatabaseHelper(context)

    class MahasiswaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtDataNama: TextView = itemView.findViewById(R.id.txtDataNama)
        val txtDataNim: TextView = itemView.findViewById(R.id.txtDataNim)
        val txtDataJurusan: TextView = itemView.findViewById(R.id.txtDataJurusan)
        val btnEdit: ImageView = itemView.findViewById(R.id.btnEdit)
        val btnDelete: ImageView = itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MahasiswaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_mahasiswa,
            parent,
            false
        )
        return MahasiswaViewHolder(view)
    }

    override fun onBindViewHolder(holder: MahasiswaViewHolder, position: Int) {
        val noteData = mhs[position]
        holder.txtDataNama.text = noteData.nama
        holder.txtDataNim.text = noteData.nim
        holder.txtDataJurusan.text = noteData.jurusan

        holder.btnEdit.setOnClickListener(){
            val intent = Intent(holder.itemView.context, UpdateData::class.java).apply {
                putExtra("note_id", noteData.id)
            }
            holder.itemView.context.startActivity(intent)

        }

        holder.btnDelete.setOnClickListener(){
            AlertDialog.Builder(holder.itemView.context).apply{
                setTitle("Confirmation")
                setMessage("Do you want to continue ?")
                setIcon(R.drawable.delete)

                setPositiveButton("yes") {
                        dialogInterface, i->
                    db.deleteNote(noteData.id)
                    refreshData(db.getAllNotes())
                    Toast.makeText(holder.itemView.context, "note berhasil di hapus",
                        Toast.LENGTH_SHORT).show()
                    dialogInterface.dismiss()
                }
                setNeutralButton("no"){
                        dialogInterface, i->
                    dialogInterface.dismiss()
                }
            }.show()
        }

        // Set listener untuk menangani klik pada item
        holder.itemView.setOnClickListener {
            onClick(noteData)
        }
    }

    override fun getItemCount(): Int {
        return mhs.size
    }


    fun refreshData(newNotes: List<ModelMahasiswa>) {
        mhs = newNotes
        notifyDataSetChanged()
    }
}