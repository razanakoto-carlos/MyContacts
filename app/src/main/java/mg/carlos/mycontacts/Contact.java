package mg.carlos.mycontacts;

public class Contact {
    private int id;
    private String nom;
    private String telephone;
    private String email;
    private String note;

    public Contact(int id, String nom, String telephone, String email, String note) {
        this.id = id;
        this.nom = nom;
        this.telephone = telephone;
        this.email = email;
        this.note = note;
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getTelephone() { return telephone; }
    public String getEmail() { return email; }
    public String getNote() { return note; }

    public void setId(int id) { this.id = id; }
    public void setNom(String nom) { this.nom = nom; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public void setEmail(String email) { this.email = email; }
    public void setNote(String note) { this.note = note; }
}