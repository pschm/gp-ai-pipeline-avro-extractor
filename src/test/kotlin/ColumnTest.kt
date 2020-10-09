import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ColumnTest {

    @Test
    fun applyShouldAlterState() {
        val readRow = Column("name")

        val result = readRow.applyValue("")

        assertEquals(ColumnType.STRING, result.type)
        assertEquals(true, result.nullable)
    }

    @Test
    fun double() {
        val readRow = Column("name")

        val result = readRow.applyValue("124231.41324132412")

        assertEquals(ColumnType.DOUBLE, result.type)
        assertEquals(false, result.nullable)
    }

    @Test
    fun int() {
        val readRow = Column("name")

        val result = readRow.applyValue("41324132412")

        assertEquals(ColumnType.INTEGER, result.type)
        assertEquals(false, result.nullable)
    }

    @Test
    fun overw() {
        val readRow = Column("name")

        val first = readRow.applyValue("1324a")
        val second = first.applyValue("1234")

        assertEquals(ColumnType.STRING, second.type)
        assertEquals(false, second.nullable)
    }

    @Test
    fun ovedrw() {
        val readRow = Column("name")

        val first = readRow.applyValue("1234.14")
        val second = first.applyValue("14a")

        assertEquals(ColumnType.STRING, second.type)
        assertEquals(false, second.nullable)
    }

    @Test
    fun ovedrasdfw() {
        val readRow = Column("name")

        val first = readRow.applyValue("1234")
        val second = first.applyValue("14a")

        assertEquals(ColumnType.STRING, second.type)
        assertEquals(false, second.nullable)
    }

    @Test
    fun ovedrasdfaw() {
        val readRow = Column("name")

        val first = readRow.applyValue("1234")
        val second = first.applyValue("")

        assertEquals(ColumnType.INTEGER, second.type)
        assertEquals(true, second.nullable)
    }

    @Test
    fun ovedraw() {
        val readRow = Column("name")

        val first = readRow.applyValue("1234")
        val second = first.applyValue("143124.223")

        assertEquals(ColumnType.DOUBLE, second.type)
        assertEquals(false, second.nullable)
    }

    @Test
    fun oveasdfdraw() {
        val readRow = Column("name")

        val first = readRow.applyValue("1234.1324")
        val second = first.applyValue("143124")

        assertEquals(ColumnType.DOUBLE, second.type)
        assertEquals(false, second.nullable)
    }

    @Test
    fun dsf() {
        val readRow = Column("name")

        val first = readRow.applyValue("1234")
        val second = first.applyValue("")

        assertEquals(ColumnType.INTEGER, second.type)
        assertEquals(true, second.nullable)
    }

    @Test
    fun afasdf() {
        val readRow = Column("name")

        val first = readRow.applyValue("")
        val second = first.applyValue("1234")

        assertEquals(ColumnType.INTEGER, second.type)
        assertEquals(true, second.nullable)
    }
}