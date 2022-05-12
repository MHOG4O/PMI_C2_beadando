package diary;

public class Car {
    private String plate;
    private Brand brand;
    private String type;
    private int productionYear;
    private Color color;

    private int price;

    public Car(String plate, Brand brand, String type, int productionYear, Color color,int price) {
        this.plate = plate;
        this.brand = brand;
        this.type = type;
        this.productionYear = productionYear;
        this.color = color;
        this.price=price;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public Color getColor() {
        return color;
    }

    public int getPrice(){return price;}

    public void setColor(Color color) {
        this.color = color;
    }
    @Override
    public String toString() {
        return "\n---------------------------\n" +
                "Rendszam: " + plate + "\n" +
                "Marka: " + brand + "\n"+
                "Tipus: " + type + "\n" +
                "Gyartasi ev: " + productionYear + "\n"+
                "Szin: "+color+"\n"+
                "Ar: "+price+" Ft"+
                "\n---------------------------\n";
    }
}
