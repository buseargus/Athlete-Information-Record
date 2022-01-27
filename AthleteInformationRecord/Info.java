package AthleteInformationRecord;

import java.util.ArrayList;

public class Info {

    private String nameSurname;
    private ArrayList<String> teams = new ArrayList<>();
    private Date birthDate;

    public Info(String nameSurname, ArrayList<String> teams, Date birthDate) {
        this.nameSurname = nameSurname;
        this.teams = teams;
        this.birthDate = birthDate;
    }

    public Info() {
        nameSurname = null;
        teams = null;
        birthDate = null;
    }
    
    @Override
    public String toString(){
        String team = String.join(",", teams);
        return String.format("%s, %s, %s", getNameSurname(), birthDate.toString(), team);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (getClass() != obj.getClass()) {
            return false;
        } else {
            Info other = (Info) obj;
            return other.nameSurname.equals(nameSurname)
                    && other.teams.equals(teams)
                    && other.birthDate.equals(birthDate);
        }
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public ArrayList<String> getTeams() {
        return teams;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public void setTeams(ArrayList<String> teams) {
        this.teams = teams;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
