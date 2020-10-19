package com.company;
import java.beans.Customizer;
import java.util.*;

interface User{
    void menu();
    void printReward();
    void details();

}

class Food{
    private final int id;

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    private String name;
    private int price;
    private int quantity;
    private String category;
    private int offer;
    private int restaurant;
    private float boughtfor;
    Food(int id,String name,int price,int quantity,String category,int offer,int restaurant){
        this.id=id;
        this.name=name;
        this.quantity=quantity;
        this.category=category;
        this.price=price;
        this.offer=offer;
        this.restaurant=restaurant;
        boughtfor=0;
    }

    public float getBoughtfor() {
        return boughtfor;
    }

    public void setBoughtfor(float boughtfor) {
        this.boughtfor = boughtfor;
    }

    public int getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(int restaurant) {
        this.restaurant = restaurant;
    }

    public int getId(){
        return this.id;
    }
    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public int getOffer() {
        return offer;
    }

    public void setOffer(int offer) {
        this.offer = offer;
    }


}

class Restaurant implements User{
    private final int id;
    private final String name;
    private final String city;
    private int rewards;
    private HashMap<Integer,Food> foods=new HashMap<>();

    @Override
    public void details(){
        System.out.println(name+" "+city+" "+rewards);
    }
    public HashMap<Integer, Food> getFoods() {
        return foods;
    }

    public void setFoods(HashMap<Integer, Food> foods) {
        this.foods = foods;
    }

    Restaurant(int id, String name, String city){
        this.id=id;
        this.name=name;
        this.city=city;
        rewards=0;

    }
    public String getName(){
        return this.name;

    }
    public int getId(){
        return this.id;
    }

    public int getRewards(){
        return this.rewards;
    }

    public void setRewards(int add){
        this.rewards+=add;
    }
    public String getCity(){
        return this.city;
    }
    public String getCategory(){
        return "No";
    }
    public float getDiscount(){
        return 0;
    }
    @Override
    public void menu(){
        while(true) {
            System.out.println("Welcome " + this.name);
            System.out.println("1) Add item");
            System.out.println("2) Edit item");
            System.out.println("3) Print Rewards");
            System.out.println("4) Discount on bill value");
            System.out.println("5) Exit");
            int choice = Main.in.nextInt();
            if (choice == 1) {
                addItem();
            } else if (choice == 2) {
                editItem();
            } else if (choice == 3) {
                printReward();
            } else if (choice == 4) {
                billdiscount();
            } else {
                break;
            }
        }
    }
    public void addItem(){
        String name,category;
        int price,offer,quantity;
        String temp=Main.in.nextLine();
        System.out.println("Enter Name");
        name=Main.in.nextLine();
        System.out.println("Enter price");
        price=Main.in.nextInt();
        System.out.println("Enter quantity");
        quantity=Main.in.nextInt();
        System.out.println("Enter category");
        category=Main.in.next();
        System.out.println("Enter offer");
        offer=Main.in.nextInt();
        foods.put(Main.foodidcounter,new Food(Main.foodidcounter,name,price,quantity,category,offer,this.id));
        System.out.println((Main.foodidcounter++)+" "+name+" "+price+" "+quantity+" "+offer+"% off  "+category );
    }
    public void editItem(){
        System.out.println("Choose item by code");

        foods.forEach((k,v) -> {
            System.out.println(k+" "+name+" - "+v.getName()+" "+v.getPrice()+" "+v.getQuantity()+" "+v.getOffer()+"% off  "+v.getCategory());
        });
        int foodnumber=Main.in.nextInt();
        Food food=foods.get(foodnumber);
        System.out.println("Choose an attribute to edit");
        System.out.println("1) Name");
        System.out.println("2) Price");
        System.out.println("3) Quantity");
        System.out.println("4) Category");
        System.out.println("5) Offer");
        int choice=Main.in.nextInt();
        if(choice==1){
            System.out.println("Enter new name -");
            food.setName(Main.in.next());
        }
        else if(choice==2){
            System.out.println("Enter new price -");
            food.setPrice(Main.in.nextInt());

        }
        else if(choice==3){
            System.out.println("Enter new Quantity -");
            food.setQuantity(Main.in.nextInt());

        }
        else if(choice==4){
            System.out.println("Enter new Category -");
            food.setCategory(Main.in.next());
        }
        else if(choice==5){
            System.out.println("Enter new offer -");
            food.setOffer(Main.in.nextInt());
        }


    }
    @Override
    public void printReward(){
        System.out.println("Reward points given: "+rewards );
    }
    public void billdiscount(){
        System.out.println("This functionality is not valid for this type of Restaurant");
    }

}

class fastFood extends Restaurant{

    private int rewards;
    private final String category;
    private float discount;
    fastFood(int id, String name, String city){
        super(id,name,city);
        this.category="Fast Food";
        discount=0;

    }

    @Override
    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public void billdiscount(){
        System.out.println("Enter the offer on total bill value -");
        this.discount=Main.in.nextFloat();
    }
}

class Authentic extends Restaurant{

    private final String category;
    private float discount;
    Authentic(int id, String name, String city){
        super(id,name,city);
        this.category="Authentic";
        discount=0;
    }

    @Override
    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public void billdiscount(){
        System.out.println("Enter the offer on total bill value -");
        this.discount=Main.in.nextFloat();
    }
}






class Customer implements User{

    private final int id;
    private final String name;
    private final String city;
    private int rewards;
    private float wallet;
    private final int delivery;
    HashMap<Integer,Restaurant> restaurants;
    HashMap<Integer,Food> cart;
    HashMap<Integer,HashMap<Integer,Food>> recentOrders;
    @Override
    public void details(){

    }

    Customer(int id,String name,String city,HashMap<Integer,Restaurant> restaurants){
        this.id=id;
        this.name=name;
        this.city=city;
        this.restaurants=restaurants;
        this.cart=new HashMap<>();
        wallet=1000;
        rewards=0;
        recentOrders=new HashMap<>();
        delivery=40;

    }

    public String getName(){
        return this.name;

    }
    public int getId(){
        return this.id;
    }
    public int getRewards(){
        return this.rewards;
    }
    public void setRewards(int add){
        this.rewards+=add;
    }

    public float getWallet(){
        return this.wallet;

    }
    public void setWallet(float num){
        this.wallet-=num;
    }

    public String getCity(){
        return this.city;
    }

    @Override
    public void menu(){
        while(true) {
            System.out.println("Welcome " + this.name);
            System.out.println("Customer Menu");
            System.out.println("1) Select Restaurant");
            System.out.println("2) checkout cart");
            System.out.println("3) Reward won");
            System.out.println("4) Print recent orders");
            System.out.println("5) Exit");
            int choice = Main.in.nextInt();
            if (choice == 1) {
                selectRestaurant();
            } else if (choice == 2) {
               checkout();
            } else if (choice == 3) {
                printReward();
            } else if (choice == 4) {
                recentOrders();
            } else {
                break;
            }
        }
    }
    public void selectRestaurant(){

        System.out.println("Choose Restaurant");
        restaurants.forEach((k,v) -> {
            System.out.println(k+") "+v.getName()+" ("+(fastFood.class.isInstance(v)?((fastFood)v).getCategory():"")+(Authentic.class.isInstance(v)?((Authentic)v).getCategory():"")+")");
        });
        int choice=Main.in.nextInt();
        System.out.println("Choose Item by Code");
        restaurants.get(choice).getFoods().forEach((k,v) -> {
            System.out.println(k+" "+restaurants.get(choice).getName()+" - "+v.getName()+" "+v.getPrice()+" "+v.getQuantity()+" "+v.getOffer()+"% off  "+v.getCategory());
        });

        int choice2=Main.in.nextInt();
        System.out.println("Enter the quantity");
        int choice3=Main.in.nextInt();
        if(restaurants.get(choice).getFoods().get(choice2).getQuantity()<choice3){
            System.out.println("Available quantity is less than entered quantity");
            return;
        }
        if(cart.containsKey(choice2)){
            Food temp=cart.get(choice2);
            temp.setQuantity(temp.getQuantity()+choice3);
            restaurants.get(choice).getFoods().get(choice2).setQuantity(restaurants.get(choice).getFoods().get(choice2).getQuantity()-choice3);
            cart.replace(choice2,temp);
        }
        else{
            Food temp1=restaurants.get(choice).getFoods().get(choice2);
            Food temp=new Food(temp1.getId(), temp1.getName(), temp1.getPrice(),0,temp1.getCategory(), temp1.getOffer(), temp1.getRestaurant());
            restaurants.get(choice).getFoods().get(choice2).setQuantity(restaurants.get(choice).getFoods().get(choice2).getQuantity()-choice3);
            temp.setQuantity(choice3);
            cart.put(choice2,temp);

        }

        System.out.println("Item added to cart");
    }
    public void checkout(){
        System.out.println("Items in Cart -");
        float total=0;
        int id=0;
        for(int k:cart.keySet()){
            id=cart.get(k).getRestaurant();
            System.out.println(k+" "+cart.get(k).getName()+" "+cart.get(k).getPrice()+" "+cart.get(k).getQuantity()+" "+cart.get(k).getOffer()+"% off  "+cart.get(k).getCategory());
            total+=(cart.get(k).getPrice()*cart.get(k).getQuantity())-(cart.get(k).getPrice()*cart.get(k).getQuantity()*(cart.get(k).getOffer()/100.0));
        }
        if(!restaurants.get(id).getCategory().equals("No")){
            total-=(total*(restaurants.get(id).getDiscount()/100));
        }


        if(restaurants.get(id).getCategory()=="Authentic"){
            if(total>100){
                total-=50;
            }
        }

        System.out.println("Delivery Charge - "+delivery+"/-");
        total+=delivery;
        System.out.println("Total order value - INR "+total+"/-");
        System.out.print("1) Proceed to checkout");
        int  choice2=Main.in.nextInt();
        if(rewards<total){
            wallet-=(total-rewards);
            rewards=0;

        }
        else{
            wallet-=(rewards-total);
            rewards=0;
        }
        if(restaurants.get(id).getCategory()=="Authentic"){
            rewards+=((int)(total/200))*25;
            restaurants.get(id).setRewards(rewards);

        }
        else if(restaurants.get(id).getCategory()=="fastFood"){
            rewards+=((int)(total/150))*10;
            restaurants.get(id).setRewards(rewards);
        }
        else{
            rewards+=((int)(total/100))*5;
            restaurants.get(id).setRewards(rewards);
        }
        Main.earning+=((total/100));
        Main.delivery+=delivery;
        if(choice2==1){
            for(int k:cart.keySet()){
                cart.get(k).setBoughtfor(total);
            }
            recentOrders.put(Main.ordercounter++,cart);
            cart=new HashMap<>();
        }
    }
    @Override
    public void printReward(){
        System.out.println("Reward won - "+rewards);
    }
    public void recentOrders(){
        for(int k:recentOrders.keySet()){
            System.out.print("Bought ");
            float price=0;
            int id=0;
            for(int i:recentOrders.get(k).keySet()){
                System.out.print(recentOrders.get(k).get(i).getName()+" Quantity - "+recentOrders.get(k).get(i).getQuantity()+",");
                price=recentOrders.get(k).get(i).getBoughtfor();
                id=recentOrders.get(k).get(i).getRestaurant();

            }
            System.out.print(" for "+price+" from "+restaurants.get(id).getName()+" Delivery Price- "+delivery);
            System.out.println();
        }
    }
}

class Elite extends Customer{

    private final String category;
    private final int discount;
    private final int delivery;
    private int rewards;
    private float wallet;
    Elite(int id,String name,String city,HashMap<Integer,Restaurant> restaurants){
        super(id,name,city,restaurants);
        category="Elite";
        discount=50;
        this.delivery=0;
        rewards=0;
        wallet=1000;
    }

    public int getDelivery() {
        return delivery;
    }

    public String getCategory() {
        return category;
    }

    public int getDiscount() {
        return discount;
    }

    @Override
    public void checkout(){
        System.out.println("Items in Cart -");
        float total=0;
        int id=0;
        for(int k:cart.keySet()){
            id=cart.get(k).getRestaurant();
            System.out.println(k+" "+cart.get(k).getName()+" "+cart.get(k).getPrice()+" "+cart.get(k).getQuantity()+" "+cart.get(k).getOffer()+"% off  "+cart.get(k).getCategory());
            total+=(cart.get(k).getPrice()*cart.get(k).getQuantity())-(cart.get(k).getPrice()*cart.get(k).getQuantity()*(cart.get(k).getOffer()/100.0));
        }
        if(!restaurants.get(id).getCategory().equals("No")){
            total-=(total*(restaurants.get(id).getDiscount()/100));
        }


        if(restaurants.get(id).getCategory()=="Authentic"){
            if(total>100){
                total-=50;
            }

        }
        if(rewards<total){
            wallet-=(total-rewards);
            rewards=0;

        }
        else{
            wallet-=(rewards-total);
            rewards=0;
        }
        if(restaurants.get(id).getCategory()=="Authentic"){
            rewards+=((int)(total/200))*25;
            restaurants.get(id).setRewards(rewards);

        }
        else if(restaurants.get(id).getCategory()=="fastFood"){
            rewards+=((int)(total/150))*10;
            restaurants.get(id).setRewards(rewards);
        }
        else{
            rewards+=((int)(total/100))*5;
            restaurants.get(id).setRewards(rewards);
        }
        Main.earning+=(total/100);
        Main.delivery+=delivery;
        System.out.println("Delivery Charge - "+delivery+"/-");
        total+=delivery;
        System.out.println("Total order value - INR "+total+"/-");
        System.out.print("1) Proceed to checkout");
        int  choice2=Main.in.nextInt();
        if(choice2==1){
            for(int k:cart.keySet()){
                cart.get(k).setBoughtfor(total);
            }
            recentOrders.put(Main.ordercounter++,cart);
            cart=new HashMap<>();
        }
    }
    @Override
    public void recentOrders(){
        for(int k:recentOrders.keySet()){
            System.out.print("Bought ");
            float price=0;
            int id=0;
            for(int i:recentOrders.get(k).keySet()){
                System.out.print(recentOrders.get(k).get(i).getName()+" Quantity - "+recentOrders.get(k).get(i).getQuantity()+",");
                price=recentOrders.get(k).get(i).getBoughtfor();
                id=recentOrders.get(k).get(i).getRestaurant();

            }
            System.out.print(" for "+price+" from "+restaurants.get(id).getName()+" Delivery Price- "+delivery);
            System.out.println();
        }
    }

}
class Special extends Customer{

    private final String category;
    private final int discount;
    private final int delivery;
    private int rewards=0;
    private int wallet=1000;
    Special(int id,String name,String city,HashMap<Integer,Restaurant> restaurants){
        super(id,name,city,restaurants);
        category="Special";
        discount=25;
        this.delivery=20;
    }

    public int getDelivery() {
        return delivery;
    }

    public String getCategory() {
        return category;
    }

    public int getDiscount() {
        return discount;
    }
    @Override
    public void checkout(){
        System.out.println("Items in Cart -");
        float total=0;
        int id=0;
        for(int k:cart.keySet()){
            id=cart.get(k).getRestaurant();
            System.out.println(k+" "+cart.get(k).getName()+" "+cart.get(k).getPrice()+" "+cart.get(k).getQuantity()+" "+cart.get(k).getOffer()+"% off  "+cart.get(k).getCategory());
            total+=(cart.get(k).getPrice()*cart.get(k).getQuantity())-(cart.get(k).getPrice()*cart.get(k).getQuantity()*(cart.get(k).getOffer()/100.0));
        }
        if(!restaurants.get(id).getCategory().equals("No")){
            total-=(total*(restaurants.get(id).getDiscount()/100.0));
        }


        if(restaurants.get(id).getCategory()=="Authentic"){
            if(total>100){
                total-=50;
            }
        }

        System.out.println("Delivery Charge - "+delivery+"/-");
        total+=delivery;
        if(rewards<total){
            wallet-=(total-rewards);
            rewards=0;

        }
        else{
            wallet-=(rewards-total);
            rewards=0;
        }
        if(restaurants.get(id).getCategory()=="Authentic"){
            rewards+=((int)(total/200))*25;
            restaurants.get(id).setRewards(rewards);

        }
        else if(restaurants.get(id).getCategory()=="fastFood"){
            rewards+=((int)(total/150))*10;
            restaurants.get(id).setRewards(rewards);
        }
        else{
            rewards+=((int)(total/100))*5;
            restaurants.get(id).setRewards(rewards);
        }
        Main.earning+=((total/100));
        Main.delivery+=delivery;
        System.out.println("Total order value - INR "+total+"/-");
        System.out.print("1) Proceed to checkout");
        int  choice2=Main.in.nextInt();
        if(choice2==1){
            for(int k:cart.keySet()){
                cart.get(k).setBoughtfor(total);
            }
            recentOrders.put(Main.ordercounter++,cart);
            cart=new HashMap<>();
        }
    }
    @Override
    public void recentOrders(){
        for(int k:recentOrders.keySet()){
            System.out.print("Bought ");
            float price=0;
            int id=0;
            for(int i:recentOrders.get(k).keySet()){
                System.out.print(recentOrders.get(k).get(i).getName()+" Quantity - "+recentOrders.get(k).get(i).getQuantity()+",");
                price=recentOrders.get(k).get(i).getBoughtfor();
                id=recentOrders.get(k).get(i).getRestaurant();

            }
            System.out.print(" for "+price+" from "+restaurants.get(id).getName()+" Delivery Price- "+delivery);
            System.out.println();
        }
    }
}


public class Main implements User{

    static Scanner in=new Scanner(System.in);
    static int foodidcounter=1,ordercounter=1;
    static float earning=0;
    static int delivery=0;
    public static HashMap<Integer,Restaurant> restaurants;
    public static HashMap<Integer,Customer> customers;
    static User a;
    @Override
    public void menu(){
        while(true) {
            System.out.println("Welcome to Zotato");
            System.out.println("1) Enter as Restaurant Owner");
            System.out.println("2) Enter as Customer");
            System.out.println("3) Check User Details");
            System.out.println("4) Company Account Details");
            System.out.println("5) Exit");
            int choice = Main.in.nextInt();
            if (choice == 1) {
                restaurants.forEach((k, v) -> {
                    System.out.println(k + ") " + v.getName() + " (" + (fastFood.class.isInstance(v) ? ((fastFood) v).getCategory() : "") + (Authentic.class.isInstance(v) ? ((Authentic) v).getCategory() : "") + ")");
                });
                int select = Main.in.nextInt();
                a = restaurants.get(select);
                a.menu();
            } else if (choice == 2) {
                customers.forEach((k, v) -> {
                    System.out.println(k + ") " + v.getName() + " (" + (Special.class.isInstance(v) ? ((Special) v).getCategory() : "") + (Elite.class.isInstance(v) ? ((Elite) v).getCategory() : "") + ")");
                });
                int select = Main.in.nextInt();
                a = customers.get(select);
                a.menu();
            }
            else if(choice==3){
                details();
            }
            else if(choice==4){
                printReward();

            }
            else{
                System.out.println("Exiting");
                break;

            }

        }
    }
    @Override
    public void printReward(){
        System.out.println("Total company balance- "+earning);
        System.out.println("Total delivery charge collected- "+delivery);
    }
    @Override
    public void details(){
        System.out.println("1) Customer List");
        System.out.println("2) Restaurant List");
        int choice=in.nextInt();
        if(choice==1){
            customers.forEach((k, v) -> {
                System.out.println(k + ") " + v.getName() + " (" + (Special.class.isInstance(v) ? ((Special) v).getCategory() : "") + (Elite.class.isInstance(v) ? ((Elite) v).getCategory() : "") + ")");
            });
            int select = Main.in.nextInt();
            System.out.print(customers.get(select).getName());
            if(Elite.class.isInstance(customers.get(select))){
                System.out.print("(Elite) "+customers.get(select).getCity()+" "+customers.get(select).getWallet());
                System.out.println();
            }
            else if(Special.class.isInstance(customers.get(select))){
                System.out.print("(Special) "+customers.get(select).getCity()+" "+customers.get(select).getWallet());
                System.out.println();
            }
            else{
                System.out.print(" "+customers.get(select).getCity()+" "+customers.get(select).getWallet());
                System.out.println();
            }
        }
        else{
            restaurants.forEach((k, v) -> {
                System.out.println(k + ") " + v.getName() + " (" + (fastFood.class.isInstance(v) ? ((fastFood) v).getCategory() : "") + (Authentic.class.isInstance(v) ? ((Authentic) v).getCategory() : "") + ")");
            });
            int select = Main.in.nextInt();
            System.out.print(restaurants.get(select).getName());
            if(!restaurants.get(select).getCategory().equals("No")){
                System.out.print("("+restaurants.get(select).getCategory()+") "+restaurants.get(select).getCity()+" "+restaurants.get(select).getRewards());
                System.out.println();
            }
            else{
                System.out.print(" "+restaurants.get(select).getCity()+" "+restaurants.get(select).getRewards());
                System.out.println();
            }
        }
    }

    public static void main(String[] args){
        restaurants=new HashMap<>();
        customers=new HashMap<>();

        restaurants.put(1,new Authentic(1,"Shah","Delhi"));
        restaurants.put(2,new Restaurant(2,"Ravi's","Delhi"));
        restaurants.put(3,new Authentic(3,"The Chinese","Delhi"));
        restaurants.put(4,new fastFood(4,"Wangs's","Delhi"));
        restaurants.put(5,new Restaurant(5,"Paradise","Delhi"));

        customers.put(1,new Elite(1,"Ram","Delhi",restaurants));
        customers.put(2,new Elite(2,"Sam","Delhi",restaurants));
        customers.put(3,new Special(3,"Tim","Delhi",restaurants));
        customers.put(4,new Customer(4,"Kim","Delhi",restaurants));
        customers.put(5,new Customer(5,"Jim","Delhi",restaurants));

        Main company=new Main();

        a=company;
        a.menu();



    }
}



