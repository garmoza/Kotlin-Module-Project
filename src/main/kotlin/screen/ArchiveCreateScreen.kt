package screen

import model.Archive
import java.util.Scanner

class ArchiveCreateScreen(
        private val scanner: Scanner,
        private val stack: ArrayDeque<Screen>,
        private val archives: MutableList<Archive>
) : Screen {

    override fun render() {
        println("Введите название архива")
        val input = scanner.nextLine()
        if (input.isBlank()) {
            println("Название архива не может быть пустым")
        } else {
            archives.add(Archive(input))
            stack.removeLast()
        }
    }
}
