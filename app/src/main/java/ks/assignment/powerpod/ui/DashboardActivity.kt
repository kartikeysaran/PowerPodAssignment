package ks.assignment.powerpod.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.camera.core.ExperimentalGetImage
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ks.assignment.powerpod.R
import ks.assignment.powerpod.adapter.QRCodeAdapter
import ks.assignment.powerpod.api.ResultData
import ks.assignment.powerpod.databinding.ActivityDashboardBinding
import ks.assignment.powerpod.model.QRCode
import ks.assignment.powerpod.view_models.DashboardViewModel
import okhttp3.internal.notify

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    private val viewModel: DashboardViewModel by viewModels()
    private lateinit var adapter: QRCodeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)

        binding.btnScanQrcode.setOnClickListener{
            startActivity(Intent(this, QRCodeActivity::class.java))
        }

        val recyclerView = binding.recyclerview
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        var qrCodeList = emptyList<QRCode>()// Initialize your list of ViewModel items
        adapter = QRCodeAdapter(qrCodeList)
        recyclerView.adapter = adapter

        viewModel.getQRCodeList()

        viewModel.qrData.observe(this, Observer {
            when (it) {
                is ResultData.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is ResultData.Success -> {
                    binding.progressBar.visibility = View.GONE
                    qrCodeList = it.data!!
                    adapter.setItems(it.data);
                }

                is ResultData.Failed -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, "Error"+" "+it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })

    }

}