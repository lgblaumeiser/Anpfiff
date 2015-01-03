import java.awt.*;

public class InitialInformation extends Panel {
  List teamList;
  Panel listPanel;
  TextField sne;
  String spielerName;
  
  InitialInformation () {
    super ();
    setBackground (Color.lightGray);
    setLayout (null);
    sne = new TextField ();
    add (sne);
    sne.reshape (0, 0, 150, 30);
    listPanel = new Panel ();
    listPanel.setLayout (new GridLayout (1, 1));
    teamList = new List (3, false);
    String teams[] = { "x", "y", "z", "a", "b", "c", "d" }; // BundesligaManager.allgemeineDaten.getTeams ();
    for (int i = 0; i < teams.length; i++)
      teamList.addItem (teams[i]);
    listPanel.add (teamList);
    add(listPanel);
    listPanel.reshape (10, 10, 150, 100);
    spielerName = null;
  }

  public void paint (Graphics g) {
    Dimension mySize = size ();
    if ((mySize.width < 250) || (mySize.height < 150))
      return;
    g.setColor (getBackground ());
    g.fillRect (0, 0, mySize.width-1, mySize.height-1);
    g.setColor (getForeground ());
    g.setFont (new Font ("Helvetica", Font.PLAIN, 12));
    FontMetrics fm = g.getFontMetrics ();
    int x, y;
    x = mySize.width / 2 - 125;
    y = mySize.height / 2 - 75;
    g.drawRect (x-2, y-2, 253, 153);
    g.drawRect (x-1, y-1, 251, 151);
    g.drawRect (x, y, 249, 149);
    g.drawString ("Name:", x + 10, y + (20 - fm.getHeight() / 2 + fm.getAscent ()));
    sne.move (x + 90, y + 5);
    g.drawString ("Team:", x+10, (y+30) + (55 - fm.getHeight() / 2 + fm.getAscent()));
    listPanel.move (x + 90, y + 45);
  }
  
  public void waitOnInfo () {
    System.out.println ("Type Character");
    try {
      int c = System.in.read ();
    } catch (java.io.IOException e) {
    }
  }
}


