package workshop.kotlin._07_nullability

// TODO: find the error in this code and fix it to pass the test
class MysteryNull {

    private val c: String

    init {
        bar()
        c = ""
    }
    private fun bar() {
        println(c.length)
    }
}