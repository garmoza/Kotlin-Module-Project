package screen

import model.Archive
import java.util.Scanner

class NoteSelectScreen(
        private val scanner: Scanner,
        private val stack: ArrayDeque<Screen>,
        private val archive: Archive
) : SelectScreen(scanner) {

    override fun createMenu() {
        menu.add(MenuItem("Создать заметку") { stack.addLast(NoteCreateScreen(scanner, stack, archive)) })
        for (note in archive.notes) {
            menu.add(MenuItem(note.name) { stack.addLast(NoteViewScreen(scanner, stack, note)) })
        }
        menu.add(MenuItem("Назад") { stack.removeLast() })
    }
}