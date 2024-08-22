package com.example.kotlinlivedata

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinlivedata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vb = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(vb.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val vm = ViewModelProvider(this)[MainActivityViewModel::class.java]

        vm.seconds().observe(this) { vb.textView.text = it.toString() }

        vm.finished().observe(this) {
            if(it) Toast.makeText(this, "Finished", Toast.LENGTH_SHORT).show()
        }

        vb.button.setOnClickListener {
            if(vb.editTextText.text.isEmpty() || vb.editTextText.text.length < 4) Toast.makeText(this, "Invalid Number", Toast.LENGTH_SHORT).show()
            else {
                vm.timerValue.value = vb.editTextText.text.toString().toLong()
                vm.startTimer()
            }
        }

        vb.button2.setOnClickListener {
            vm.stopTimer()
            vm.clearTimer()
            vb.textView.text = "0"
            vb.editTextText.text.clear()
        }

    }
}