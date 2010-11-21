package anthology.nodeadlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class main
{
    public static void main(String[] args)
    {
        Lock lock = new ReentrantLock();
        Condition responseHandled = lock.newCondition();
        Receivers receivers = new Receivers(10, lock, responseHandled);

        lock.lock();
        try
        {
            int remainingLoops = 5;

            // @todo:
            // Encapsulate the current state of the receivers in a list of ints and have the Receivers class return
            // that.  Compare the list with a predicate to determine if we are making progress.
            int numReceived1 = 0;
            int numReceived2 = 0;

            while (!receivers.isComplete() && remainingLoops > 0)
            {
                try {
                    responseHandled.await(5000, TimeUnit.SECONDS);
                }
                catch (InterruptedException e)
                {}

                // Ensure we are making progress... receiving messages.
                if (receivers.getNumReceived1() <= numReceived1
                    && receivers.getNumReceived2() <= numReceived2)
                {
                    remainingLoops--;
                }
            }
        }
        finally
        {
            lock.unlock();
        }
    }

}
