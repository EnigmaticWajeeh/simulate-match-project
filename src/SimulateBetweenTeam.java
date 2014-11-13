import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;


public class SimulateBetweenTeam {

	public static void main(String[] args) {
		String teamPlayerArray[]={""};
		
	if(simulateBetweenPlayers(9,9,"Spain","England","wajeeh1")){
		System.out.println("Team 1 wins");
	};
	
	System.out.println("Winner is : " + SimulateTeam("SPAIN", "ENGLAND", "wajeeh1"));
		
	}
	
	
 public static boolean simulateBetweenPlayers(int jerseyNoPL1,int jerseyNoPL2 , String team1, String team2, String userid){
	 Double calculatedResult1=(double) 0;
	 Double calculatedResult2=(double) 0;


	 
	 
	 String playerStatQuery1="select * from FBM_PLAYER_TABLE_STATS where USER_ID='" + userid +"' AND " +
	 "JERSEY_NO='" + jerseyNoPL1 + "' AND TEAM_ID=(select TEAM_ID from FBM_TABLE_TEAM_ID where TEAM_NAME='"+
		team1.toUpperCase()	+ "')";
	 
	 String playerStatQuery2="select * from FBM_PLAYER_TABLE_STATS where USER_ID='" + userid +"' AND " +
	 "JERSEY_NO='" + jerseyNoPL2 + "' AND TEAM_ID=(select TEAM_ID from FBM_TABLE_TEAM_ID where TEAM_ID='"+
		team2.toUpperCase()	+ "')";
	 
	 Connection conn=getConnection();

	 ResultSet rs1;
	try {
		rs1 = conn.createStatement().executeQuery(playerStatQuery1);

		 while(rs1.next()){
			 calculatedResult1= ( (double) rs1.getInt("ATTACKING") +  (double) rs1.getInt("ATTACKING") +
					 (double) rs1.getInt("DEFENDING") + (double) rs1.getInt("GOAL_KEEPING") + 
					  (double) rs1.getInt("SHORT_PASSING") + (double) rs1.getInt("LONG_PASSING") +
					   (double) rs1.getInt("CROSSING") + (double) rs1.getInt("SPEED") +
					    (double) rs1.getInt("STAMINA") + (double) rs1.getInt("TACKLING") +
					     (double) rs1.getInt("POWER") + (double) rs1.getInt("ACCURACY") ) * 
					     (double) rs1.getInt("LUCK") ;
		 }
		 
	
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	
	
	 try {
		ResultSet rs2=conn.createStatement().executeQuery(playerStatQuery2);
		
		while(rs2.next()){
			
			 while(rs2.next()){
				 calculatedResult2= ( (double) rs2.getInt("ATTACKING") +  (double) rs2.getInt("ATTACKING") +
						 (double) rs2.getInt("DEFENDING") + (double) rs2.getInt("GOAL_KEEPING") + 
						  (double) rs2.getInt("SHORT_PASSING") + (double) rs2.getInt("LONG_PASSING") +
						   (double) rs2.getInt("CROSSING") + (double) rs2.getInt("SPEED") +
						    (double) rs2.getInt("STAMINA") + (double) rs2.getInt("TACKLING") +
						     (double) rs2.getInt("POWER") + (double) rs2.getInt("ACCURACY") ) * 
						     (double) rs2.getInt("LUCK");
			 }
			 
			
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 

	 if(calculatedResult1>calculatedResult2){
		 return true;
	 }else{
		 return false;
	 }
	 
	 
	}//--------------> end simulateBetweenPlayers method here
	
	
	
 
 
 	public static String SimulateTeam(String team1, String team2, String userID){
		
 		int playerTeam1=0;
 		int playerTeam2=0;
 		int team1player[];
 		team1player=new int[30];
 		int team2player[];
 		team2player=new int[30];
 		
 		for(int i=0;i<30;i++){
 			team1player[i]=0;
 			team2player[i]=0;
 		}
 		
 		Connection conn=getConnection();
 		
 		
 		//select 15 jersey numbers
 		String teamQuery1="select JERSEY_NO from FBM_PLAYER_TABLE_STATS where TEAM_ID=("+
 		"select TEAM_ID from FBM_TABLE_TEAM_ID where TEAM_NAME='"+ team1.toUpperCase()+"')";
 		String teamQuery2="select JERSEY_NO from FBM_PLAYER_TABLE_STATS where TEAM_ID=("+
 		 		"select TEAM_ID from FBM_TABLE_TEAM_ID where TEAM_NAME='"+ team2.toUpperCase()+"')";
 		int count2=0;
 		int count1=0;
 		
 		
 		
 		System.out.println(teamQuery1);
 		System.out.println(teamQuery2);
 		
 		
 		
 		System.out.println("here 1");
 		
 		
 		
 		try {
			Statement st1=conn.createStatement();
			ResultSet rs1=st1.executeQuery(teamQuery1);
			
			while(rs1.next()){
				team1player[count1]=rs1.getInt("JERSEY_NO");
				System.out.print(team1player);
				
				count1++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		


 		
 		try {
			Statement st2=conn.createStatement();
			ResultSet rs2=st2.executeQuery(teamQuery1);
			
			while(rs2.next()){
				team1player[count1]=rs2.getInt("JERSEY_NO");
				System.out.print(team2player);
				
				count2++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		// 15 jersey numbers taken of each team
 		count1=0;
 		count2=0;

 		System.out.println("here 2");
 		for(int i=0;i<15;i++){

 	 		if(simulateBetweenPlayers(team1player[count1], team2player[count2], team1.toUpperCase(), team2.toUpperCase(), global.getUserID())){
 	 			playerTeam1++;
 	 		}else{
 	 			playerTeam2++;
 	 		}
 	 			
 		}
 		
 		
 		if(playerTeam1>playerTeam2){
 			return team1.toUpperCase();
 		}else{
 			return team2.toUpperCase();
 		}
 		
 		
 		
 		
 	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	public static Connection getConnection(){
		System.out.println("Establishing JDBC connection .......");
		
		//making connection to oracle
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Check The Location Of JDBC?");
			e.printStackTrace();
		}
		System.out.println("JDBC Connected");
		
		Connection connection=null;
		
		try {
			connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "C##BMG", "bmg");
		} catch (SQLException e) {
			System.out.println("Check The Connection");
			e.printStackTrace();
		}
		if (connection != null) {
			System.out.println("Working Fine Returning Connection");
			return connection;
		}else{
			System.out.println("Connection not fine");
		}
		return connection;
	}	//----------------------------------> getConnection ENDS, return connection
	
}
