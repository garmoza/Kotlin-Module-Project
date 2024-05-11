package screen

import model.Archive
import java.util.Scanner

class ArchiveSelectScreen(
        private val scanner: Scanner,
        private val stack: ArrayDeque<Screen>,
        private val archives: MutableList<Archive>
) : SelectScreen(scanner) {

    override fun createMenu() {
        menu.add(MenuItem("Созадть архив") { stack.addLast(ArchiveCreateScreen(scanner, stack, archives)) })
        for (archive in archives) {
            menu.add(MenuItem(archive.name) { stack.addLast(NoteSelectScreen(scanner, stack, archive)) })
        }
        menu.add(MenuItem("Выход") {
            stack.removeLast()
            println("Пока! Пока!")
        })
    }
}
