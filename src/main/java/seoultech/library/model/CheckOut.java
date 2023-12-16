package seoultech.library.model;

import com.google.common.base.Objects;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = CheckOut.TABLE_NAME)
public class CheckOut {

    protected static final String TABLE_NAME = "check_out";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "check_out_id", updatable = false, unique = true, nullable = false)
    private Long id;

    @Column(name = "check_out_date")
    private LocalDate checkOutDate;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @Column(name = "fine")
    private BigDecimal fine;

    @ManyToOne
    @JoinColumn(name = "book_item_id")
    private BookItem bookItem;

    @ManyToOne
    @JoinColumn(name = "borrower_id")
    private User borrower;

    public CheckOut() {
        super();
    }

    public CheckOut(Long id) {
        super();
        this.id = id;
    }

    public CheckOut(LocalDate dueDate, BookItem bookItem, User borrower) {
        super();
        this.dueDate = dueDate;
        this.bookItem = bookItem;
        this.borrower = borrower;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public BigDecimal getFine() {
        return fine;
    }

    public void setFine(BigDecimal fine) {
        this.fine = fine;
    }

    public BookItem getBookItem() {
        return bookItem;
    }

    public void setBookItem(BookItem bookItem) {
        this.bookItem = bookItem;
    }

    public User getBorrower() {
        return borrower;
    }

    public void setBorrower(User borrower) {
        this.borrower = borrower;
    }

    @PrePersist
    public void prePersist() {
        checkOutDate = LocalDate.now();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(obj == this) return true;
        if(getClass() != obj.getClass()) return false;
        final CheckOut other = (CheckOut)obj;
        return Objects.equal(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id);
    }

    @Override
    public String toString() {
        return "CheckOut[id=" + id + ",checkOutDate=" + checkOutDate + ",dueDate=" + dueDate+ ",borrower=" + borrower.getUsername() + ",returnDate=" + returnDate + "]";
    }
}
