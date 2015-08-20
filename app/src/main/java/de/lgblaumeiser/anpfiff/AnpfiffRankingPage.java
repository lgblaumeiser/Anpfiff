package de.lgblaumeiser.anpfiff;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import de.lgblaumeiser.anpfiff.simulation.SimulationManager;
import de.lgblaumeiser.anpfiff.simulation.model.Game;
import de.lgblaumeiser.anpfiff.simulation.model.GameResult;
import de.lgblaumeiser.anpfiff.simulation.model.SeasonConstants;
import de.lgblaumeiser.anpfiff.simulation.model.TableEntry;
import de.lgblaumeiser.anpfiff.simulation.services.season.SeasonManager;

public class AnpfiffRankingPage extends ActionBarActivity {
    private SeasonManager seasonManager;
    private PageState currentDisplay = PageState.Table;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anpfiff_ranking_page);
        seasonManager = new SimulationManager().simulation();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SeasonManager season = seasonManager.newSeason();
        findViewById(R.id.left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leftClicked();
            }
        });
        findViewById(R.id.right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rightClicked();
            }
        });
        redraw();
    }

    private void redraw() {
        switch(currentDisplay) {
            case Table:
                drawTable();
                break;
            case NextGameDay:
                drawGameDay();
                break;
            case LastGameDay:
                drawResults();
                break;
            case PlayGameDay:
                playGameDay();
                break;
        }
    }

    private void drawTable() {
        StringBuilder htmlTableResult = new StringBuilder(2000);
        htmlTableResult.append("<html><head><title>Anpfiff Table View</title></head><body>");
        htmlTableResult.append("<h3>Table for game day ");
        htmlTableResult.append(seasonManager.getCurrentGameDay());
        htmlTableResult.append("</h3>");
        htmlTableResult.append("<table><thead><tr><th>Team</th><th>Points</th><th>Diff</th></tr></thead><tbody>");
        for (final TableEntry entry : seasonManager.getTableForLastGameDay()) {
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

        Button leftButton = (Button) findViewById(R.id.left);
        leftButton.setText("Last Game Day");
        leftButton.setActivated(seasonManager.getCurrentGameDay() > 0);
        Button rightButton = (Button) findViewById(R.id.right);
        rightButton.setText("Next Game Day");
        rightButton.setActivated(seasonManager.getCurrentGameDay() < SeasonConstants.NUMBER_OF_GAME_DAYS);
    }

    private void drawGameDay() {
        StringBuilder htmlTableResult = new StringBuilder(2000);
        htmlTableResult.append("<html><head><title>Anpfiff Game Day View</title></head><body>");
        htmlTableResult.append("<h3>Games for game day ");
        htmlTableResult.append(seasonManager.getCurrentGameDay() + 1);
        htmlTableResult.append("</h3>");
        htmlTableResult.append("<table><thead><tr><th>Home Team</th><th></th><th>Guest Team</th></tr></thead><tbody>");
        for (final Game entry : seasonManager.getNextGameDay()) {
            htmlTableResult.append("<tr><td>");
            htmlTableResult.append(makeNamesHTMLReady(entry.getHometeam().getName()));
            htmlTableResult.append("</td><td align=\"center\">vs.</td><td align=\"center\">");
            htmlTableResult.append(makeNamesHTMLReady(entry.getGuestteam().getName()));
            htmlTableResult.append("</td></tr></tbody>");
        }
        htmlTableResult.append("</body></html>");

        WebView browser = (WebView) findViewById(R.id.rankingsView);
        browser.loadData(htmlTableResult.toString(), "text/html", "UTF-8");

        Button leftButton = (Button) findViewById(R.id.left);
        leftButton.setText("Current Table");
        leftButton.setActivated(true);
        Button rightButton = (Button) findViewById(R.id.right);
        rightButton.setText("Play Game Day");
        rightButton.setActivated(true);
    }

    private void drawResults() {
        StringBuilder htmlTableResult = new StringBuilder(2000);
        htmlTableResult.append("<html><head><title>Anpfiff Game Day View</title></head><body>");
        htmlTableResult.append("<h3>Results for game day ");
        htmlTableResult.append(seasonManager.getCurrentGameDay());
        htmlTableResult.append("</h3>");
        htmlTableResult.append("<table><thead><tr><th>Home Team</th><th></th><th>Guest Team</th><th>Result</th></tr></thead><tbody>");
        for (final GameResult entry : seasonManager.getResultsForLastGameDay()) {
            htmlTableResult.append("<tr><td>");
            htmlTableResult.append(makeNamesHTMLReady(entry.getHometeam().getName()));
            htmlTableResult.append("</td><td align=\"center\">vs.</td><td align=\"center\">");
            htmlTableResult.append(makeNamesHTMLReady(entry.getGuestteam().getName()));
            htmlTableResult.append("</td><td align=\"center\">");
            htmlTableResult.append(entry.getHometeamgoals());
            htmlTableResult.append("&nbsp;:&nbsp;");
            htmlTableResult.append(entry.getGuestteamgoals());
            htmlTableResult.append("</td></tr></tbody>");
        }
        htmlTableResult.append("</body></html>");

        WebView browser = (WebView) findViewById(R.id.rankingsView);
        browser.loadData(htmlTableResult.toString(), "text/html", "UTF-8");

        Button leftButton = (Button) findViewById(R.id.left);
        leftButton.setText("No Way");
        leftButton.setActivated(false);
        Button rightButton = (Button) findViewById(R.id.right);
        rightButton.setText("Show New Table");
        rightButton.setActivated(true);
    }

    private void playGameDay() {
        if (seasonManager.getCurrentGameDay() < SeasonConstants.NUMBER_OF_GAME_DAYS) {
            seasonManager.playNextGameDay();
        }
        currentDisplay = PageState.LastGameDay;
        redraw();
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

    private void rightClicked() {
        switch (currentDisplay) {
        case Table:
            currentDisplay = PageState.NextGameDay;
            break;
        case NextGameDay:
            currentDisplay = PageState.PlayGameDay;
            break;
        case LastGameDay:
            currentDisplay = PageState.Table;
            break;
        case PlayGameDay:
            currentDisplay = PageState.PlayGameDay;
            break;
        }
        redraw();
    }

    private void leftClicked() {
        switch (currentDisplay) {
        case Table:
            currentDisplay = PageState.LastGameDay;
            break;
        case NextGameDay:
            currentDisplay = PageState.Table;
            break;
        case LastGameDay:
            currentDisplay = PageState.LastGameDay;
            break;
        case PlayGameDay:
            currentDisplay = PageState.PlayGameDay;
            break;
        }
        redraw();
    }

}
