package ks.assignment.powerpod.adapter

import android.content.ClipData.Item
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ks.assignment.powerpod.R
import ks.assignment.powerpod.databinding.ItemLayoutBinding
import ks.assignment.powerpod.model.QRCode


class QRCodeAdapter(private var qrCodeList: List<QRCode>) : RecyclerView.Adapter<QRCodeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemLayoutBinding>(
            inflater,
            R.layout.item_layout,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val qrcode = qrCodeList[position]
        holder.bind(qrcode)
    }

    override fun getItemCount(): Int = qrCodeList.size

    inner class ViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: QRCode) {
            binding.qrcode = item
            binding.executePendingBindings()
        }
    }

    fun setItems(qrCodes: ArrayList<QRCode>) {
        qrCodeList = qrCodes
        notifyDataSetChanged()
    }

}