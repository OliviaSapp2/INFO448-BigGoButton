package edu.uw.ischool.osapp2.biggobutton

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.graphics.Color
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var pushButton: Button
    private var pushCount = 0
    private val random = Random
    private var isAnimating = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        pushButton = findViewById(R.id.button)
        pushButton.setOnClickListener{

            //update the button text based on the push count
            pushCount ++
            val timesText = if (pushCount == 1) "" else "s"
            pushButton.text = getString(R.string.pushed_count, pushCount, timesText)

            // Change background color to a random color
            pushButton.setBackgroundColor(Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256)))

            // Change text color to a random color
            pushButton.setTextColor(Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256)))

            //animate the button
            if(isAnimating){
                //stop rotating animation
                pushButton.clearAnimation()
                isAnimating = false
            } else {
                //start rotating animation
                val rotateAnimation = RotateAnimation(
                    0f, 360f, // Start and end degrees
                    Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point X
                    Animation.RELATIVE_TO_SELF, 0.5f // Pivot point Y
                )
                rotateAnimation.duration = 1000 // Duration in milliseconds
                rotateAnimation.repeatCount = Animation.INFINITE
                rotateAnimation.repeatMode = Animation.RESTART

                pushButton.startAnimation(rotateAnimation)
                isAnimating = true
            }
        }
    }

}