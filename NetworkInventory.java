import java.util.Scanner;

public class NetworkInventory {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] deviceNames = {
            "router1", "router2", "router3",
            "switch1", "switch2", "switch3",
            "switch4", "switch5", "switch6",
            "switch7", "switch8", "switch9"
        };

        String[] ipAddresses = {
            "10.10.10.1", "20.20.20.1", "30.30.30.1",
            "10.10.10.2", "10.10.10.3", "10.10.10.4",
            "10.10.10.5", "20.20.20.2", "20.20.20.3",
            "30.30.30.2", "30.30.30.3", "30.30.30.4"
        };

        String[] deviceTypes = {
            "router", "router", "router",
            "switch", "switch", "switch",
            "switch", "switch", "switch",
            "switch", "switch", "switch"
        };

        displayInventory(deviceNames, ipAddresses);

        
        int ipUpdates = 0;
        int[] invalidAttempts = {0};
        String[] updatedDevices = new String[deviceNames.length];
        String[] updatedIps = new String[deviceNames.length];
        int updatedCount = 0;
        while(true){
            System.out.println("Which device would you like to update (enter x to quit)?");
            String input = scan.nextLine();
            
            if (input.equals("x")){
                break;
            }
            
            boolean onNetwork = isOnNetwork(input, deviceNames);
            if (onNetwork){
                String ipInput = getNewIpAddress(scan, invalidAttempts);
                for (int i = 0; i < deviceNames.length; i++){
                    if (deviceNames[i].equals(input)){
                        ipAddresses[i] = ipInput;
                        break;
                    }
                }
                System.out.println(input +" was updated; the new IP address is " + ipInput);
                ipUpdates++;
                updatedDevices[updatedCount] = input;
                updatedIps[updatedCount] = ipInput;
                updatedCount++;
            }
        }
        scan.close();
        printSummary(ipUpdates, invalidAttempts[0], updatedDevices, updatedIps, updatedCount, deviceNames, ipAddresses, deviceTypes);
    } // Main end 
        

    // Displays whole inventory at start of program 
    public static void displayInventory(String[] deviceNames, String[] ipAddresses){
        System.out.println();
        System.out.println("Network Equipment Inventory\n");
        System.out.printf("%-15s %s%n", "equipment name", "IP address");

        for (int i = 0; i < deviceNames.length; i++) {
            System.out.printf("%-15s %s%n", deviceNames[i], ipAddresses[i]);
        }
    } // Method end 

    // Taking a device input and determining if it exists on the array 
    public static boolean isOnNetwork(String input, String[] deviceNames){
        for (int i = 0; i < deviceNames.length; i++){
            if (deviceNames[i].equals(input)){
                return true;
            }
        }
        System.out.println("That device is not in the network inventory.");
        return false;
    }// Method end 

    // Determines if IP address entered is a valid IP address
    public static boolean isValidIp(String ip){
        for (int i = 0; i < ip.length(); i++) {
            char ch = ip.charAt(i);
            if (Character.isLetter(ch)) {
                System.out.println("Invalid input - no letters or characters");
                return false;
            }
        }
        String[] parts = ip.split("\\.");
        if (parts.length != 4) {
            System.out.println("Sorry, that is not a valid IP address");
            return false;
        }

        for (int i = 0; i < parts.length; i++) {
            int value = Integer.parseInt(parts[i]);
            if (value < 0 || value > 255) {
            System.out.println("Sorry, that is not a valid IP address");
            return false;
            }
        }
        return true;
    } // Method end 

    // allows user to input IP address uses isValidIp to determine if Valid address 
    public static String getNewIpAddress(Scanner scan, int[] invalidAttempts){
        System.out.println("What is the new ip address (111.111.111.111): ");
        String input = scan.nextLine();
        
        while (true){
            boolean validIp = isValidIp(input);
            if (validIp){
                return input;
            }
            invalidAttempts[0]++;
            System.out.println("What is the new ip address (111.111.111.111): ");
            input = scan.nextLine();
            
            
        }
    } // Method end 

    // Prints summary 
    public static void printSummary(int ipUpdates, int invalidAttempts, 
                                 String[] updatedDevices, String[] updatedIps, 
                                 int updatedCount, String[] deviceNames, 
                                 String[] ipAddresses, String[] deviceTypes) {

        System.out.println("\nSummary:\n");
        System.out.println("Number of devices updated: " + ipUpdates);

        for (int i = 0; i < updatedCount; i++) {
            System.out.println(updatedDevices[i] + " -> " + updatedIps[i]);
        }

        System.out.println("\nNumber of invalid addresses attempted: " + invalidAttempts);

        System.out.println("\nThe updated router inventory:");
        for (int i = 0; i < deviceNames.length; i++) {
            if (deviceTypes[i].equals("router")) {
                System.out.printf("%-15s %s%n", deviceNames[i], ipAddresses[i]);
            }
        }

        System.out.println("\nThe updated switch inventory:");
        for (int i = 0; i < deviceNames.length; i++) {
            if (deviceTypes[i].equals("switch")) {
            System.out.printf("%-15s %s%n", deviceNames[i], ipAddresses[i]);
            }
        }
    } // Method end 
} // Class end 
