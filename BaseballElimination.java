import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.FordFulkerson;

import java.util.HashMap;
import java.util.LinkedList;

public class BaseballElimination {

    private HashMap<Integer, String>  teams_is;
    private HashMap<String,Integer> teams_si;
    private int amount;
    private LinkedList<String> team_names;
    private int[][] w;
    private int[][] g;

    public BaseballElimination(String filename){
        In a = new In(filename);
        amount= a.readInt();
        w = new int[amount][3];
        g = new int[amount][amount];
        teams_is = new HashMap<>();
        teams_si = new HashMap<>();
        team_names = new LinkedList<>();
        int n = 0;

        while(!a.isEmpty()){
            String team_name = a.readString();
            team_names.addLast(team_name);
            teams_si.put(team_name,n);
            teams_is.put(n,team_name);
            for(int i = 0;i<3;i++)
                w[n][i] = a.readInt();
            for(int i = 0; i<amount;i++)
                g[n][i] = a.readInt();
            //System.out.print(n);
            n++;
        }
       // System.out.println(teams_is.size());

    }

    public int numberOfTeams(){
        return amount;
    }

    public Iterable<String> teams(){
        return team_names;
    }

    public int wins(String team){
             if (!teams_si.containsKey(team))
                 throw new IllegalArgumentException("IllegalArgumentException");
             else return w[teams_si.get(team)][0];
    }

    public int losses(String team){
             if(!teams_si.containsKey(team))
                 throw new IllegalArgumentException("IllegalArgumentException");
             else return w[teams_si.get(team)][1];
    }

    public int remaining(String team){
        if(!teams_si.containsKey(team))
            throw new IllegalArgumentException("IllegalArgumentException");
        else return w[teams_si.get(team)][2];
    }

    public int against(String team1, String team2){
        if((!teams_si.containsKey(team1))||(!teams_si.containsKey(team2)))
            throw new IllegalArgumentException("IllegalArgumentException");
        else return g[teams_si.get(team1)][teams_si.get(team2)];
    }

    public boolean isEliminated(String team){
        int lamount = amount-1;
        int n = (lamount*(lamount-1))/2+2+lamount;
        FlowNetwork flownetwork = new FlowNetwork(n);
        int i = 1;// notice here, you don't need to find the relation between the team and teh sequence number, just add.
        for(int team1 =0; team1 < lamount;team1++){
            if(teams_is.get(team1).equals(team))
                continue;
            for(int team2 = team1+1;team2<=lamount;team2++){
                if(teams_is.get(team2).equals(team))
                    continue;
                FlowEdge flowedge = new FlowEdge(0,i,g[team1][team2]);
                flownetwork.addEdge(flowedge);
                i++;
            }
        }


         int first_team_seq = i;
         //int z = i;
         int t = 0;
         for(int j = first_team_seq; j<n-1; j++){

             if(teams_is.get(t).equals(team))
                 t++;
             int k = w[teams_si.get(team)][0]+w[teams_si.get(team)][2]-w[t][0];
             if(k<0){
                 return true;
             }

             FlowEdge flowEdge = new FlowEdge(j,n-1,k);
             flownetwork.addEdge(flowEdge);
             t++;
         }


         HashMap<Integer,Integer> map = new HashMap<>();

         t= 0;

         for(int z = first_team_seq; z<n-1;z++){
             if(teams_is.get(t).equals(team))
                 t++;
             map.put(t,z);
             t++;
         }

        // System.out.print(map.size());

         i=1;
         for(int team1 =0; team1 < lamount;team1++){
             if(teams_is.get(team1).equals(team))
                 continue;
             for(int team2 = team1+1;team2<=lamount;team2++){
                 if(teams_is.get(team2).equals(team))
                     continue;

                 FlowEdge flowedge = new FlowEdge(i,map.get(team1),Double.POSITIVE_INFINITY);
                 flownetwork.addEdge(flowedge);

                  flowedge = new FlowEdge(i,map.get(team2),Double.POSITIVE_INFINITY);
                 flownetwork.addEdge(flowedge);
                     i++;
             }
         }

     //    Iterable<FlowEdge> tedges = flownetwork.adj(0);

        FordFulkerson fordFulkerson = new FordFulkerson(flownetwork,0,n-1);

   //     System.out.print(flownetwork.toString());

        Iterable<FlowEdge> edges =  flownetwork.adj(0);

        for (FlowEdge e: edges) {
            if(e.residualCapacityTo(0)!=e.capacity()){
                return true;
            }
        }

        return false;
    }

    public Iterable<String> certificateOfElimination(String team){
        LinkedList<String> r_teams= new LinkedList<>();
        int lamount = amount-1;
        int n = (lamount*(lamount-1))/2+2+lamount;
        FlowNetwork flownetwork = new FlowNetwork(n);
        int i = 1;// notice here, you don't need to find the relation between the team and teh sequence number, just add.
        for(int team1 =0; team1 < lamount;team1++){
            if(teams_is.get(team1).equals(team))
                continue;
            for(int team2 = team1+1;team2<=lamount;team2++){
                if(teams_is.get(team2).equals(team))
                    continue;
                FlowEdge flowedge = new FlowEdge(0,i,g[team1][team2]);
                flownetwork.addEdge(flowedge);
                i++;
            }
        }

        int first_team_seq = i;
        //int z = i;
        int t = 0;
        for(int j = first_team_seq; j<n-1; j++){

            if(teams_is.get(t).equals(team))
                t++;
            int k = w[teams_si.get(team)][0]+w[teams_si.get(team)][2]-w[t][0];
            if(k<0){
                r_teams.addLast(teams_is.get(t));
                return r_teams;
            }

            FlowEdge flowEdge = new FlowEdge(j,n-1,k);
            flownetwork.addEdge(flowEdge);
            t++;
        }

        t= 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int z = first_team_seq; z<n-1;z++){
            if(teams_is.get(t).equals(team))
                t++;
            map.put(t,z);
            t++;
        }

        i=1;
        for(int team1 =0; team1 < lamount;team1++){
            if(teams_is.get(team1).equals(team))
                continue;
            for(int team2 = team1+1;team2<=lamount;team2++){
                if(teams_is.get(team2).equals(team))
                    continue;

                FlowEdge flowedge = new FlowEdge(i,map.get(team1),Double.POSITIVE_INFINITY);
                flownetwork.addEdge(flowedge);

                flowedge = new FlowEdge(i,map.get(team2),Double.POSITIVE_INFINITY);
                flownetwork.addEdge(flowedge);
                i++;
            }
        }

      //  Iterable<FlowEdge> tedges = flownetwork.adj(0);

        FordFulkerson fordFulkerson = new FordFulkerson(flownetwork,0,n-1);

        //System.out.print(flownetwork.toString());

        // this part should be right, you have checked
        Iterable<FlowEdge> edges = flownetwork.adj(0);
        for (FlowEdge e: edges) {
            if(e.residualCapacityTo(0)!=e.capacity()) {
                t = 0;
                for(int j = first_team_seq; j<n-1;j++) {
                    if (teams_is.get(t).equals(team))
                        t++;
                    if (fordFulkerson.inCut(j))
                        r_teams.addLast(teams_is.get(t));
                    t++;
                }
                return r_teams;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        BaseballElimination division = new BaseballElimination(args[0]);
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
}
