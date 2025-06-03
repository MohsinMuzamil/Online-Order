package com.mohsin.onlineorder


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class OrderConfirmationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)

        val name = intent.getStringExtra("userName") ?: ""
        val address = intent.getStringExtra("userAddress") ?: ""
        val phone = intent.getStringExtra("userPhone") ?: ""
        val itemName = intent.getStringExtra("itemName") ?: ""
        val itemPrice = intent.getStringExtra("itemPrice") ?: ""

        findViewById<TextView>(R.id.tvReceiptName).text = "Name: $name"
        findViewById<TextView>(R.id.tvReceiptAddress).text = "Address: $address"
        findViewById<TextView>(R.id.tvReceiptPhone).text = "Phone: $phone"
        findViewById<TextView>(R.id.tvReceiptItem).text = "Item: $itemName"
        findViewById<TextView>(R.id.tvReceiptPrice).text = "Price: $itemPrice"

        findViewById<Button>(R.id.btnBackToMenu).setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }
}