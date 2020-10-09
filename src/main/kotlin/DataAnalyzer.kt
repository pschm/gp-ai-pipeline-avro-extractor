class DataAnalyzer {

    fun analyze(
        rows: List<Map<String, String>>
    ): List<Column> {
        val columns = rows.first().keys
            .map { Column(it) }


        return rows.fold(columns) { givenRows, data ->
            givenRows.map {
                it.applyValue(data[it.name]!!)
            }
        }
    }
}