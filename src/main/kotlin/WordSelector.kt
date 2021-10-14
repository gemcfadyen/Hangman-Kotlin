import kotlin.random.Random

fun selectWordFrom(dictionary: List<String>, randomGenerator: Random): String {
    val wordIndex = randomGenerator.nextInt(0, dictionary.size)
    return dictionary[wordIndex]
}