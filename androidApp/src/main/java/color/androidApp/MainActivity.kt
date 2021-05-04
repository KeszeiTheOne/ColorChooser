package color.androidApp

import android.graphics.Color
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import color.shared.ColorChooser
import color.shared.ColorChooserRequest
import color.shared.ColorChooserView
import color.shared.Greeting
import kotlin.properties.Delegates

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    private var chooser: ColorChooser by Delegates.notNull()
    private var view: ColorChooserView by Delegates.notNull()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val text: TextView = findViewById(R.id.text_view)
        view = ColorChooserSeekBarView(text)
        chooser = ColorChooser(view)

        val tv: TextView = text

        subscribeOnChangeColor(R.id.redSeekBar, "red")
        subscribeOnChangeColor(R.id.blueSeekBar, "blue")
        subscribeOnChangeColor(R.id.greenSeekBar, "green")
        tv.setText("Red: 0, Green: 0, Blue: 0")
    }

    private fun subscribeOnChangeColor(seekBarId: Int, color: String) {
        val seekBar: SeekBar = findViewById(seekBarId)
        val step = 1
        val max = 255
        val min = 0

// Ex :
// If you want values from 3 to 5 with a step of 0.1 (3, 3.1, 3.2, ..., 5)
// this means that you have 21 possible values in the seekbar.
// So the range of the seek bar will be [0 ; (5-3)/0.1 = 20].

// Ex :
// If you want values from 3 to 5 with a step of 0.1 (3, 3.1, 3.2, ..., 5)
// this means that you have 21 possible values in the seekbar.
// So the range of the seek bar will be [0 ; (5-3)/0.1 = 20].
        seekBar.setMax((max - min) / step)
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                chooser.run(request(color, progress))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
    }

    private fun request(color: String, value: Int): ColorChooserRequest {
        val request = ColorChooserRequest()
        request.color = color
        request.value = value

        return request
    }


}

class ColorChooserSeekBarView(private var text: TextView) : ColorChooserView {

    private var red: Int = 0;
    private var green: Int = 0;
    private var blue: Int = 0;
    override fun chooseColor(color: String, value: Int) {

        if (color== "red"){
            red = value
        }
        if (color== "green"){
            green = value
        }
        if (color== "blue"){
            blue = value
        }

        text.setText("Red: $red, Green: $green, Blue: $blue")
        text.setBackgroundColor(Color.rgb(red, green, blue))
    }

}