import java.util.Scanner;

//Class barang yang mengimplementasikan interface penjualan
public class barang implements penjualan {

    String namamenu , notrans, namakasir, ukurannya;
    String ukurn[] = {"kecil", "menengah", "besar"};
    int ukur;
    int hargamenu, jumlahmenu;

    int subttl,diskonn,total;

    public barang(int hargamenu, int jumlahmenu) {
        this.hargamenu = hargamenu;
        this.jumlahmenu = jumlahmenu;
    }

    Scanner menu = new Scanner(System.in);

    public void kasir(){
        System.out.print("Masukkan nama kasir : ");
        namakasir = menu.nextLine();

    }

    public void notransaksi() {  
        System.out.print("Pastikan tidak ada yang sama!!!!!!!\n");
        System.out.print("Masukkan nomor transaksi : ");
        notrans = menu.nextLine();

    }

    public void namamenu() {
        System.out.print("\nNama Menu : ");
        this.namamenu = menu.nextLine();
        //Percabangan (if)
        if(namamenu.isEmpty()){
            System.out.println("isikan nama barang anda!!");
            System.exit(0);}
        //System.out.println("Nama barang : " + namabrg);
    }

    public void ukuran() {
        System.out.print("Ukuran(0->kecil | 1->menengah | 2-besar) : ");
        this.ukur = menu.nextInt();
        ukurannya = ukurn[ukur];
        boolean henti=false;
        try {
            System.out.println("ukuran " + ukur + " adalah " + ukurannya + "\n");}
        catch (Exception e){
            // System.err.println(e);
            henti=true;
            System.out.println("index yang dimasukkan tidak sesuai!1!1!");
            if(henti){System.exit(0);};
        }
    }

    public void hargamenu() {
        System.out.print("Harga : ");
        this.hargamenu = menu.nextInt(); 
        //System.out.println("Harga : " + hargabrg);       
    }

    public void jumlah() {
        System.out.print("Jumlah : ");
        this.jumlahmenu = menu.nextInt();
        //System.out.println("Jumlah : " + jumlahbrg);       
    }

    public int subtotal() {        
        return 0;
    }

    public int diskon() {        
        return 0;
    }

    public int totalharga() {        
        return 0;
    }
    public void tampil(){};
    
}
