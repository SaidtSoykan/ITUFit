package groupeighteen.itufit.webapi;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import groupeighteen.itufit.application.services.meal.MealService;
import groupeighteen.itufit.application.shared.response.IResponse;

import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("meals")
public class MealController {
    
    private final MealService mealService;

    public MealController(MealService mealService){
        this.mealService = mealService;
    }

    @PostMapping(value = "fetchData", produces = "application/json")
    public IResponse fetchData() {
        return mealService.fetchData();
    }
}
