import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter


fun main() {
    val file = File("data/monthly_flights.csv")
    val rows = csvReader().readAllWithHeader(file)

    val analyzedColumns = analyzeData(rows)

    val schema = SchemaCreator("raw_data", analyzedColumns).create()

    val writer = BufferedWriter(FileWriter("out.avsc"))

    writer.write(schema.toString())
    writer.close()
}

private fun analyzeData(
    rows: List<Map<String, String>>
): List<Column> {
    val columns = rows.first().keys
        .map { Column(it) }

    return rows.fold(columns) { givenRows, data ->
        (givenRows zip data.values).map { it.first.applyValue(it.second) }
    }
}