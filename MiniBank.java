public class MiniBank {
    static class Customer {
        private String name;
        private String email;
        private String mobile;
        private final String customerId;

        private static long customerCounter = 0;

        private static String generateCustomerId() {
            customerCounter++;
            return String.format("CUST%03d", customerCounter);
        }

        public Customer(String name, String email, String mobile) {
            this.name = name;
            this.email = email;
            this.mobile = mobile;
            this.customerId = generateCustomerId();
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getMobile() {
            return mobile;
        }

        public String getCustomerId() {
            return customerId;
        }
    }

    static class Account {
        private final String accountNumber;
        private String ownerName;
        private long balance;
        private boolean active;

        private static long accountCounter = 0;

        private static String generateAccountNumber() {
            accountCounter++;
            return String.format("AC%03d", accountCounter);
        }

        public Account(String ownerName, long openingBalance) {
            this.accountNumber = generateAccountNumber();
            this.ownerName = ownerName;
            this.balance = Math.max(0, openingBalance);
            this.active = true;
        }

        public Account(String ownerName) {
            this(ownerName, 0);
        }

        public void deposit(long amount) {
            if (active && amount > 0) {
                balance += amount;
                System.out.println("Deposited Rs. " + amount + " into " + accountNumber);
            } else {
                System.out.println("Deposit rejected for " + accountNumber);
            }
        }

        public boolean withdraw(long amount) {
            if (active && amount > 0 && balance >= amount) {
                balance -= amount;
                System.out.println("Withdrawn Rs. " + amount + " from " + accountNumber);
                return true;
            }

            System.out.println("Withdrawal rejected for " + accountNumber);
            return false;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public String getOwnerName() {
            return ownerName;
        }

        public long getBalance() {
            return balance;
        }

        public boolean isActive() {
            return active;
        }
    }

    public static void main(String[] args) {
        Customer customer1 = new Customer("Aarav Shah", "aarav@example.com", "9876543210");
        Customer customer2 = new Customer("Diya Patel", "diya@example.com", "9876501234");
        Customer customer3 = new Customer("Kabir Mehta", "kabir@example.com", "9876512340");

        System.out.println("MiniBank Customer Records");
        System.out.println(customer1.getCustomerId() + " | " + customer1.getName()
                + " | " + customer1.getEmail() + " | " + customer1.getMobile());
        System.out.println(customer2.getCustomerId() + " | " + customer2.getName()
                + " | " + customer2.getEmail() + " | " + customer2.getMobile());
        System.out.println(customer3.getCustomerId() + " | " + customer3.getName()
                + " | " + customer3.getEmail() + " | " + customer3.getMobile());

        Account[] accounts = {
            new Account(customer1.getName(), 1000),
            new Account(customer2.getName()),
            new Account(customer3.getName(), 500)
        };

        System.out.println("\\nAccount transactions:");
        accounts[0].deposit(500);
        accounts[0].withdraw(200);
        accounts[1].deposit(750);
        accounts[1].withdraw(1000); // Insufficient balance; balance remains unchanged.
        accounts[2].deposit(250);
        accounts[2].withdraw(100);

        System.out.println("\\nFinal account balances:");
        for (Account account : accounts) {
            System.out.println(account.getAccountNumber() + " | "
                    + account.getOwnerName() + " | Balance: Rs. "
                    + account.getBalance() + " | Active: " + account.isActive());
        }
    }
}
