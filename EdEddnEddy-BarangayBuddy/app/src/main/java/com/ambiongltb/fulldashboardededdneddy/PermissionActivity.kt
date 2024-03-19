package com.ambiongltb.fulldashboardededdneddy

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat

import com.ambiongltb.fulldashboardededdneddy.databinding.ActivityNotificationBinding

class PermissionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotificationBinding
    private lateinit var requestLauncher: ActivityResultLauncher<String>
    private lateinit var toolbar: Toolbar

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbar = findViewById(R.id.appToolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            title = "Notifications"
            setDisplayHomeAsUpEnabled(true)
        }
        toolbar.navigationIcon?.setTint(ContextCompat.getColor(this, R.color.white))

        requestLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) {
                //main activity
                startActivity(
                    Intent(
                        this@PermissionActivity, com.ambiongltb.fulldashboardededdneddy.MainActivity::class.java
                    )
                )
            } else {
                //show error message
                showErrorMessage()
            }
        }

        binding.okayBtn.setOnClickListener {
            askForNotificationPermission()
        }
        binding.skipBtn.setOnClickListener {
            showErrorMessage()
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun askForNotificationPermission() {
        requestLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
    }

    private fun showErrorMessage() {
        Toast.makeText(
            this, "Permission is not granted", Toast.LENGTH_SHORT
        ).show()
    }
}