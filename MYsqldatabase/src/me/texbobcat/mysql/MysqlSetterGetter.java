package me.texbobcat.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
public class MysqlSetterGetter implements Listener {
	
	MysqlMain plugin = MysqlMain.getPlugin(MysqlMain.class);
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		createPlayer(player.getUniqueId(), player);
		
	 }
	public boolean playerExists(UUID uuid){
		        try {
		            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " +  plugin.table + " WHERE UUID=?");
		            statement.setString(1, uuid.toString());
		           
		            ResultSet results = statement.executeQuery();
		            if (results.next()) {
		                // player found
		                return true;
		            }
		            return false;
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		        return false;
		    }
		   
		   
		    public void createPlayer(final UUID uuid, Player player) {
		        try {
		            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " +  plugin.table + " WHERE UUID=?");
		            statement.setString(1, uuid.toString());
		            ResultSet results = statement.executeQuery();
		            results.next();
		            if (!playerExists(uuid)) {
		                PreparedStatement insert = plugin.getConnection()
		                        .prepareStatement("INSERT INTO " + plugin.table + " (UUID,NAME,COINS,) VALUE (?,?,?)");
		                insert.setString(1,  uuid.toString());
		                insert.setString(2, player.getName());
		                insert.setInt(3, 500);
		                insert.executeUpdate();
		            }
		        } catch (SQLException e ) {
		            e.printStackTrace();
		        }
		    }
		   
		    public void updateCoins(UUID uuid, int coins) {
		        try {
		            PreparedStatement statement = plugin.getConnection().prepareStatement("UPDATE " +  plugin.table + " SET 'COINS'=? WHERE UUID=?");
		            statement.setInt(1, coins);
		            statement.setString(2,  uuid.toString());
		            statement.executeUpdate();
		           
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		   
		    public void getCoins(UUID uuid) {
		        try {
		            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " +  plugin.table + " WHERE UUID=?");
		            statement.setString(1, uuid.toString());
		           
		            ResultSet results = statement.executeQuery();
		            results.next();
		           
		 
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		}
