import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter


fun main() {
    val file = File("data/monthly_flights.csv")
    val rows = csvReader().readAllWithHeader(file)

    val analyzedColumns = DataAnalyzer().analyze(rows)

    val schema = SchemaCreator("raw_data", analyzedColumns).create()

    val writer = BufferedWriter(FileWriter("raw_data_schema.avsc"))

    writer.write(schema.toString())
    writer.close()

    println("created: raw_data_schema")
}

