package com.example.hilttraining.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hilttraining.R
import com.example.hilttraining.data.Heroes
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModel: MainViewModel
    @Inject
    lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        observer()
    }

    private fun observer() {
        viewModel.getHeroes().observe(this, {
            if (it.isEmpty()) {
                progress_bar.visibility = View.VISIBLE
            } else {
                progress_bar.visibility = View.GONE
                val heroes = mutableListOf<Heroes>()

                it.forEach { data ->
                    heroes.add(data)
                }
                heroes.sortBy { hero ->
                    hero.localized_name
                }

                mainAdapter.updateItems(heroes)
            }
        })
    }
}