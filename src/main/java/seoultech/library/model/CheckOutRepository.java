package seoultech.library.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckOutRepository extends JpaRepository<CheckOut, Long> {

    CheckOut findTopByBookItem_Id(Long bookItemId);
}

