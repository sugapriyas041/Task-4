import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class StockInventoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockInventoryApplication.class, args);
    }
}

// Sample Product class
class Product {
    private String id;
    private String name;
    private int quantity;

    // Constructors, getters, and setters
}

// Sample Controller for managing products
@RestController
@RequestMapping("/products")
class ProductController {
    private List<Product> products = new ArrayList<>();

    @GetMapping
    public List<Product> getAllProducts() {
        return products;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable String id) {
        // Implement logic to retrieve a product by ID
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        // Implement logic to add a new product
        products.add(product);
        return product;
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable String id, @RequestBody Product updatedProduct) {
        // Implement logic to update an existing product
        Product existingProduct = getProductById(id);
        if (existingProduct != null) {
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setQuantity(updatedProduct.getQuantity());
        }
        return existingProduct;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) {
        // Implement logic to delete a product
        products.removeIf(product -> product.getId().equals(id));
    }
}