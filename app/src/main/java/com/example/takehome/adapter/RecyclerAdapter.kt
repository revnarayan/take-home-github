package com.example.takehome.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.takehome.R
import com.example.takehome.model.Repo
import kotlinx.android.synthetic.main.repo_list_item.view.*

class RecyclerAdapter(private val repoList: List<Repo>) :
    RecyclerView.Adapter<RecyclerAdapter.RepoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.repo_list_item, parent, false)
        return RepoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return repoList.size
        }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
       repoList.run {
           val repositoryName = this[position].name
           val repositoryDescription = this[position].descripton


           holder.repoName.text = repositoryName
           holder.repoDescription.text = repositoryDescription
       }
    }

    class RepoViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var repoName = itemView.repo_name
        val repoDescription = itemView.repo_description

    }

}