package com.example.plantslog.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.plantslog.databinding.TrefleLoadStateFooterBinding

class TrefleLoadStateAdapter(private val retry: () -> Unit): LoadStateAdapter<TrefleLoadStateAdapter.LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = TrefleLoadStateFooterBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false
        )

        return LoadStateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }


    inner class LoadStateViewHolder(private val binding: TrefleLoadStateFooterBinding):
            RecyclerView.ViewHolder(binding.root) {

            init {
                binding.btRetry.setOnClickListener {
                    retry.invoke()
                }
            }

             fun bind(loadState: LoadState) {
                 binding.apply {
                     progressBar.isVisible = loadState is LoadState.Loading
                     btRetry.isVisible = loadState is LoadState.Error
                     tvError.isVisible = loadState is LoadState.Error
                    }
                }
    }
}