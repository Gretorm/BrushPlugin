package com.gretorm.timercheck.listeners;

import com.gretorm.timercheck.TimerCheck;
import com.gretorm.timercheck.data.DataPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class HeldItemListener implements Listener {

    @EventHandler
    public void onHeldItemChanged(PlayerItemHeldEvent event){
        // Get the player that just moved
        Player p = event.getPlayer();
        DataPlayer playerData = TimerCheck.getInstance().getDataManager().getDataPlayer(p);
        int newSlot = event.getNewSlot();
        int oldSlot = event.getPreviousSlot();
        int diff = newSlot - oldSlot;
        if(diff == 1 || diff == -8){
            if(playerData.range > 0){
                playerData.range --;
            }
        }else if(diff == -1 || diff == 8){
            playerData.range ++;
        }

    }
}
