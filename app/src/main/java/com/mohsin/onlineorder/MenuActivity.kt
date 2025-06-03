package com.mohsin.onlineorder

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohsin.onlineorder.model.MenuItem
import androidx.recyclerview.widget.RecyclerView
import android.widget.EditText
import com.mohsin.onlineorder.Adapter.MenuAdapter

class MenuActivity : AppCompatActivity() {

    private lateinit var adapter: MenuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val menuList = listOf(
            MenuItem(
                name = "BBQ chicken pizza",
                description = "Delicious BBQ chicken pizza with extra cheese.",
                imageResId = R.drawable.pizza,
                price = "Rs. 900"
            ),
            MenuItem(
                name = "Burger",
                description = "Big beef burger with fresh veggies.",
                imageResId = R.drawable.burger,
                price = "Rs. 500"
            ),
            MenuItem(
                name = "Super Deal",
                description = "Family combo deal.",
                imageResId = R.drawable.deal,
                price = "Rs. 1200",
                isDeal = true,
                dealItems = "1 Large Pizza, 2 Drinks, 2 Fried chicken"
            ),
            MenuItem(
                name = "Fries",
                description = "Crispy golden fries.",
                imageResId = R.drawable.fries,
                price = "Rs. 200"
            )
        )

        adapter = MenuAdapter(menuList) { item ->
            // Handle item click: launch order page
            val intent = Intent(this, OrderDetailsActivity::class.java)
            intent.putExtra("itemName", item.name)
            intent.putExtra("itemPrice", item.price)
            intent.putExtra("itemImageRes", item.imageResId)
            startActivity(intent)
        }

        val rvMenu = findViewById<RecyclerView>(R.id.rvMenu)
        rvMenu.layoutManager = LinearLayoutManager(this)
        rvMenu.adapter = adapter

        val etSearch = findViewById<EditText>(R.id.etSearch)
        etSearch.clearFocus()
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter(s.toString())
            }
        })
    }
}