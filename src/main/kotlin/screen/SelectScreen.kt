package screen

import model.MenuItem
import java.lang.NumberFormatException
import java.util.Scanner

abstract class SelectScreen(private val scanner: Scanner) : Screen {

    protected val menu: MutableList<MenuItem> = mutableListOf()

    override fun render() {
        menu.clear()
        createMenu()
        renderMenu()
        selectMenu()
    }

    abstract fun createMenu()

    private fun renderMenu() {
        for ((index, item) in menu.withIndex()) {
            println("$index. ${item.label}")
        }
    }

    private fun selectMenu() {
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
}