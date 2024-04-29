package screen

import model.Archive
import model.MenuItem
import java.lang.NumberFormatException
import java.util.Scanner

class NoteSelectScreen(
        private val stack: ArrayDeque<Screen>,
        private val archive: Archive
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
                println("Указанной цифры нет. Пожалуйста, введите идин из доступных вариантов")
            }
        } catch (e: NumberFormatException) {
            println("Пожалуйста, введите цифру")
        }
    }

    private fun updateMenu() {
        menu.clear()
        menu.add(MenuItem("Создать заметку") { stack.addLast(NoteCreateScreen(stack, archive)) })
        for (note in archive.notes) {
            menu.add(MenuItem(note.name) { stack.addLast(NoteViewScreen(stack, note)) })
        }
        menu.add(MenuItem("Назад") { stack.removeLast() })
    }

    private fun renderMenu() {
        for ((index, item) in menu.withIndex()) {
            println("$index. ${item.label}")
        }
    }
}