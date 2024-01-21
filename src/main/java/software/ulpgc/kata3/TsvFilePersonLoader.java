package software.ulpgc.kata3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
public class TsvFilePersonLoader implements PersonLoader{
    private final File file;

    private TsvFilePersonLoader(File file) {
        this.file = file;
    }
    public static PersonLoader with(String file){
        return new TsvFilePersonLoader(new File(file));
    }
    @Override
    public List<Person> load(){
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            return load(reader);
        } catch (IOException e){
            return emptyList();
        }
    }
    private List<Person> load(BufferedReader reader) throws IOException{
        return reader.lines()
                .skip(1)
                .map(this::toPerson)
                .collect(toList());
    }
    private Person toPerson(String line){
        return toPerson(line.split("\t"));
    }
    private Person toPerson(String[] fields){
        return new Person(
                parseInt(fields[0]),
                parseDouble(fields[1]),
                parseDouble(fields[2])
        );
    }
}
