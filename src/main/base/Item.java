package base;

public class Item {
    private String name;
    private int size, value;

    public Item(String name, int size, int value) {
        this.name = name;
        this.size = size;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Item) {
            Item compare = (Item) obj;
            if (compare.name.equals(name) && compare.size == size && compare.value == value) {
                return true;
            }
        }
        return false;
    }
}