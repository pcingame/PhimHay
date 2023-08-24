package com.pcingame.phimhay

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.pcingame.phimhay.databinding.ActivityMainnBinding

class MainAActivity : AppCompatActivity() {

    private var layoutManager: GridLayoutManager? = null
    private lateinit var binding: ActivityMainnBinding
    private var adapter: SimpleAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainnBinding.inflate(layoutInflater)
        setContentView(binding.root)

        layoutManager = GridLayoutManager(this, 1, GridLayoutManager.HORIZONTAL, false)
        binding.rcvMain.layoutManager = layoutManager
        adapter = SimpleAdapter(layoutManager)
        binding.rcvMain.adapter = adapter
        binding.rcvMain.setHasFixedSize(true)

        binding.btn.setOnClickListener {
            if (layoutManager?.spanCount == 1) {
                layoutManager?.spanCount = 3
                layoutManager?.orientation = GridLayoutManager.VERTICAL
            } else {
                layoutManager?.spanCount = 1
                layoutManager?.orientation = GridLayoutManager.HORIZONTAL
            }
            adapter?.notifyItemRangeChanged(0, adapter?.itemCount ?: 0)
        }
    }
}