package dbms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class project {

    public Connection connect() {
    	String url = "jdbc:mysql://localhost:3306/mini?useSSL=false";
 // Update with your DB URL
        String user = "root"; // Your DB username
        String password = "mainsql@2024"; // Your DB password
          
        Connection conn = null;
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established!");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return conn;
    }

    // Customer operations
    public void insertCustomer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Customer ID: ");
        int customer_id = scanner.nextInt(); scanner.nextLine(); // consume newline
        System.out.print("Enter Customer Name: ");
        String customer_name = scanner.nextLine();
        System.out.print("Enter Contact Info: ");
        String contactInfo = scanner.nextLine();
        System.out.print("Enter Status (Active/Inactive): ");
        String status = scanner.nextLine();
        System.out.print("Enter Industry: ");
        String industry = scanner.nextLine();

        String sql = "INSERT INTO Customer (Customer_id, Customer_name, Contact_info, Date_created, Status, Industry) VALUES (?, ?, ?, NOW(), ?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customer_id);
            pstmt.setString(2, customer_name);
            pstmt.setString(3, contactInfo);
            pstmt.setString(4, status);
            pstmt.setString(5, industry);
            pstmt.executeUpdate();
            System.out.println("Customer inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCustomer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Customer ID to update: ");
        int customer_id = scanner.nextInt(); scanner.nextLine(); // consume newline
        System.out.print("Enter new Contact Info: ");
        String newContactInfo = scanner.nextLine();
        System.out.print("Enter new Status: ");
        String newStatus = scanner.nextLine();

        String sql = "UPDATE Customer SET Contact_info = ?, Status = ? WHERE Customer_id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newContactInfo);
            pstmt.setString(2, newStatus);
            pstmt.setInt(3, customer_id);
            pstmt.executeUpdate();
            System.out.println("Customer updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCustomer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Customer ID to delete: ");
        int customer_id = scanner.nextInt(); scanner.nextLine(); // consume newline

        String sql = "DELETE FROM Customer WHERE Customer_id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customer_id);
            pstmt.executeUpdate();
            System.out.println("Customer deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Contact operations
    public void insertContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Contact ID: ");
        int contact_id = scanner.nextInt(); scanner.nextLine(); // consume newline
        System.out.print("Enter Customer ID: ");
        int customer_id = scanner.nextInt(); scanner.nextLine(); // consume newline
        System.out.print("Enter Contact Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Role: ");
        String role = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();

        String sql = "INSERT INTO Contacts (Contact_id, Customer_id, Name, Role, Email, Phone, Date_created) VALUES (?, ?, ?, ?, ?, ?, NOW())";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, contact_id);
            pstmt.setInt(2, customer_id);
            pstmt.setString(3, name);
            pstmt.setString(4, role);
            pstmt.setString(5, email);
            pstmt.setString(6, phone);
            pstmt.executeUpdate();
            System.out.println("Contact inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Contact ID to update: ");
        int contact_id = scanner.nextInt(); scanner.nextLine(); // consume newline
        System.out.print("Enter new Email: ");
        String newEmail = scanner.nextLine();
        System.out.print("Enter new Phone: ");
        String newPhone = scanner.nextLine();

        String sql = "UPDATE Contacts SET Email = ?, Phone = ? WHERE Contact_id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newEmail);
            pstmt.setString(2, newPhone);
            pstmt.setInt(3, contact_id);
            pstmt.executeUpdate();
            System.out.println("Contact updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Contact ID to delete: ");
        int contact_id = scanner.nextInt(); scanner.nextLine(); // consume newline

        String sql = "DELETE FROM Contacts WHERE Contact_id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, contact_id);
            pstmt.executeUpdate();
            System.out.println("Contact deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Lead operations
    public void insertLead() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Lead ID: ");
        int leadId = scanner.nextInt(); scanner.nextLine(); // consume newline
        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt(); scanner.nextLine(); // consume newline
        System.out.print("Enter Source: ");
        String source = scanner.nextLine();
        System.out.print("Enter Status: ");
        String status = scanner.nextLine();
        System.out.print("Enter Assigned To: ");
        String assignedTo = scanner.nextLine();
        System.out.print("Enter Notes: ");
        String notes = scanner.nextLine();

        String sql = "INSERT INTO Leads (LeadID, CustomerID, Source, Status, AssignedTo, DateCreated, Notes) VALUES (?, ?, ?, ?, ?, NOW(), ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, leadId);
            pstmt.setInt(2, customerId);
            pstmt.setString(3, source);
            pstmt.setString(4, status);
            pstmt.setString(5, assignedTo);
            pstmt.setString(6, notes);
            pstmt.executeUpdate();
            System.out.println("Lead inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateLead() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Lead ID to update: ");
        int leadId = scanner.nextInt(); scanner.nextLine(); // consume newline
        System.out.print("Enter new Status: ");
        String newStatus = scanner.nextLine();

        String sql = "UPDATE Leads SET Status = ? WHERE LeadID = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newStatus);
            pstmt.setInt(2, leadId);
            pstmt.executeUpdate();
            System.out.println("Lead updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteLead() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Lead ID to delete: ");
        int leadId = scanner.nextInt(); scanner.nextLine(); // consume newline

        String sql = "DELETE FROM Leads WHERE LeadID = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, leadId);
            pstmt.executeUpdate();
            System.out.println("Lead deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // SalesRep operations
    public void insertSalesRep() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Sales Rep ID: ");
        int salesRepId = scanner.nextInt(); scanner.nextLine(); // consume newline
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter Territory: ");
        String territory = scanner.nextLine();

        String sql = "INSERT INTO SalesReps (SalesRepID, Name, Email, Phone, Territory, DateCreated) VALUES (?, ?, ?, ?, ?, NOW())";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, salesRepId);
            pstmt.setString(2, name);
            pstmt.setString(3, email);
            pstmt.setString(4, phone);
            pstmt.setString(5, territory);
            pstmt.executeUpdate();
            System.out.println("Sales Rep inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSalesRep() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Sales Rep ID to update: ");
        int salesRepId = scanner.nextInt(); scanner.nextLine(); // consume newline
        System.out.print("Enter new Email: ");
        String newEmail = scanner.nextLine();
        System.out.print("Enter new Phone: ");
        String newPhone = scanner.nextLine();

        String sql = "UPDATE SalesReps SET Email = ?, Phone = ? WHERE SalesRepID = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newEmail);
            pstmt.setString(2, newPhone);
            pstmt.setInt(3, salesRepId);
            pstmt.executeUpdate();
            System.out.println("Sales Rep updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSalesRep() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Sales Rep ID to delete: ");
        int salesRepId = scanner.nextInt(); scanner.nextLine(); // consume newline

        String sql = "DELETE FROM SalesReps WHERE SalesRepID = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, salesRepId);
            pstmt.executeUpdate();
            System.out.println("Sales Rep deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Interaction operations
    public void insertInteraction() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Interaction ID: ");
        int interactionId = scanner.nextInt(); scanner.nextLine(); // consume newline
        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt(); scanner.nextLine(); // consume newline
        System.out.print("Enter Date: ");
        String date = scanner.nextLine();
        System.out.print("Enter Type: ");
        String type = scanner.nextLine();
        System.out.print("Enter Notes: ");
        String notes = scanner.nextLine();

        String sql = "INSERT INTO Interactions (InteractionID, CustomerID, Date, Type, Notes) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, interactionId);
            pstmt.setInt(2, customerId);
            pstmt.setString(3, date);
            pstmt.setString(4, type);
            pstmt.setString(5, notes);
            pstmt.executeUpdate();
            System.out.println("Interaction inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateInteraction() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Interaction ID to update: ");
        int interactionId = scanner.nextInt(); scanner.nextLine(); // consume newline
        System.out.print("Enter new Notes: ");
        String newNotes = scanner.nextLine();

        String sql = "UPDATE Interactions SET Notes = ? WHERE InteractionID = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newNotes);
            pstmt.setInt(2, interactionId);
            pstmt.executeUpdate();
            System.out.println("Interaction updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteInteraction() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Interaction ID to delete: ");
        int interactionId = scanner.nextInt(); scanner.nextLine(); // consume newline

        String sql = "DELETE FROM Interactions WHERE InteractionID = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, interactionId);
            pstmt.executeUpdate();
            System.out.println("Interaction deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Product operations
    public void insertProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Product ID: ");
        int product_id = scanner.nextInt(); scanner.nextLine(); // consume newline
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Price: ");
        double price = scanner.nextDouble(); scanner.nextLine(); // consume newline
        System.out.print("Enter Description: ");
        String description = scanner.nextLine();

        String sql = "INSERT INTO Products (Product_id, Name, Price, Description) VALUES (?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, product_id);
            pstmt.setString(2, name);
            pstmt.setDouble(3, price);
            pstmt.setString(4, description);
            pstmt.executeUpdate();
            System.out.println("Product inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Product ID to update: ");
        int product_id = scanner.nextInt(); scanner.nextLine(); // consume newline
        System.out.print("Enter new Price: ");
        double newPrice = scanner.nextDouble(); scanner.nextLine(); // consume newline

        String sql = "UPDATE Products SET Price = ? WHERE Product_id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, newPrice);
            pstmt.setInt(2, product_id);
            pstmt.executeUpdate();
            System.out.println("Product updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Product ID to delete: ");
        int product_id = scanner.nextInt(); scanner.nextLine(); // consume newline

        String sql = "DELETE FROM Products WHERE Product_id= ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, product_id);
            pstmt.executeUpdate();
            System.out.println("Product deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Sales operations
    public void insertSale() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Sale ID: ");
        int saleId = scanner.nextInt(); scanner.nextLine(); // consume newline
        System.out.print("Enter Product ID: ");
        int productId = scanner.nextInt(); scanner.nextLine(); // consume newline
        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt(); scanner.nextLine(); // consume newline
        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt(); scanner.nextLine(); // consume newline

        String sql = "INSERT INTO Sales (SaleID, ProductID, CustomerID, Quantity, Date) VALUES (?, ?, ?, ?, NOW())";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, saleId);
            pstmt.setInt(2, productId);
            pstmt.setInt(3, customerId);
            pstmt.setInt(4, quantity);
            pstmt.executeUpdate();
            System.out.println("Sale inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSale() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Sale ID to update: ");
        int saleId = scanner.nextInt(); scanner.nextLine(); // consume newline
        System.out.print("Enter new Quantity: ");
        int newQuantity = scanner.nextInt(); scanner.nextLine(); // consume newline

        String sql = "UPDATE Sales SET Quantity = ? WHERE SaleID = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, newQuantity);
            pstmt.setInt(2, saleId);
            pstmt.executeUpdate();
            System.out.println("Sale updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSale() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Sale ID to delete: ");
        int saleId = scanner.nextInt(); scanner.nextLine(); // consume newline

        String sql = "DELETE FROM Sales WHERE SaleID = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, saleId);
            pstmt.executeUpdate();
            System.out.println("Sale deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Feedback operations
    public void insertFeedback() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Customer ID: ");
        int customer_id = scanner.nextInt(); scanner.nextLine(); // consume newline
        System.out.print("Enter Customer ID: ");
        int product_id = scanner.nextInt(); scanner.nextLine(); // consume newline
        System.out.print("Enter Feedback Text: ");
        String feedbackText = scanner.nextLine();

        String sql = "INSERT INTO Feedback ( Customer_id,product_id, FeedbackText, Date) VALUES ( ?,?, ?, NOW())";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customer_id);
            pstmt.setInt(2,product_id);
            pstmt.setString(3, feedbackText);
            pstmt.executeUpdate();
            System.out.println("Feedback inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateFeedback() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Feedback ID to update: ");
        int feedback_id = scanner.nextInt(); scanner.nextLine(); // consume newline
        System.out.print("Enter new Feedback Text: ");
        String newFeedbackText = scanner.nextLine();

        String sql = "UPDATE Feedback SET FeedbackText = ? WHERE Feedback_iD = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newFeedbackText);
            pstmt.setInt(2, feedback_id);
            pstmt.executeUpdate();
            System.out.println("Feedback updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFeedback() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Feedback ID to delete: ");
        int feedback_id = scanner.nextInt(); scanner.nextLine(); // consume newline

        String sql = "DELETE FROM Feedback WHERE FeedbackID = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, feedback_id);
            pstmt.executeUpdate();
            System.out.println("Feedback deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Main menu
    public static void main(String[] args) {
        project crm = new project();
        Scanner scanner = new Scanner(System.in);
        int choice;

        while(true) {
            System.out.println("\nCRM Database Operations:");
            System.out.println("1. Insert Customer");
            System.out.println("2. Update Customer");
            System.out.println("3. Delete Customer");
            System.out.println("4. Insert Contact");
            System.out.println("5. Update Contact");
            System.out.println("6. Delete Contact");
            System.out.println("7. Insert Lead");
            System.out.println("8. Update Lead");
            System.out.println("9. Delete Lead");
            System.out.println("10. Insert Sales Rep");
            System.out.println("11. Update Sales Rep");
            System.out.println("12. Delete Sales Rep");
            System.out.println("13. Insert Interaction");
            System.out.println("14. Update Interaction");
            System.out.println("15. Delete Interaction");
            System.out.println("16. Insert Product");
            System.out.println("17. Update Product");
            System.out.println("18. Delete Product");
            System.out.println("19. Insert Sale");
            System.out.println("20. Update Sale");
            System.out.println("21. Delete Sale");
            System.out.println("22. Insert Feedback");
            System.out.println("23. Update Feedback");
            System.out.println("24. Delete Feedback");
            System.out.println("25. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt(); scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    crm.insertCustomer();
                    break;
                case 2:
                    crm.updateCustomer();
                    break;
                case 3:
                    crm.deleteCustomer();
                    break;
                case 4:
                    crm.insertContact();
                    break;
                case 5:
                    crm.updateContact();
                    break;
                case 6:
                    crm.deleteContact();
                    break;
                case 7:
                    crm.insertLead();
                    break;
                case 8:
                    crm.updateLead();
                    break;
                case 9:
                    crm.deleteLead();
                    break;
                case 10:
                    crm.insertSalesRep();
                    break;
                case 11:
                    crm.updateSalesRep();
                    break;
                case 12:
                    crm.deleteSalesRep();
                    break;
                case 13:
                    crm.insertInteraction();
                    break;
                case 14:
                    crm.updateInteraction();
                    break;
                case 15:
                    crm.deleteInteraction();
                    break;
                case 16:
                    crm.insertProduct();
                    break;
                case 17:
                    crm.updateProduct();
                    break;
                case 18:
                    crm.deleteProduct();
                    break;
                case 19:
                    crm.insertSale();
                    break;
                case 20:
                    crm.updateSale();
                    break;
                case 21:
                    crm.deleteSale();
                    break;
                case 22:
                    crm.insertFeedback();
                    break;
                case 23:
                    crm.updateFeedback();
                    break;
                case 24:
                    crm.deleteFeedback();
                    break;
                case 25:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        

        
    }
}
