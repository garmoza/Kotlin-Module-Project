package screen

import model.Archive
import model.Note
import java.util.Scanner

class NoteCreateScreen(
        private val stack: ArrayDeque<Screen>,
        private val archive: Archive
) : Screen {

    private val scanner: Scanner = Scanner(System.`in`)
    private var noteName: String = ""
    private var noteBody: String = ""

    override fun render() {
        when {
            noteName.isBlank() -> inputNoteName()
            noteBody.isBlank() -> inputNoteBody()
            else -> {
                archive.notes.add(Note(noteName, noteBody))
                stack.removeLast()
            }
        }
    }

    private fun inputNoteName() {
        println("Введите название заметки")
        noteName = scanner.nextLine()
        if (noteName.isBlank()) {
            println("Название заметки не может быть пустым")
        }
    }

    private fun inputNoteBody() {
        println("Введите текст заметки")
        noteBody = scanner.nextLine()
        if (noteBody.isBlank()) {
            println("Текст заметки не может быть пустым")
        }
    }
}