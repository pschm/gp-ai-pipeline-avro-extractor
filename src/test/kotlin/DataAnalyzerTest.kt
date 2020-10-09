import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DataAnalyzerTest {

    @Test
    fun name() {

        val list = listOf(
            mapOf(Pair("colName", "70.37"), Pair("colName2", "456")),
            mapOf(Pair("colName", "70.30"), Pair("colName2", "st")),
            mapOf(Pair("colName", "70.34"), Pair("colName2", "5.34")),
            mapOf(Pair("colName", "70.31"), Pair("colName2", "4")),
            mapOf(Pair("colName", "70.38"), Pair("colName2", "6")),
        )

        val result = DataAnalyzer().analyze(list)
        assertEquals(listOf(
            Column("colName", ColumnType.DOUBLE, false),
            Column("colName2", ColumnType.STRING, false),
        ), result)
    }
}