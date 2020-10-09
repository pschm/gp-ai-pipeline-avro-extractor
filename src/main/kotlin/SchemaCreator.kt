import org.apache.avro.Schema
import org.apache.avro.SchemaBuilder

class SchemaCreator(
    private val schemaName: String,
    private val columns: List<Column>
) {
    fun create(): Schema {
        var schemaBuilder = SchemaBuilder
            .builder()
            .record(schemaName)
            .fields()

        columns.forEach {
            val namedColumnBuilder = schemaBuilder.name(cleanName(it.name))

            val nullColumnBuilder = if (it.nullable) {
                namedColumnBuilder.type().nullable()
            } else {
                namedColumnBuilder.type()
            }

            val typeColumnBuilder = when (it.type) {
                ColumnType.STRING -> nullColumnBuilder.stringType()
                ColumnType.INTEGER -> nullColumnBuilder.intType()
                ColumnType.DOUBLE -> nullColumnBuilder.doubleType()
            }

            schemaBuilder = typeColumnBuilder.noDefault()
        }

        return schemaBuilder.endRecord()
    }

    private fun cleanName(name: String): String = name.replace(".", "")
}