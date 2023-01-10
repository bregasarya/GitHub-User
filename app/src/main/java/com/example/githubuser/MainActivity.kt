package com.example.githubuser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<GitUser>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvUser.setHasFixedSize(true)
        list.addAll(listUser)
        showRecyclerList()
    }

    private val listUser: ArrayList<GitUser>
        get() {
            val dataName = resources.getStringArray(R.array.name)
            val dataUsername = resources.getStringArray(R.array.username)
            val dataAvatar = resources.obtainTypedArray(R.array.avatar)
            val dataLocation = resources.getStringArray(R.array.location)
            val dataRepository = resources.getStringArray(R.array.repository)
            val dataCompany = resources.getStringArray(R.array.company)
            val dataFollowers = resources.getStringArray(R.array.followers)
            val dataFollowing = resources.getStringArray(R.array.following)
            val listUser = ArrayList<GitUser>()
            for (i in dataName.indices) {
                val User = GitUser(dataName[i], dataUsername[i], dataAvatar.getResourceId(i, -1), dataLocation[i], dataRepository[i], dataCompany[i], dataFollowers[i], dataFollowing[i])
                listUser.add(User)
            }
            return listUser
        }

    private fun showRecyclerList() {
        binding.rvUser.layoutManager = LinearLayoutManager(this)
        val listGitUserAdapter = ListGitUserAdapter(list)
        binding.rvUser.adapter = listGitUserAdapter

        listGitUserAdapter.setOnItemClickCallback(object : ListGitUserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: GitUser) {
                showSelectedUser(data)
            }
        })
    }

    private fun showSelectedUser(user: GitUser) {
        val intentToDetail = Intent(this@MainActivity, DetailUserActivity::class.java)
        intentToDetail.putExtra(DetailUserActivity.detail_user, user)
        startActivity(intentToDetail)
    }
}