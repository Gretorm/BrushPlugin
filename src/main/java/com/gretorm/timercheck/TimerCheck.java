package com.gretorm.timercheck;

import com.gretorm.timercheck.commands.BrushEditCommand;
import com.gretorm.timercheck.data.DataManager;
import com.gretorm.timercheck.events.JoinQuitEvents;
import com.gretorm.timercheck.guis.SignMenuFactory;
import com.gretorm.timercheck.listeners.ChatListener;
import com.gretorm.timercheck.listeners.HeldItemListener;
import com.gretorm.timercheck.listeners.PacketListener;
import com.gretorm.timercheck.listeners.PlayerMovementListener;
import com.gretorm.timercheck.task.PaintTask;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class TimerCheck extends JavaPlugin {

    private static TimerCheck instance;
    private DataManager dataManager;
    private SignMenuFactory signMenuFactory;
    @Override
    public void onEnable() {
        instance = this;
        this.dataManager = new DataManager();
        // Plugin startup logic

        initListeners();

        initCommands();

        this.signMenuFactory = new SignMenuFactory(this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void initListeners(){
        Bukkit.getPluginManager().registerEvents(new PlayerMovementListener(), this);
        Bukkit.getPluginManager().registerEvents(new HeldItemListener(), this);
        Bukkit.getPluginManager().registerEvents(new JoinQuitEvents(), this);
        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
        PacketListener packetListener = new PacketListener(this);
    }

    public void initCommands(){
        this.getCommand("brush").setExecutor(new BrushEditCommand());
    }

    public SignMenuFactory getSignMenuFactory() {
        return this.signMenuFactory;
    }

    public static TimerCheck getInstance() {
        return instance;
    }

    public DataManager getDataManager() {
        return dataManager;
    }
}
