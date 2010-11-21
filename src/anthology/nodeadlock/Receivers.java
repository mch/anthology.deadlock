package anthology.nodeadlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Receivers
{
    // Should really be a collection of receivers
    private ReceiverOne one;
    private ReceiverTwo two;

    Receivers(int numExpected, Lock lock, Condition condition)
    {
        one = new ReceiverOne(numExpected, lock, condition);
        two = new ReceiverTwo(numExpected, lock, condition);
    }

    public boolean isComplete()
    {
        return one.isComplete() && two.isComplete();
    }

    public int getNumReceived1()
    {
        return one.getNumReceived();
    }

    public int getNumReceived2()
    {
        return two.getNumReceived();
    }
}
