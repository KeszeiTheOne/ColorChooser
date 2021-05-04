package color.shared

import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertSame

class ColorChooserTest {

    private var view: ColorChooserViewFake? = null;
    private var controller: ColorChooser? = null;

    @BeforeTest
    fun before() {
        view = ColorChooserViewFake()
        controller = ColorChooser(view)
    }

    @Test
    fun choosedColor() {
        run(request())

        assertSame("red", view?.choosedColor)
        assertSame(100, view?.choosedValue)
        assertSame(1, view?.choosedTimes)
    }

    @Test
    fun invalidColor() {
        val request = request()
        request.color = "invalid"
        assertFailsWith<RuntimeException> {
            run(request)
        }
    }

    @Test
    fun emptyColo() {
        val request = request()
        request.color = null
        assertFailsWith<RuntimeException> {
            run(request)
        }
    }

    @Test
    fun invalidValue() {
        val request = request()
        request.value = 256
        assertFailsWith<RuntimeException> {
            run(request)
        }
    }

    @Test
    fun nullValue() {
        val request = request()
        request.value = -1

        assertFailsWith<RuntimeException> {
            run(request)
        }
    }


    private fun run(request: ColorChooserRequest) {
        ColorChooser(view).run(request)
    }

    private fun request(): ColorChooserRequest {
        var request = ColorChooserRequest()
        request.color = "red"
        request.value = 100
        return request
    }
}

class ColorChooserViewFake : ColorChooserView {
    var choosedColor: String? = null
    var choosedValue: Int? = null
    var choosedTimes: Int = 0
    override fun chooseColor(color: String, value: Int) {
        choosedColor = color
        choosedValue = value
        choosedTimes++
    }


}

