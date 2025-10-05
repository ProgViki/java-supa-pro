
// 🟨 INTERMEDIATE LEVEL (Collections, Streams, Design Patterns – 11 to 20)
// 11. Student Grade Analyzer

Use a List<Student> to calculate average, highest, lowest.

record Student(String name, int score) {}

// 12. Inventory Management (HashMap-based)
Map<String, Integer> inventory = new HashMap<>();
inventory.put("Laptop", 5);
inventory.put("Mouse", 20);

// 13. Observer Pattern (Event Notification)
interface Observer { void update(String msg); }
class Subject {
    List<Observer> obs = new ArrayList<>();
    void notifyAll(String msg){ obs.forEach(o -> o.update(msg)); }
}

// 14. Builder Pattern Example
class User {
    private String name, email;
    static class Builder { /* chain methods returning Builder */ }
}

// 15. Custom Exception Handling
class InvalidAgeException extends Exception {
    InvalidAgeException(String msg){ super(msg); }
}

// 16. Read JSON Using Jackson
ObjectMapper mapper = new ObjectMapper();
Map<String, Object> map = mapper.readValue("{\"key\":\"value\"}", Map.class);

// 17. Library Management System (Mini Project)

Add/remove/search books.

Track borrowed books with Map<User, Book>.

// 18. Thread Synchronization Example
class Counter {
    private int count;
    synchronized void increment() { count++; }
}

// 19. Stream Filtering Example
List<Integer> nums = List.of(1, 2, 3, 4, 5);
nums.stream().filter(n -> n % 2 == 0).forEach(System.out::println);

// 20. Chat Message Logger (File + Thread)

/* Accept messages.

Log to a file in a background thread. */
