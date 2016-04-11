import java.io.File;
import java.io.FileWriter;
import java.util.*;

/**
 * Created by zach on 10/19/15.
 * eg:
 *  Russia=[Carol Flores from Russia, Kenneth Parker from Russia, Roger Sanchez from Russia, Irene Turner from Russia,
 *  Pamela Moore from Russia, Marilyn Carr from Russia, Beverly Martinez from Russia, Carol Stewart from Russia,
 *  Anna Rogers from Russia, Anne Henderson from Russia, Mildred Alexander from Russia, Kenneth Boyd from Russia,
 *  Judith Carter from Russia, Irene Alexander from Russia, Lori Fisher from Russia, Martin Pierce from Russia,
 *  Evelyn Morrison from Russia, Bonnie Harrison from Russia, Jimmy Frazier from Russia, Anthony Richardson from Russia]
 */
public class People {
    public static void main(String[] args) throws Exception {

        // Map of Person's full name to list of people
        // ie;   Joe -> [Joe, Charlie, Bob]   Joe is the team lead for these people.
        // The first person on the team is the lead.
        HashMap<String, ArrayList<Person>> teamLeadList;

        // 1. Read all people in from CSV file
        ArrayList<Person> people = Person.loadPersons();
        Collections.sort(people);
        //System.out.println(people + "\n\n\n");

        // 2. print out results
        teamLeadList = processByCountry(people);
        System.out.println(teamLeadList);

        // 3. write out results to json formatted file
        Person.saveTeamList(teamLeadList);
    }


    /** read people file and sort by country
     * for each person grab the country and that person to a new country specific list if it matches the previous
     */

    static HashMap<String, ArrayList<Person>> processByCountry(ArrayList<Person> people) throws Exception {
        HashMap<String, ArrayList<Person>> teamList = new HashMap<>();
        ArrayList<Person> peopleByCountry = new ArrayList<>();

        // For each country loop over people and collect into a list for that same country
        String country = "";

        // make sure the first person has a country
        Person firstPerson = people.get(0);
        if ( firstPerson.getCountry().isEmpty() ) {
            throw new Exception("First country has empty string = NOT allowed!");
        }

        for (Person person : people) {
            // When the person is from the same country as last person add to specific list
            if (country.equalsIgnoreCase(person.getCountry())) {
                peopleByCountry.add(person);
            }
            // Otherwise, start new list and grab new country name
            else {
                // add the country list only once when a new country is found (first line is the header)
                if (! country.isEmpty()) {
                    teamList.put(country, peopleByCountry);
                }
                peopleByCountry = new ArrayList<>();
                country = person.getCountry();
                peopleByCountry.add(person);
            }
        }
        // add last person and their country list to the team list
        teamList.put(country, peopleByCountry);
        return teamList;
    }
}
