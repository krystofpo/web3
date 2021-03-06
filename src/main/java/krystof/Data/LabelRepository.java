package krystof.Data;

import krystof.business.Label;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface LabelRepository extends CrudRepository<Label, Long>{


    List<Label> findByLabel(String label);

    List<Label> findByLabelIgnoreCaseContaining(String label);

    List<Label> findAll();
}
