import javax.swing.*;
import java.awt.*;

public class AddItem {
    private JPanel panel;
    private JTextField namaField;
    private JTextField jenisField;
    private JTextField jumlahField;
    private JTextField kodeField;
    private final Color PRIMARY_COLOR = new Color(0, 51, 102);
    private final Color SECONDARY_COLOR = new Color(0, 102, 204);
    private final Color HOVER_COLOR = new Color(0, 128, 255);

    public AddItem() {
        initializePanel();
        initializeComponents();
    }

    private void initializePanel() {
        panel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(255, 255, 255, 200));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2d.dispose();
            }
        };
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
    }

    private void initializeComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Create title
        JLabel titleLabel = new JLabel("Tambah Barang Baru", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(PRIMARY_COLOR);

        // Initialize text fields
        namaField = createStyledTextField();
        jenisField = createStyledTextField();
        jumlahField = createStyledTextField();
        kodeField = createStyledTextField();

        // Create submit button
        JButton submitButton = createStyledButton("Tambah Barang");

        // Layout components
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        addFormRow("Kode Barang:", kodeField, gbc, 1);
        addFormRow("Nama Barang:", namaField, gbc, 2);
        addFormRow("Jenis Barang:", jenisField, gbc, 3);
        addFormRow("Jumlah:", jumlahField, gbc, 4);

        // Add some vertical spacing before the button
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(Box.createVerticalStrut(20), gbc);

        // Add submit button
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(submitButton, gbc);

        // Add button action
        submitButton.addActionListener(e -> handleSubmit());
    }

    private JTextField createStyledTextField() {
        JTextField field = new JTextField(20);
        field.setPreferredSize(new Dimension(200, 35));
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(PRIMARY_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        return field;
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(PRIMARY_COLOR);
        return label;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(200, 40));
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(SECONDARY_COLOR);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(HOVER_COLOR);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(SECONDARY_COLOR);
            }
        });

        return button;
    }

    private void addFormRow(String labelText, JTextField field, GridBagConstraints gbc, int row) {
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(createStyledLabel(labelText), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(field, gbc);
    }

    private void handleSubmit() {
        try {
            String nama = namaField.getText().trim();
            String jenis = jenisField.getText().trim();
            String kode = kodeField.getText().trim();

            // Validate empty fields
            if (nama.isEmpty() || jenis.isEmpty() || kode.isEmpty() || jumlahField.getText().trim().isEmpty()) {
                showError("Semua field harus diisi!");
                return;
            }

            int jumlah = Integer.parseInt(jumlahField.getText().trim());

            if (jumlah <= 0) {
                showError("Jumlah harus lebih dari 0!");
                return;
            }

            Item newItem = new Item(nama, jenis, jumlah, kode);
            InventoryData.addItem(newItem);

            clearFields();
            showSuccess("Barang berhasil ditambahkan!");
        } catch (NumberFormatException ex) {
            showError("Jumlah harus berupa angka!");
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(panel, message,
                "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showSuccess(String message) {
        JOptionPane.showMessageDialog(panel, message,
                "Sukses", JOptionPane.INFORMATION_MESSAGE);
    }

    private void clearFields() {
        namaField.setText("");
        jenisField.setText("");
        jumlahField.setText("");
        kodeField.setText("");
        kodeField.requestFocus();
    }

    public JPanel getPanel() {
        return panel;
    }
}