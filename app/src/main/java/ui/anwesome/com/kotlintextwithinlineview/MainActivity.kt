package ui.anwesome.com.kotlintextwithinlineview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import ui.anwesome.com.textwithinlineview.TextWithinLineView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TextWithinLineView.create(this,"Hello World")
    }
}
