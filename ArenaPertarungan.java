import java.util.Scanner;

public class ArenaPertarungan {

    public static void main(String[] arg) {

        Scanner input = new Scanner(System.in);

        Musuh[] gelombangMonster = new Musuh[3];
        gelombangMonster[0] = new Slime();
        gelombangMonster[1] = new Naga();
        gelombangMonster[2] = new Zombie();

        System.out.println("========================");
        System.out.println("WELCOME TO BATTLE ARENA");
        System.out.println("========================\n");

        boolean isBermain = true;

        while (isBermain) {

            System.out.println("\n--- Status Monster ---");

            for (int i = 0; i < gelombangMonster.length; i++) {
                System.out.println((i + 1) + "." +
                        gelombangMonster[i].namamusuh +
                        " (HP: " +
                        gelombangMonster[i].healtpoint + ")");
            }

            System.out.println("4. Kabur dari pertarungan");
            System.out.println("\nPilih target monster yang ingin diserang (1/2/3) atau 4 untuk kabur:");

            int pilihanTarget = input.nextInt();

            if (pilihanTarget == 4) {
                System.out.println("\nAnda melarikan diri dari Arena pertarungan");
                isBermain = false;
                continue;       
            }

            if (pilihanTarget < 1 || pilihanTarget > 3) {

                System.out.println("Pilihan tidak valid! Anda membuang giliran.");

            } else {

                System.out.print("Masukan kekuatan serangan Anda (10-100): ");
                int power = input.nextInt();

                System.out.println("\n>>> Hasil Serangan <<<");

                int indeksMonster = pilihanTarget - 1;

                gelombangMonster[indeksMonster].terimaDamage(power);

                if (gelombangMonster[indeksMonster].healtpoint <= 0) {

                    System.out.println(
                    gelombangMonster[indeksMonster].namamusuh +" telah dikalahkan!"
                 );

                 if(gelombangMonster[indeksMonster] instanceof Loot){
                    
                    Loot lootMonster = (Loot) gelombangMonster[indeksMonster];
                    lootMonster.jatuhkanItem();
                 }


                System.out.println("\n<<< GILIRAN MONSTER MEMBALAS >>>");

                for (int i = 0; i < gelombangMonster.length; i++) {

                    if (gelombangMonster[i].healtpoint > 0) {
                        Musuh monsterAktif = gelombangMonster[i];
                        monsterAktif.suaraKhas();

                        if (monsterAktif instanceof BisaTerbang){
                            System.out.println("[PERINGATAN! SERANGAN UDARA TERDEKTEKSI");
                            BisaTerbang monsterTerbang = (BisaTerbang) monsterAktif;
                            monsterTerbang.lepasLandas();
                            monsterTerbang.seranganUdara();

                    } else {
                        monsterAktif.serangPemain();
                    }
                }
            }

                System.out.println("\n--------------------------------");
            }
        }
    }
    boolean semuaMati = true;
    for (int i = 0; i < gelombangMonster.length; i++){
        if(gelombangMonster[i].healtpoint > 0){
            semuaMati = false;
            break;
        }
    }

    if (semuaMati) {
        System.out.println("\nSELAMAT! Anda telah Menyelesaikan gelombang monster ini");
        isBermain = false;
    }
     input.close();
        System.out.println("Permainan Berakhir. Sampai jumpa pada Pertarungan berikutnya");
}
}