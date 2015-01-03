import java.awt.*;

public class ManagerMarkt extends Panel {
  Panel button;
  
  ManagerMarkt () {
    super ();
    setBackground (Color.lightGray);
    setLayout (null);
    button = new Panel ();
    button.setLayout (new GridLayout (3, 3));
    button.add (new Button ("Tototip"));
    button.add (new Button ("Transfermarkt"));
    button.add (new Button ("Baumaﬂnahmen"));
    button.add (new Button ("Tabelle zeigen"));
    button.add (new Button ("Spielerstatus zeigen"));
    button.add (new Button ("N‰chsten Gegener zeigen"));
    button.add (new Button ("Mannschaft aufstellen"));
    button.add (new Button ("N‰chsten Spieltag zeigen"));
    button.add (new Button ("N‰chsten Spieltag spielen"));
    add (button);
    button.reshape (0, 60, 600, 90);
  }

  public void paint (Graphics g) {
    Dimension mySize = size ();
    g.setColor (getBackground ());
    g.fillRect (0, 0, mySize.width-1, mySize.height-1);
    g.setColor (getForeground ());
    g.setFont (new Font ("Helvetica", Font.PLAIN, 18));
    FontMetrics fm = g.getFontMetrics ();
    int x, y;
    String ausgabe = new String ("M A N A G E R M A R K T");
    x = (mySize.width - fm.stringWidth (ausgabe)) / 2;
    y = 35;
    g.drawString (ausgabe, x, y);
    g.setFont (new Font ("Helvetica", Font.PLAIN, 12));
    fm = g.getFontMetrics ();
    y = 150 + (100 - 5 * fm.getHeight ()) / 2 + fm.getAscent ();
    g.drawString ("Kapital:", 40, y);
    g.drawString ("xx DM", 150, y);
    y += fm.getHeight ();
    g.drawString ("Spieltag:", 40, y);
    g.drawString ("x", 150, y);
    y += fm.getHeight ();
    g.drawString ("Stadionpl‰tze:", 40, y);
    g.drawString ("xxxxx", 150, y);
    y += fm.getHeight ();
    g.drawString ("Managername:", 40, y);
    g.drawString ("Lars Geyer", 150, y);
    y += fm.getHeight ();
    g.drawString ("Managerfaktor:", 40, y);
    g.drawString ("x", 150, y);
  }
}

class MMTCanvas extends Canvas {
  MMTCanvas () {
    super ();
    setBackground (Color.lightGray);
  }

  public void paint (Graphics g) {
    Dimension mySize = size ();
    g.setFont (new Font ("Helvetica", Font.PLAIN, 18));
    String ausgabe = new String ("M A N A G E R M A R K T");
    FontMetrics fm = g.getFontMetrics ();
    int x = (mySize.width - fm.stringWidth (ausgabe)) / 2;
    int y = (mySize.height - fm.getHeight ()) / 2 + fm.getAscent ();
    g.drawString (ausgabe, x, y);
  }
}

class MMICanvas extends Canvas {
  MMICanvas () {
    super ();
    setBackground (Color.lightGray);
  }

  public void paint (Graphics g) {
    Dimension mySize = size ();
    g.setFont (new Font ("Helvetica", Font.PLAIN, 12));
    FontMetrics fm = g.getFontMetrics ();
    int x1 = 40;
    int x2 = 150;
    int y = (mySize.height - 5 * fm.getHeight ()) / 2 + fm.getAscent ();
    g.drawString ("Kapital:", x1, y);
    g.drawString ("xx DM", x2, y);
    y += fm.getHeight ();
    g.drawString ("Spieltag:", x1, y);
    g.drawString ("x", x2, y);
    y += fm.getHeight ();
    g.drawString ("Stadionpl‰tze:", x1, y);
    g.drawString ("xxxxx", x2, y);
    y += fm.getHeight ();
    g.drawString ("Managername:", x1, y);
    g.drawString ("Lars Geyer", x2, y);
    y += fm.getHeight ();
    g.drawString ("Managerfaktor:", x1, y);
    g.drawString ("x", x2, y);
  }
}
