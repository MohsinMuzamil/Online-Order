package com.mohsin.onlineorder

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class OrderDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_details)

        val etUserName = findViewById<EditText>(R.id.etUserName)
        val etAddress = findViewById<EditText>(R.id.etAddress)
        val etPhone = findViewById<EditText>(R.id.etPhone)
        val tvSelectedItemName = findViewById<TextView>(R.id.tvSelectedItemName)
        val tvSelectedItemPrice = findViewById<TextView>(R.id.tvSelectedItemPrice)
        val imgSelectedItem = findViewById<ImageView>(R.id.imgSelectedItem)
        val btnPlaceOrder = findViewById<Button>(R.id.btnPlaceOrder)

        // Get data from intent (sent from previous activity)
        val itemName = intent.getStringExtra("itemName") ?: ""
        val itemPrice = intent.getStringExtra("itemPrice") ?: ""
        val itemImageRes = intent.getIntExtra("itemImageRes", R.drawable.ic_launcher_foreground)

        tvSelectedItemName.text = itemName
        tvSelectedItemPrice.text = itemPrice
        imgSelectedItem.setImageResource(itemImageRes)

        btnPlaceOrder.setOnClickListener {
            val name = etUserName.text.toString()
            val address = etAddress.text.toString()
            val phone = etPhone.text.toString()

            if (name.isEmpty() || address.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, OrderConfirmationActivity::class.java)
                intent.putExtra("userName", name)
                intent.putExtra("userAddress", address)
                intent.putExtra("userPhone", phone)
                intent.putExtra("itemName", itemName)
                intent.putExtra("itemPrice", itemPrice)
                startActivity(intent)
                finish()
                Toast.makeText(this, "Order placed! Thank you, $name", Toast.LENGTH_LONG).show()
            }
        }
    }
}