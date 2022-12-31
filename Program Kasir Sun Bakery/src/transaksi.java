import java.util.InputMismatchException;
import java.sql.*;
// import com.mysql.cj.protocol.Resultset;
import java.util.Scanner;

//inheritance
public class transaksi extends barang {

    //koneksi ke database mysql xampp
    static Connection conn;
    String urll = "jdbc:mysql://localhost:3306/suncafe";

    Scanner inputan = new Scanner (System.in);
    
    //constructor
    public transaksi(int hargamenu, int jumlahmenu) {
        super(hargamenu, jumlahmenu);
    }

    @Override
    public int subtotal() {  
        //Aritmatika (Proses perkalian) 
        subttl = hargamenu*jumlahmenu;
        //System.out.println("\nSubtotal : " + subttl);
        return subttl;
    }

    @Override
    public int diskon() { 
        if(subttl>100000 && subttl<=200000){
            diskonn = 5000;
        }
        else if (subttl>200000 && subttl<=500000){
            diskonn = 10000;
        }
        else if (subttl>500000 && subttl<=750000){
            diskonn = 20000;
        }
        else if (subttl>750000){
            diskonn = 50000;
        }
        else {diskonn = 0;}
        //System.out.println("Diskon yang didapat : " + diskonn);
        return diskonn;
    }

    @Override
    public int totalharga() {  
        total = subttl - diskonn;
        //System.out.println("\nTotal : " + total);
        return total;    
    }

    //Mengimplementasikan method dari interface penjualan
    //Method tampil :
    //Berfungsi untuk menampilkan data transaksi suncafe

    @Override
    public void tampil(){

        System.out.println("Nomor transaksi : " + notrans + "\n");
        System.out.println("Kasir : " + namakasir);
        System.out.println("\nNama Menu : " + namamenu);
        System.out.println("Ukuran : " + ukurn[ukur]);
        System.out.println("Jumlah menu : " + jumlahmenu);
        System.out.println("\nSubtotal : " + subttl);
        System.out.println("Diskon yang didapat : " + diskonn);
        System.out.println("\nTotal harga: " + total);

    }
    //Mengimplementasikan method dari interface penjualan
    //Method view :
    //Berfungsi untuk menampilkan data transaksi suncafe
    public void view()throws SQLException{  
    
        System.out.println("Daftar isi seluruh suncafe");

        //Mengakses Database 
        //Mengecek data yang ada pada database suncafe tabel suncafe
        String sql = "SELECT * FROM suncafe";
        conn = DriverManager.getConnection(urll,"root","");
        Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);
        
        while (result.next())
        {
            System.out.print("\nNo transaksi\t: ");
            System.out.print(result.getInt("notransaksi"));
            
            System.out.print("\nKasir\t\t: ");
            System.out.print(result.getString("kasir"));

            System.out.print("\nNama menu\t: ");
            System.out.print(result.getString("namamenu"));
            
            System.out.print("\nUkuran menu\t: ");
            System.out.print(result.getString("ukuranmenu"));

            System.out.print("\nHarga\t\t: Rp.");
            System.out.print(result.getInt("harga"));
            
            System.out.print("\nJumlah menu\t: ");
            System.out.print(result.getInt("jumlah"));

            System.out.print("\nTotal bayar\t: Rp.");
            System.out.println(result.getInt("totalbayar"));

        }
    }


    public void save() throws SQLException{
        try{

            kasir();        
            namamenu();
            ukuran();
            hargamenu();
            jumlah();
            subtotal();
            diskon();
            totalharga();
            tampil();
    
            String sql = "INSERT INTO suncafe (notransaksi, kasir, namamenu, ukuranmenu, harga, jumlah, totalbayar) VALUES (0,'"+namakasir+"','"+namamenu+"','"+ukurannya+"','"+hargamenu+"' ,'"+jumlahmenu+"' ,'"+total+"' )";

            conn = DriverManager.getConnection(urll,"root","");
            Statement statement = conn.createStatement();
            statement.execute(sql);
            System.out.println("Berhasil input data!!");
        }
        
        catch (ArrayIndexOutOfBoundsException e){
            System.err.println("Error!! masukkan indeks ukuran dengan benar");
        }
        //exception, jika inputan tidak sesuai jenis data
        catch (InputMismatchException e){
            System.err.println("Input pilihan dengan angka saja");
        }
        
    }

    public void delete() throws SQLException{
        view();
        //Exception Handling
        //Menggunakan try catch
        //Untuk mengatasi jika nantinya program terjadi eror
        try{
            System.out.print("Masukkan nomor transaksi yang akan dihapus : ");
            int keyword = inputan.nextInt();

            String sql = "DELETE FROM suncafe WHERE notransaksi = "+ keyword;

            conn = DriverManager.getConnection(urll,"root","");
	        Statement statement = conn.createStatement();

            if(statement.executeUpdate(sql) > 0){
	            System.out.println("Berhasil menghapus data suncafe (Nomor "+keyword+")");
	        }
        }
        catch(SQLException e){
	        System.out.println("Terjadi kesalahan dalam menghapus data");
	    }
        catch(Exception e){
        //     System.out.println("masukan data yang benar");
        }
    }


    public void search() throws SQLException{
        System.out.print("Masukkan Nama menu yang ingin dilihat : ");    
		String keyword = inputan.nextLine();
		
		String sql = "SELECT * FROM suncafe WHERE namamenu LIKE '%"+keyword+"%'";
		conn = DriverManager.getConnection(urll,"root","");
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql); 

        while (result.next()){
            System.out.print("\nNo transaksi\t: ");
            System.out.print(result.getInt("notransaksi"));
            System.out.print("\nKasir\t\t: ");
            System.out.print(result.getString("kasir"));
            System.out.print("\nNama menu\t: ");
            System.out.print(result.getString("namamenu"));
            System.out.print("\nUkuran menu\t: ");
            System.out.print(result.getString("ukuranmenu"));
            System.out.print("\nHarga\t\t: ");
            System.out.print(result.getInt("harga"));
            System.out.print("\nJumlah\t\t: ");
            System.out.print(result.getInt("jumlah"));
            System.out.print("\nTotal bayar\t: ");
            System.out.println(result.getInt("totalbayar"));
        }
        
    }

    public void update() throws SQLException{
        view();
        try{
            System.out.print("\nMasukkan nomor transaksi barang yang hendak diubah: ");
            Integer keywordd = inputan.nextInt();

            String sql = "SELECT * FROM suncafe WHERE notransaksi = " +keywordd;
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            if (result.next())
            {
                System.out.print("\nNama baru \t: ");
                String namabarumenu = inputan.next();
                
                System.out.print("\n");
                sql = "UPDATE suncafe SET namamenu='"+namabarumenu+"' WHERE notransaksi='"+keywordd+"'";
            
                // conn = DriverManager.getConnection(urll,"root","");
                // Statement statementt = conn.createStatement();
                // statementt.execute(sql);
                // System.out.println("Berhasil memperbaharui data transaksi (Nomor "+keywordd+")");

                if(statement.executeUpdate(sql) > 0)
                {
                    System.out.println("Berhasil memperbaharui data transaksi (Nomor "+keywordd+")");
                }
            }
            statement.close();
        }
            //exeption SQL 
            catch (SQLException e){
                System.err.println("Terjadi kesalahan dalam mengedit data transaksi");
                System.err.println(e.getMessage());
            }
        
    }

}
