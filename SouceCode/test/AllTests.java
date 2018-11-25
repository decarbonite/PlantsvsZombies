import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        ViewTest.class,
        ControllerTest.class,
        BoardTest.class,
        BoardNodeTest.class,
        BoardRowTest.class,
        MoneyPlantTest.class,
        PlantTest.class,
        ZombieTest.class
})


public class AllTests {

}