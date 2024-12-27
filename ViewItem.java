import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ViewItem {
    private JPanel panel;
    private JTable table;
    private DefaultTableModel tableModel;

    public ViewItem() {
        panel = new JPanel(new BorderLayout());

        // Create table model with non-editable cells
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Add columns
        String[] columns = {"Kode Barang", "Nama Barang", "Jenis Barang", "Jumlah"};
        for (String column : columns) {
            tableModel.addColumn(column);
        }

        // Create and configure table
        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true); // Enable sorting

        // Style the table
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.setRowHeight(25);

        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800, 600));
        panel.add(scrollPane, BorderLayout.CENTER);

        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Add refresh button
        JButton refreshButton = new JButton("Refresh");
        refreshButton.setFont(new Font("Arial", Font.PLAIN, 12));
        refreshButton.addActionListener(e -> refreshTable());
        buttonPanel.add(refreshButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Initial table load
        refreshTable();
    }

    private void refreshTable() {
        tableModel.setRowCount(0); // Clear existing rows

        // Get sorted items and add to table
        for (Item item : InventoryData.getItems()) {
            Object[] row = {
                    item.getKode(),    // Kode first for sorting
                    item.getNama(),
                    item.getJenis(),
                    item.getJumlah()
            };
            tableModel.addRow(row);
        }
    }

    public JPanel getPanel() {
        return panel;
    }
}