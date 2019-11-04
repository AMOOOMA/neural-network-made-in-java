import org.junit.jupiter.api.Test;

class TrainerTest {
    DataParser dP = new DataParser("src\\data-daily.csv");
    DataConstructor d = new DataConstructor(dP.data, 30);

    @Test
    public void testGradientDescent() {
        Trainer g = new Trainer(d.data, d.attribute, 1, 2);
        g.epoch(1, 100, 10);
    }
}