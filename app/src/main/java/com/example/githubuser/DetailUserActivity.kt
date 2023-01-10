package com.example.githubuser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.githubuser.databinding.ActivityDetailUserBinding

class DetailUserActivity: AppCompatActivity(){
    companion object {
        const val detail_user = "extra_user"
    }

    private lateinit var binding:  ActivityDetailUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<GitUser>(detail_user) as GitUser
        binding.apply {
            tvDetailAvatar.setImageResource(data.avatar)
            tvDetailName.text = data.name
            tvDetailUsername.text = "@ ${data.username}"
            tvDetailRFF.text = "Repository : ${data.repository} | Followers : ${data.followers} | Following : ${data.following}"
            tvDetailCompany.text = "Company : ${data.company}"
            tvDetailLocation.text = "Location : ${data.location}"
        }

        setActionBar(data.username)
    }

    private fun setActionBar(title: String){
        supportActionBar?.title = title
    }
}
