import model.Archive
import screen.ArchiveSelectScreen
import screen.Screen
import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    val stack = ArrayDeque<Screen>()
    val archives: MutableList<Archive> = mutableListOf()

    stack.add(ArchiveSelectScreen(scanner, stack, archives))

    while (!stack.isEmpty()) {
        stack.last().render()
    }
}