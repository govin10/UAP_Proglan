import javax.swing.*;
import java.awt.*;

public class DeleteItem {
    private JPanel panel;
    private JTextField namaField;

    public DeleteItem() {
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        namaField = new JTextField(20);
        JButton deleteButton = new JButton("Hapus");
        JButton cancelButton = new JButton("Batal");

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Nama Barang:"), gbc);
        gbc.gridx = 1;
        panel.add(namaField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(deleteButton, gbc);
        gbc.gridx = 1;
        panel.add(cancelButton, gbc);

        deleteButton.addActionListener(e -> {
            String nama = namaField.getText();
            if (InventoryData.deleteItem(nama)) {
                JOptionPane.showMessageDialog(panel, "Barang berhasil dihapus!");
                namaField.setText("");
            } else {
                JOptionPane.showMessageDialog(panel, "Barang tidak ditemukan!");
            }
        });

        cancelButton.addActionListener(e -> namaField.setText(""));
    }

    public JPanel getPanel() {
        return panel;
    }
}