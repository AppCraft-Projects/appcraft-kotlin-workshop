package workshop.kotlin._10_destructuring

fun main(args: Array<String>) {
    data class Position(val column: Int,
                        val row: Int)

    var map: HashMap<Int, Position> = HashMap()
    map.put(1, Position(3, 4))

    for((key, value) in map){
        println("Key: $key, Value: $value")
    }
}