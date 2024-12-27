import javax.swing.*;
import java.awt.*;

public class SearchItem {
    private JPanel panel;
    private JTextField namaField;
    private JTextArea resultArea;

    public SearchItem() {
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        namaField = new JTextField(20);
        JButton searchButton = new JButton("Cari");
        resultArea = new JTextArea(5, 20);
        resultArea.setEditable(false);

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Nama Barang:"), gbc);
        gbc.gridx = 1;
        panel.add(namaField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(searchButton, gbc);

        gbc.gridy = 2;
        panel.add(new JScrollPane(resultArea), gbc);

        searchButton.addActionListener(e -> {
            Item item = InventoryData.findItem(namaField.getText());
            if (item != null) {
                resultArea.setText(
                        "Nama: " + item.getNama() + "\n" +
                                "Jenis: " + item.getJenis() + "\n" +
                                "Jumlah: " + item.getJumlah() + "\n" +
                                "Kode: " + item.getKode()
                );
            } else {
                resultArea.setText("Barang tidak ditemukan!");
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}