package net.wolfpac.cpportal.v2.sorter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.net.Proxy.Type;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import net.wolfpac.cpportal.v2.clients.ClientType.TYPE;
import net.wolfpac.cpportal.v2.db.DBConnector;
import net.wolfpac.logger.BasicLoggerPool;

public class Sorter  {
	private static BasicLoggerPool loggerPool = BasicLoggerPool.getLogger(System.getProperty("confFile"));
	
	private DBConnector dao;
	private Properties prop;
	private File dirList;
	private TYPE type;
	
	public Sorter(Properties prop,  File dirList) throws Exception {
		this.prop = prop;
		this.dirList = dirList;
		this.type = type;
		//
		
	}
	
	public void spawnThreads() throws FileNotFoundException, IOException{
		//FileReader file = new FileReader(this.dirList);
		BufferedReader file = new BufferedReader(new FileReader(this.dirList));
		ExecutorService es = Executors.newFixedThreadPool(20);
		String line;
		while( (line = file.readLine()) != null) {
			try {
				String[] arrLine = line.split(":".intern());
				SorterThread st = null;
				type = TYPE.valueOf(arrLine[3]);
				if (type == TYPE.REQUESTER ) {
					st = new RequesterSorterThread(this.prop, new File(arrLine[0]), new File(arrLine[1]), Integer.parseInt(arrLine[2]),  arrLine[4]);
				}
				else if (type == TYPE.REPORTER){
					st = new ReporterSorterThread(this.prop, new File(arrLine[0]), new File(arrLine[1]), Integer.parseInt(arrLine[2]) );
				}
				es.submit(st);				
			}
			catch(Exception e) {
				
			}
		}
		
		file.close();
		
	}
	
	
	
	
	
	public static void main(String[] args) throws Exception {
		loggerPool.logMeHum("sorter application started");
		Properties prop = new Properties();
		prop.load(new FileInputStream(args[0]));
		
		File file = new File(args[1]);
		
		Sorter sorter = new Sorter(prop, file);
		sorter.spawnThreads();
		
		
	}
}
