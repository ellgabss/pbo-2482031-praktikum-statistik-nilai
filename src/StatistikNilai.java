import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class StatistikNilai {

    static final int SELESAI = -1;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Integer> daftar = new ArrayList<>();

        int nilai;

        System.out.println("===== STATISTIK NILAI KELAS =====");
        System.out.println("Ketik -1 kalau sudah selesai.");

        do {
            System.out.print("Nilai ke-" + (daftar.size() + 1) + " : ");
            nilai = input.nextInt();

            if (nilai == SELESAI) {
                continue;
            }
            if (nilai < 0 || nilai > 100) {
                System.out.println("  Ditolak, harus 0-100");
                continue;
            }

            daftar.add(nilai);
        } while (nilai != SELESAI);

        System.out.println();

        if (daftar.isEmpty()) {
            System.out.println("Belum ada nilai yang tersimpan.");
            input.close();
            return;
        }

        // Tertinggi dan terendah dimulai dari daftar.get(0), bukan dari 0 atau 100, supaya
        // tetap benar untuk kasus ekstrem (semua nilai di bawah 0-anggapan atau semua di atas 100-anggapan).
        int tertinggi = daftar.get(0);
        int terendah = daftar.get(0);
        double total = 0;
        for (int i = 0; i < daftar.size(); i++) {
            int n = daftar.get(i);
            if (n > tertinggi) {
                tertinggi = n;
            }
            if (n < terendah) {
                terendah = n;
            }
            total += n;
        }
        double rata = total / daftar.size();

        // Jumlah di atas rata-rata baru bisa dihitung di putaran kedua, sesudah rata-rata diketahui.
        // Rata-rata hanya pasti nilainya setelah SEMUA data dibaca dan dijumlahkan; saat 85 dibaca
        // di putaran pertama, total dan jumlah data belum lengkap, jadi belum ada rata-rata untuk dibandingkan.
        int diAtasRata = 0;
        for (int i = 0; i < daftar.size(); i++) {
            if (daftar.get(i) > rata) {
                diAtasRata++;
            }
        }

        int[] jumlahGrade = new int[5];
        for (int i = 0; i < daftar.size(); i++) {
            int n = daftar.get(i);
            if (n >= 90) {
                jumlahGrade[0]++;
            } else if (n >= 80) {
                jumlahGrade[1]++;
            } else if (n >= 70) {
                jumlahGrade[2]++;
            } else if (n >= 60) {
                jumlahGrade[3]++;
            } else {
                jumlahGrade[4]++;
            }
        }
        char[] labelGrade = {'A', 'B', 'C', 'D', 'E'};
        StringBuilder distribusi = new StringBuilder();
        for (int i = 0; i < jumlahGrade.length; i++) {
            if (i > 0) {
                distribusi.append(" ");
            }
            distribusi.append(labelGrade[i]).append("=").append(jumlahGrade[i]);
        }

        ArrayList<Integer> terurut = new ArrayList<>(daftar);
        Collections.sort(terurut);

        System.out.println("Nilai tersimpan : " + daftar);
        System.out.println("Jumlah          : " + daftar.size());
        System.out.println("Rata-rata       : " + String.format("%.2f", rata));
        System.out.println("Tertinggi       : " + tertinggi);
        System.out.println("Terendah        : " + terendah);
        System.out.println("Di atas rata2   : " + diAtasRata + " orang");
        System.out.println("Distribusi      : " + distribusi);
        System.out.println("Terurut         : " + terurut);
        System.out.println("Urutan asli     : " + daftar);

        input.close();
    }
}
