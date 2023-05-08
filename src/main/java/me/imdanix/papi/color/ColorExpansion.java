package me.imdanix.papi.color;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.clip.placeholderapi.expansion.Relational;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.clip.placeholderapi.PlaceholderAPI.setBracketPlaceholders;
import static me.clip.placeholderapi.PlaceholderAPI.setRelationalPlaceholders;
import static org.bukkit.ChatColor.translateAlternateColorCodes;

public class ColorExpansion extends PlaceholderExpansion implements Relational {
    @Override
    public @NotNull String getIdentifier() {
        return "color";
    }

    @Override
    public @NotNull String getAuthor() {
        return "imDaniX";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public String onRequest(OfflinePlayer player, @NotNull String params) {
        String bracket = "{" + params + "}";
        String parsed = setBracketPlaceholders(player, bracket);
        return bracket.equals(parsed)
                ? null
                : translateAlternateColorCodes('&', parsed);
    }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String params) {
        return onRequest(player, params);
    }

    @Override
    public String onPlaceholderRequest(Player player1, Player player2, String params) {
        String bracket = "{" + params + "}";
        String parsed = setRelationalPlaceholders(player1, player2, bracket);
        return bracket.equals(parsed)
                ? null
                : translateAlternateColorCodes('&', parsed);
    }
}
