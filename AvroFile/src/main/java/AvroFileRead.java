import avrofile.Automobile;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.io.DatumReader;
import org.apache.avro.specific.SpecificDatumReader;


import java.io.File;
import java.io.IOException;

/**
 * Created by lagger on 26.11.2016.
 */
public class AvroFileRead {

    public static void main (String args[]) {

        File avroOutput = new File("avro/automobile.avsc");

        try {

            DatumReader<Automobile> avroAuto = new SpecificDatumReader<Automobile>(Automobile.class);
            DataFileReader<Automobile> dataRead = new DataFileReader<Automobile>(new File("auto.avro"),avroAuto);

            Automobile a = null;

            while (dataRead.hasNext()) {
                a = dataRead.next(a);
                System.out.println(a);
            }

        } catch (IOException e) {

        }
    }
}
