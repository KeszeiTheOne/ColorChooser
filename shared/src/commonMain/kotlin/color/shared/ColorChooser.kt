package color.shared

class ColorChooser(private var view: ColorChooserView?) {

    fun run(request: ColorChooserRequest) {
        val color = request.color
        if (!validColor(color)) {
            throw RuntimeException()
        }
        val value = request.value
        if (!validValue(value)) {
            throw RuntimeException()
        }
        view?.chooseColor(color!!, value!!)
    }

    private fun validColor(color: String?): Boolean {
        if (null === color) {
            return false
        }

        return color.equals("red") ||
                color.equals("blue") ||
                color.equals("green")
    }

    private fun validValue(value: Int?): Boolean {
        if (null === value) {
            return false
        }

        return value <= 255 && 0 <= value
    }

}