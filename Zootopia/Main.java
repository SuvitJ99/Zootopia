import java.util.*;

public class Main {
    static class Visitor {
        private String name;
        private int age;
        private String phone;
        private double balance;
        private String email;
        private String password;

        public Visitor(String name, int age, String phone, double balance, String email, String password) {
            this.name = name;
            this.age = age;
            this.phone = phone;
            this.balance = balance;
            this.email = email;
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Map<Integer, String> Attractions = new HashMap<>();
        Map<Integer, String> Attractions_status = new HashMap<>();
        Map<Integer, Integer> Attractions_price = new HashMap<>();
        Map<Integer, Integer> Attractions_tickets_counter = new HashMap<>();
        List<Visitor> registeredVisitors = new ArrayList<>();

        Attractions_price.put(1, 750);
        Attractions_price.put(2, 500);
        Attractions_price.put(3, 200);

        Attractions_tickets_counter.put(1, 30);
        Attractions_tickets_counter.put(2, 40);
        Attractions_tickets_counter.put(3, 45);

        Attractions_status.put(1, "OPEN");
        Attractions_status.put(2, "CLOSED");
        Attractions_status.put(3, "OPEN");

        Attractions.put(1, "Safari experience");
        Attractions.put(2, "A botanical garden");
        Attractions.put(3, "a dinosaur show");

        boolean exit = false;

        while (!exit) {
            System.out.println("Enter index to choose for : \n(1) Admin\n(2) Visitor\n(3) Exit");
            int index = scan.nextInt();

            switch (index) {
                case 1:
                    // Admin
                    adminMenuLoop(scan, Attractions, Attractions_status, Attractions_price, Attractions_tickets_counter);
                    break;
                case 2:
                    // Visitor
                    visitorMenuLoop(scan, registeredVisitors,Attractions, Attractions_status, Attractions_price, Attractions_tickets_counter);
                    break;
                case 3:
                    // Exit
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        }
    }

    public static void visitor_registration(List<Visitor> registeredVisitors) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Visitor Registration:");
        System.out.print("Enter your name: ");
        String name = scan.nextLine();
        System.out.print("Enter your age: ");
        int age = Integer.parseInt(scan.nextLine());
        System.out.print("Enter your phone number: ");
        String phone = scan.nextLine();
        System.out.print("Enter your initial balance: ");
        double balance = Double.parseDouble(scan.nextLine());
        System.out.print("Enter your email: ");
        String email = scan.nextLine();
        System.out.print("Create a password: ");
        String password = scan.nextLine();
        Visitor visitor = new Visitor(name, age, phone, balance, email, password);
        registeredVisitors.add(visitor);
        System.out.println("Registration successful.");
    }

    public static void visitor_login(List<Visitor> registeredVisitors) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Visitor Login:");
        System.out.print("Enter your email: ");
        String loginEmail = scan.nextLine();
        System.out.print("Enter your password: ");
        String loginPassword = scan.nextLine();

        boolean loggedIn = false;
        for (Visitor v : registeredVisitors) {
            if (v.getEmail().equals(loginEmail) && v.getPassword().equals(loginPassword)) {
                System.out.println("Login successful. Welcome, " + v.getName());
                loggedIn = true;
                break;
            }
        }

        if (!loggedIn) {
            System.out.println("Login failed. Invalid email or password.");
        }
    }

    public static Integer visitor_menu() {
        Scanner scan = new Scanner(System.in);
        System.out.println(" - MENU - \n(1) Register a new visitor\n(2)Login in as visitor\n(3)Browse all attractions\n");
        System.out.println("Enter Index from menu you want to choose : ");
        int index2 = scan.nextInt();
        return index2;
    }
    public static void visitorMenuLoop(Scanner scan, List<Visitor> registeredVisitors,Map<Integer,String> Attractions,Map<Integer,String> Attractions_status,Map<Integer,Integer> Attractions_price,Map<Integer,Integer> Attractions_tickets_counter) {
        int index2 = visitor_menu();
        if (index2 == 1) {
            visitor_registration(registeredVisitors);
        } else if (index2 == 2) {
            visitor_login(registeredVisitors);
        }
        else if(index2 ==3){
            display_attractions(Attractions, Attractions_status, Attractions_price, Attractions_tickets_counter);
        }
    }
    public static Integer admin_menu() {
        Scanner scan = new Scanner(System.in);
        System.out.println(" - MENU - \nChoose by entering the index number : \n(1) display all attractions\n(2) add an attraction\n(3) modify an attraction\n(4) remove an attraction\n(5) Change Status of Attractions\n(6) Change the price for an attraction\n");
        System.out.println("Enter Index from menu you want to choose : ");
        int index2 = scan.nextInt();
        return index2;
    }
public static void adminMenuLoop(Scanner scan, Map<Integer, String> Attractions, Map<Integer, String> Attractions_status, Map<Integer, Integer> Attractions_price, Map<Integer, Integer> Attractions_tickets_counter) {
    int admin_pass = 0;
    while (true) {
        System.out.println("Common Username = root");
        System.out.print("Enter Username : ");
        String username = scan.next();
        if (username.equals("root")) {
            System.out.print("Enter Password : ");
            String password = scan.next();
            while (true) {
                if (password.equals("1234")) {
                    System.out.println("- Welcome Administrator -");
                    admin_pass = 1;
                    int index1 = admin_menu();
                    if (index1 == 1) {
                        display_attractions(Attractions, Attractions_status, Attractions_price, Attractions_tickets_counter);
                    } else if (index1 == 2) {
                        add_attractions(Attractions, Attractions_status, Attractions_price, Attractions_tickets_counter);
                    } else if (index1 == 3) {
                        modify_attractions(Attractions);
                    } else if (index1 == 4) {
                        remove_attractions(Attractions, Attractions_status, Attractions_price, Attractions_tickets_counter);
                    } else if (index1 == 5) {
                        status_attractions(Attractions_status);
                    } else if (index1 == 6) {
                        price_attractions(Attractions_price);
                    }
                    break;
                } else {
                    System.out.println("Wrong Password, try again !");
                    password = scan.next();
                }
            }
            break;
        } else {
            System.out.println("Username not found, try again!");
            username = scan.next();
        }
    }
}

    public static void display_attractions(Map<Integer,String> Attractions,Map<Integer,String> Attractions_status,Map<Integer,Integer> Attractions_price,Map<Integer,Integer> Attractions_tickets_counter){
        System.out.println("Attraction contents : ");
        for (Integer Index : Attractions.keySet()){
            String animal = Attractions.get(Index);
            String status = Attractions_status.get(Index);
            Integer price = Attractions_price.get(Index);
            System.out.println("Index: " + Index + ", Attraction: " + animal + ", Status: " + status + ", Price: " + price);
        }
    }
    public static void add_attractions(Map<Integer,String> Attractions,Map<Integer,String> Attractions_status,Map<Integer,Integer> Attractions_price,Map<Integer,Integer> Attractions_tickets_counter){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the Index number : ");
        Integer new_Index = scan.nextInt();
        System.out.println("Enter the Attraction : ");
        scan.nextLine();
        String new_attraction = scan.nextLine();
        System.out.println("Enter the status for this attraction : ");
        String new_status = scan.nextLine();
        System.out.println("Enter the price for the attraction : ");
        Integer new_price = scan.nextInt();
        scan.nextLine();
        Attractions.put(new_Index,new_attraction);
        Attractions_status.put(new_Index,new_status);
        Attractions_price.put(new_Index,new_price);
        Attractions_tickets_counter.put(new_Index,0);
    }
    public static void modify_attractions(Map<Integer,String> Attractions){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the Index you want to modify : ");
        Integer modify_Index = scan.nextInt();
        scan.nextLine();
        if (Attractions.containsKey(modify_Index)) {
            System.out.println("Enter new Attraction : ");
            String new_attraction = scan.nextLine();
            Attractions.put(modify_Index, new_attraction);
            System.out.println("Attraction successfully modified !");
        }
        else{
            System.out.println("key not found, try again !");
            modify_attractions(Attractions);
        }
        admin_menu();
    }
    public static void remove_attractions(Map<Integer,String> Attractions,Map<Integer,String> Attractions_status,Map<Integer,Integer> Attractions_price,Map<Integer,Integer> Attractions_tickets_counter){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the Index you want to remove : ");
        Integer remove_Index = scan.nextInt();
        if(Attractions.containsKey(remove_Index)){
            Attractions.remove(remove_Index);
            Attractions_status.remove(remove_Index);
            Attractions_price.remove(remove_Index);
            System.out.println("Attraction successfully removed at the given Index !");
        }
        else{
            System.out.println("Index not found, try again !");
            modify_attractions(Attractions);
        }
        admin_menu();
    }
    public static void status_attractions(Map<Integer,String> Attractions){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the Index of the attraction you want to change the status for : ");
        Integer status_Index = scan.nextInt();
        if(Attractions.containsKey(status_Index)){
            System.out.println("Enter new Status : ");
            scan.nextLine();
            String new_status = scan.nextLine();
            Attractions.put(status_Index,new_status);
            System.out.println("Attraction status successfully updated at the given Index !");
        }
        else{
            System.out.println("Index not found, try again !");
            modify_attractions(Attractions);
        }
    }
    public static void price_attractions(Map<Integer,Integer> Attractions_price){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the Index of the attraction you want to change the price for : ");
        Integer price_Index = scan.nextInt();
        if(Attractions_price.containsKey(price_Index)){
            System.out.println("Enter new price : ");
            Integer new_price = scan.nextInt();
            Attractions_price.put(price_Index,new_price);
            System.out.println("Attraction price successfully updated at the given Index !");
        }
        else{
            System.out.println("Index not found, try again !");
            price_attractions(Attractions_price);
        }
    }
}

