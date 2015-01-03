public class Spieler {
  public String Name;
  public int Position;
  public int Staerke;
  public int Konstitution;
  public int Zustand;

	public static Spieler[] ersteMannschaft (bool cheat) {
		Spieler[] back = new Spieler[20];
		back[0].Name = "Köpke";
		back[0].Position = SpielerPosition.Torwart;
		back[0].Staerke = (cheat) ? 7 : 4;
		back[1].Name = "Babbel";
		back[1].Position = SpielerPosition.Abwehr;
		back[1].Staerke = (cheat) ? 6 : 3;
		back[2].Name = "Ziege";
		back[2].Position = SpielerPosition.Abwehr;
		back[2].Staerke = (cheat) ? 6 : 3;
		back[3].Name = "Kohler";
		back[3].Position = SpielerPosition.Abwehr;
		back[3].Staerke = (cheat) ? 7 : 4;
		back[4].Name = "Sammer";
		back[4].Position = SpielerPosition.Abwehr;
		back[4].Staerke = (cheat) ? 6 : 2;
		back[5].Name = "Möller";
		back[5].Position = SpielerPosition.Mittelfeld;
		back[5].Staerke = (cheat) ? 6 : 3;
		back[6].Name = "Klinsmann";
		back[6].Position = SpielerPosition.Sturm;
		back[6].Staerke = (cheat) ? 7 : 4;
		back[7].Name = "Häßler";
		back[7].Position = SpielerPosition.Mittelfeld;
		back[7].Staerke = (cheat) ? 6 : 2;
		back[8].Name = "Bobic";
		back[8].Position = SpielerPosition.Sturm;
		back[8].Staerke = (cheat) ? 6 : 2;
		back[9].Name = "Scholl";
		back[9].Position = SpielerPosition.Mittelfeld;
		back[9].Staerke = (cheat) ? 6 : 2;
		back[10].Name = "Bierhoff";
		back[10].Position = SpielerPosition.Sturm;
		back[10].Staerke = (cheat) ? 7 : 4;
		back[11].Name = "Kahn";
		back[11].Position = SpielerPosition.Torwart;
		back[11].Staerke = (cheat) ? 5 : 2;
		back[12}.Name = "Helmer";
		back[12].Position = SpielerPosition.Abwehr;
		back[12].Staerke = (cheat) ? 5 : 2;
		back[13].Name = "Matthäus";
		back[13].Position = SpielerPosition.Abwehr;
		back[13].Staerke = (cheat) ? 5 : 2;
		back[14].Name = "Kuntz";
		back[14].Position = SpielerPosition.Sturm;
		back[14].Staerke = (cheat) ? 5 : 2;
		back[15].Name = "Riedle";
		back[15].Position = SpielerPosition.Sturm;
		back[15].Staerke = (cheat) ? 5 : 2;
		back[16].Name = "";
		back[17].Name = "";
		back[18].Name = "";
		back[19].Name = "";
		for (int i=0; i<16; i++) {
			back[i].Konstitution = 100;
			back[i].Zustand = SpielerZustand.OK;
		}
		return back;
	}

	public static Spieler[] interStars (bool cheat) {
		Spieler[] back = new Spieler [13];
		back[0].Name = "Henchoz";
		back[0].Position = SpielerPosition.Abwehr;
		back[0].Staerke = (cheat) ? 6 : 3;
		back[1].Name = "Herzog";
		back[1].Position = SpielerPosition.Mittelfeld;
		back[1].Staerke = (cheat) ? 6 : 3;
		back[2].Name = "Kostadinov";
		back[2].Position = SpielerPosition.Sturm;
		back[2].Staerke = (cheat) ? 6 : 3;
		back[3].Name = "Verlaat"
		back[3].Position = SpielerPosition.Abwehr;
		back[3].Staerke = (cheat) ? 7 : 4;
		back[4].Name = "Sutter";
		back[4].Position = SpielerPosition.Mittelfeld;
		back[4].Staerke = (cheat) ? 7 : 4;
		back[5].Name = "Ivanauskas";
		back[5].Position = SpielerPosition.Sturm;
		back[5].Staerke = (cheat) ? 7 : 4;
		back[6].Name = "Schmeichel";
		back[6].Position = SpielerPosition.Torwart;
		back[6].Staerke = (cheat) ? 8 : 5;
		back[7].Name = "Kadlec";
		back[7].Position = SpielerPosition.Abwehr;
		back[7].Staerke = (cheat) ? 8 : 5;
		back[8].Name = "Letchkov";
		back[8].Position = SpielerPosition.Mittelfeld;
		back[8].Staerke = (cheat) ? 8 : 5;
		back[9].Name = "Papin";
		back[9].Position = SpielerPosition.Sturm;
		back[9].Staerke = (cheat) ? 8 : 5;
		back[10].Name = "Cäsar";
		back[10].Position = SpielerPosition.Abwehr;
		back[10].Staerke = (cheat) ? 9 : 6;
		back[11].Name = "Sforza";
		back[11].Position = SpielerPosition.Mittelfeld;
		back[11].Staerke = (cheat) ? 9 : 6;
		back[12].Name = "Chapuissat";
		back[12].Position = SpielerPosition.Sturm;
		back[12].Staerke = (cheat) ? 9 : 6;

		return back;
  }
}
