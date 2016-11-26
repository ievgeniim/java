import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;


import java.io.File;
import java.io.IOException;

import avrofile.Automobile;

/**
 * Created by lagger on 26.11.2016.
 */


public class AvroFile {

    public static void main (String args[]) {

        Automobile mersedes = new Automobile(1,"Mercedes-Benz","GLA",2012);
        Automobile audi = new Automobile(2,"Audi","A6",2014);
        Automobile bwm = new Automobile(3,"Bmw","535 XDrive",2016);

        File avroOutput = new File("avro/automobile.avsc");

        try {

            DatumWriter<Automobile> avroAuto = new SpecificDatumWriter<Automobile>(Automobile.class);
            DataFileWriter<Automobile> dataWrite  = new DataFileWriter<Automobile>(avroAuto);
            dataWrite.create(mersedes.getSchema(),new File("auto.avro"));
            dataWrite.append(mersedes);
            dataWrite.append(audi);
            dataWrite.append(bwm);
            dataWrite.close();

        } catch (IOException e) {

        }
    }
}
