package com.example.profile_app


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_store.AppDatabase
import com.example.profile_app.databinding.ActivityMainBinding

import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

private lateinit var  db: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnAdd.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }
        db = AppDatabase.getDatabase(this)
        loadData()

        // binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager= LinearLayoutManager(this)


    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    @SuppressLint("SetTextI18n")
    private fun loadData() {

        val list = db.noteDao().getAllNotes()

        binding.countTv.text = "Total Notes : ${list.size}"

        val adapter = NoteAdapter(
            list,

            onEdit = { note ->
                val intent = Intent(this@MainActivity, AddActivity::class.java)

                intent.putExtra("id", note.id)
                intent.putExtra("name", note.name)
                intent.putExtra("quantity", note.quantity)
                intent.putExtra("category", note.category)
                intent.putExtra("price", note.price)
                startActivity(intent)

            },
            onDelete = { note ->
                val view = layoutInflater.inflate(R.layout.dialog_delete, null)
                val dialog = MaterialAlertDialogBuilder(this)
                    .setView(view)
                    .create()

                view.findViewById<Button>(R.id.btnConfirmDelete).setOnClickListener {
                    db.noteDao().delete(note)
                    loadData()
                    dialog.dismiss()
                }

                dialog.show()
            }
        )

        binding.recyclerView.adapter = adapter

    }


}
