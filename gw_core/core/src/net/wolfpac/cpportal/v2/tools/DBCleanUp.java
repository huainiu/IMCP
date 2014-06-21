package net.wolfpac.cpportal.v2.tools;

import java.io.FileInputStream;
import java.util.Properties;

import net.wolfpac.cpportal.v2.db.DBConnector;
import net.wolfpac.cpportal.v2.db.FreqType;
import net.wolfpac.cpportal.v2.db.ResetType;
import net.wolfpac.logger.BasicLoggerPool;

public class DBCleanUp {
	private static BasicLoggerPool loggerPool = BasicLoggerPool.getLogger(System.getProperty("confFile"));
	private DBConnector dbc;
	
	public DBCleanUp( Properties prop, int reset ) {
		try {
			this.dbc = new DBConnector(prop.getProperty("DB_URL"), prop.getProperty("DB_USER_RESET"), prop.getProperty("DB_PASS_RESET"));
			int count = 0;
			loggerPool.logMeHum(ResetType.getType(reset)+"["+reset+"] > resetting!");
			switch ( ResetType.getType(reset) ) {
				case FREQ_TYPE_RESET:
					count = dbc.resetFrequencyType(FreqType.DAILY);
					break;
				case RRN_RESET:
					count = dbc.resetRRNTable();
					break;
				case BROADCAST_RESET:
					count = dbc.resetbcastTB();
					break;
				case DISPATCHED_RESET:
					count = dbc.resetDispatchedTB();					
					break;
				case MSG_RESET:
					count = dbc.resetMsgTB();
					break;
				case SUBS_COUNT_RESET:
					count = dbc.resetSubsTBcount();
					break;
				case UNKNOWN:
					break;
				default:
					break;				
			}			
			loggerPool.logMeHum(ResetType.getType(reset)+"["+reset+"] < done - count :"+ count);
			
		}catch( Exception e ){
			e.printStackTrace();
			
		}		
		
	}	
	
	public static void main(String[] args) {
		try {
    		loggerPool.logMeError("DBClean UP started!");
	    	Properties prop = new Properties();    	
	    	prop.load(new FileInputStream(args[0]));
	    	/**
	    	 *   RRN_RESET(0), FREQ_TYPE_RESET(1), SUBS_COUNT_RESET(2), DISPATCHED_RESET(3), BROADCAST_RESET(4), MSG_RESET(5), UNKNOWN(-1);
	    	 */
	    	DBCleanUp dc = new DBCleanUp(prop, Integer.parseInt(args[1]) );
	    	
	    		    	
    	}
    	catch(Exception e) 
    	{
    		loggerPool.logMeError(e.toString());
    		e.printStackTrace();
    	}
	}
	
	
	
	
}
