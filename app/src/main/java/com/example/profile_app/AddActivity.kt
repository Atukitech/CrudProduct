package com.example.profile_app

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app_store.AppDatabase
import com.example.profile_app.databinding.ActivityAddBinding


class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding

    private lateinit var db: AppDatabase

    private var noteid = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //getDatabase function called
        db = AppDatabase.getDatabase(this)

        noteid =intent.getIntExtra("id",-1)

        if (noteid!=-1){

            binding.nameET.setText(intent.getStringExtra("name"))
            binding.quantityET.setText(intent.getStringExtra("quantity"))
            binding.categoryET.setText(intent.getStringExtra("category"))
            binding.priceET.setText(intent.getStringExtra("price"))
            binding.availableET.setText(intent.getStringExtra("available"))


        }

        binding.button.setOnClickListener {
            val name = binding.nameET.text.toString()
            val quantity= binding.quantityET.text.toString()
            val category =binding.categoryET.text.toString()
            val price =binding.priceET.text.toString()
            val available =binding.availableET.text.toString()

            //VALIDATION
            if (name.isEmpty()) {
                binding.nameET.error="Enter Product Name"
                return@setOnClickListener
            }else{
                binding.nameET.error=null
            }
            if (quantity.isEmpty()) {
                binding.quantityET.error="Enter Product Quantity"
                return@setOnClickListener
            }else{
                binding.priceET.error=null
            }
            if (name.isEmpty()) {
                binding.priceET.error="Enter Price"
                return@setOnClickListener
            }else{
                binding.availableET.error=null
            }
            if (available.isEmpty()) {
                binding.availableET.error="Enter Availablity"
                return@setOnClickListener
            }else{
                binding.availableET.error=null
            }













            if (noteid== -1){
                //insert
                val note = Note(name = name, quantity = quantity, category = category, price = price,
                    available = available)
                db.noteDao().insert(note)

            }else{
                //Update
                val note =Note(id = noteid, name = name, quantity= quantity, category=category, price=price,
                    available=available)
                db.noteDao().update(note)
            }
            Toast.makeText(this@AddActivity, "data saved successfully", Toast.LENGTH_SHORT).show()
            finish()


        }


    }
}