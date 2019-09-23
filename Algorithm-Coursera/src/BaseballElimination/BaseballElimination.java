import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FordFulkerson;

import java.util.Arrays;
import java.util.HashMap;

public class BaseballElimination {
  private static final double EPSILON = 0.00001;

  private final int numberOfTeams;
  private final String[] teams;
  private final int[] wins;
  private final int[] loses;
  private final int[] remains;
  private final int[][] games;
  private final HashMap<String, Integer> nameToIndex;
  private final int maxWin;
  private final String maxWinTeam;
  private final HashMap<String, Bag<String>> certificateMap;
  private final HashMap<String, Boolean> eliminatedMap;

  // create a baseball division from given filename in format specified below
  public BaseballElimination(String filename) {
    String tempMaxWinTeam;
    int tempMaxWin;
    if (filename == null) throw new IllegalArgumentException("null input");

    In in = new In(filename);
    numberOfTeams = in.readInt();
    teams = new String[numberOfTeams];
    wins = new int[numberOfTeams];
    loses = new int[numberOfTeams];
    remains = new int[numberOfTeams];
    games = new int[numberOfTeams][numberOfTeams];
    nameToIndex = new HashMap<>();
    tempMaxWin = 0;
    tempMaxWinTeam = null;
    certificateMap = new HashMap<>();
    eliminatedMap = new HashMap<>();

    for (int i = 0; i < numberOfTeams; i++) {
      teams[i] = in.readString();
      nameToIndex.put(teams[i], i);

      wins[i] = in.readInt();
      if (wins[i] > tempMaxWin) {
        tempMaxWin = wins[i];
        tempMaxWinTeam = teams[i];
      }

      loses[i] = in.readInt();
      remains[i] = in.readInt();

      for (int j = 0 ; j < numberOfTeams; j++) {
        games[i][j] = in.readInt();
      }
    }
    maxWinTeam = tempMaxWinTeam;
    maxWin = tempMaxWin;
  }

  //  unit testing (optional)
  public static void main(String[] args) {
    BaseballElimination division = new BaseballElimination("teams4.txt");
    for (String team : division.teams()) {
      if (division.isEliminated(team)) {
        StdOut.print(team + " is eliminated by the subset R = { ");
        for (String t : division.certificateOfElimination(team)) {
          StdOut.print(t + " ");
        }
        StdOut.println("}");
      }
      else {
        StdOut.println(team + " is not eliminated");
      }
    }
  }

  // number of teams
  public int numberOfTeams() {
    return numberOfTeams;
  }

  // all teams
  public Iterable<String> teams() {
    return Arrays.asList(teams);
  }

  // number of wins for given team
  public int wins(String team) {
    if (team == null) throw new IllegalArgumentException("null input");
    if (nameToIndex.get(team) == null) throw new IllegalArgumentException("no such team");
    return wins[nameToIndex.get(team)];
  }

  // number of losses for given team
  public int losses(String team) {
    if (team == null) throw new IllegalArgumentException("null input");
    if (nameToIndex.get(team) == null) throw new IllegalArgumentException("no such team");
    return loses[nameToIndex.get(team)];
  }

  // number of remaining games for given team
  public int remaining(String team) {
    if (team == null) throw new IllegalArgumentException("null input");
    if (nameToIndex.get(team) == null) throw new IllegalArgumentException("no such team");
    return remains[nameToIndex.get(team)];
  }

  // number of remaining games between team1 and team2
  public int against(String team1, String team2) {
    if (team1 == null || team2 == null) throw new IllegalArgumentException("null input");
    if (nameToIndex.get(team1) == null || nameToIndex.get(team2) == null) throw new IllegalArgumentException("no such team");
    return games[nameToIndex.get(team1)][nameToIndex.get(team2)];
  }

  // is given team eliminated?
  public boolean isEliminated(String team) {
    if (team == null) throw new IllegalArgumentException("null input");
    if (nameToIndex.get(team) == null) throw new IllegalArgumentException("no such team");

    if (!eliminatedMap.containsKey(team)) checkEliminated(team);
    return eliminatedMap.get(team);
  }

  // subset R of teams that eliminates given team; null if not eliminated
  public Iterable<String> certificateOfElimination(String team) {
    if (team == null) throw new IllegalArgumentException("null input");
    if (nameToIndex.get(team) == null) throw new IllegalArgumentException("no such team");

    if (!certificateMap.containsKey(team)) checkEliminated(team);
    return certificateMap.get(team);
  }

  private void checkEliminated(String team) {
    if (wins(team) + remaining(team) < maxWin) {
      Bag<String> b = new Bag<>();
      b.add(maxWinTeam);
      if (!certificateMap.containsKey(team)) certificateMap.put(team, b);
      if (!eliminatedMap.containsKey(team)) eliminatedMap.put(team, true);
      return;
    }

    FlowNetwork fn = initializeFlowNetwork(nameToIndex.get(team));
    int s = fn.V() - 2;
    int t = fn.V() - 1;
    FordFulkerson ff = new FordFulkerson(fn, s, t);
    if (isFullFromSource(fn, s)) {
      eliminatedMap.put(team, false);
      certificateMap.put(team, null);
      return;
    }

    eliminatedMap.put(team, true);
    Bag<String> b = new Bag<>();
    for (int i = 0; i < numberOfTeams; ++i) {
      if (ff.inCut(i)) {
        b.add(teams[i]);
      }
    }
    certificateMap.put(team, b);
  }

  private FlowNetwork initializeFlowNetwork(int idx) {

    int numGames = numberOfTeams * (numberOfTeams-1) / 2;
    int numV = numGames + numberOfTeams + 2;
    int s = numV - 2;
    int t = numV - 1;

    // Vertices:
    // 0 to numberOfTeams-1: teams
    // numberOfTeams to numberOfTeams+numGames: games
    // numV-2: source
    // numV-1: target

    FlowNetwork fn = new FlowNetwork(numV);

    int v = numberOfTeams;
    for (int i = 0; i < numberOfTeams; i++) {
      for (int j = i + 1; j < numberOfTeams; j++) {
        // edges from s to games
        fn.addEdge(new FlowEdge(s, v, games[i][j]));
        // edges from games to teams
        fn.addEdge(new FlowEdge(v, i, Double.POSITIVE_INFINITY));
        fn.addEdge(new FlowEdge(v, j, Double.POSITIVE_INFINITY));
        v++;
      }
    }

    int sum = wins[idx] + remains[idx];
    for (int i = 0; i < numberOfTeams; i++) {
      // edges from teams to t
      fn.addEdge(new FlowEdge(i, t, sum - wins[i]));
    }
    return fn;
  }

  private boolean isFullFromSource(FlowNetwork fn, int s) {
    for (FlowEdge fe : fn.adj(s)) {
      if (Math.abs(fe.flow() - fe.capacity()) > EPSILON) {
        return false;
      }
    }
    return true;
  }
}
