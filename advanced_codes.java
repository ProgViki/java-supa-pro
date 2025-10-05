// 🟥 ADVANCED LEVEL (Spring, REST, DB, Multithreading, Algorithms – 21 to 30+)
// 21. Spring Boot REST API – Employee Service
@RestController
@RequestMapping("/employees")
class EmployeeController {
    @GetMapping public List<Employee> getAll() { return service.findAll(); }
}

// 22. Spring Boot + JPA CRUD

Entity: User

Repository: UserRepository extends JpaRepository<User, Long>

Controller with CRUD endpoints.

// 23. JWT Authentication Example

Use io.jsonwebtoken to generate and validate tokens.

Store tokens in header Authorization: Bearer ....

// 24. WebSocket Chat App (Spring Boot + STOMP)

Real-time chat with frontend integration.

// 25. File Upload API
@PostMapping("/upload")
public ResponseEntity<String> upload(@RequestParam MultipartFile file) throws IOException {
    file.transferTo(new File("uploads/" + file.getOriginalFilename()));
    return ResponseEntity.ok("Uploaded!");
}

// 26. Scheduler (Spring @Scheduled)
@Scheduled(fixedRate = 5000)
public void reportStatus() {
    System.out.println("Running task every 5 seconds");
}

// 27. Spring Boot + PostgreSQL Connection

Application connects via spring.datasource.url, CRUDs records.

// 28. RESTful Weather API Client (WebClient)
WebClient.create("https://api.weather.com")
         .get().retrieve().bodyToMono(String.class).block();

// 29. Thread Pool Executor Example
ExecutorService pool = Executors.newFixedThreadPool(3);
for(int i=0; i<5; i++) pool.execute(() -> System.out.println(Thread.currentThread().getName()));

// 30. Binary Search Tree Implementation
class Node { int value; Node left, right; }


Insert, Search, Inorder Traversal.

/* 31. Tic Tac Toe Game (Console)

3x3 board.

Player X and O take turns.

Win/draw detection logic.

32. Hangman Game

Random word generator.

User guesses letters.

Track remaining lives.

33. Expense Tracker (File or DB-based)

Add, delete, list expenses.

Monthly summary.
*/

// 34. Unit Testing with JUnit
@Test
void addTest() {
    assertEquals(5, calculator.add(2, 3));
}

/* 35. JavaFX GUI: Contact Manager

UI with Add/Delete/Search.

Persist contacts to file.

36. API Integration (Fetch GitHub User Data)

Use HttpClient to fetch and parse GitHub JSON user details.

37. Multithreaded Downloader

Split file into parts, download concurrently, merge afterward.

38. E-commerce Product Service (Spring Boot Mini App)

REST endpoints for CRUD.

Cart and checkout simulation.

39. Real-time Stock Price Updater

Use scheduled tasks + WebClient to fetch price updates periodically.

40. Spring Boot + Security + JWT + PostgreSQL Project

Full authentication and authorization pipeline.
    */
