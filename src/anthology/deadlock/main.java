package anthology.deadlock;

/**
 * Created by IntelliJ IDEA.
 * User: mch
 * Date: 21-Nov-2010
 * Time: 9:17:00 AM
 * To change this template use File | Settings | File Templates.
 */
public class main {
    public static void main(String[] args) {
        ReceiverOne receiver = new ReceiverOne(10);

        // start threads to send messages to the receivers

        synchronized(receiver)
        {
            int remainingLoops = 5;
            int numReceived1 = 0;
            int numReceived2 = 0;

            while (!receiver.isComplete() && remainingLoops > 0)
            {
                try {
                    receiver.wait(5000);
                }
                catch (InterruptedException e)
                {}

                if (receiver.getNumReceived1() <= numReceived1
                 && receiver.getNumReceived2() <= numReceived2)
                {
                    remainingLoops--;
                }

                numReceived1 = receiver.getNumReceived1();
                numReceived2 = receiver.getNumReceived2();
            }
        }
    }
}
