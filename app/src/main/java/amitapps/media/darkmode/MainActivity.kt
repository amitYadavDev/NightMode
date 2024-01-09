package amitapps.media.darkmode

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate


class MainActivity : AppCompatActivity() {
    lateinit var darkModeButton: Button
    // Saving state of our app
    // using SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        darkModeButton = findViewById(R.id.mode)
        val sharedPreferences: SharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        var isDarkModeOn = sharedPreferences.getBoolean("isDarkModeOn", false)
        // When user reopens the app
        // dark/light mode remains persistence
        if (isDarkModeOn) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            darkModeButton.text = "Disable Dark Mode"
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            darkModeButton.text = "Enable Dark Mode"
        }

        activeDarkMode(editor)
    }

    private fun activeDarkMode(editor: SharedPreferences.Editor) {

        darkModeButton.setOnClickListener {
            if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                editor.putBoolean("isDarkModeOn", false)
                editor.apply()
                darkModeButton.text = "Enable Dark Mode";
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                editor.putBoolean("isDarkModeOn", true)
                editor.apply()
                darkModeButton.text = "Disable Dark Mode";
            }
        }
    }
}