package net.wolfpac.cpportal.v2.db;

public class RequesterReporterDir {

	private int ac_id;
	private String requester_dir;
	private String reporter_dir;
	private int kwd_id;
	private String kwd;
	
	
	
	
	
	
	public RequesterReporterDir() {
		// TODO Auto-generated constructor stub
	}

	public int getAc_id() {
		return ac_id;
	}

	

	public String getRequester_dir() {
		return requester_dir;
	}

	public String getReporter_dir() {
		return reporter_dir;
	}

	public int getKwd_id() {
		return kwd_id;
	}

	public String getKwd() {
		return kwd;
	}

	public RequesterReporterDir(int ac_id, String requester_dir,
			String reporter_dir, int kwd_id, String kwd) {	
		this.ac_id = ac_id;
		this.requester_dir = requester_dir;
		this.reporter_dir = reporter_dir;
		this.kwd_id = kwd_id;
		this.kwd = kwd;		
	}
	
	
	
	
	
}
