package diary;

import java.util.ArrayList;
import java.util.Scanner;

import static diary.Writer.saveUsersToXml;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String filepath = "src/main/resources/cars.xml";
        ArrayList<Car> cars = Reader.readUsersFromXml(filepath);
        menuProcessing(cars);
        saveUsersToXml(cars, filepath);
    }
    static void menuProcessing(ArrayList<Car> cars) {
        int choice = -1;
        System.out.println("\nKURUMA JAPAN AUTOKERESKEDES\n");
        while (choice != 0) {
            System.out.println("Valasszon muveletet: ");
            System.out.println("1 - Autok listazasa\r\n2 - Uj auto hozzaadasa\r\n3 - Auto modositasa\r\n" +
                    "4 - Auto torlese\r\n0 - Kilepes");
            try {
                System.out.print("--> ");choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Ervenytelen!\n");
            }
            scanner.nextLine();
            switch (choice) {
                case 1 -> listCars(cars);
                case 2 -> addNewCar(cars);
                case 3 -> modifyCar(cars);
                case 4 -> deleteCar(cars);
            }
        }
    }

    private static void deleteCar(ArrayList<Car> cars) {
        System.out.print("Irja be a torolni kivant auto rendszamat: ");
        try {
            cars.remove(findCar(cars, scanner.nextLine()));
            System.out.println("Sikeres torles\n");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void modifyCar(ArrayList<Car> cars) {
        Brand brand;
        System.out.print("Adja meg a modositani kivant auto rendszamat: ");
        int choise;
        try {
            Car car = findCar(cars, scanner.next());
            System.out.println("Mit szeretne modositani: ");
            System.out.println("1 - Rendszamot\n2 - Markat es tipust\n3 - Gyartasi evet\n4 - Szint\n5 - Arat\n0 - Megse");
            System.out.print("--> ");
            Scanner scanner1=new Scanner(System.in);
            while(scanner1.hasNextInt()!=true){
                System.out.println("Ervenytelen formatum!\n");
                scanner1=new Scanner(System.in);
            }
            choise=scanner1.nextInt();
            switch (choise){
                case 0:break;
                case 1:cars.set(cars.indexOf(car), new Car(inputPlate(cars), car.getBrand(),car.getType(), car.getProductionYear(),car.getColor(),car.getPrice()));break;
                case 2:
                    brand=inputBrand();
                    cars.set(cars.indexOf(car), new Car(car.getPlate(), brand,inputType(brand), car.getProductionYear(),car.getColor(),car.getPrice()));break;
                case 3:cars.set(cars.indexOf(car), new Car(car.getPlate(), car.getBrand(),car.getType(), inputProductionYear(),car.getColor(),car.getPrice()));break;
                case 4:cars.set(cars.indexOf(car), new Car(car.getPlate(), car.getBrand(),car.getType(), car.getProductionYear(),inputColor(),car.getPrice()));break;
                case 5:cars.set(cars.indexOf(car), new Car(car.getPlate(), car.getBrand(),car.getType(), car.getProductionYear(),car.getColor(),inputPrice()));break;
                default:System.out.println("Ilyen lehetoseg nincs!\n");modifyCar(cars);break;
            }
            System.out.println("Sikeres modositas!\n");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Car findCar(ArrayList<Car> cars, String plate)throws IllegalArgumentException {
        for (Car car : cars) {
            if (car.getPlate().equals(plate)) {
                return car;
            }
        }
        throw new IllegalArgumentException("Nem talalhato auto a megadott rendszammal: " + plate+"\n");
    }

    private static void addNewCar(ArrayList<Car> cars){
        String plate=inputPlate(cars);
        Brand brand=inputBrand();
        String type=inputType(brand);
        int productionYear=inputProductionYear();
        Color color=inputColor();
        int price=inputPrice();
        cars.add(new Car(plate,brand,type,productionYear,color,price));
        System.out.println("Sikeres hozzaadas\n");
    }

    private static String inputPlate(ArrayList<Car> cars) {
        System.out.print("Adja meg a rendszamot(pl: ABC123): ");
        String plate=scanner.nextLine();
        if(plate.charAt(0)>='A' && plate.charAt(0)<='Z' && plate.charAt(1)>='A' && plate.charAt(1)<='Z' &&
                plate.charAt(2)>='A' && plate.charAt(2)<='Z' && plate.charAt(3)>='0' && plate.charAt(3)<='9' &&
                plate.charAt(4)>='0' && plate.charAt(4)<='9' && plate.charAt(5)>='0' && plate.charAt(5)<='9'){
            for (Car car : cars) {
                if (car.getPlate().equals(plate)) {
                    System.out.println("Ilyen rendszam mar van!\n");
                    inputPlate(cars);
                }
            }
            return plate;
        }else{
            System.out.println("Ervenytelen formatum!\n");
            inputPlate(cars);
        }
        return "";
    }

    private static Brand inputBrand(){
        Brand brand=Brand.Honda;
        int brandInt;
        System.out.println("Adja meg a markat: ");
        System.out.println("1 - Honda\n2 - Lexux\n3 - Mazda\n4 - Mitsubishi\n5 - Nissan\n6 - Suzuki\n" +
                "7 - Toyota\n8 - Daihatsu\n");
        System.out.print("Adja meg a markat: ");
        Scanner scanner1=new Scanner(System.in);
        while(scanner1.hasNextInt()!=true){
            System.out.println("Szamformatum szukseges!\n");
            System.out.print("--> ");scanner1=new Scanner(System.in);
        }
        brandInt = scanner1.nextInt();
        if(brandInt>0 && brandInt<9){
            switch (brandInt){
                case 1:brand=Brand.Honda;break;
                case 2:brand=Brand.Lexus;break;
                case 3:brand=Brand.Mazda;break;
                case 4:brand=Brand.Mitsubishi;break;
                case 5:brand=Brand.Nissan;break;
                case 6:brand=Brand.Suzuki;break;
                case 7:brand=Brand.Toyota;break;
                case 8:brand=Brand.Daihatsu;break;
                default:
                    System.out.println("Ilyen nincs");inputBrand();
            }
        }
        return brand;
    }

    private static String inputType(Brand brand) {
        int choise;
        String type = null;
        if (brand == Brand.Honda) {
            System.out.println("Valassza ki a tipust(1-5): ");
            System.out.println("1 - Accord\n2 - Civic\n3 - CR-V\n4 - Jazz\n5 - S 2000\n");
            System.out.print("--> ");
            Scanner scanner1=new Scanner(System.in);
            while(scanner1.hasNextInt()!=true){
                System.out.println("Szamformatum szukseges!\n");scanner1=new Scanner(System.in);
            }
            choise = scanner1.nextInt();
            switch (choise) {
                case 1:
                    type=Honda.Accord.toString();break;
                case 2:
                    type=Honda.Civic.toString();break;
                case 3:
                    type=Honda.CR_V.toString();break;
                case 4:
                    type=Honda.Jazz.toString();break;
                case 5:
                    type=Honda.S_2000.toString();break;
                default:
                    System.out.println("Ilyen tipus nincs!");
                    inputType(brand);
            }
        }
        else if (brand==Brand.Lexus) {
            System.out.println("Valassza ki a tipust(1-5): ");
            System.out.println("1 - CT\n2 - GS\n3 - IS\n4 - NX\n5 - RX\n");
            System.out.print("--> ");
            Scanner scanner1=new Scanner(System.in);
            while(scanner1.hasNextInt()!=true){
                System.out.println("Szamformatum szukseges!\n");
                System.out.print("Valassza ki a tipust(1-5): ");scanner1=new Scanner(System.in);
            }
            choise = scanner1.nextInt();
            switch (choise) {
                case 1:
                    type=Lexus.CT.toString();break;
                case 2:
                    type=Lexus.GS.toString();break;
                case 3:
                    type=Lexus.IS.toString();break;
                case 4:
                    type=Lexus.NX.toString();break;
                case 5:
                    type=Lexus.RX.toString();break;
                default:
                    System.out.println("Ilyen tipus nincs!");
                    inputType(brand);
            }
        }
        else if (brand==Brand.Mazda) {
            System.out.println("Valassza ki a tipust(1-7): ");
            System.out.println("1 - 2\n2 - 3\n3 - 5\n4 - 6\n5 - CX-5\n6 - MX-7\n7 - RX-8\n");
            System.out.print("--> ");
            Scanner scanner1=new Scanner(System.in);
            while(scanner1.hasNextInt()!=true){
                System.out.println("Szamformatum szukseges!\n");
                System.out.print("Valassza ki a tipust(1-7): ");scanner1=new Scanner(System.in);
            }
            choise = scanner1.nextInt();
            switch (choise) {
                case 1:
                    type=Mazda._2.toString();break;
                case 2:
                    type=Mazda._3.toString();break;
                case 3:
                    type=Mazda._5.toString();break;
                case 4:
                    type=Mazda._6.toString();break;
                case 5:
                    type=Mazda.CX_5.toString();break;
                case 6:
                    type=Mazda.MX_5.toString();break;
                case 7:
                    type=Mazda.RX_8.toString();break;
                default:
                    System.out.println("Ilyen tipus nincs!");
                    inputType(brand);
            }
        }
        else if (brand==Brand.Mitsubishi) {
            System.out.println("Valassza ki a tipust(1-6): ");
            System.out.println("1 - ASX\n2 - Colt\n3 - Eclipse Cross\n4 - L 200\n5 - Lancer\n6 - Outlander\n");
            System.out.print("--> ");
            Scanner scanner1=new Scanner(System.in);
            while(scanner1.hasNextInt()!=true){
                System.out.println("Szamformatum szukseges!\n");
                System.out.print("Valassza ki a tipust(1-6): ");scanner1=new Scanner(System.in);
            }
            choise = scanner1.nextInt();
            switch (choise) {
                case 1:
                    type=Mitsubishi.ASX.toString();break;
                case 2:
                    type=Mitsubishi.Colt.toString();break;
                case 3:
                    type=Mitsubishi.Eclipse_Cross.toString();break;
                case 4:
                    type=Mitsubishi.L_200.toString();break;
                case 5:
                    type=Mitsubishi.Lancer.toString();break;
                case 6:
                    type=Mitsubishi.Outlander.toString();break;
                default:
                    System.out.println("Ilyen tipus nincs!");
                    inputType(brand);
            }
        }
        else if (brand==Brand.Nissan) {
            System.out.println("Valassza ki a tipust(1-8): ");
            System.out.println("1 - 350 Z\n2 - 370 Z\n3 - Almera\n4 - GT-R\n5 - Juke\n6 - Leaf\n7 - Micra\n8 - Qashqai\n");
            System.out.print("--> ");
            Scanner scanner1=new Scanner(System.in);
            while(scanner1.hasNextInt()!=true){
                System.out.println("Szamformatum szukseges!\n");
                System.out.print("Valassza ki a tipust(1-8): ");scanner1=new Scanner(System.in);
            }
            choise = scanner1.nextInt();
            switch (choise) {
                case 1:
                    type=Nissan._350_Z.toString();break;
                case 2:
                    type=Nissan._370_Z.toString();break;
                case 3:
                    type=Nissan.Almera.toString();break;
                case 4:
                    type=Nissan.GT_R.toString();break;
                case 5:
                    type=Nissan.Juke.toString();break;
                case 6:
                    type=Nissan.Leaf.toString();break;
                case 7:
                    type=Nissan.Micra.toString();break;
                case 8:
                    type=Nissan.Qashqai.toString();break;
                default:
                    System.out.println("Ilyen tipus nincs!");
                    inputType(brand);
            }
        }
        else if(brand==Brand.Suzuki){
            System.out.println("Valassza ki a tipust(1-8): ");
            System.out.println("1 - Alto\n2 - Baleno\n3 - Ignis\n4 - Jimny\n5 - Splash\n6 - Swift\n7 - SX4\n8 - SX4 S-Cross\n");
            System.out.print("--> ");
            Scanner scanner1=new Scanner(System.in);
            while(scanner1.hasNextInt()!=true){
                System.out.println("Szamformatum szukseges!\n");
                System.out.print("Valassza ki a tipust(1-8): ");scanner1=new Scanner(System.in);
            }
            choise = scanner1.nextInt();
            switch (choise){
                case 1:type=Suzuki.Alto.toString();break;
                case 2:type=Suzuki.Baleno.toString();break;
                case 3:type=Suzuki.Ignis.toString();break;
                case 4:type=Suzuki.Jimny.toString();break;
                case 5:type=Suzuki.Splash.toString();break;
                case 6:type=Suzuki.Swift.toString();break;
                case 7:type=Suzuki.SX4.toString();break;
                case 8:type=Suzuki.SX4_S_Cross.toString();break;
                default:System.out.println("Ilyen tipus nincs!");
                    inputType(brand);
            }
        }
        else if(brand==Brand.Toyota){
            System.out.println("Valassza ki a tipust(1-9): ");
            System.out.println("1 - Auris\n2 - Avensis\n3 - C-HR\n4 - Celica\n5 - Corolla\n6 - Land Cruiser\n7 - Prius\n8 - Supra" +
                    "\n9 - Yaris\n");
            System.out.print("--> ");
            Scanner scanner1=new Scanner(System.in);
            while(scanner1.hasNextInt()!=true){
                System.out.println("Szamformatum szukseges!\n");
                System.out.print("Valassza ki a tipust(1-9): ");scanner1=new Scanner(System.in);
            }
            choise = scanner1.nextInt();
            switch (choise){
                case 1:type=Toyota.Auris.toString();break;
                case 2:type=Toyota.Avensis.toString();break;
                case 3:type=Toyota.C_HR.toString();break;
                case 4:type=Toyota.Celica.toString();break;
                case 5:type=Toyota.Corolla.toString();break;
                case 6:type=Toyota.Land_Cruiser.toString();break;
                case 7:type=Toyota.Prius.toString();break;
                case 8:type=Toyota.Supra.toString();break;
                case 9:type=Toyota.Yaris.toString();break;
                default:System.out.println("Ilyen tipus nincs!");
                    inputType(brand);
            }
        }
        else if(brand==Brand.Daihatsu){
            System.out.println("Valassza ki a tipust(1-3): ");
            System.out.println("1 - Curoe\n2 - Materia\n3 - Sirion\n");
            System.out.print("--> ");
            Scanner scanner1=new Scanner(System.in);
            while(scanner1.hasNextInt()!=true){
                System.out.println("Szamformatum szukseges!\n");
                System.out.print("Valassza ki a tipust(1-3): ");scanner1=new Scanner(System.in);
            }
            choise = scanner1.nextInt();
            switch (choise){
                case 1:type=Daihatsu.Cuore.toString();break;
                case 2:type=Daihatsu.Materia.toString();break;
                case 3:type=Daihatsu.Sirion.toString();break;
                default:System.out.println("Ilyen tipus nincs!");
                    inputType(brand);
            }
        }
        return type;
    }
    private static int inputProductionYear() {
        int productionYear = 0;
        while (productionYear < 1960 || productionYear > 2022) {
            System.out.print("Irja be a gyartasi evet: ");
            try {
                productionYear = scanner.nextInt();
                if (productionYear < 1960 || productionYear > 2022) {
                    System.out.println("A gyartasi evnek 1900 és 2022 kozott kell lennie!");
                }
            } catch (Exception e) {
                System.out.println("A datumnak szamnak kell lennie!");
            }
            scanner.nextLine();
        }
        return productionYear;
    }

    private static Color inputColor() {
        Color color = Color.KEK;
        int colorInt;
        System.out.println("Adja meg a szint: ");
        System.out.println("1 - KEK\n2 - FEHER\n3 - FEKETE\n4 - PIROS\n5 - SARGA\n6 - SZURKE\n7 - ZOLD\n");
        System.out.print("--> ");
        Scanner scanner1=new Scanner(System.in);
        while(scanner1.hasNextInt()!=true){
            System.out.println("Szamformatum szukseges!\n");
            System.out.print("Adja meg a szint: ");scanner1=new Scanner(System.in);
        }
        colorInt=scanner1.nextInt();
        switch (colorInt){
            case 1:color=Color.KEK;break;
            case 2:color=Color.FEHER;break;
            case 3:color=Color.FEKETE;break;
            case 4:color=Color.PIROS;break;
            case 5:color=Color.SARGA;break;
            case 6:color=Color.SZURKE;break;
            case 7:color=Color.ZOLD;break;
            default:
                System.out.println("Ilyen lehetoseg nincs!\n");inputColor();
        }
        return color;
    }

    private static int inputPrice(){
        System.out.print("Adja meg az arat(Ft): ");
        Scanner scanner1=new Scanner(System.in);
        while(scanner1.hasNextInt()!=true){
            System.out.println("Szamformatum szukseges!: ");
            System.out.print("Adja meg az arat: ");scanner1=new Scanner(System.in);
        }
        return scanner1.nextInt();
    }
    private static void listCars(ArrayList<Car> cars) {
        System.out.println(cars);
    }
}
