package com.example.tictactoe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        cell_0x0.setOnClickListener(cellClickHandler)
        cell_0x1.setOnClickListener(cellClickHandler)
        cell_0x2.setOnClickListener(cellClickHandler)

        cell_1x0.setOnClickListener(cellClickHandler)
        cell_1x1.setOnClickListener(cellClickHandler)
        cell_1x2.setOnClickListener(cellClickHandler)

        cell_2x0.setOnClickListener(cellClickHandler)
        cell_2x1.setOnClickListener(cellClickHandler)
        cell_2x2.setOnClickListener(cellClickHandler)
    }

    val cellClickHandler = View.OnClickListener {
        view ->
        when(view.id) {
            cell_0x0.id -> Toast.makeText(this, "cell 0x0", Toast.LENGTH_SHORT).show()
            cell_0x1.id -> Toast.makeText(this, "cell 0x1", Toast.LENGTH_SHORT).show()
            cell_0x2.id -> Toast.makeText(this, "cell 0x2", Toast.LENGTH_SHORT).show()
            cell_1x0.id -> Toast.makeText(this, "cell 1x0", Toast.LENGTH_SHORT).show()
            cell_1x1.id -> Toast.makeText(this, "cell 1x1", Toast.LENGTH_SHORT).show()
            cell_1x2.id -> Toast.makeText(this, "cell 1x2", Toast.LENGTH_SHORT).show()
            cell_2x0.id -> Toast.makeText(this, "cell 2x0", Toast.LENGTH_SHORT).show()
            cell_2x1.id -> Toast.makeText(this, "cell 2x1", Toast.LENGTH_SHORT).show()
            cell_2x2.id -> Toast.makeText(this, "cell 2x2", Toast.LENGTH_SHORT).show()
        }
    }


}
