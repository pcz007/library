package pl.piotrekcz.library;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class BookRepository {

    private EntityManager entityManager;

    public BookRepository(EntityManagerFactory entityManagerFactory) {
        entityManager = entityManagerFactory.createEntityManager();
    }

    public List<Book> showShelf() {
        //noinspection JpaQlInspection
        TypedQuery<Book> query = entityManager.createQuery("select m from Book m", Book.class);
        List<Book> books = query.getResultList();
        return books;
    }

    public void save(Book book) {
        entityManager.getTransaction().begin();
        entityManager.persist(book);
        entityManager.getTransaction().commit();
    }

    public Book findBookId(Long id) {
        Book book = entityManager.find(Book.class, id);
        return book;
    }
}
