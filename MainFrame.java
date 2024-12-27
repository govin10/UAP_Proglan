import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {
    private AddItem addItem;
    private EditItem editItem;
    private DeleteItem deleteItem;
    private SearchItem searchItem;
    private ViewItem viewItem;

    private final int FRAME_WIDTH = 1280; // Matched with LoginFrame
    private final int FRAME_HEIGHT = 800; // Matched with LoginFrame

    public MainFrame() {
        // Initialize panels
        addItem = new AddItem();
        editItem = new EditItem();
        deleteItem = new DeleteItem();
        searchItem = new SearchItem();
        viewItem = new ViewItem();

        initializeFrame();
        initComponents();
    }

    private void initializeFrame() {
        setTitle("Sistem Penyimpanan Barang");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false); // Match LoginFrame's resizable setting
    }

    private void initComponents() {
        // Create background panel with gradient
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        setContentPane(backgroundPanel);

        // Create semi-transparent main panel
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(255, 255, 255, 200));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2d.dispose();
            }
        };
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setOpaque(false);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        // Layout setup
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title Label with matching style
        JLabel titleLabel = new JLabel("SISTEM PENYIMPANAN BARANG", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 51, 102)); // Match LoginFrame color
        gbc.insets = new Insets(20, 15, 30, 15);
        mainPanel.add(titleLabel, gbc);

        // Reset insets for buttons
        gbc.insets = new Insets(15, 15, 15, 15);

        // Create buttons with matching styling
        JButton addButton = createStyledButton("Tambah Barang");
        JButton viewButton = createStyledButton("Lihat Barang");
        JButton editButton = createStyledButton("Edit Barang");
        JButton deleteButton = createStyledButton("Hapus Barang");
        JButton searchButton = createStyledButton("Cari Barang");

        // Add buttons
        mainPanel.add(addButton, gbc);
        mainPanel.add(viewButton, gbc);
        mainPanel.add(editButton, gbc);
        mainPanel.add(deleteButton, gbc);
        mainPanel.add(searchButton, gbc);

        // Add action listeners
        addButton.addActionListener(e -> showDialog("Tambah Barang", addItem.getPanel()));
        viewButton.addActionListener(e -> showDialog("Lihat Barang", viewItem.getPanel()));
        editButton.addActionListener(e -> showDialog("Edit Barang", editItem.getPanel()));
        deleteButton.addActionListener(e -> showDialog("Hapus Barang", deleteItem.getPanel()));
        searchButton.addActionListener(e -> showDialog("Cari Barang", searchItem.getPanel()));

        // Center the main panel in the background
        backgroundPanel.setLayout(new GridBagLayout());
        backgroundPanel.add(mainPanel);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(300, 50)); // Larger width for main menu buttons
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 102, 204)); // Match LoginFrame button color
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover effect matching LoginFrame
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 128, 255));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 102, 204));
            }
        });

        return button;
    }

    // Custom dialog styling
    private void showDialog(String title, JPanel panel) {
        JDialog dialog = new JDialog(this, title, true);
        dialog.setSize(new Dimension(FRAME_WIDTH / 2, FRAME_HEIGHT / 2));

        // Add semi-transparent background to dialog
        JPanel dialogPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(255, 255, 255, 240));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2d.dispose();
            }
        };
        dialogPanel.setLayout(new BorderLayout());
        dialogPanel.setOpaque(false);
        dialogPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        dialogPanel.add(panel, BorderLayout.CENTER);
        dialog.setContentPane(dialogPanel);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    // Custom background panel class matching LoginFrame
    private class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            try {
                backgroundImage = new ImageIcon(getClass().getResource("/images/background.jpg")).getImage();
            } catch (Exception e) {
                backgroundImage = null;
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            } else {
                // Create gradient background as fallback
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(0, 51, 102),
                        getWidth(), getHeight(), new Color(0, 153, 204)
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new MainFrame().setVisible(true);
        });
    }
}