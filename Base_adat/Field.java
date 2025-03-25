/**
 * Az absztrakt Field osztály a mezőket reprezentálja.
 * Minden mező rendelkezik egy landOn metódussal, amely meghatározza, mi történik, amikor egy játékos rálép.
 */
public abstract class Field {
    /**
     * Ha egy Player a Fieldre lép, ez a method fut le.
     *
     * @param player A Player, aki rálép a Fieldre.
     */
    public abstract void landOn(Player player);
}
