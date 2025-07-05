package topic.java.learning.oops_concept.design_class.inheritence_inner_class;

public class Book extends LibraryItem {

    private String isbnNumber;
    private String category;

    public String getIsbnNumber() {
        return isbnNumber;
    }

    public void setIsbnNumber(String isbnNumber) {
        this.isbnNumber = isbnNumber;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
