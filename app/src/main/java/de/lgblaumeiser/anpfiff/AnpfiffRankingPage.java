package de.lgblaumeiser.anpfiff;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import de.lgblaumeiser.anpfiff.simulation.SimulationManager;
import de.lgblaumeiser.anpfiff.simulation.model.SeasonConstants;
import de.lgblaumeiser.anpfiff.simulation.model.TableEntry;
import de.lgblaumeiser.anpfiff.simulation.services.season.SeasonManager;

public class AnpfiffRankingPage extends ActionBarActivity {
    private SeasonManager seasonManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anpfiff_ranking_page);
        seasonManager = new SimulationManager().simulation();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SeasonManager season = seasonManager.newSeason();
        for (int gameday = 0; gameday < SeasonConstants.NUMBER_OF_GAME_DAYS; gameday++) {
            season.playNextGameDay();
        }

        StringBuilder htmlTableResult = new StringBuilder(2000);
        htmlTableResult.append("<html><head><title>Anpfiff Table View</title></head><body>");
        htmlTableResult.append("<h3>Table for game day 34</h3>");
        htmlTableResult.append("<table><thead><tr><th>Team</th><th>Points</th><th>Diff</th></tr></thead><tbody>");
        for (final TableEntry entry : season.getTableForLastGameDay()) {
            htmlTableResult.append("<tr><td>");
            htmlTableResult.append(makeNamesHTMLReady(entry.getTeam().getName()));
            htmlTableResult.append("</td><td align=\"center\">");
            htmlTableResult.append(entry.getPoints());
            htmlTableResult.append("</td><td align=\"center\">");
            htmlTableResult.append(entry.getGoalDifference());
            htmlTableResult.append("</td></tr>");
        }
        htmlTableResult.append("</tbody></body></html>");

        WebView browser = (WebView)findViewById(R.id.rankingsView);
        browser.loadData(htmlTableResult.toString(), "text/html", "UTF-8");
    }

    private static String makeNamesHTMLReady(String input) {
        return input.replaceAll("ü", "&uuml;")
                    .replaceAll("ö", "&ouml;")
                    .replaceAll("ä", "&auml;")
                    .replaceAll("ß", "&szlig;")
                    .replaceAll("Ü", "&Uuml;")
                    .replaceAll("Ö", "&Ouml;")
                    .replaceAll("Ä", "&Auml;");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_anpfiff_ranking_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
