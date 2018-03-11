package elena.app1;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by elina_000 on 10.03.2018.
 */
public class PointTests {
    @Test
    public void testArea(){
        Point p1 = new Point(10, 15);
        Point p2 = new Point(18, 12);
        assert p1.distance(p2)==8.54400374531753;

    }

    @Test
    public void testAreaFailSample(){
        Point p1 = new Point(10, 15);
        Point p2 = new Point(18, 12);
        Assert.assertEquals(p1.distance(p2),8.5);

    }
}
