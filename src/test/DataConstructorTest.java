import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DataConstructorTest {
    DataParser dP = new DataParser("src\\data-monthly.csv");
    DataConstructor d = new DataConstructor(dP.data, 24);

    @Test
    public void testData(){
        Assertions.assertEquals(d.data.length, 18);
        Assertions.assertEquals(d.data[0][13], 0.625);
        Assertions.assertEquals(d.data[0][23], 0.6835737234260715);
    }

    @Test
    public void testAttribute(){
        Assertions.assertEquals(d.attribute.length, 226 / 12);
        Assertions.assertEquals(d.attribute[1], 0);
        Assertions.assertEquals(d.attribute[11], 0);
    }
}