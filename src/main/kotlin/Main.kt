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
        menu.add(MenuItem("Выход", ::onClose))
    }

    private fun renderMenu() {
        for ((index, item) in menu.withIndex()) {
            println("$index. ${item.label}")
        }
    }

    override fun render() {
        println("Это первый экрна")

        renderMenu()

        val input: String = scanner.nextLine()
        menu[input.toInt()].onSelect()
    }

    fun onClose() {
        stack.removeLast()
        println("Пока! Пока!")
    }
}

fun main(args: Array<String>) {
    val stack = ArrayDeque<Screen>()
    stack.add(ArchiveSelectableScreen(stack))

    while (!stack.isEmpty()) {
        stack.last().render()
    }
}