package com.example.task1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.bottomsheet.BottomSheetDialog

class Bootom_Sheet : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bootom_sheet)

        var bottom_BTID:Button=findViewById(R.id.bottom_BTID)
        bottom_BTID.setOnClickListener{
            openbottomsheet()
        }
    }

    private fun openbottomsheet() {
        val dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.bottom_sheet_practice, null)
        dialog.setCancelable(true)
        dialog.setContentView(view)
        dialog.show()
    }
}