import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;

class dataParserTest {
    DataParser d = new DataParser("src\\data-daily.csv");
    ArrayList<DataParser.Node> data = d.data;

    @Test
    public void testDataDaily(){
        Assertions.assertEquals(data.get(1117).data, 0.6404508774177021);
    }

    @Test
    public void testCountry(){
        Assertions.assertEquals(data.get(0).country, "China");
        Assertions.assertEquals(data.get(1321).country, "United Kingdom");
    }

    @Test
    public void testDate(){
        Assertions.assertEquals(data.get(15).date, "15-01-23");
    }
}