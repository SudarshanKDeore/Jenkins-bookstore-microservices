@Entity
public class Book {
  @Id @GeneratedValue
  private Long id;
  private String name;
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
}
