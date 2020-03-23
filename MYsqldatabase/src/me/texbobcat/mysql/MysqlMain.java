package me.texbobcat.mysql;

import java.sql.SQLException;
import java.sql.DriverManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import net.md_5.bungee.api.ChatColor;
import java.sql.Connection;

public class MysqlMain extends JavaPlugin {
	private Connection connection;
	public String host, database, username, password, table;
	public int port;
	
	public void onEnable() {
		mysqlSetup();
		this.getServer().getPluginManager().registerEvents(new MysqlSetterGetter(), this);
		
	}
	
	public void mysqlSetup() {
		host = "147.135.30.11";
	    port = 3306;
	    database = "s2678_database";
	    password = "AmuJ9FAnCWA1av9kfwyb7pk9";
	    username = "u2678_LjRlPhXol1";
	    table = "s2678_database . bukkitcoding";
	    try{

	    	synchronized (this){
	    		if(getConnection() != null && !getConnection().isClosed()){
		    		return;
		    	}
	    		
	    		Class.forName("com.mysql.jdbc.Driver");
	    		setConnection(DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, this.username, this.password));
	    		
	    		Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GREEN + "MYSQL connected");
	    	}
	    }catch(SQLException e){
	    	e.printStackTrace();
	    }catch(ClassNotFoundException e){
	    	e.printStackTrace();
	    }
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
}
