package hr.markopetricevic.autodiygarage.models

import kotlin.random.Random

data class RepairsInfo(
    var uid: String = generateRandomStringId(),
    var image: String = "",
    var title: String = "",
    var description: String = ""
)



fun generateRandomStringId(): String {
    val characters = "abcdefghijklmnopqrstuvwxyz123456789"
    val random = Random
    val sb = StringBuilder(16)

    repeat(16) {
        val randomIndex = random.nextInt(characters.length)
        sb.append(characters[randomIndex])
    }

    return sb.toString()
}