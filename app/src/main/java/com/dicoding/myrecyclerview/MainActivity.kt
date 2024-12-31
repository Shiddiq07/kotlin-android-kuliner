package com.dicoding.myrecyclerview

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.myrecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var rvFoods: RecyclerView
    private val list = ArrayList<Food>()
    private lateinit var binding: ActivityMainBinding

    private fun getListFoods(): ArrayList<Food> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val listFood = ArrayList<Food>()
        for (i in dataName.indices) {
            val food = Food(dataName[i], dataDescription[i],  dataPhoto[i])
            listFood.add(food)
        }
        return listFood
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        rvFoods = findViewById(R.id.rv_foods)
        rvFoods.setHasFixedSize(true)
        list.addAll(getListFoods())
        showRecyclerList()
    }

    private fun showRecyclerList() {
        rvFoods.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListFoodAdapter(list)
        rvFoods.adapter = listHeroAdapter
        listHeroAdapter.setOnItemClickCallback(object : ListFoodAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Food) {
                showSelectedFood(data)
            }


        })
    }
    private fun showSelectedFood(food: Food) {
        Toast.makeText(this, "Kamu memilih " + food.name, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        untuk tampilan agar menjadi grid saat landscape
//        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            rvHeroes.layoutManager = GridLayoutManager(this, 2)
//        } else {
//            rvHeroes.layoutManager = LinearLayoutManager(this)
//        }
        when (item.itemId) {
            R.id.action_list -> {
                rvFoods.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                rvFoods.layoutManager = GridLayoutManager(this, 2)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}