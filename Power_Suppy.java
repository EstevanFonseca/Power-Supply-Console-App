//Disclaimer: made with chatGPT help

import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

class Device {
    private int power;

    // Constructor for Device class
    public Device(int power) {
        // Ensure non-negative device power
        this.power = Math.max(power, 0);
    }

    // Getter for device power
    public int getPower() {
        return power;
    }

    // Overrides the equals method for proper comparison of Device objects
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Device device = (Device) obj;
        return power == device.power;
    }

    // Overrides the hashCode method for proper hashing of Device objects
    @Override
    public int hashCode() {
        return Objects.hash(power);
    }
}

class PowerSupply {

    private int powerCapacity;
    private Set<Device> connectedDevices;  // Use a Set to ensure unique devices

    // Constructor for PowerSupply class
    public PowerSupply(int powerCapacity) {
        // Ensure non-negative power supply capacity
        this.powerCapacity = Math.max(powerCapacity, 0);

        // Ensure that the power supply is not created with a capacity of zero
        if (this.powerCapacity == 0) {
            throw new IllegalArgumentException("Power supply cannot have a capacity of zero.");
        }

        this.connectedDevices = new HashSet<>();
    }

    // Adds a new device to the power supply
    public boolean addDevice(Device device) {
        // Ensure non-negative device power capacity
        if (device.getPower() < 0) {
            System.out.println("Invalid device power. Power must be non-negative.");
            return false;
        }

        // Ensure non-negative power supply capacity
        if (powerCapacity < 0) {
            System.out.println("Invalid power supply capacity. Capacity must be non-negative.");
            return false;
        }

        // Ensure that the power supply does not exceed the maximum capacity
        if (powerCapacity >= device.getPower()) {
            // Ensure unique devices
            if (connectedDevices.contains(device)) {
                System.out.println("Device already exists in the list.");
                return false;
            }

            // Add device to the connected devices list
            connectedDevices.add(device);
            // Update the power capacity of the power supply
            powerCapacity -= device.getPower();
            return true;
        } else {
            System.out.println("Exceeds power supply capacity. Device cannot be added.");
            return false;
        }
    }

    // Removes a device from the power supply
    public void removeDevice(Device device) {
        // Checks if the device is present in the connected devices list
        if (connectedDevices.remove(device)) {
            // Update the power capacity by adding back the device's power
            powerCapacity += device.getPower();
            // Ensure power capacity is not negative
            powerCapacity = Math.max(powerCapacity, 0);
            System.out.println("Device removed successfully.");
        } else {
            System.out.println("Device not found in the list.");
        }
    }

    // Getter for power supply's current power capacity
    public int getPowerCapacity() {
        return powerCapacity;
    }
}

public class Power_Suppy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PowerSupply powerSupply = initializePowerSupply(scanner);

        int option;
        do {
            // Display menu options
            System.out.println("\nOptions:");
            System.out.println("1 - Add Device");
            System.out.println("2 - Remove Device");
            System.out.println("3 - View Power Capacity");
            System.out.println("0 - Exit");

            System.out.print("Enter your choice: ");
            option = scanner.nextInt();

            // Ensure a valid menu option
            if (option < 0 || option > 3) {
                System.out.println("Invalid option. Please enter a valid option.");
                continue;
            }

            switch (option) {
                case 1:
                    addDevice(scanner, powerSupply);
                    break;
                case 2:
                    removeDevice(scanner, powerSupply);
                    break;
                case 3:
                    viewPowerCapacity(powerSupply);
                    break;
                case 0:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
            }

        } while (option != 0);
    }

    // Initializes the power supply with user-provided initial capacity
    private static PowerSupply initializePowerSupply(Scanner scanner) {
        int initialCapacity;
        do {
            System.out.print("Enter the initial power capacity for the power supply: ");
            initialCapacity = scanner.nextInt();

            if (initialCapacity < 0) {
                System.out.println("Invalid input. Power capacity must be non-negative. Please try again.");
            }
        } while (initialCapacity < 0);

        return new PowerSupply(initialCapacity);
    }

    // Adds a new device to the power supply
    private static void addDevice(Scanner scanner, PowerSupply powerSupply) {
        System.out.print("Enter the power of the device to add: ");
        int devicePower = scanner.nextInt();
        Device newDevice = new Device(devicePower);
        powerSupply.addDevice(newDevice);
    }

    // Removes a device from the power supply
    private static void removeDevice(Scanner scanner, PowerSupply powerSupply) {
        System.out.print("Enter the power of the device to remove: ");
        int devicePower = scanner.nextInt();
        Device deviceToRemove = new Device(devicePower);
        powerSupply.removeDevice(deviceToRemove);
    }

    // Displays the current power capacity of the power supply
    private static void viewPowerCapacity(PowerSupply powerSupply) {
        System.out.println("Current power capacity: " + powerSupply.getPowerCapacity());
    }
}
