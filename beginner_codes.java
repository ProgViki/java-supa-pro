// 🟩 BEGINNER LEVEL (Core Java & OOP – 1 to 10)
// 1. Basic Calculator (OOP-based)
class Calculator {
    double add(double a, double b) { return a + b; }
    double subtract(double a, double b) { return a - b; }
    double multiply(double a, double b) { return a * b; }
    double divide(double a, double b) { return b != 0 ? a / b : 0; }
}

// 2. Palindrome Checker
String str = "madam";
boolean isPal = str.equalsIgnoreCase(new StringBuilder(str).reverse().toString());
System.out.println(isPal ? "Palindrome" : "Not Palindrome");

// 3. Reverse Words in a Sentence
String input = "Java is powerful";
String result = Arrays.stream(input.split(" "))
                      .reduce("", (a, b) -> b + " " + a).trim();
System.out.println(result);

// 4. Bank Account Class
class BankAccount {
    private double balance;
    void deposit(double amount) { balance += amount; }
    void withdraw(double amount) { if (balance >= amount) balance -= amount; }
    double getBalance() { return balance; }
}

// 5. Prime Number Generator
for (int i = 2; i <= 50; i++) {
    boolean prime = true;
    for (int j = 2; j <= Math.sqrt(i); j++) if (i % j == 0) prime = false;
    if (prime) System.out.print(i + " ");
}

// 6. Employee Class with Inheritance
class Employee { String name; double salary; }
class Manager extends Employee { double bonus; }

// 7. File Reader and Writer
Files.writeString(Path.of("test.txt"), "Hello Java");
String content = Files.readString(Path.of("test.txt"));
System.out.println(content);

// 8. Factorial Using Recursion
static long factorial(int n) { return (n == 0) ? 1 : n * factorial(n - 1); }

// 9. Sorting Numbers
int[] nums = {5, 2, 9, 1, 7};
Arrays.sort(nums);
System.out.println(Arrays.toString(nums));

// 10. Simple CLI To-Do App

/* Add tasks to a list.

Mark tasks complete.

Show all tasks.

*/
