import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Koneksi {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    // String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/pbo-kamis";
    private static final String USER = "root";
    private static final String PASS = "";

    private static Connection connect;
    private static Statement statement;
    private static ResultSet resultData;

    private static void connection() {
        try {
            Class.forName(JDBC_DRIVER);
            connect = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Koneksi Berhasil!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Koneksi Gagal");
        }
    }

    public static String getAllData() {
        connection();
        String data = "Data Tidak Ditemukan!!!";
        try {
            statement = connect.createStatement();
            String query = "select id_teman, nama, telpon from teman";
            resultData = statement.executeQuery(query);
            data = "";
            int count = 0;
            while (resultData.next()) {
                data += "ID Teman : " +
                        resultData.getInt("id_teman") +
                        ", Nama : " +
                        resultData.getString("nama") +
                        ", Telpon : " +
                        resultData.getString("telpon") +
                        "\n";
                count++;
            }
            System.out.println("Jumlah Data : " + count);
            if (count == 0) {
                data = "Data Tidak Ditemukan !!!";
            }

            statement.close();
            connect.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static String detaildata(int id) {
        connection();
        String data = "Data tidak Ditemukan";
        try {
            statement=connect.createStatement();
            String query="select id_teman,nama,telpon from teman where id_teman="+id;
            resultData=statement.executeQuery(query);
            data="";
            int count = 0;
            while (resultData.next()) {
                data+="- ID :"+resultData.getInt("id_teman")+
                "\n- Nama :"+resultData.getString("nama")+
                "\n- Telpon :"+resultData.getString("telpon");
                count++;
            }
            if (count==0) {
                data="Data tidak ditemuan";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
    
    public static String cariData(String keyword) {
        connection();
        String data="Data Tidak Ditemukan";
        try {
            statement=connect.createStatement();
            String query="select id_teman,nama,telpon from teman where (id_teman like '%"+
            keyword+"%' or nama like '%"+keyword+"%' or telpon like '%"+
            keyword+"%')";
            resultData= statement.executeQuery(query);
            data="";
            int count = 0;
            while (resultData.next()) {
                data+="- ID :"+resultData.getInt("id_teman")+
                "\n- Nama :"+resultData.getString("nama")+
                "\n- Telpon :"+resultData.getString("telpon")+"\n\n";
                count++;
            }
            if (count==0) {
                data="Data tidak ditemuan";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static boolean tambahData(String nama, String telpon) {
        connection();
        boolean data = false;
        try {
            statement=connect.createStatement();
            String query = "insert into teman(nama,telpon) values ('"+
            nama+"','"+
            telpon+"')";
            if(!statement.execute(query)) {
                data=true;
            }
            statement.close();
            connect.close();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return data;
    }
    public static boolean updateData(int id_teman, String nama, String telpon) {
        connection();
        boolean data = false;
        try {
            statement=connect.createStatement();
            String queryCek="select id_teman,nama,telpon from teman where id_teman="+id_teman;
            resultData=statement.executeQuery(queryCek);
            String namaCek = "", telponCek = "";
            while (resultData.next()) {
                namaCek=resultData.getString("nama");
                telponCek=resultData.getString("telpon");
                
            } 
            if(!nama.equalsIgnoreCase("")) {
                namaCek=nama;
            }
            if(!telpon.equalsIgnoreCase("")) {
                telponCek=telpon;
            }
            String queryUpdate="update teman set nama ='"+
            namaCek+"',telpon='"+telponCek+"' where id_teman="+id_teman;
            if(!statement.execute(queryUpdate)) {
                data=true;
            }
            statement.close();
            connect.close();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return data;
    }

    public static boolean deleteData(int id_teman) {
        connection();
        boolean data = false;
        try {
            statement=connect.createStatement();
            String query = "delete from teman where id_teman=" + id_teman;
            if (!statement.execute(query)) {
                data = true;
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return data;
    }

    public static String laporanData() {
        connection();
        String data = "Belum ada Data Masuk";

        try {
            statement = connect.createStatement();
            String query = "select nama, telpon from teman";
            resultData = statement.executeQuery(query);

            data = "Laporan Data\n\n";
            data += "-----------------------------------------------------------------------\\\n";
            data += "| No. \t | Nama\t\t | Telpon\t\t\t |\n";
            data += "------------------------------------------------------------------------\n";
            int count = 0;
            while (resultData.next()) {
                data += "| " + (count + 1) + "\t| " + resultData.getString("nama") + "\t\t| " +
                resultData.getString("telpon") + "\t\t\t\t|\n";
                count++;
            }
            data +="-------------------------------------------------------------------------/\n";
            if (count == 0) {
                data = "Belum Ada Data Masuk";
            }
            statement.close();
            connect.close();
        } catch (Exception e) {
         e.printStackTrace();
        }
        return data;
    }
    
}
