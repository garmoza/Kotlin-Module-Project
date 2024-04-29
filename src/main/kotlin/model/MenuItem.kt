package model

data class MenuItem(val label: String, val onSelect: () -> Unit)
