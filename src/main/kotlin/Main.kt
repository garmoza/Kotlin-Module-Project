import model.Archive
import screen.ArchiveSelectScreen
import screen.Screen

fun main() {
    val archives: MutableList<Archive> = mutableListOf()

    val stack = ArrayDeque<Screen>()
    stack.add(ArchiveSelectScreen(stack, archives))

    while (!stack.isEmpty()) {
        stack.last().render()
    }
}