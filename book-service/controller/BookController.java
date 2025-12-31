@RestController
@RequestMapping("/books")
public class BookController {
  private final BookRepository repo;
  public BookController(BookRepository repo) { this.repo = repo; }

  @PostMapping
  public void save(@RequestBody Book book) {
    repo.save(book);
  }
}
