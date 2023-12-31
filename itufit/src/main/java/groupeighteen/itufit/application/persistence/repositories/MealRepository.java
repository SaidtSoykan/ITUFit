package groupeighteen.itufit.application.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import groupeighteen.itufit.domain.meal.Meal;

public interface MealRepository extends JpaRepository<Meal, Long>{
    
}
