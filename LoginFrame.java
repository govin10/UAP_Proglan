import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class LoginFrame extends JFrame {
    private HashMap<String, String> adminAccounts;
    private final int FRAME_WIDTH = 1280; // 16:9 ratio width
    private final int FRAME_HEIGHT = 720; // 16:9 ratio height

    public LoginFrame() {
        adminAccounts = new HashMap<>();
        // Default admin account
        adminAccounts.put("admin", "admin123");

        setTitle("Login Admin");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();
    }

    private void initComponents() {
        // Create background panel with image
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        setContentPane(backgroundPanel);

        // Create semi-transparent login panel
        JPanel loginPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(255, 255, 255, 200));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2d.dispose();
            }
        };
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setOpaque(false);
        loginPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title with custom styling
        JLabel titleLabel = new JLabel("SISTEM PENYIMPANAN BARANG");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 51, 102));

        // Styled input fields
        JTextField usernameField = createStyledTextField();
        JPasswordField passwordField = createStyledPasswordField();

        // Styled labels
        JLabel usernameLabel = createStyledLabel("Username:");
        JLabel passwordLabel = createStyledLabel("Password:");

        // Styled login button
        JButton loginButton = createStyledButton("Login");

        // Layout components
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 2;
        loginPanel.add(Box.createVerticalStrut(30), gbc); // Spacing

        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        loginPanel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        loginPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        loginPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        loginPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(Box.createVerticalStrut(20), gbc); // Spacing

        gbc.gridy = 5;
        loginPanel.add(loginButton, gbc);

        // Add login panel to center of background
        backgroundPanel.setLayout(new GridBagLayout());
        backgroundPanel.add(loginPanel);

        // Login action
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (validateLogin(username, password)) {
                JOptionPane.showMessageDialog(this, "Login berhasil!");
                this.dispose();
                new MainFrame().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Username atau Password salah!",
                        "Login Gagal", JOptionPane.ERROR_MESSAGE);
                passwordField.setText("");
            }
        });
    }

    private JTextField createStyledTextField() {
        JTextField field = new JTextField(15);
        field.setPreferredSize(new Dimension(200, 35));
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 51, 102), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        return field;
    }

    private JPasswordField createStyledPasswordField() {
        JPasswordField field = new JPasswordField(15);
        field.setPreferredSize(new Dimension(200, 35));
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 51, 102), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        return field;
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(new Color(0, 51, 102));
        return label;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(150, 40));
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 102, 204));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover effect
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

    // Custom background panel class
    private class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            // Load the background image (replace with your image path)
            try {
                backgroundImage = new ImageIcon(getClass().getResource("/images/background.jpg")).getImage();
            } catch (Exception e) {
                // If image loading fails, create a gradient background
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

    private boolean validateLogin(String username, String password) {
        return adminAccounts.containsKey(username) &&
                adminAccounts.get(username).equals(password);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Set system look and feel
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new LoginFrame().setVisible(true);
        });
    }
}