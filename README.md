# Network Equipment Inventory Manager
### Java Implementation — by Danny Dalesandro

---

## What This Program Does

This program manages a network equipment inventory. It displays a table of routers and switches with their IP addresses, lets you update any device's IP address, validates the new address, and prints a full summary when you're done.

---

## How To Run It

**Requirements:**
- Java JDK 17 or higher installed
- Any terminal or command prompt

**Steps:**
```
javac NetworkInventory.java
java NetworkInventory
```

---

## How To Use It

1. The full inventory table prints at the start
2. You are prompted to enter a device name to update
3. Type the exact device name (e.g. `router1`, `switch4`)
4. Enter a new valid IP address in the format `111.111.111.111`
5. Type `x` to quit and print the summary

---

## Key Differences From Python 

This was originally written as a Python assignment using dictionaries. I rebuilt it in Java using parallel arrays instead. Here is how the two approaches map to each other:

### Data Storage

**Python (dictionary):**
```python
devices = {
    "router1": "10.10.10.1",
    "router2": "20.20.20.1"
}
```

**Java (parallel arrays):**
```java
String[] deviceNames  = {"router1", "router2"};
String[] ipAddresses  = {"10.10.10.1", "20.20.20.1"};
```
The index is the link — `deviceNames[0]` and `ipAddresses[0]` always belong to the same device.

---

### Looking Up a Device

**Python:**
```python
if device_name in devices:
    print("found")
```

**Java:**
```java
for (int i = 0; i < deviceNames.length; i++) {
    if (deviceNames[i].equals(input)) {
        return true;
    }
}
```

---

### Updating a Device

**Python:**
```python
devices[device_name] = new_ip
```

**Java:**
```java
for (int i = 0; i < deviceNames.length; i++) {
    if (deviceNames[i].equals(input)) {
        ipAddresses[i] = newIp;
        break;
    }
}
```

---

### IP Validation

**Python (checking for letters):**
```python
if any(c.isalpha() for c in ip):
    print("Invalid input - no letters or characters")
```

**Java:**
```java
for (int i = 0; i < ip.length(); i++) {
    if (Character.isLetter(ip.charAt(i))) {
        System.out.println("Invalid input - no letters or characters");
        return false;
    }
}
```

**Python (splitting and checking range):**
```python
parts = ip.split(".")
if len(parts) != 4:
    return False
for part in parts:
    if not 0 <= int(part) <= 255:
        return False
```

**Java:**
```java
String[] parts = ip.split("\\.");
if (parts.length != 4) return false;
for (int i = 0; i < parts.length; i++) {
    int value = Integer.parseInt(parts[i]);
    if (value < 0 || value > 255) return false;
}
```

---

### Functions vs Methods

Python uses standalone functions. Java uses static methods inside a class — they work the same way, just different syntax.

**Python:**
```python
def is_valid_ip(ip):
    # logic here
```

**Java:**
```java
public static boolean isValidIp(String ip) {
    // logic here
}
```

---

### Main Function / Entry Point

**Python:**
```python
if __name__ == "__main__":
    main()
```

**Java:**
```java
public static void main(String[] args) {
    // program starts here
}
```

---

## Program Structure

```
NetworkInventory.java
│
├── main()              — sets up data, runs the main loop, calls printSummary
├── displayInventory()  — prints the full device table
├── isOnNetwork()       — checks if a device name exists in the inventory
├── isValidIp()         — validates an IP address (no letters, 4 parts, 0-255)
├── getNewIpAddress()   — prompts and loops until a valid IP is entered
└── printSummary()      — prints update count, invalid attempts, final inventory
```

---

## Sample Output

```
Network Equipment Inventory

equipment name  IP address
router1         10.10.10.1
router2         20.20.20.1
router3         30.30.30.1
switch1         10.10.10.2
...

Which device would you like to update (enter x to quit)? router9
That device is not in the network inventory.

Which device would you like to update (enter x to quit)? switch1
What is the new IP address (111.111.111.111): bbb.bb
Invalid input - no letters or characters
What is the new IP address (111.111.111.111): 192.168.1.1
switch1 was updated; the new IP address is 192.168.1.1

Which device would you like to update (enter x to quit)? x

Summary:

Number of devices updated: 1
switch1 -> 192.168.1.1

Number of invalid addresses attempted: 1

The updated router inventory:
router1         10.10.10.1
...

The updated switch inventory:
switch1         192.168.1.1
...
```

---

*Built as a Java conversion of a Python networking assignment. GitHub: danieldalesandro*
