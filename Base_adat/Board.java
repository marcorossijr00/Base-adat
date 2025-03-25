import java.util.ArrayList;
import java.util.List;

/**
 * Létrehozza az üres játéktáblát, amely mezőket tartalmaz.
 */
public class Board {
    List<Field> fields = new ArrayList<>();

    /**
     * Mező hozzáadása a táblához.
     *
     * @param field A hozzáadandó mező.
     */
    public void addField(Field field) {
        fields.add(field);
    }

    /**
     * A tábla méret.
     *
     * @return A mezők száma a táblán.
     */
    public int getSize() {
        return fields.size();
    }

    /**
     * Mező lekérdezése.
     *
     * @param position A pozíció, amelyen a játékos áll.
     * @return A pozícióhoz tartozó mező.
     */
    public Field getField(int position) {
        return fields.get(position);
    }
}
