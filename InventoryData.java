import javax.swing.*;
import java.io.*;
import java.util.*;

public class InventoryData {
    private static List<Item> items = new ArrayList<>();
    private static final String FILE_PATH = "inventory.dat";

    static {
        loadFromFile();
    }

    public static void addItem(Item item) {
        items.add(item);
        saveToFile();
    }

    public static List<Item> getItems() {
        List<Item> sortedItems = new ArrayList<>(items);
        Collections.sort(sortedItems, new Comparator<Item>() {
            @Override
            public int compare(Item item1, Item item2) {
                return item1.getKode().compareTo(item2.getKode());
            }
        });
        return sortedItems;
    }

    public static boolean updateItem(String kode, Item updatedItem) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getKode().equals(kode)) {
                items.set(i, updatedItem);
                saveToFile();
                return true;
            }
        }
        return false;
    }

    public static boolean deleteItem(String nama) {
        boolean result = items.removeIf(item -> item.getNama().equals(nama));
        if (result) {
            saveToFile();
        }
        return result;
    }

    public static Item findItem(String nama) {
        for (Item item : items) {
            if (item.getNama().equals(nama)) {
                return item;
            }
        }
        return null;
    }

    private static void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(FILE_PATH))) {
            oos.writeObject(new ArrayList<>(items));
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Error saving data: " + e.getMessage());
        }
    }

    private static void loadFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            items = new ArrayList<>();
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(FILE_PATH))) {
            items = (ArrayList<Item>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            items = new ArrayList<>();
            JOptionPane.showMessageDialog(null,
                    "Error loading data: " + e.getMessage());
        }
    }
}