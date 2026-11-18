package com.spectrumai.app

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.net.Uri
import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.widget.*

class MainActivity : Activity() {
    private val bg = Color.rgb(12, 15, 25)
    private val panel = Color.rgb(24, 29, 45)
    private val accent = Color.rgb(91, 116, 255)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = bg
        window.navigationBarColor = bg
        val root = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL; setPadding(22, 28, 22, 20); setBackgroundColor(bg) }
        val title = TextView(this).apply { text = "SPECTRUM AI"; textSize = 27f; setTextColor(Color.WHITE); gravity = Gravity.CENTER; typeface = android.graphics.Typeface.DEFAULT_BOLD }
        val subtitle = TextView(this).apply { text = "PUBLIC SOURCE SEARCH"; textSize = 11f; setTextColor(0xFF9AA6C8.toInt()); gravity = Gravity.CENTER; setPadding(0, 5, 0, 24) }
        root.addView(title); root.addView(subtitle)
        val type = Spinner(this)
        val types = listOf("Имя или организация", "Никнейм", "Email (только свой или с разрешением)", "Телефон (только свой или с разрешением)")
        type.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, types)
        root.addView(type, matchWrap())
        val query = EditText(this).apply { hint = "Введите запрос для открытого поиска"; setHintTextColor(0xFF8993AD.toInt()); setTextColor(Color.WHITE); textSize = 16f; setPadding(16, 14, 16, 14); setBackgroundColor(panel); setSingleLine(true) }
        val qParams = matchWrap().apply { topMargin = 14 }
        root.addView(query, qParams)
        val button = Button(this).apply { text = "ИСКАТЬ В ОТКРЫТЫХ ИСТОЧНИКАХ"; setTextColor(Color.WHITE); setBackgroundColor(accent) }
        val bParams = matchWrap().apply { topMargin = 16 }
        root.addView(button, bParams)
        val note = TextView(this).apply { text = "Поиск открывает внешние поисковые системы. Приложение не подключается к закрытым базам, банкам или государственным аккаунтам. Не вводите чужие персональные данные без разрешения."; textSize = 12f; setTextColor(0xFF9AA6C8.toInt()); setPadding(4, 22, 4, 0) }
        root.addView(note)
        button.setOnClickListener {
            val value = query.text.toString().trim()
            if (value.isEmpty()) { Toast.makeText(this, "Сначала введите запрос", Toast.LENGTH_SHORT).show(); return@setOnClickListener }
            val url = "https://www.google.com/search?q=" + Uri.encode(value)
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        }
        setContentView(ScrollView(this).apply { addView(root) })
    }
    private fun matchWrap() = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
}
