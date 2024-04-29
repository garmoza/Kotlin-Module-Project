package screen

import model.Archive
import model.MenuItem
import java.lang.NumberFormatException
import java.util.Scanner

class ArchiveSelectScreen(
        private val stack: ArrayDeque<Screen>,
        private val archives: MutableList<Archive>
) : Screen {

    private val scanner: Scanner = Scanner(System.`in`)
    private val menu: MutableList<MenuItem> = mutableListOf()

    override fun render() {
        updateMenu()
        renderMenu()

        val input: String = scanner.nextLine()
        try {
            val index = input.toInt()

            if (index in menu.indices) {
                menu[index].onSelect()
            } else {
                println("Указанной цифры нет. Пожалуйста, введите один из доступных вариантов")
            }
        } catch (e: NumberFormatException) {
            println("Пожалуйста, введите цифру")
        }
    }

    private fun updateMenu() {
        menu.clear()
        menu.add(MenuItem("Созадть архив") { stack.addLast(ArchiveCreateScreen(stack, archives)) })
        for (archive in archives) {
            menu.add(MenuItem(archive.name) { stack.addLast(NoteSelectScreen(stack, archive)) })
        }
        menu.add(MenuItem("Выход") {
            stack.removeLast()
            println("Пока! Пока!")
        })
    }

    private fun renderMenu() {
        for ((index, item) in menu.withIndex()) {
            println("$index. ${item.label}")
        }
    }
}
