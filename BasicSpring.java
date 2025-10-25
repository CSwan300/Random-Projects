import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

// REST Controller
@RestController
@RequestMapping("/api")
class ApiController {
    
    private final Map<Long, User> users = new ConcurrentHashMap<>();
    private final AtomicLong counter = new AtomicLong();
    
    // Constructor with sample data
    public ApiController() {
        users.put(1L, new User(1L, "John Doe", "john@example.com"));
        users.put(2L, new User(2L, "Jane Smith", "jane@example.com"));
        counter.set(2L);
    }
    
    // GET /api/hello - Simple hello endpoint
    @GetMapping("/hello")
    public Map<String, String> hello() {
        return Map.of(
            "message", "Hello from Spring Boot!",
            "timestamp", new Date().toString()
        );
    }
    
    // GET /api/users - Get all users
    @GetMapping("/users")
    public Collection<User> getAllUsers() {
        return users.values();
    }
    
    // GET /api/users/{id} - Get user by ID
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        User user = users.get(id);
        if (user == null) {
            throw new RuntimeException("User not found with id: " + id);
        }
        return user;
    }
    
    // POST /api/users - Create new user
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        Long id = counter.incrementAndGet();
        user.setId(id);
        users.put(id, user);
        return user;
    }
    
    // PUT /api/users/{id} - Update user
    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User user = users.get(id);
        if (user == null) {
            throw new RuntimeException("User not found with id: " + id);
        }
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        return user;
    }
    
    // DELETE /api/users/{id} - Delete user
    @DeleteMapping("/users/{id}")
    public Map<String, String> deleteUser(@PathVariable Long id) {
        User user = users.remove(id);
        if (user == null) {
            throw new RuntimeException("User not found with id: " + id);
        }
        return Map.of("message", "User deleted successfully");
    }
}

// User Model
class User {
    private Long id;
    private String name;
    private String email;
    
    public User() {}
    
    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
