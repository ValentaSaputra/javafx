public class Person {
    private int id_teman;
    private String nama;
    private String telpon;

    public Person(int id_teman, String nama, String telpon) {
        this.id_teman = id_teman;
        this.nama = nama;
        this.telpon = telpon;
    }

    public int getId_teman() { return id_teman; }
    public void setId_teman(int id_teman) { this.id_teman = id_teman; }
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    public String getTelpon() { return telpon; }
    public void setTelpon(String telpon) { this.telpon = telpon; }
}