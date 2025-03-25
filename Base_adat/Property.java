public class Property extends Field {
    public static final int PURCHASE_PRICE = 1000;
    public static final int HOUSE_PRICE = 4000;

    private Player owner;
    private boolean hasHouse;

    public Property() {
        this.owner = null;
        this.hasHouse = false;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public boolean hasHouse() {
        return hasHouse;
    }

    public void buildHouse() {
        this.hasHouse = true;
    }

    @Override
    public void landOn(Player player) {
        if (owner != null && owner != player) {
            int rent = hasHouse ? 2000 : 500;
            player.pay(rent);
            owner.receive(rent);
            System.out.println(player.getName() + " paid " + rent + " to " + owner.getName());
        }
    }

    @Override
    public String toString() {
        return "Property{owner=" + (owner != null ? owner.getName() : "none") +
                ", hasHouse=" + hasHouse + "}";
    }
}
