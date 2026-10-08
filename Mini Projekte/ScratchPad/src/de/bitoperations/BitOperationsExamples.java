/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.bitoperations;

// https://www.google.com/search?q=Bitweise+Operationen+Java&rlz=1C1GCEA_enDE1178DE1179&sourceid=chrome&ie=UTF-8&amc=1&aep=42&cud=0&source=chrome.crn.rb&atvm=2&mstk=AUtExfBvhQgAD0cVb_nhVDpVoqpowhAoDbnmcCprDTd0AOlGGBAQyAzbRR5eJUrrrFnWfQfmgxWoN23fDCgsmu1KzeM8_ryy7bZ5wT-xnrHMKzHXL40wKHk5mI2gr-Bbc6lssZ7lND7a-Zk6LR8jZv6BoNCB7-ZsHV-3jOPKuX7BnoPtlF7Yp5ShKyDLL7fA2DRKi4crZVXtX_2KeLhMB6reOUuXaPih-wbmHRefUf1biSCZRMCS9mgh6tBSR1qbLQnnT7fkI6T8ALAaPw&csuir=1&mtid=iuWoavLuHbqG7NYPrOCU4As&udm=50
/**
 *
 * @author Trainer
 */
public class BitOperationsExamples {

    public static void main(String[] args) {
        //BitwiseAndMasking();
        //BitwiseOrFlags();
        UnsignedRightShift();
    }

    public static void BitwiseAndMasking() {
        int zahl = 15;
        Integer dezimalZahl = 0; // Binär: 1111

        String binärzahl = dezimalZahl.toBinaryString(zahl);

        if ((zahl & 1) == 1) {
            System.out.println("Die Zahl ist ungerade." + binärzahl); // Wird ausgeführt
        } else {
            System.out.println("Die Zahl ist gerade." + binärzahl); // Wird ausgeführt
        }
    }

    public static void BitwiseOrFlags() {
        int READ_PERMISSION = 1; // Binär: 0001
        int WRITE_PERMISSION = 2; // Binär: 0010

        Integer read = 0; // Binär: 1111
        Integer write = 0; // Binär: 1111

        // Kombiniert beide Berechtigungen
        int userPermissions = READ_PERMISSION | WRITE_PERMISSION;

        String readPermissions = read.toBinaryString(READ_PERMISSION);
        String writePermissions = write.toBinaryString(WRITE_PERMISSION);
        String userPermissionFlag = write.toBinaryString(userPermissions);

        System.out.println("readPermissions: " + readPermissions);
        System.out.println("writePermissions: " + writePermissions);
        System.out.println("userPermissionFlag: " + userPermissionFlag);

    }

    public static void UnsignedRightShift() {

        int color = 0xFF336699; // Ein ARGB-Farbwert (Hexadezimal)

        // Extrahiert den Rot-Kanal (schiebt ihn ganz nach rechts)
        int red = (color >>> 16) & 0xFF;

        System.out.println(red); // Ausgabe: 51 (entspricht 0x33)

    }

}
