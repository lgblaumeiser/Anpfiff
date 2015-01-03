import java.awt.*;

public class BundesligaManager extends java.applet.Applet implements Runnable {
  Thread myThread;
  InitialInformation initInfo;
  ManagerMarkt manMarkt;

  public void init () {
    resize (600, 250);
    myThread = new Thread (this);
  }

  public void start () {
    setLayout (new GridLayout (1, 1));
    initInfo = new InitialInformation ();
    manMarkt = new ManagerMarkt ();
    add (initInfo);
//    initInfo.reshape (0, 0, size().width, size().height);
    myThread.start ();
  }

  public void stop () {
    myThread.stop ();
  }

  public void run () {
    try {
      Thread.sleep (5000);
    } catch (Exception e) {
    }
    removeAll ();
    add (manMarkt);
    validate ();
//    manMarkt.resize (size().width, size().height);
    while (true);
  }
}
