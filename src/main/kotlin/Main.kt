import java.lang.NumberFormatException
import java.util.Scanner

class Archive(val notes: List<String>) {
}

interface Screen {

    fun render()
}

//abstract class StackableScreen(private val stack: ArrayDeque<Screen>) : Screen {
//
//    override fun onClose() {
//        stack.removeLast()
//    }
//}

data class MenuItem(val label: String, val onSelect: () -> Unit)

//class SelectableScreen(
//        stack: ArrayDeque<Screen>
//) : StackableScreen(stack) {
//
//    val menu: MutableList<MenuItem> = mutableListOf()
//
//    init {
//        menu.add(MenuItem("Выход", ::onClose))
//    }
//
//    override fun render() {
//        TODO("Not yet implemented")
//    }
//}

class ArchiveSelectableScreen(
        private val stack: ArrayDeque<Screen>
) : Screen {

    private val scanner: Scanner = Scanner(System.`in`)
    private val menu: MutableList<MenuItem> = mutableListOf()

    init {
        menu.add(MenuItem("Созадть архив") { println("Архив создан") })
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
}

fun main(args: Array<String>) {
    val stack = ArrayDeque<Screen>()
    stack.add(ArchiveSelectableScreen(stack))

    while (!stack.isEmpty()) {
        stack.last().render()
    }
}