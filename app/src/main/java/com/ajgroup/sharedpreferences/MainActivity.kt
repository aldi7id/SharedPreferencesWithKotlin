package com.ajgroup.sharedpreferences

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ajgroup.sharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val sharedPrefFile = "kotlinsharedpreference"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile, MODE_PRIVATE)
        binding.btnSave.setOnClickListener {
            val id: Int = Integer.parseInt(binding.etId.text.toString())
            val name: String = binding.etName.text.toString()
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putInt("id_key", id)
            editor.putString("name_key", name)
            editor.apply()
            Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show()
        }
        binding.btnView.setOnClickListener { 
            val sharedIdValue = sharedPreferences.getInt("id_key", 0)
            val sharedNameValue = sharedPreferences.getString("name_key","defaultname")
            if (sharedIdValue.equals(0) && sharedNameValue.equals("defaultname")){
                binding.tvName.setText("default name: ${sharedNameValue}").toString()
                binding.tvId.setText("default id: ${sharedIdValue.toString()}")
                Toast.makeText(this, "Data View Kosong", Toast.LENGTH_SHORT).show()
            } else {
                binding.tvName.setText(sharedNameValue).toString()
                binding.tvId.setText(sharedIdValue.toString())
                Toast.makeText(this, "Data View Ditampilkan", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnClear.setOnClickListener { 
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            binding.tvName.setText("")
            binding.tvId.setText("")
            Toast.makeText(this, "Data Clear", Toast.LENGTH_SHORT).show()
        }
    }
}