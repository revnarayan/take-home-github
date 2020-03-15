package com.example.takehome

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.takehome.adapter.RecyclerAdapter
import com.example.takehome.retrofit.GithubApiInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set recycler view adapter and layout manager
        val viewManager = LinearLayoutManager(this)

        recycler_view.apply {

            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager
        }


        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(
                RxJava2CallAdapterFactory.create()
            )
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .baseUrl("https://api.github.com")
            .build()
        val github = retrofit.create(GithubApiInterface::class.java)

        //User Rxjava to subscribe and observe

        get_details_button.setOnClickListener {
            github.getRepoDetail(user_name_text_view.text.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result ->
                        Log.i("github", "success")
                        Glide
                            .with(this)
                            .load(result.avatar_url)
                            .centerCrop()
                            .placeholder(R.drawable.ic_launcher_background)
                            .into(avatar_image);

                        user_name.setText(user_name_text_view.text)

                    },
                    { error ->
                        Log.e("github", error.message.toString())
                    }
                )

            github.fetchRepoList(user_name_text_view.text.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { repoList ->
                        Log.i("github", "success")
                        viewAdapter = RecyclerAdapter(repoList)
                        recycler_view.adapter = viewAdapter
                    },
                    { error ->
                        Log.e("github", error.message.toString())
                    }
                )
        }


    }

}
