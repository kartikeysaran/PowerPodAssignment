package ks.assignment.powerpod.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import ks.assignment.powerpod.R
import ks.assignment.powerpod.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)

        binding.btnScanQrcode.setOnClickListener{
            startActivity(Intent(this, QRCodeActivity::class.java))
        }
    }

}