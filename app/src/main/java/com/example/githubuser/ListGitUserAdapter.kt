package com.example.githubuser


import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuser.databinding.ItemRowUserBinding

class ListGitUserAdapter (private val listGitUser: ArrayList<GitUser>) : RecyclerView.Adapter<ListGitUserAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(var binding: ItemRowUserBinding) : RecyclerView.ViewHolder(binding.root) {	}

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val user = listGitUser[position]
        holder.binding.apply {
            Glide.with(root.context)
                .load(user.avatar)
                .circleCrop()
                .into(holder.binding.imgItemPhoto)
            tvItemName.text = user.name
            tvItemUsername.text = "@ ${user.username}"
            tvItemRepository.text = "${user.repository} Repository"
            tvItemFollow.text = "${user.following} Following | ${user.followers} Followers"

            root.setOnClickListener { onItemClickCallback.onItemClicked(listGitUser[position]) }
        }
    }

    override fun getItemCount(): Int = listGitUser.size

    interface OnItemClickCallback {
        fun onItemClicked(data: GitUser)
    }
}