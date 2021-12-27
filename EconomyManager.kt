
import net.milkbowl.vault.economy.Economy
import net.milkbowl.vault.economy.EconomyResponse
import org.bukkit.OfflinePlayer
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.RegisteredServiceProvider

/**
 * Vaultと連携した経済管理
 *
 * @author M1n1don
 * @param plugin プラグイン
 * @since 2021年12月27日
 */
class EconomyManager(val plugin: Plugin)
{
    var economy: Economy? = null

    /**
     * Vaultセットアップ
     */
    fun init()
    {
        if (!plugin.server.pluginManager.isPluginEnabled("Vault")) return

        val rsp: RegisteredServiceProvider<Economy> = plugin.server.servicesManager.getRegistration(Economy::class.java) ?: return

        economy = rsp.provider
    }

    /**
     * プレイヤーに入金
     *
     * @param player ターゲット
     * @param amount 金額
     * @return Economyレスポンス
     */
    fun deposit(player: OfflinePlayer, amount: Double): EconomyResponse?
        = economy?.depositPlayer(player, amount)

    /**
     * プレイヤーから出勤
     *
     * @param player ターゲット
     * @param amount 金額
     * @return Economyレスポンス
     */
    fun withdraw(player: OfflinePlayer, amount: Double): EconomyResponse?
        = economy?.withdrawPlayer(player, amount)

    /**
     * プレイヤーの残高を取得
     *
     * @param player ターゲット
     * @return 残高
     */
    fun getBalance(player: OfflinePlayer): Double?
        = economy?.getBalance(player)
}
