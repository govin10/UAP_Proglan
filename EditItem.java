import javax.swing.*;
import java.awt.*;

public class EditItem implements ItemPanel {
    private static final int FIELD_WIDTH = 20;
    private static final Color BUTTON_COLOR = new Color(0, 102, 204);
    private static final Color BUTTON_HOVER_COLOR = new Color(0, 128, 255);

    private final JPanel panel;
    private final JTextField searchNamaField;
    private final JTextField searchKodeField;
    private final JTextField namaField;
    private final JTextField jenisField;
    private final JTextField jumlahField;
    private final JTextField kodeField;

    public EditItem() {
        panel = new JPanel(new GridBagLayout());

        // Initialize fields
        searchNamaField = createStyledTextField();
        searchKodeField = createStyledTextField();
        namaField = createStyledTextField();
        jenisField = createStyledTextField();
        jumlahField = createStyledTextField();
        kodeField = createStyledTextField();

        initializeComponents();
    }

    private void initializeComponents() {
        GridBagConstraints gbc = createBaseConstraints();

        // Add search section
        addSearchSection(gbc);

        // Add separator
        addSeparator(gbc);

        // Add edit section
        addEditSection(gbc);
    }

    private GridBagConstraints createBaseConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        return gbc;
    }

    private JTextField createStyledTextField() {
        JTextField field = new JTextField(FIELD_WIDTH);
        field.setFont(new Font("Arial", Font.PLAIN, 12));
        return field;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setForeground(Color.WHITE);
        button.setBackground(BUTTON_COLOR);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(BUTTON_HOVER_COLOR);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(BUTTON_COLOR);
            }
        });

        return button;
    }

    private void addSearchSection(GridBagConstraints gbc) {
        JButton searchButton = createStyledButton("Cari");

        // Search nama
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 1;
        panel.add(createStyledLabel("Nama Barang:"), gbc);
        gbc.gridx = 1;
        panel.add(searchNamaField, gbc);

        // Search kode
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(createStyledLabel("Kode Barang:"), gbc);
        gbc.gridx = 1;
        panel.add(searchKodeField, gbc);

        // Search button
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        panel.add(searchButton, gbc);

        searchButton.addActionListener(e -> handleSearch());
    }

    private void addEditSection(GridBagConstraints gbc) {
        JButton updateButton = createStyledButton("Update");
        String[] labels = {"Nama Baru:", "Jenis Baru:", "Jumlah Baru:", "Kode Baru:"};
        JTextField[] fields = {namaField, jenisField, jumlahField, kodeField};

        for (int i = 0; i < labels.length; i++) {
            gbc.gridwidth = 1;
            gbc.gridx = 0;
            gbc.gridy = i + 4;
            panel.add(createStyledLabel(labels[i]), gbc);

            gbc.gridx = 1;
            panel.add(fields[i], gbc);
        }

        gbc.gridx = 0;
        gbc.gridy = labels.length + 4;
        gbc.gridwidth = 2;
        panel.add(updateButton, gbc);

        updateButton.addActionListener(e -> handleUpdate());
    }

    private void addSeparator(GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 10, 15, 10);
        panel.add(new JSeparator(), gbc);
        gbc.insets = new Insets(5, 10, 5, 10);  // Reset insets
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 12));
        return label;
    }

    private void handleSearch() {
        Item item = InventoryData.findItem(searchNamaField.getText());
        if (item != null && item.getKode().equals(searchKodeField.getText())) {
            populateFields(item);
        } else {
            showErrorMessage("Barang tidak ditemukan!");
        }
    }

    private void handleUpdate() {
        try {
            String originalKode = searchKodeField.getText();
            Item updatedItem = createUpdatedItem();

            if (InventoryData.updateItem(originalKode, updatedItem)) {
                showSuccessMessage("Barang berhasil diupdate!");
                clearFields();
            } else {
                showErrorMessage("Gagal mengupdate barang!");
            }
        } catch (NumberFormatException ex) {
            showErrorMessage("Masukkan data yang valid!");
        }
    }

    private Item createUpdatedItem() {
        return new Item(
                namaField.getText(),
                jenisField.getText(),
                Integer.parseInt(jumlahField.getText()),
                kodeField.getText()
        );
    }

    private void populateFields(Item item) {
        namaField.setText(item.getNama());
        jenisField.setText(item.getJenis());
        jumlahField.setText(String.valueOf(item.getJumlah()));
        kodeField.setText(item.getKode());
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(panel, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(panel, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void clearFields() {
        JTextField[] fields = {
                searchNamaField, searchKodeField,
                namaField, jenisField, jumlahField, kodeField
        };
        for (JTextField field : fields) {
            field.setText("");
        }
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }
}