package screen

import model.Note
import java.util.Scanner

class NoteViewScreen(
        private val scanner: Scanner,
        private val stack: ArrayDeque<Screen>,
        private val note: Note
) : Screen {

    override fun render() {
        println(note.name)
        println(note.body)
        println("Нажмите, чтобы продолжить...")
        scanner.nextLine()
        stack.removeLast()
    }
}