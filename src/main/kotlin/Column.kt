data class Column(
    val name: String,
    private val _type: ColumnType? = null,
    private val _columnHasNullValues: Boolean = false
) {

    val nullable: Boolean
        get() = _columnHasNullValues

    val type: ColumnType
        get() {
            if (nullable && _type == ColumnType.INTEGER) return ColumnType.DOUBLE
            return _type ?: ColumnType.STRING
        }

    fun applyValue(value: String): Column {
        val isBlank = value.isBlank() || value == "NaN" || value == "Null"

        val newType = when {
            isBlank -> _type
            isAlreadyString() -> ColumnType.STRING
            isDouble(value) -> ColumnType.DOUBLE
            isInteger(value) && isTypeDouble() -> ColumnType.DOUBLE
            isInteger(value) -> ColumnType.INTEGER
            else -> ColumnType.STRING
        }

        return copy(
            _columnHasNullValues = isBlank || this.nullable,
            _type = newType
        )
    }

    private fun isTypeDouble() = _type == ColumnType.DOUBLE

    private fun isAlreadyString() = _type == ColumnType.STRING

    private fun isDouble(value: String) = value.matches(Regex("[-]?[0-9]+\\.[0-9]+"))

    private fun isInteger(value: String) = value.matches(Regex("[-]?[0-9]+"))
}