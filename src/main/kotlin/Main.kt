import java.lang.NumberFormatException
import java.util.Scanner

interface Screen {
    fun render()
}

data class MenuItem(val label: String, val onSelect: () -> Unit)

class Archive(val name: String, val notes: List<String>)

class ArchiveSelectScreen(
        stack: ArrayDeque<Screen>,
        archives: MutableList<Archive>
) : Screen {

    private val scanner: Scanner = Scanner(System.`in`)
    private val menu: MutableList<MenuItem> = mutableListOf()

    init {
        menu.add(MenuItem("Созадть архив") { stack.addLast(ArchiveCreateScreen(stack)) })
        for (archive in archives) {
            menu.add(MenuItem(archive.name) { stack.addLast(NoteSelectScreen(stack)) })
        }
        menu.add(MenuItem("Выход") {
            stack.removeLast()
            println("Пока! Пока!")
        })
    }

    override fun render() {
        renderMenu()

        val input: String = scanner.nextLine()
        try {
            val index = input.toInt()

            if (index in menu.indices) {
                menu[input.toInt()].onSelect()
            } else {
                println("Указанной цифры нет. Пожалуйста, введите идин из доступных вариантов")
            }
        } catch (e: NumberFormatException) {
            println("Пожалуйста, введите цифру")
        }
    }

    private fun renderMenu() {
        for ((index, item) in menu.withIndex()) {
            println("$index. ${item.label}")
        }
    }
}

class ArchiveCreateScreen(
        stack: ArrayDeque<Screen>
) : Screen {

    private val scanner: Scanner = Scanner(System.`in`)

    override fun render() {
        println("Введите название архива")
        val input: String = scanner.nextLine()


    }
}

class NoteSelectScreen(
        stack: ArrayDeque<Screen>
) : Screen {

    override fun render() {
        TODO("Not yet implemented")
    }
}

fun main(args: Array<String>) {
    val archives: MutableList<Archive> = mutableListOf()

    val stack = ArrayDeque<Screen>()
    stack.add(ArchiveSelectScreen(stack, archives))

    while (!stack.isEmpty()) {
        stack.last().render()
    }
}