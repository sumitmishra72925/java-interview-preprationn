package topic.java.learning.oops_concept.design_class.inheritence_inner_class;

public class LibraryItem {
    protected String itemId;
    protected String title;
    protected String author;

    public LibraryItem(){

    }

    public LibraryItem(String itemId, String title, String author) {
        this.itemId = itemId;
        this.title = title;
        this.author = author;
    }


    protected void  checkout(){
        System.out.println("You have checkout the item: "+ this.itemId);
    }

    protected void returnItem(){
        System.out.println("Thanks for returning the item: "+this.itemId);
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public static void main(String[] args) {
        Book b1 = new Book();
        b1.setAuthor("Sumit");
        b1.setItemId("123");
        b1.setTitle("Java Interview");
        b1.setIsbnNumber("B1234JHYT");
        b1.checkout();
        b1.returnItem();
    }



}
