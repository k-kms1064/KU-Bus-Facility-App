package com.konkuk.ottae.facility

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.konkuk.ottae.R

class FacilityCategoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facility_category)

        findViewById<ImageView>(R.id.btnBack).setOnClickListener { finish() }

        val btnKorean = findViewById<LinearLayout>(R.id.btnKorean)
        val btnChinese = findViewById<LinearLayout>(R.id.btnChinese)
        val btnJapanese = findViewById<LinearLayout>(R.id.btnJapanese)
        val btnChicken = findViewById<LinearLayout>(R.id.btnChicken)
        val btnBurger = findViewById<LinearLayout>(R.id.btnBurger)
        val btnSnack = findViewById<LinearLayout>(R.id.btnSnack)
        val btnCafe = findViewById<LinearLayout>(R.id.btnCafe)
        val btnMart = findViewById<LinearLayout>(R.id.btnMart)

        btnKorean.setOnClickListener { openCategory("한식") }
        btnChinese.setOnClickListener { openCategory("중식") }
        btnJapanese.setOnClickListener { openCategory("일식/돈까스") }
        btnChicken.setOnClickListener { openCategory("치킨/피자") }
        btnBurger.setOnClickListener { openCategory("버거") }
        btnSnack.setOnClickListener { openCategory("분식") }
        btnCafe.setOnClickListener { openCategory("카페") }
        btnMart.setOnClickListener { openCategory("편의점") }
    }

    private fun openCategory(category: String) {
        val intent = Intent(this, FacilityListActivity::class.java)
        intent.putExtra("category", category)
        startActivity(intent)
    }
}
