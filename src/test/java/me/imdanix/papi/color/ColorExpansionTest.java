package me.imdanix.papi.color;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class ColorExpansionTest {
    private final ColorExpansion expansion = new ColorExpansion();
    private final Player mockPlayer = Mockito.mock(Player.class);

    @BeforeClass
    public void setup() {
        when(mockPlayer.getUniqueId()).thenReturn(UUID.randomUUID());
    }

    @DataProvider
    public Object[][] onRequestData() {
        return new Object[][] {
                {"placeholder", "&aHello", "§aHello"},
                {"placeholder", "&aHello&x&1&2&3&4&5&6", "§aHello§x§1§2§3§4§5§6"},
                {"placeholder", "Test", "Test"},
                {"placeholder", "{placeholder}", null}
        };
    }

    @Test(dataProvider = "onRequestData")
    public void onRequestTest(String params, String phResult, String expected) {
        try (MockedStatic<PlaceholderAPI> mockPapi = Mockito.mockStatic(PlaceholderAPI.class)) {
            mockPapi.when(() -> PlaceholderAPI.setBracketPlaceholders(nullable(OfflinePlayer.class), nullable(String.class)))
                    .thenReturn(phResult);
            assertEquals(
                    expansion.onPlaceholderRequest(mockPlayer, params),
                    expected
            );
        }
    }
}