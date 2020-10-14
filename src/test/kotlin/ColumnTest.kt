import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ColumnTest {

    private lateinit var column: Column

    @BeforeEach
    internal fun setUp() {
        column = Column(SCHEMA_NAME)
    }

    @Nested
    inner class SingleValueTests {

        @Test
        fun `applyValue should return nullable string when only value is empty`() {
            val result = column.applyValue("")

            assertEquals(ColumnType.STRING, result.type)
            assertEquals(true, result.nullable)
        }

        @Test
        fun `applyValue should return non nullable double when only value is double`() {
            val result = column.applyValue("124231.41324132412")

            assertEquals(ColumnType.DOUBLE, result.type)
            assertEquals(false, result.nullable)
        }

        @Test
        fun `applyValue should return non nullable integer when only value is integer`() {
            val result = column.applyValue("41324132412")

            assertEquals(ColumnType.INTEGER, result.type)
            assertEquals(false, result.nullable)
        }

        @Test
        fun `applyValue should return non nullable integer when only value is string`() {
            val result = column.applyValue("asdf134")

            assertEquals(ColumnType.STRING, result.type)
            assertEquals(false, result.nullable)
        }
    }

    @Nested
    inner class MultipleValuesTests {
        @Test
        fun `applyValue should return non nullable string when only non null value values are given`() {
            val result = column.applyValue("asdf134")
                .applyValue("asiohfg3")
                .applyValue("dfg24t")
                .applyValue("w4zh")
                .applyValue("3456gh.34")

            assertEquals(ColumnType.STRING, result.type)
            assertEquals(false, result.nullable)
        }

        @Test
        fun `applyValue should return non nullable double when only non null value values are given`() {
            val result = column.applyValue("34.12")
                .applyValue("345.2")
                .applyValue("23452.1")
                .applyValue("462345.12")
                .applyValue("3456.34")

            assertEquals(ColumnType.DOUBLE, result.type)
            assertEquals(false, result.nullable)
        }
    }

    @Nested
    inner class MixedNonNullTypes {

        @Test
        fun `applyValue should return non nullable string when first value is string and second not`() {
            val first = column.applyValue("1324a")
            val second = first.applyValue("1234")

            assertEquals(ColumnType.STRING, second.type)
            assertEquals(false, second.nullable)
        }

        @Test
        fun `applyValue should return non nullable string when first value is double and second string`() {
            val first = column.applyValue("1234.14")
            val second = first.applyValue("14a")

            assertEquals(ColumnType.STRING, second.type)
            assertEquals(false, second.nullable)
        }

        @Test
        fun `applyValue should return non nullable string when first value is integer and second string`() {
            val first = column.applyValue("1234")
            val second = first.applyValue("14a")

            assertEquals(ColumnType.STRING, second.type)
            assertEquals(false, second.nullable)
        }


        @Test
        fun `applyValue should return non nullable double when first value is integer and second double`() {
            val first = column.applyValue("1234")
            val second = first.applyValue("143124.223")

            assertEquals(ColumnType.DOUBLE, second.type)
            assertEquals(false, second.nullable)
        }

        @Test
        fun `applyValue should return non nullable double when first value is double and second integer`() {
            val first = column.applyValue("1234.1324")
            val second = first.applyValue("143124")

            assertEquals(ColumnType.DOUBLE, second.type)
            assertEquals(false, second.nullable)
        }
    }

    @Nested
    inner class MixedNullTypes {

        @Test
        fun `applyValue should return nullable double when first value is integer and second empty`() {
            val first = column.applyValue("1234")
            val second = first.applyValue("")

            assertEquals(ColumnType.DOUBLE, second.type)
            assertEquals(true, second.nullable)
        }

        @Test
        fun `applyValue should return nullable double when first value is empty and second integer`() {
            val first = column.applyValue("")
            val second = first.applyValue("1234")

            assertEquals(ColumnType.DOUBLE, second.type)
            assertEquals(true, second.nullable)
        }

        @Test
        fun `applyValue should return non nullable double when first value is empty and second double`() {
            val first = column.applyValue("")
            val second = first.applyValue("123.32")

            assertEquals(ColumnType.DOUBLE, second.type)
            assertEquals(true, second.nullable)
        }

        @Test
        fun `applyValue should return nullable double when first value is double and second empty`() {
            val first = column.applyValue("70.37")
            val second = first.applyValue("")

            assertEquals(ColumnType.DOUBLE, second.type)
            assertEquals(true, second.nullable)
        }
    }

    companion object {
        private const val SCHEMA_NAME = "some name"
    }
}