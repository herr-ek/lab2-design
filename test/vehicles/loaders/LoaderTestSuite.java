package vehicles.loaders;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({   TestCarTransport.class,
                        TestScania.class,
                        TestWorkshop.class})
public class LoaderTestSuite {
}
