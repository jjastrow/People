import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by johnjastrow on 4/11/16.
 */
public class Person implements Comparable {
    private Integer id;
    private String first_name;
    private String last_name;
    private String email;
    private String country;
    private String ip_address;

    static void saveTeamList(HashMap<String, ArrayList<Person>> teamLeadList) throws IOException {
        JsonSerializer s = new JsonSerializer();
        String json = s.include("*").serialize(teamLeadList);

        File f = new File("peopleMap.json");
        FileWriter fw = new FileWriter(f);
        fw.write(json);
        fw.close();
    }

    static ArrayList<Person> loadPersons() throws FileNotFoundException {
        File f = new File("people.csv");

        // read all the posts into memory
        ArrayList<Person> persons = new ArrayList<>();
        Scanner fileScanner = new Scanner(f);
        boolean firstLine = true;  // skip the header
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            if (firstLine) {firstLine = false; continue;}
            String[] c = line.split("\\,");
            Person person = new Person(Integer.valueOf(c[0]), c[1], c[2], c[3], c[4], c[5]);
            persons.add(person);
        }
        return persons;
    }

    @Override
    public int compareTo(Object o) {
        Person p = (Person) o;
        return this.country.compareTo(p.country);
    }

    public Person(){};

    public Person(Integer id, String first_name, String last_name, String email, String country, String ip_address) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.country = country;
        this.ip_address = ip_address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    @Override
    public String toString() {
        return String.format("%s %s from %s", first_name, last_name, country);
    }
}
