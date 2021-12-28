import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;

/**
 * Vaultと連携した経済管理 for Java
 *
 * @author M1n1don
 * @since 2021年12月27日
 */
public class EconomyManager
{
    private final Plugin plugin;
    private Economy economy;

    public EconomyManager(Plugin plugin)
    {
        this.plugin = plugin;
    }

    /**
     * Vaultセットアップ
     */
    public void init()
    {
        if (!plugin.getServer().getPluginManager().isPluginEnabled("Vault"))
            return;

        final RegisteredServiceProvider<Economy> rsp = plugin.getServer().getServicesManager().getRegistration(Economy.class);

        if (rsp == null)
            return;

        economy = rsp.getProvider();
    }

    /**
     * プレイヤーに入金
     *
     * @param player ターゲット
     * @param amount 金額
     * @return Economyレスポンス
     */
    public EconomyResponse deposit(OfflinePlayer player, Double amount)
    {
        return economy.depositPlayer(player, amount);
    }

    /**
     * プレイヤーから出金
     *
     * @param player ターゲット
     * @param amount 金額
     * @return Economyレスポンス
     */
    public EconomyResponse withdraw(OfflinePlayer player, Double amount)
    {
        return economy.withdrawPlayer(player, amount);
    }

    /**
     * プレイヤーの残高を取得
     *
     * @param player ターゲット
     * @return 残高
     */
    public Double getBalance(OfflinePlayer player)
    {
        return economy.getBalance(player);
    }
}
