


import java.security.Key;
import java.util.Scanner;

public class Menu {
    public static Scanner input = new Scanner(System.in);

    public static void index() {
        while (true) {
            System.out.print("\n=== Menu \n" +
                    "1. Lihat Semua Data\n" +
                    "2. Detail Data\n" +
                    "3. Cari Data\n" +
                    "4. Tambah Data\n" +
                    "5. Update Data\n" +
                    "6. Delete Data\n" +
                    "7. Laporan\n" +
                    "0. Exit\n" +
                    "Pilihan Anda[1/2/3/4/5/6/7/0] : ");
            String pilihan = input.next();
            if (pilihan.equalsIgnoreCase("0")) {
                System.out.println("Terima Kasih ...");
                break;
            }
            switch (pilihan) {
                case "1":
                    System.out.println(" === Lihat Semua Data === ");
                    System.out.println(Koneksi.getAllData());
                    break;
                case "2":
                    System.out.println("=== Detail Data === ");
                    detailData();
                    break;
                case "3":
                    System.out.println("=== Cari Data ===");
                    cariData();
                    break;
                case "4":
                    System.out.println("=== Tambah Data ===");
                  tambahData();
                    break;
                case "5":
                    System.out.println("=== Update Data ===");
                    updateData();
                    break;
                case "6":
                    System.out.println("=== Delete Data ===");
                    deleteData();
                    break;
                case "7":
                System.out.println("=== Laporan ===");
                    laporanData();
                break;
                default:
                    System.out.println("{Pilihan Anda Salah!}");
                    break;
            }
        }
    }
    public static void detailData() {
        System.out.print("Masukkan ID: ");
        int id=input.nextInt();
        System.out.println(Koneksi.detaildata(id));
    }
    public static void cariData() {
        System.out.print("Masukan keyword : ");
        String keyword= input.nextLine();
        keyword=input.nextLine();
        System.out.println(Koneksi.cariData(keyword));
    }

    public static void tambahData() {
        System.out.println("Masukkan Nama : ");
        String nama=input.nextLine();
        nama=input.nextLine();
        System.out.print("Masukkan Telpon : ");
        String telpon=input.nextLine();
        if(Koneksi.tambahData(nama, telpon)) {
            System.out.println("Data berhasil di tambahkan!");
            } else {
                System.out.println("Data Gagal DiTambahkan!");
                }
    }

    public static void updateData() {
        System.out.print("Masukan ID : ");
        int id=input.nextInt();
        System.out.println("\nGanti Data\n");
        System.out.println("Nama (Kosongkan jika tidak diganti) : ");
        String nama = input.nextLine();
        nama = input.nextLine();
        System.out.println("Telpon (Kosongkan jika tidak diganti) : ");
        String telpon = input.nextLine();
        if(Koneksi.updateData(id, nama, telpon)) {
            System.out.println("Data Sukses dirubah !");
        } else {
            System.out.println("Data Gagal Dirubah !");
        }
    }

    public static void deleteData() {
        System.out.println("Masukkan ID : ");

        int id_teman = input.nextInt();

        if(Koneksi.deleteData(id_teman)) {
            System.out.println("Data Sukses Dihapus !");
            Koneksi.getAllData();
        } else {
            System.out.println("Data Gagal Dihapus !");
        }
    }

    public static void laporanData () {
        System.out.println(Koneksi.laporanData());
    }
}