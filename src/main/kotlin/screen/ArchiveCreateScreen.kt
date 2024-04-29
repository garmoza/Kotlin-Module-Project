package screen

import model.Archive
import java.util.Scanner

class ArchiveCreateScreen(
        private val stack: ArrayDeque<Screen>,
        private val archives: MutableList<Archive>
) : Screen {

    private val scanner: Scanner = Scanner(System.`in`)

    override fun render() {
        println("Введите название архива")
        val input = scanner.nextLine()
        if (input != "") {
            archives.add(Archive(input))
            stack.removeLast()
        } else {
            println("Название архива не может быть пустым")
        }
    }
}
