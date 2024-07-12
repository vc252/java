import java.util.ArrayList;
import java.util.Scanner;


/**
 * Input format
 * Banana 6
 * Milk 30 5
 * End
 * Banana 5
 * Milk 2
 * Checkout
 *
 * discount is per item not for whole order of perishables and non perishable
 */

/**
 * store,biller,packer classes are implimented as a singleton classes
 */
class Store {
    //list for storing available items
    private ArrayList<Item> availableItems;
    //static instance of store object to access it
    private static Store instance = new Store();
    //private constructor which can be called only from this class
    private Store() {
        availableItems = new ArrayList<Item>();
    }
    public static Store getInstance() {
        return instance;
    }
    public ArrayList<Item> getAvailableItems() {
        return availableItems;
    }

    //add items function to store perishable items if two inputs given
    public void addItem(String name, double price) {
        //this will be a perishable item
        if (instance.itemExist(name)) {
            //already existing elements are not added
            System.out.println("item already added");
        } else {
            Item stock = new Perishable(name,price);
            availableItems.add(stock);
        }
    }
    //function to check availability
    public Boolean itemExist(String product) {
        for (Item i:availableItems) {
            if ((i.getItemName()).equals(product)) {
                return true;
            }
        }
        return false;
    }
    //to store non perishable items if 3 inputs given
    void addItem(String name,double price,double volume) {
        //this will be a non perishable item
        if (instance.itemExist(name)) {
            //already existing elements are not added
            System.out.println("item already added");
        } else {
            Item stock = new NonPerishable(name,price,volume);
            availableItems.add(stock);
        }
    }
}

class Cart {
    Store store;//store to check availability of the items
    public Cart(Store store) {
        this.store = store;
        cart = new ArrayList<>();
    }
    private ArrayList<Item> cart;//cart where we will be storing our items
    void addToCart(String name,double amount) {
        ArrayList<Item> itemList = store.getAvailableItems();
        Boolean found = false;
        for (Item i : cart) {
            if (i.getItemName().equals(name)) {
                found = true;
                i.setQuantity(i.getQuantity() + amount);
            }
        }
        if (!found) {
            for (Item i: itemList) {
                if ((i.getItemName()).equals(name)) {
                    found = true;
                    if (i instanceof Perishable) {
                        Perishable a = new Perishable(name,i.getPricePerUnit(),amount);
                        cart.add(a);
                    } else if (i instanceof NonPerishable){
                        NonPerishable a = new NonPerishable(name,i.getPricePerUnit(),((NonPerishable)i).getVolume(),amount);
                        cart.add(a);
                    } else {
                        System.out.println("program galat hai");
                    }
                }
            }
        }

        if (!found) {
            System.out.println("item not available in store");
        }
    }

    public ArrayList<Item> getCart() {
        return cart;
    }
}

class Item {
    private String itemName;
    private double pricePerUnit;
    private double quantity;

    Item(String name,double price) {
        this.itemName = name;
        this.pricePerUnit = price;
        this.quantity = 0;
    }

    Item(String name,double price,double quantity) {
        this.itemName = name;
        this.pricePerUnit = price;
        this.quantity = quantity;
    }
    public double getPricePerUnit() {
        return pricePerUnit;
    }
    public String getItemName() {
        return itemName;
    }
    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}

class Perishable extends Item {
    public Perishable(String name,double price) {
        super(name, price);
    }

    public Perishable(String name,double price,double quantity) {
        super(name, price,quantity);
        //this.quantity = quantity;
    }
}

class NonPerishable extends Item {
    private double volume;
    public NonPerishable(String name,double price,double volume) {
        super(name, price);
        this.volume = volume;
    }

    public NonPerishable(String name,double price,double volume,double quantity) {
        super(name, price,quantity);
        this.volume = volume;
    }
    public double getVolume() {
        return volume;
    }
}


class Biller {
    private double bill;
    private static Biller instance = new Biller();
    private Biller() {
        this.bill = 0;
    }

    public static Biller getBiller() {
        return instance;
    }
    public void billing(ArrayList<Item> list) {
        Packer pack = Packer.getPacker();
        bill = 0;
        double shipping = 0;
        for (Item i: list) {
            System.out.println(i.getItemName()+" "+i.getPricePerUnit()+" "+i.getQuantity());
            bill += i.getPricePerUnit() * i.getQuantity();//doesn't care whether the item is perishable or not
            shipping += pack.getCost(i);
            bill += shipping;
        }
        System.out.println("Shipping "+shipping);
        System.out.println("Total "+bill);
    }

}
class Packer {
    private static Packer instance = new Packer();
    public static Packer getPacker() {
        return instance;
    }
    public double getCost(Item i) {
        if (i instanceof Perishable) {
            if (i.getQuantity()<=5) {
                return 0;
            } else {
                return 5*(i.getQuantity()-5);//since quantity is same as number of kgs
            }

        } else if(i instanceof NonPerishable){
            if (i.getQuantity()<=4) {
                return 0;
            } else {
                return (2*((((NonPerishable)i).getVolume() * i.getQuantity())-4));
            }

        } else {
            System.out.println("code galat hui gya");
            return -1;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Store openStore = Store.getInstance();
        String input = "start";
        while (!input.equals("End")) {
            input = sc.nextLine();
            if (input.equals("End")) {
                break;
            }
            String[] inputs = input.split(" ");
            if (inputs.length==3) {
                openStore.addItem(inputs[0],Double.parseDouble(inputs[1]),Double.parseDouble(inputs[2]));
            } else if(inputs.length==2) {
                openStore.addItem(inputs[0],Double.parseDouble(inputs[1]));
            } else {
                System.out.println("invalid input");
            }
        }
        Cart cart = new Cart(openStore);
        //System.out.println("Add items to the cart (Enter 'checkout' to finish):");
        while (true) {
            input = sc.nextLine();
            if (input.equals("Checkout")) {
                break;
            }

            String[] cartInputs = input.split(" ");
            if (cartInputs.length == 2) {
                cart.addToCart(cartInputs[0], Double.parseDouble(cartInputs[1]));
            } else {
                System.out.println("Invalid cart input");
            }
        }

        Biller c = Biller.getBiller();
        c.billing(cart.getCart());
    }
}