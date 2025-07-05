package topic.java.learning.oops_concept.cons_chain_and_code_block;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Constructor chaining is essential for creating robust,maintainable Java applications with flexible object creation patterns.
 *
 * <p>
 *
 * Benefits of Constructor Chaining
 * 1. Code Reusability
 *
 * Avoid duplicating initialization logic
 * Single point of complex initialization code
 * Reduces maintenance overhead
 *
 * 2. Consistency
 *
 * Ensures all objects initialized through same validation/processing logic
 * Maintains uniform object state regardless of constructor used
 *
 * 3. Flexibility
 *
 * Multiple ways to create objects with different parameter combinations
 * Easy to add new constructors without rewriting initialization logic
 *
 * 4. Maintainability
 *
 * Changes to initialization logic only need to be made in one place
 * Easier to debug and modify object creation process
 *
 * 5. Default Value Management
 *
 * Centralized handling of default values
 * Easy to change defaults without affecting multiple constructors
 * </p>
 */



public class ConstructorChaining {
    private String url;
    private String method;
    private Map<String, String> headers;
    private String body;
    
    public ConstructorChaining(String url) {
        this(url, "GET", new HashMap<>(), null);
    }
    
    public ConstructorChaining(String url, String method) {
        this(url, method, new HashMap<>(), null);
    }
    
    public ConstructorChaining(String url, String method, Map<String, String> headers, String body) {
        this.url = validateUrl(url);
        this.method = method.toUpperCase();
        this.headers = new HashMap<>(headers);
        this.body = body;
        addDefaultHeaders();
    }

    private void addDefaultHeaders() {
    }

    private String validateUrl(String url) {
        return url.toUpperCase();
    }
}


/**
 *  Use Cases for Instance Blocks:
 * 1. Common initialization needed by all constructors
 * 2. Complex initialization logic that's hard to put in field declarations
 * 3. Exception handling during initialization
 * 4. Logging/debugging object creation
 *
 *
 * Constructor Chaining with Instance Blocks Example Class:
 *
 *
 * */
class Account {
    private String accountId;
    private Date createdDate;

    {
        // Runs before ANY constructor
        accountId = generateAccountId();
        createdDate = new Date();
        System.out.println("Account initialization block");
    }

    private String generateAccountId() {
        return UUID.randomUUID().toString();
    }

    public Account() {
        this("DEFAULT");
        System.out.println("Default constructor");
    }

    public Account(String type) {
        System.out.println("Parameterized constructor: " + type);
        // accountId and createdDate already set by instance block
    }
}