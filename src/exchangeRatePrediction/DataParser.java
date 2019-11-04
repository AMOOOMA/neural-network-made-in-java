import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;


public class DataParser {
    public ArrayList<Node> data;

    /**
     * The constructor take a path to csv file as a input and put all data into form of nodes.
     * Sample form of csv file format:
     * 2015-01-02,China,6.2046
     * 2015-01-05,China,6.2201
     * 2015-01-06,China,6.2125
     * 2015-01-07,China,6.2127
     * 2015-01-08,China,6.2143
     * 2015-01-09,China,6.2085
     * @param path
     */
    public DataParser(String path){
        data = new ArrayList<>();
        try{
            BufferedReader csvReader = new BufferedReader(new FileReader(path));
            String row = csvReader.readLine();
            while (row != null) {
                String[] set = row.split(",");
                String date = set[0].substring(2, set[0].length());
                if (set.length > 2) {
                    data.add(new Node(set[1], date, Double.parseDouble(set[2])));
                } else {
                    data.add(new Node(set[1], date, data.get(data.size() - 1).data));
                }
                row = csvReader.readLine();
            }
        } catch (Exception e){
            System.out.println("File not Found");
        }
    }

    public class Node{
        public String country;
        public String date;
        public double data;
        public Node(String country, String date, double data){
            this.country = country;
            this.date = date;
            this.data = data;
        }
    }
}