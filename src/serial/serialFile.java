package serial;

public class serialFile implements java.io.Serializable 
{ 
    public int id; 
    public String name;
    public int price;
    public int quantity;
  
    // Default constructor 
    public serialFile(int id, String name,int price,int quantity) 
    { 
        this.id = id; 
        this.name = name; 
        this.price = price;
        this.quantity = quantity;
    } 
  
} 