package miro.socialmath.repository;

import miro.socialmath.domain.Multiplication;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MultiplicationRepository extends CrudRepository<Multiplication, Long> {

    Optional<Multiplication> findMultiplicationByFactorAAndAndFactorB(int factorA, int factorB);
}
